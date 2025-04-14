package am.staff.components.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static am.staff.helper.WebDriverHelper.getDriver;

public abstract class WebElementToDataClass {
    protected WebElement element;

    public WebElementToDataClass(WebElement element) {
        this.element = element;
        PageFactory.initElements(getElement(), this);
        setupData();
    }

    protected abstract void setupData();

    public WebElement getElement() {
        return element;
    }
}
