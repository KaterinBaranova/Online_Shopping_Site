package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.CartHoverPage;
import pages.CheckOutPage;
import pages.ItemPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.ItemPage.ITEM_URL;

public class CartHoverTest extends BaseTest {
    @Test
    public void checkCountInHoverTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        CartHoverPage cartHoverPage = new CartHoverPage(driver);
        assertEquals(cartHoverPage.getCartHoverNumberInCart(), 0, "The number of items in the cart for a new user is more than zero");
        itemPage.clickAddToCart();
        assertEquals(cartHoverPage.getCartHoverNumberInCart(), 1, "The number of items in the cart is increased after adding an item to it");
    }

    @Test
    public void removeItemFromHoverTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        itemPage.clickAddToCart();
        itemPage.closeCartFrame();
        CartHoverPage cartHoverPage = new CartHoverPage(driver);
        /*cartHoverPage.removeFromHover();*/
        /*assertEquals(cartHoverPage.getCartHoverNumberInCart(), 0, "The number of items in the cart for a new user is more than zero");*/
    }

    @Test
    public void checkOutFromHoverTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get("http://prestashop.qatestlab.com.ua/en/blouses/2-blouse.html#/size-s/color-black");
        itemPage.clickAddToCart();
        itemPage.closeCartFrame();
        CartHoverPage cartHoverPage = new CartHoverPage(driver);
        cartHoverPage.checkOut();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        /*assertEquals(checkOutPage.getItemsInCart(),2);*/
    }
}
