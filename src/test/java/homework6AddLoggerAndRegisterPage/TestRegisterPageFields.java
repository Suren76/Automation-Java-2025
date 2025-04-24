package homework6AddLoggerAndRegisterPage;

import BaseTestComponents.BaseTestWithDriverInitClose;
import am.staff.pages.CandidateRegisterPage;
import am.staff.pages.HomePage;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class TestRegisterPageFields extends BaseTestWithDriverInitClose {
    Dotenv dotenv;

    @BeforeEach
    @Override
    public void setUp() {
        dotenv = Dotenv.configure()
                .directory("src/test/java/homework6AddLoggerAndRegisterPage")
                .filename(".TestRegisterPageFields.credentials.env")
                .load();
        super.setUp();
    }

    @Test
    public void checkResultPageFields() throws URISyntaxException {
        String firstName = "Mamikon";
        String lastName = "Joloxyan";

        String year = "2025";
        String month = "June";
        String day = "6";

        String invalidEmail = "parsyansuren=gamil.-com";
        String email = "parsyansuren@gamil.com";

        String password = dotenv.get("PASSWORD");

        HomePage homePage = new HomePage();
        homePage.openPage();

        CandidateRegisterPage candidateRegisterPage = homePage.clickCandidateRegisterPage();

        candidateRegisterPage.sendTextToFirstNameField(firstName);
        candidateRegisterPage.sendTextToLastNameField(lastName);

        candidateRegisterPage.selectYearDropdownOption(year);
        candidateRegisterPage.selectMonthDropdownOption(month);
        candidateRegisterPage.selectDayDropdownOption(day);

        candidateRegisterPage.sendTextToEmailField(invalidEmail);
        Assertions.assertTrue(candidateRegisterPage.isInvalidEmailFieldMessageDisplayed(),
                "On Register page under Email field invalid email format message not displayed");

        candidateRegisterPage.sendTextToEmailField(email);

        candidateRegisterPage.sendTextToPasswordField(password);
        candidateRegisterPage.sendTextToConfirmPasswordField(password);

        candidateRegisterPage.clickAcceptPrivacyPolicyCheckbox();
        Assertions.assertTrue(candidateRegisterPage.isRegisterButtonClickable(),
                "Register button is not clickable");
    }
}
