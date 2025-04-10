package am.staff;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;

import static am.staff.helper.WebDriverHelper.getDriver;
import static am.staff.utils.WaitUtility.getMiddleWait;

public class BaseInteractor {
    public void scrollTo(WebElement element) {

        scrollIntoElementView(element);

//         calculate sleep time to the end of scroll process: (scroll time in Millis)
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("getScrollHeight(element): " + getScrollHeight(element));
        int calculatedSleepTimeBasedOnScrollHeight = (getScrollHeight(element) > 1000)? 6*100: 3*100;
        System.out.println("calculatedSleepTimeBasedOnScrollHeight: " + calculatedSleepTimeBasedOnScrollHeight);
//        int calculatedSleepTimeBasedOnScrollHeight = (getScrollHeight(element) / 200);
        sleep(calculatedSleepTimeBasedOnScrollHeight);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

//        scrollIntoElementView(element);

//        // WebDriverWait not working here
//        getMiddleWait().waitElementToBeVisible(element);
//        getMiddleWait().waitElementToBeClickable(element);
    }

    public void scrollTo(By element) {
        scrollTo(getMiddleWait().waitElementPresence(element));
    }

    public void sleep(int sleep_ms)  {
        // todo: implement general sleep for elements
        try {
            Thread.sleep(sleep_ms);
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
                "arguments[0].scrollIntoViewIfNeeded(); " +
                        "setTimeout(() => console.log('done'), 3000); ",
                element
        );
    }

    private int getScrollHeight(WebElement element) {
//        getJavaScriptExecutor()
//                .executeScript("allow pasting;");

        var n = ((Map<String, Number>) (
                getJavaScriptExecutor()
                        .executeScript(
                                "return arguments[0].getBoundingClientRect();",
                element
                )
        ));
        System.out.println(n);
        System.out.println(element.getLocation());
//        return n.get("Y").intValue();
        return Math.abs(n.get("y").intValue());
    }

}
