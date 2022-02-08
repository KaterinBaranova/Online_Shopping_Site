package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;

import static org.testng.Assert.assertTrue;

public class SignInTest extends BaseTest {

    private final static String INVALID_USERNAME = "Katee1@test2.com";
    private final static String INVALID_PASSWORD = "1234";


    @Test
    public void signInPositiveTest() {

        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInLink();
        signInPage.signInWithCredentials(LOGIN, PASSWORD);
        assertTrue(signInPage.isSignOutLinkDisplayed(), "Sign out link is not displayed on the page");
    }

    @Test
    public void signInWithEmptyLoginTest() {

        String expected_error_message = "An email address required.";
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInLink();
        signInPage.signInWithCredentials(" ", PASSWORD);
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), expected_error_message);
    }

    @Test
    public void signInWithEmptyPasswordTest() {

        String expected_error_message = "Password is required.";
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInLink();
        signInPage.signInWithCredentials(LOGIN, " ");
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), expected_error_message);
    }

    @Test
    public void signInWithInvalidCredentials() {

        String expected_error_message = "Authentication failed.";
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInLink();
        signInPage.signInWithCredentials(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), expected_error_message);
    }

    @Test
    public void signOut() {

        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInLink();
        signInPage.signInWithCredentials(LOGIN, PASSWORD);
        signInPage.signOut();
        assertTrue(signInPage.isSignInLinkDisplayed(),"SignIn link was not displayed after sign out action");}
}

