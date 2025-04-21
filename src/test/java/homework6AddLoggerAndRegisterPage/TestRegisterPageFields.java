package homework6AddLoggerAndRegisterPage;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.pages.CandidateRegisterPage;
import am.staff.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRegisterPageFields extends BaseTestWithDriverInitClose {
    @Test
    public void checkResultPageFields() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        CandidateRegisterPage candidateRegisterPage = homePage.clickCandidateRegisterPage();

        candidateRegisterPage.sendTextToFirstNameField("Mamikon");
        candidateRegisterPage.sendTextToLastNameField("Joloxyan");

        candidateRegisterPage.selectYearDropdownOption("2025");
        candidateRegisterPage.selectMonthDropdownOption("June");
        candidateRegisterPage.selectDayDropdownOption("6");

        // invalid email
        candidateRegisterPage.sendTextToEmailField("parsyansuren@gamil_com");
        candidateRegisterPage.sendTextToPasswordField("Ts3st.Password");
        candidateRegisterPage.sendTextToConfirmPasswordField("Ts3st.Password");

        candidateRegisterPage.clickAcceptPrivacyPolicyCheckbox();
        Assertions.assertTrue(candidateRegisterPage.isRegisterButtonClickable(), "Register button is not clickable");

    }
}
