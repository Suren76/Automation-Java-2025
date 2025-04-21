package homework4StaffAm;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.components.resultPageComponents.CompanyItemBlock;
import am.staff.pages.CompanyPage;
import am.staff.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestStaffAmFilterResults extends BaseTestWithDriverInitClose {
    @Test
    public void testCompaniesListFiltering() {
        HomePage homePage = new HomePage();
        homePage.openPage();

        CompanyPage resultPage = homePage.clickCompaniesField();

        List<CompanyItemBlock> companyItemBlockListAtAllTabNoFilter =  resultPage.getResultItemBlockList();
        System.out.println(companyItemBlockListAtAllTabNoFilter.size());
        resultPage.addFilter("Filter By Industry", "Sport");
        List<CompanyItemBlock> companyItemBlockListAtAllTab =  resultPage.getResultItemBlockList();

        resultPage.switchTabTo("Hiring");
        List<CompanyItemBlock> companyItemBlockListAtHiringTab = resultPage.getResultItemBlockList();

        resultPage.clickFooterMenuViewAllCompaniesItem();

        resultPage.addFilter("Filter By Industry", "Sport");
        List<CompanyItemBlock> companyItemBlockListAtAllTabSecond = resultPage.getResultItemBlockList();

        resultPage.switchTabTo("Hiring");
        List<CompanyItemBlock> companyItemBlockListAtHiringTabSecond = resultPage.getResultItemBlockList();

        Assertions.assertEquals(companyItemBlockListAtAllTab, companyItemBlockListAtAllTabSecond,
                "check results on `All` tab");
        Assertions.assertEquals(companyItemBlockListAtHiringTab, companyItemBlockListAtHiringTabSecond,
                "check results on `Hiring` tab");
    }
}
