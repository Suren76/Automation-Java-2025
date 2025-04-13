package am.staff.components.base;

import am.staff.BaseInteractor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

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
        return getMiddleWait().waitElementPresence(xpathToElement);
    }

    public void clickElement(WebElement element) {
        scrollTo(element);
        getMiddleWait()
                .waitElementToBeClickable(element)
                .click();
    }

    public void clickElement(By xpathToClickElement) {
        clickElement(find(xpathToClickElement));
    }

    public boolean isElementExists(By xpathToElement) {
        try {
            find(xpathToElement);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
