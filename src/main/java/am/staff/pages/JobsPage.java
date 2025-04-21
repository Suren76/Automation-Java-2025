package am.staff.pages;

import am.staff.components.resultPageComponents.JobItemBlock;
import org.openqa.selenium.WebElement;

public class JobsPage extends ResultPage<JobItemBlock> {
    @Override
    protected boolean isPageLoaded() {
        return false;
    }

    @Override
    public void openPage() {
        openPageByPath(URL_PATH_JOBS);
    }

    @Override
    protected JobItemBlock getResultItemBlockBy(WebElement elementToSerialize) {
        return new JobItemBlock(elementToSerialize);
    }
}
