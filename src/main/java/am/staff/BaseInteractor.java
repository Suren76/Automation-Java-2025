package am.staff;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static am.staff.helper.WebDriverHelper.getDriver;
import static am.staff.utils.Logger.debug;
import static am.staff.utils.WaitUtility.getMiddleWait;

public class BaseInteractor {
    protected static Actions actions = new Actions(getDriver());

    public BaseInteractor() {
        PageFactory.initElements(getDriver(), this);
    }

    public void scrollTo(WebElement element) {
        debug("scroll to %s".formatted(element.toString()));
        int calculatedSleepTimeBasedOnScrollHeight = (getScrollHeight(element) > 1000)? 12*100: 5*10;
        scrollIntoElementView(element);
        sleep(calculatedSleepTimeBasedOnScrollHeight);
    }

    public void scrollTo(By element) {
        scrollTo(getMiddleWait().waitElementPresence(element));
    }

    public void sleep(int sleepMillis)  {
        debug("sleep: [%s]ms".formatted(sleepMillis));
        // todo: implement general sleep for elements
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected JavascriptExecutor getJavaScriptExecutor() {
        return ((JavascriptExecutor) getDriver());
    }

    private void scrollIntoElementView(WebElement element) {
        // todo: test js version and Actions
        //  if Actions work correct remove `JavascriptExecutor`
//        new Actions(getDriver()).scrollToElement(element).perform();
        getJavaScriptExecutor().executeScript(
                "arguments[0].scrollIntoViewIfNeeded(); ",
                element
        );
    }

    private void scrollIntoElementViewWithoutAnimation(WebElement element) {
        getJavaScriptExecutor().executeScript(
                "arguments[0].scrollIntoView({" +
                            "behavior: 'instant', " +
                            "block: 'center' " +
                        "}); ",
                element
        );
    }

    private int getScrollHeight(WebElement element) {
        Map<String, Number> jsRectData = ((Map<String, Number>) (
                getJavaScriptExecutor()
                        .executeScript(
                                "return arguments[0].getBoundingClientRect();",
                element
                )
        ));
        // return the height to scroll to the element
        return Math.abs(jsRectData.get("y").intValue());
    }

}
