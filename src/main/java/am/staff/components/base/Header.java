package am.staff.components.base;

import am.staff.exceptions.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static am.staff.utils.Log.info;

public class Header extends BaseComponent {
    private static String templateXpathToNavbarItem = "//nav//a[.//*[text()='%s']]";
    private static String templateXpathToNavbarDropdownItem = "//nav//*[./*[text()='%s']]/following-sibling::div[.//a]/..";

    private static By xpathToLogoLink = By.xpath("//nav//a[@href='/'][.//*[name()='svg']]");

    private static By xpathToNavbarJobField = By.xpath(templateXpathToNavbarItem.formatted("Jobs"));
    private static By xpathToNavbarTrainingsField = By.xpath(templateXpathToNavbarItem.formatted("Trainings"));
    private static By xpathToNavbarCompaniesField = By.xpath(templateXpathToNavbarItem.formatted("Companies"));


    public Header(WebElement element) {
        super(element);
    }

    public void switchToLightTheme() throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void switchToDarkTheme() throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void changeLanguageTo(String language) throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void openStaffMediaAm() throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void openCareerFactory() throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void openCompassByStaffAm() throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void clickJobsField() {
        clickElement(xpathToNavbarJobField);
    }

    public void clickTrainingsField() {
        clickElement(xpathToNavbarTrainingsField);
    }

    public void clickCompaniesField() {
        clickElement(xpathToNavbarCompaniesField);
    }

    public boolean isLoaded() {
        return isElementExists(xpathToNavbarCompaniesField);
    }

    protected void clickMenuHoverDropdownOption(String dropdownName, String dropdownOption) {
        new HoverDropdownComponent(dropdownName).clickMenuHoverDropdownOption(dropdownOption);
    }

    public void clickEmployerDropdownOption(String dropdownOption) {
        clickMenuHoverDropdownOption("Employer", dropdownOption);
    }

    public void clickCandidateDropdownOption(String dropdownOption) {
        clickMenuHoverDropdownOption("Candidate", dropdownOption);
    }
}
