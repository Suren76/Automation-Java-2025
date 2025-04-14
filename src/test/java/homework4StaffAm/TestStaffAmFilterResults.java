package homework4StaffAm;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.components.resultPageComponents.CompanyItemBlock;
import am.staff.pages.HomePage;
import am.staff.pages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestStaffAmFilterResults extends BaseTestWithDriverInitClose {
    @Test
    public void testCompaniesListFiltering() {
        HomePage homePage = new HomePage();

        ResultPage resultPage = homePage.clickCompaniesField();

        resultPage.addFilter("Filter By Industry", "Sport");
        List<CompanyItemBlock> companyItemBlockListAtAllTab = (List<CompanyItemBlock>) resultPage.getResultItemBlockList();

        resultPage.switchTabTo("Hiring");
        List<CompanyItemBlock> companyItemBlockListAtHiringTab = (List<CompanyItemBlock>) resultPage.getResultItemBlockList();

        resultPage.clickFooterMenuViewAllCompaniesItem();

        resultPage.addFilter("Filter By Industry", "Sport");
        List<CompanyItemBlock> companyItemBlockListAtAllTabSecond = (List<CompanyItemBlock>) resultPage.getResultItemBlockList();

        resultPage.switchTabTo("Hiring");
        List<CompanyItemBlock> companyItemBlockListAtHiringTabSecond = (List<CompanyItemBlock>) resultPage.getResultItemBlockList();

        Assertions.assertEquals(companyItemBlockListAtAllTab, companyItemBlockListAtAllTabSecond,
                "check results on `All` tab");
        Assertions.assertEquals(companyItemBlockListAtHiringTab, companyItemBlockListAtHiringTabSecond,
                "check results on `Hiring` tab");
    }
}
