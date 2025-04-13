package am.staff.components.base;

import org.openqa.selenium.WebElement;

public abstract class WebElementToDataClass {
    protected WebElement element;

    public WebElementToDataClass(WebElement element) {
        this.element = element;
        setupData();
    }

    public abstract void setupData();

    public WebElement getElement() {
        return element;
    }
}
