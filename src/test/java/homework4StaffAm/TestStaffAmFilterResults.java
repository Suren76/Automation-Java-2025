package homework4StaffAm;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.components.resultPageComponents.ResultItemBlock;
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
        List<ResultItemBlock> resultItemBlockListAtAllTab = resultPage.getResultItemBlockList();

        resultPage.switchTabTo("Hiring");
        List<ResultItemBlock> resultItemBlockListAtHiringTab = resultPage.getResultItemBlockList();

        resultPage.clickFooterMenuViewAllCompaniesItem();

        resultPage.addFilter("Filter By Industry", "Sport");
        List<ResultItemBlock> resultItemBlockListAtAllTabSecond = resultPage.getResultItemBlockList();

        resultPage.switchTabTo("Hiring");
        List<ResultItemBlock> resultItemBlockListAtHiringTabSecond = resultPage.getResultItemBlockList();

        Assertions.assertEquals(resultItemBlockListAtAllTab, resultItemBlockListAtAllTabSecond,
                "check results on `All` tab");
        Assertions.assertEquals(resultItemBlockListAtHiringTab, resultItemBlockListAtHiringTabSecond,
                "check results on `Hiring` tab");
    }
}
