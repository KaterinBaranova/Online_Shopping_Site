package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;

import static org.testng.Assert.assertTrue;


public class SignInTest extends BaseTest {


    @Test
    public void signInPositiveTest() {
        //driver.findElement(By.className("login")).click();
        signInPage.signInLink();
        signInPage.signInWithCredentials(LOGIN, PASSWORD);
        assertTrue(signInPage.isSignOutLinkDisplayed(), "Sign out link is displayed on the page");
    }

    @Test
    public void signInWithEmptyLoginTest() {
        String expected_error_message= "An email address required.";
        driver.findElement(By.className("login")).click();
        signInPage.signInWithCredentials(" ", PASSWORD);
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), expected_error_message);
    }

    @Test
    public void signInWithEmptyPasswordTest() {
        String expected_error_message= "Password is required.";
        driver.findElement(By.className("login")).click();
        signInPage.signInWithCredentials(LOGIN, " ");
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), expected_error_message);
    }

    @Test
    public void signInWithInvalidCredentials() {
        String expected_error_message= "Authentication failed.";
        driver.findElement(By.className("login")).click();
        signInPage.signInWithCredentials("Katee1@test2.com", "Katee1@test2.com");
        Assert.assertTrue(signInPage.isErrorMessageDisplayed(), expected_error_message);
    }

    @Test
    public void signOut() {
        SignInPage signInPage = new SignInPage().get();
        signInPage.signInWithCredentials(LOGIN, PASSWORD);
        signInPage.signOut();
        assertTrue(signInPage.isSignInLinkDisplayed(), "SignIn link was not displayed after sign out action");
    }
}
