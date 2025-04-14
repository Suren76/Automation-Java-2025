package am.staff.components.resultPageComponents;

import am.staff.components.base.WebElementToDataClass;
import am.staff.exceptions.NotImplementedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TrainingItemBlock extends ResultItemBlock {
    private final static String xpathToReadMoreLink = ".//a[./*[text()='Learn more']]";

    @FindBy(xpath = xpathToReadMoreLink)
    public WebElement trainingPageLink;

    public TrainingItemBlock(WebElement element) throws NotImplementedException {
        super(element);
        throw new NotImplementedException();
    }

    @Override
    public void setupData() {
        super.setupData();
    }

    public String getCompanyPageLink() {
        return trainingPageLink.getDomAttribute("href");
    }
}
