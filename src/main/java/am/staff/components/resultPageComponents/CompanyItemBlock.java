package am.staff.components.resultPageComponents;

import am.staff.components.base.WebElementToDataClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;


public class CompanyItemBlock extends ResultItemBlock {
    private final static String xpathToReadMoreLink = ".//a[./*[text()='View more']]";

    private final static String xpathToPageViews = ".//*[text()='Page views']";
    private final static String xpathToFollowers = ".//*[text()='Followers']";
    private final static String xpathToActiveJob = ".//*[contains(text(), 'Active job')]";
    private final static String  xpathToJobHistory = ".//*[contains(text(), 'Job history')]";

    @FindBy(xpath = xpathToPageViews)
    private WebElement pageViewsElement;
    @FindBy(xpath = xpathToFollowers)
    private WebElement followersElement;
    @FindBy(xpath = xpathToActiveJob)
    private WebElement activeJobsElement;
    @FindBy(xpath = xpathToJobHistory)
    private WebElement jobHistoryElement;
    @FindBy(xpath = xpathToReadMoreLink)
    private WebElement companyPageLink;

    private String pageViews;
    private String followers;
    private String activeJobs;
    private String jobHistory;

    public CompanyItemBlock(WebElement element) {
        super(element);
    }

    @Override
    public void setupData() {
        super.setupData();
        pageViews = getPageViewsElementText();
        followers = getFollowersElementText();
        activeJobs = getActiveJobsElementText();
        jobHistory = getJobHistoryElementText();
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

    private String getPageViewsElementText() {
        return pageViewsElement.getText();
    }
    private String getFollowersElementText() {
        return followersElement.getText();
    }
    private String getActiveJobsElementText() {
        return activeJobsElement.getText();
    }
    private String getJobHistoryElementText() {
        return jobHistoryElement.getText();
    }

    public String getCompanyPageLink() {
        return companyPageLink.getDomAttribute("href");
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompanyItemBlock that = (CompanyItemBlock) o;
        return Objects.equals(getPageViews(), that.getPageViews()) &&
                Objects.equals(getFollowers(), that.getFollowers()) &&
                Objects.equals(getActiveJobs(), that.getActiveJobs()) &&
                Objects.equals(getJobHistory(), that.getJobHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPageViews(), getFollowers(), getActiveJobs(), getJobHistory());
    }
}
