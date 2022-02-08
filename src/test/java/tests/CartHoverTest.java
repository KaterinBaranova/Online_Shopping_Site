package tests;

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
        assertTrue(cartHoverPage.removeItemFromCart("Blouse"), "The item was not removed from Hover menu");
    }

    @Test
    public void checkOutFromHoverTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        itemPage.clickAddToCart();
        itemPage.closeCartFrame();
        CartHoverPage cartHoverPage = new CartHoverPage(driver);
        cartHoverPage.checkOut();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        assertEquals(checkOutPage.getItemsInCart(), 1, "Incorrect number of items in the cart");
    }
}
