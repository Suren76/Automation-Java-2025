package am.staff.components.resultPageComponents;

import am.staff.components.base.WebElementToDataClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static am.staff.utils.WaitUtility.getMiddleWait;

public abstract class ResultItemBlock extends WebElementToDataClass {
    protected final static String xpathToTitle = ".//*[contains(@style, 'font-size: 16px')]";

    @FindBy(xpath = xpathToTitle)
    protected WebElement titleElement;

    protected String title;

    public ResultItemBlock(WebElement element) {
        super(element);
    }

    @Override
    protected void setupData() {
        title = getTitleText();
    }

    public String getTitle() {
        return title;
    }

    private String getTitleText() {
        return getMiddleWait().waitElementToBeVisible(titleElement).getText();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResultItemBlock that = (ResultItemBlock) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
