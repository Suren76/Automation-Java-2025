package am.staff.components.resultPageComponents;

import am.staff.components.base.WebElementToDataClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

import static am.staff.utils.WaitUtility.getMiddleWait;

public class ResultItemBlock extends WebElementToDataClass {
    private static By xpathToTitle = By.xpath(
            ".//img/following-sibling::div/div/div/div[text()]" +
                    " | " +
                    "//img/following-sibling::div//a[contains(@href, '/company/')]"
    );
    private static By xpathToReadMoreLink = By.xpath(".//a[./*[text()='View more']]");

    private static By xpathToPageViews = By.xpath(".//*[text()='Page views']");
    private static By xpathToFollowers = By.xpath(".//*[text()='Followers']");
    private static By xpathToActiveJob = By.xpath(".//*[contains(text(), 'Active job')]");
    private static By xpathToJobHistory = By.xpath(".//*[contains(text(), 'Job history')]");

    private String title;
    private String pageViews;
    private String followers;
    private String activeJobs;
    private String jobHistory;

    public ResultItemBlock(WebElement element) {
        super(element);
    }

    @Override
    public void setupData() {

        title = getTitleElementText();
        pageViews = getPageViewsElementText();
        followers = getFollowersElementText();
        activeJobs = getActiveJobsElementText();
        jobHistory = getJobHistoryElementText();
    }

    public String getTitle() {
        return title.strip();
    }
    public int getPageViews() {
        return Integer.parseInt(pageViews.split("(Page views)")[0].strip());
    }
    public int getFollowers() {
        return Integer.parseInt(followers.split("(Followers)")[0].strip());
    }
    public int getActiveJobs() {
        return Integer.parseInt(activeJobs.split("(Active job)")[0].strip());
    }
    public int getJobHistory() {
        return Integer.parseInt(jobHistory.split("(Job history)")[0].strip());
    }

    private String getTitleElementText() {
        return getMiddleWait().waitElementToBeVisible(xpathToTitle).getText();
    }
    private String getPageViewsElementText() {
        return getMiddleWait().waitElementToBeVisible(xpathToPageViews).getText();
    }
    private String getFollowersElementText() {
        return getMiddleWait().waitElementToBeVisible(xpathToFollowers).getText();
    }
    private String getActiveJobsElementText() {
        return getMiddleWait().waitElementToBeVisible(xpathToActiveJob).getText();
    }
    private String getJobHistoryElementText() {
        return getMiddleWait().waitElementToBeVisible(xpathToJobHistory).getText();
    }

    public String getCompanyPageLink() {
        return getMiddleWait().waitElementToBeVisible(xpathToReadMoreLink).getDomAttribute("href");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResultItemBlock that = (ResultItemBlock) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getPageViews(), that.getPageViews()) && Objects.equals(getFollowers(), that.getFollowers()) && Objects.equals(getActiveJobs(), that.getActiveJobs()) && Objects.equals(getJobHistory(), that.getJobHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPageViews(), getFollowers(), getActiveJobs(), getJobHistory());
    }
}
