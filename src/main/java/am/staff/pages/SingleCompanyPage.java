package am.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SingleCompanyPage extends BasePage {
    private By xpathToIndustryText = By.xpath("//*[text()='Industry']/following-sibling::*[text()]");

    private By xpathToPageViews = By.xpath("//*[text()='page views']");
    private By xpathToFollowers = By.xpath("//*[text()='follower(s)']");
    private By xpathToActiveJob = By.xpath("//*[contains(text(), 'active job')]");
    private By xpathToJobHistory = By.xpath("//*[text()='job history']");


    public SingleCompanyPage(String linkToCompanyPage) {
        openPageByLink(linkToCompanyPage);
    }

    public void openPageByLink(String linkToCompanyPage) {
        getDriver().get(getUrlWithDomain(linkToCompanyPage));
    }

    public String getIndustryText() {
        return find(xpathToIndustryText).getText().strip();
    }

    public int getPageViews() {
        String text = find(xpathToPageViews).getText();
        return Integer.parseInt(text.split("(page views)")[0].strip());
    }

    public int getFollowers() {
        String text = find(xpathToFollowers).getText();
        return Integer.parseInt(text.split("(follower\\(s\\))")[0].strip());
    }

    public int getActiveJobs() {
        String text = find(xpathToActiveJob).getText();
        return Integer.parseInt(text.split("(active job)")[0].strip());
    }

    public int getJobHistory() {
        String text = find(xpathToJobHistory).getText();
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
