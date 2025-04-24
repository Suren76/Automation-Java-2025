package am.staff.pages;

import am.staff.components.base.DropdownComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static am.staff.utils.Logger.debug;
import static am.staff.utils.WaitUtility.getMiddleWait;
import static am.staff.utils.WaitUtility.getShortWait;

public class CandidateRegisterPage extends BasePage{
    private static String templateXpathToInputField = "//div[following-sibling::div[./input]][text()='%s']/..//input";

    private static By xpathToFirstNameField = By.xpath(templateXpathToInputField.formatted("First name"));
    private static By xpathToLastNameField = By.xpath(templateXpathToInputField.formatted("Last name"));

    private static DropdownComponent yearDropdown = new DropdownComponent(
            By.xpath(DropdownComponent.tepmlateXpathToDropdownElement.formatted("Year")));
    private static DropdownComponent monthDropdown = new DropdownComponent(
            By.xpath(DropdownComponent.tepmlateXpathToDropdownElement.formatted("Month")));
    private static DropdownComponent dayDropdown = new DropdownComponent(
            By.xpath(DropdownComponent.tepmlateXpathToDropdownElement.formatted("Day")));

    private static By xpathToEmailField = By.xpath(templateXpathToInputField.formatted("Email"));
    private static By xpathToPasswordField = By.xpath(templateXpathToInputField.formatted("Password"));
    private static By xpathToConfirmPasswordField = By.xpath(templateXpathToInputField.formatted("Confirm password"));

    private static By xpathToInvalidEmailNotification = By.xpath("//*[contains(@class, 'error')]//*[text()='Please, enter a valid email.']");

    private static By xpathToCheckboxAcceptPrivacyPolicy = By.xpath("//div[./*[contains(text(), 'By creating an account,')]]");

    @Override
    protected boolean isPageLoaded() {
        return false;
    }

    @Override
    protected void openPage() {
        openPageByPath("/register");
    }

    // todo: refactoring
    //   move to `BaseInteractor` if needed in future
    protected void sendTextToInputField(By xpathToInputField, String text) {
        debug("send text{%s} to element[%s]".formatted(text, xpathToInputField));
        find(xpathToInputField).sendKeys(text);
    }

    public void sendTextToFirstNameField(String text) {
        sendTextToInputField(xpathToFirstNameField, text);
    }
    public void sendTextToLastNameField(String text) {
        sendTextToInputField(xpathToLastNameField, text);
    }
    public void sendTextToEmailField(String text) {
        sendTextToInputField(xpathToEmailField, text);
    }
    public void sendTextToPasswordField(String text) {
        sendTextToInputField(xpathToPasswordField, text);
    }
    public void sendTextToConfirmPasswordField(String text) {
        sendTextToInputField(xpathToConfirmPasswordField, text);
    }

    public void selectYearDropdownOption(String dropdownOption) {
        yearDropdown.selectDropdownOption(dropdownOption);
    }
    public void selectMonthDropdownOption(String dropdownOption) {
        monthDropdown.selectDropdownOption(dropdownOption);
    }
    public void selectDayDropdownOption(String dropdownOption) {
        dayDropdown.selectDropdownOption(dropdownOption);
    }

    public void clickAcceptPrivacyPolicyCheckbox() {
        clickElement(xpathToCheckboxAcceptPrivacyPolicy);
    }

    public boolean isRegisterButtonClickable() {
        By xpathToClickableRegisterButton = By.xpath("//*[./*[text()='Register']][contains(@style, 'background-color: rgb(28, 52, 64)')]");
        try {
            getShortWait().waitElementPresence(xpathToClickableRegisterButton);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
