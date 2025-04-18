package homework4StaffAm;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.components.resultPageComponents.ResultItemBlock;
import am.staff.pages.HomePage;
import am.staff.pages.ResultPage;
import am.staff.pages.SingleCompanyPage;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;


public class TestStaffAm extends BaseTestWithDriverInitClose {
    @Test
    public void testSearchCompanyAndOpenCompanyPage() {
        String industryToSelect = "Information technologies";

        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.chooseCareerOpportunity("Companies");
        homePage.selectDropdownOption("All industries", industryToSelect);
        ResultPage resultPage = homePage.clickSearchButton();

        String randomString = new RandomString().nextString();
        resultPage.search(randomString);
        Assertions.assertTrue(resultPage.getResultItemBlockList().isEmpty(),
                "random string(%s) found items".formatted(randomString));

        String textToSearch = "ser";
        resultPage.search(textToSearch);
        List<ResultItemBlock> resultItemList = resultPage.getResultItemBlockList();
        resultItemList.forEach(resultItemBlock -> {
            Assertions.assertTrue(
                    resultItemBlock.getTitle().toLowerCase().contains(textToSearch),
                    "invalid name assertion, [%s], [%s]".formatted(resultItemBlock.getTitle(), textToSearch));
        });

        // get random item
        // may need refactoring
        ResultItemBlock randomItemToOpen = resultItemList.get(new Random().nextInt(0, resultItemList.size()));

        SingleCompanyPage randomItemCompanyPage = resultPage.openCompanyPage(randomItemToOpen);

        Assertions.assertEquals(randomItemToOpen.getPageViews(), randomItemCompanyPage.getPageViews(),
                "failed PageViews count assertion of [%s, %s]".formatted(randomItemToOpen, randomItemCompanyPage));
        Assertions.assertEquals(randomItemToOpen.getFollowers(), randomItemCompanyPage.getFollowers(),
                "failed Followers count assertion of [%s, %s]".formatted(randomItemToOpen, randomItemCompanyPage));
        Assertions.assertEquals(randomItemToOpen.getActiveJobs(), randomItemCompanyPage.getActiveJobs(),
                "failed ActiveJobs count assertion of [%s, %s]".formatted(randomItemToOpen, randomItemCompanyPage));
        Assertions.assertEquals(randomItemToOpen.getJobHistory(), randomItemCompanyPage.getJobHistory(),
                "failed JobHistory count assertion of [%s, %s]".formatted(randomItemToOpen, randomItemCompanyPage));

        Assertions.assertEquals(industryToSelect, randomItemCompanyPage.getIndustryText());

        System.out.println();
    }
}
