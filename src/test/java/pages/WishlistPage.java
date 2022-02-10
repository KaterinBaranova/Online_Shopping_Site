package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class WishlistPage extends BasePage<WishlistPage> {

    @FindBy(xpath = "//*[@id=\"best-sellers_block_right\"]/div/ul/li[1]/a")
    private WebElement firstTopSellingItem;

    @FindBy(id = "name")
    private WebElement newWishlistNameField;

    @FindBy(id = "submitWishlist")
    private WebElement submitNewWishlistButton;

    @FindBy(className = "table")
    private WebElement wishlistTable;

    @FindBy(className ="icon-remove")
    private WebElement deleteWishlistItem;

    public WishlistPage(WebDriver driver) {
        super(driver,"index.php?fc=module&module=/module/blockwishlist/mywishlist");
    }

    @Override
    protected void isLoaded() throws Error {

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

    public void create(String wishlistName) {
        getActions().type(newWishlistNameField, wishlistName);
        getActions().click(submitNewWishlistButton);
    }

    public boolean isWishlistPresent(String wishlistName) {
        getActions().isElementDisplayed(wishlistTable);
        return getCell(wishlistName, Column.NAME) != null;
    }

    public void clickDeleteToWishlist() {
        getActions().click(deleteWishlistItem);
    }

    private WebElement getCell(String wishlistName, Column columnName) {
        List<WebElement> allRows = wishlistTable.findElements(By.xpath("//*[contains(@class,\"table\")]/tbody/tr[*]/td[1]"));
        int rowNumberCounter = 1;
        for (WebElement row : allRows) {
            if (row.getText().equals(wishlistName)) {
                return wishlistTable.findElement(By.xpath("//*[contains(@class,\"table\")]/tbody/tr[" + rowNumberCounter + "]/td[" + columnName.columnNumber + "]"));
            } else {
                rowNumberCounter++;
            }
        }
        throw new NoSuchElementException("Unable to locate {" + wishlistName + "} wishlist");
    }

    public ItemPage openFirstTopSellingItem() {
        getActions().click(firstTopSellingItem);
        getActions().isPageReady();
        return new ItemPage(driver);
    }

    enum Column {
        NAME(1),
        QTY(2),
        VIEWED(3),
        CREATED(4),
        DIRECTLINK(5),
        DELETE(6);

        private final int columnNumber;

        Column(int columnNumber) {
            this.columnNumber = columnNumber;
        }
    }
}
