package am.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleCompanyPage extends BasePage {
    private static final String xpathToIndustryText = "//*[text()='Industry']/following-sibling::*[text()]";

    private static final String xpathToPageViews = "//*[text()='page views']";
    private static final String xpathToFollowers = "//*[text()='follower(s)']";
    private static final String xpathToActiveJob = "//*[contains(text(), 'active job')]";
    private static final String xpathToJobHistory = "//*[text()='job history']";

    @FindBy(xpath = xpathToIndustryText)
    private WebElement industryTextElement;
    @FindBy(xpath = xpathToPageViews)
    private WebElement pageViewsElement;
    @FindBy(xpath = xpathToFollowers)
    private WebElement followersElement;
    @FindBy(xpath = xpathToActiveJob)
    private WebElement activeJobElement;
    @FindBy(xpath = xpathToJobHistory)
    private WebElement jobHistoryElement;


    public SingleCompanyPage(String linkToCompanyPage) {
        openPageByLink(linkToCompanyPage);
    }

    public void openPageByLink(String linkToCompanyPage) {
        getDriver().get(getUrlWithDomain(linkToCompanyPage));
    }

    public String getIndustryText() {
        return industryTextElement.getText().strip();
    }

    public int getPageViews() {
        String text = pageViewsElement.getText();
        return Integer.parseInt(text.split("(page views)")[0].strip());
    }

    public int getFollowers() {
        String text = followersElement.getText();
        return Integer.parseInt(text.split("(follower\\(s\\))")[0].strip());
    }

    public int getActiveJobs() {
        String text = activeJobElement.getText();
        return Integer.parseInt(text.split("(active job)")[0].strip());
    }

    public int getJobHistory() {
        String text = jobHistoryElement.getText();
        return Integer.parseInt(text.split("(job history)")[0].strip());
    }

    @Override
    protected boolean isPageLoaded() {
        return getFooter().isLoaded();
    }

    @Override
    protected void openPage() {
        waitPageToBeLoaded();
    }
}
