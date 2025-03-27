package org.learn.automation.homework2SimpleAutomation.com.sixPm;

import org.openqa.selenium.WebElement;

public abstract class BaseWebElementToDataClass {
    protected WebElement element;

    public BaseWebElementToDataClass(WebElement element) {
        this.element = element;
        setupData();
    }

    public abstract void setupData();
}
