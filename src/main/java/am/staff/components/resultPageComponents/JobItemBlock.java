package am.staff.components.resultPageComponents;

import am.staff.components.base.WebElementToDataClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobItemBlock extends ResultItemBlock {
    private final static String xpathToReadMoreLink = ".//a[./*[text()='View more']]";

    private final static String xpathToCompanyName = "./div/div[2]/div[2]//text()";
    private final static String xpathToJobPostDate = "./div/div[2]/div[3]//text()";
    private final static String xpathToJobLocation = "./div/div[2]/div[4]//text()";

    @FindBy(xpath = xpathToCompanyName)
    private WebElement companyNameElement;
    @FindBy(xpath = xpathToJobPostDate)
    private WebElement jobPostDateElement;
    @FindBy(xpath = xpathToJobLocation)
    private WebElement jobLocationElement;
    @FindBy(xpath = xpathToReadMoreLink)
    private WebElement jobPageLink;

    private String companyName;
    private String jobPostDate;
    private String jobLocation;


    public JobItemBlock(WebElement element) {
        super(element);
    }

    @Override
    public void setupData() {
        super.setupData();
        companyName = getCompanyNameElementText();
        jobPostDate = getJobPostDateElementText();
        jobLocation = getJobLocationElementText();
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobPostDate() {
        return jobPostDate;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    private String getCompanyNameElementText() {
        return companyNameElement.getText();
    }

    private String getJobPostDateElementText() {
        return jobPostDateElement.getText();
    }

    private String getJobLocationElementText() {
        return jobLocationElement.getText();
    }

    public String getJobPageLink() {
        return jobPageLink.getDomAttribute("href");
    }
}
