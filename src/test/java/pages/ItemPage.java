package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ItemPage extends BasePage<ItemPage> {

    public static final String ITEM_URL = "http://prestashop.qatestlab.com.ua/en/tshirts/1-faded-short-sleeve-tshirts.html";

    @FindBy(id = "layer_cart")
    private WebElement cartFrame;

    @FindBy(id = "add_to_cart")
    private WebElement addToCartButton;

    @FindBy(css = "a[title='Proceed to checkout']")
    private WebElement proceedToCheckoutButton;

    @FindBy(name = "qty")
    private WebElement quantityField;

    @FindBy(id = "group_1")
    private WebElement sizesDropdown;

    @FindBy(id = "color_to_pick_list")
    private WebElement colorOptions;

    @FindBy(id = "layer_cart_product_quantity")
    private WebElement quantityInCartFrame;

    @FindBy(id = "layer_cart_product_attributes")
    private WebElement sizeAndColorInCartFrame;

    @FindBy(id = "wishlist_button")
    private WebElement addToWishlist;

    @FindBy(className = "fancybox-error")
    private WebElement fancyBox;

    @FindBy(className = "cross")
    private WebElement closeCartFrame;

    public ItemPage(WebDriver driver) {
        super(driver,ITEM_URL);
    }

    @Override
    public void open() {

    }

    @Override
    public void login(String login, String password) {

    }

    @Override
    public boolean deletelWishlist(String wishlistName) {
        return false;
    }

    public void clickAddToCart() {
        getActions().click(addToCartButton);
        getActions().isElementDisplayed(proceedToCheckoutButton);
    }

    public void clickAddToWishlist() {
        getActions().click(addToWishlist);
    }


    public boolean isCheckoutFrameDisplayed() {
        return getActions().isElementDisplayed(cartFrame);
    }


    public void setQuantity(int count) {
        quantityField.clear();
        getActions().type(quantityField, String.valueOf(count));
    }

    public int getQuantityFromCartFrame() {
        getActions().isElementDisplayed(cartFrame);
        return Integer.parseInt(quantityInCartFrame.getText());
    }

    public void setSize(String size) {
        Select selectList = new Select(sizesDropdown);
        selectList.selectByVisibleText(size);
    }

    public String getSizeFromCartFrame() {
        getActions().isElementDisplayed(cartFrame);
        return sizeAndColorInCartFrame.getText().substring(sizeAndColorInCartFrame.getText().length() - 1);
    }

    public void setColor(String color) {
        List<WebElement> colors = colorOptions.findElements(By.xpath(".//*"));
        for (WebElement li : colors) {
            if (li.getAttribute("title").equals(color)) {
                getActions().click(li);
            }
        }
    }

    public String getColorFromCartFrame() {
        getActions().isElementDisplayed(cartFrame);
        return sizeAndColorInCartFrame.getText().substring(0, sizeAndColorInCartFrame.getText().length() - 3);
    }

    public boolean getFancyBoxText() {
        getActions().isElementDisplayed(fancyBox);
        return true;
    }

    public String getUrl() {
        return getActions().getCurrentUrl();
    }

    public void closeCartFrame() {
        getActions().click(closeCartFrame);
        getActions().isElementNotDisplayed(cartFrame);
    }

    public CheckOutPage clickCheckout() {
        getActions().click(proceedToCheckoutButton);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.isLoaded();
        return checkOutPage;
    }
}