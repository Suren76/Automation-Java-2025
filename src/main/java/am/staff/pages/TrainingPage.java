package am.staff.pages;

import am.staff.components.resultPageComponents.TrainingItemBlock;
import am.staff.exceptions.NotImplementedException;
import org.openqa.selenium.WebElement;

public class TrainingPage extends ResultPage<TrainingItemBlock> {
    @Override
    protected boolean isPageLoaded() {
        return false;
    }

    @Override
    protected void openPage() {
        openPageByPath(URL_PATH_TRAINING);
    }

    @Override
    protected TrainingItemBlock getResultItemBlockBy(WebElement elementToSerialize) {
        try {
            return new TrainingItemBlock(elementToSerialize);
        } catch (NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
