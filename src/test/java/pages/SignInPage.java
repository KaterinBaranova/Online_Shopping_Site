package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage<SignInPage> {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    @FindBy(className = "logout")
    private WebElement signOutLink;

    @FindBy(className = "login")
    private WebElement signInLink;

    @FindBy(className = "alert-danger")
    private WebElement errorMessage;

    @FindBy(className = "lnk_wishlist")
    private WebElement wishListButton;


    public SignInPage() {
        super("http://prestashop.qatestlab.com.ua/en/authentication?back=my-account");
    }


    @Override
    protected void isLoaded() throws Error {

    }

    public void signInWithCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        getActions().click(signInButton);
    }


    public void signOut() {
        getActions().click(signOutLink);

    }

    public void signInLink() {
        getActions().click(signInLink);

    }

    public boolean isSignInLinkDisplayed() {
        return getActions().isElementDisplayed(signInButton);
    }

    public boolean isSignOutLinkDisplayed() {
        return getActions().isElementDisplayed(signOutLink);
    }

    public boolean isErrorMessageDisplayed() {
        return getActions().isElementDisplayed(errorMessage);
    }

    public WishlistPage openWishListPage() {
        getActions().click(wishListButton);
        getActions().isPageReady();
        return new WishlistPage();
    }
}