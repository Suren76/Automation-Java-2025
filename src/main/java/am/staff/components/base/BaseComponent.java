package am.staff.components.base;

import am.staff.BaseInteractor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static am.staff.utils.Log.debug;
import static am.staff.utils.WaitUtility.getMiddleWait;


public abstract class BaseComponent extends BaseInteractor {
    protected WebElement root;

    protected BaseComponent(WebElement root) {
        this.root = root;
    }

    protected WebElement getElement() {
        return root;
    }

    @Override
    protected WebElement find(By xpathToElement) {
        // todo: add-method
        //   check is needed wait for child element
        debug("find element[%s] inside[%s]".formatted(xpathToElement, getElement()));
        return getElement().findElement(xpathToElement);
    }

    protected void clickElement(WebElement element) {
        debug("click element[%s]".formatted(getElement()));
        scrollTo(element);
        getMiddleWait()
                .waitElementToBeClickable(element)
                .click();
    }

    protected void clickElement(By xpathToClickElement) {
        clickElement(find(xpathToClickElement));
    }

    protected boolean isElementExists(By xpathToElement) {
        debug("is element[%s] exists".formatted(xpathToElement));
        try {
            find(xpathToElement);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
