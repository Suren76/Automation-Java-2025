package am.staff.pages;

import am.staff.components.resultPageComponents.CompanyItemBlock;
import org.openqa.selenium.WebElement;

import static am.staff.helper.WebDriverHelper.closeDriver;
import static am.staff.helper.WebDriverHelper.initDriver;

public class CompanyPage extends ResultPage<CompanyItemBlock> {
    @Override
    protected boolean isPageLoaded() {
        return false;
    }

    @Override
    protected void openPage() {
        openPageByPath(URL_PATH_COMPANIES);
    }

    @Override
    protected CompanyItemBlock getResultItemBlockBy(WebElement elementToSerialize) {
        return new CompanyItemBlock(elementToSerialize);
    }

//    public static void main(String[] args) {
//        try {
//            initDriver();
//            CompanyPage companyPage = new CompanyPage();
//            companyPage.openPage();
//            companyPage.addFilter("Filter By Industry", "Sport");
//            companyPage.getResultItemBlockList().forEach(System.out::println);
//            companyPage.clickCompaniesField();
//            JobsPage jobsPage = new JobsPage();
//            jobsPage.openPage();
//            jobsPage.getResultItemBlockList().forEach(System.out::println);
//        }
//        finally {
//            closeDriver();
//        }
//    }
}
