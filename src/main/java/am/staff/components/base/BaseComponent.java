package am.staff.components.base;

import am.staff.BaseInteractor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import static am.staff.utils.Logger.debug;
import static am.staff.utils.WaitUtility.getMiddleWait;


public abstract class BaseComponent extends BaseInteractor {
    protected WebElement root;

    public BaseComponent(WebElement root) {
        this.root = root;
    }

    public WebElement getElement() {
        return root;
    }

    public WebElement find(By xpathToElement) {
        // todo: add-method
        //   check is needed wait for child element
        debug("find element[%s] inside[%s]".formatted(xpathToElement, getElement()));
        return getElement().findElement(xpathToElement);
    }

    public void clickElement(WebElement element) {
        debug("click element[%s]".formatted(getElement()));
        scrollTo(element);
        getMiddleWait()
                .waitElementToBeClickable(element)
                .click();
    }

    public void clickElement(By xpathToClickElement) {
        clickElement(find(xpathToClickElement));
    }

    public boolean isElementExists(By xpathToElement) {
        debug("is element[%s] exists".formatted(xpathToElement));
        try {
            find(xpathToElement);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
