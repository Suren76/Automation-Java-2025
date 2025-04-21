package homework5StaffAmCheckAllFilters;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.pages.JobsPage;
import am.staff.pages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestAreAllFiltersWorkAndLimitTheItemsCount extends BaseTestWithDriverInitClose {
    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                        Arguments.of(
                                "Job category", "Banking/credit",
                                "Job special tag", "Bachelor's degree"
                        )
                );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void testAreAllFiltersLimitItemsCorrect(
            String firstFilterName, String firstFilterOption,
            String secondFilterName, String secondFilterOption
    ) {
        JobsPage resultPage = new JobsPage();
        resultPage.openPage();

        int itemsCountToBeAfterFilterAddedFirst = resultPage.getFilterOptionItemsCount(firstFilterName, firstFilterOption);
        resultPage.addFilter(firstFilterName, firstFilterOption);

        int itemsCountCalculatedByPaginationFirst = resultPage.getAllFoundedItemsCount();

        Assertions.assertEquals(itemsCountToBeAfterFilterAddedFirst, itemsCountCalculatedByPaginationFirst,
                "check failed items count after add first filter");


        int itemsCountToBeAfterFilterAddedSecond = resultPage.getFilterOptionItemsCount(secondFilterName, secondFilterOption);
        resultPage.addFilter(secondFilterName, secondFilterOption);

        int itemsCountCalculatedByPaginationSecond = resultPage.getAllFoundedItemsCount();


        Assertions.assertEquals(itemsCountToBeAfterFilterAddedSecond, itemsCountCalculatedByPaginationSecond,
                "check failed items count after add second filter");

        resultPage.removeFilter(firstFilterName, firstFilterOption);
        int itemsCountToBeAfterFilterAddedRemovedFilter = resultPage.getFilterOptionItemsCount(secondFilterName, secondFilterOption);

        int itemsCountCalculatedByPaginationRemovedFilter = resultPage.getAllFoundedItemsCount();

        Assertions.assertEquals(itemsCountToBeAfterFilterAddedRemovedFilter, itemsCountCalculatedByPaginationRemovedFilter,
                "check failed items count after remove first filter");

    }
}
