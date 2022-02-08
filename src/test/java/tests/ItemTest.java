package tests;

import org.testng.annotations.Test;
import pages.ItemPage;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static pages.ItemPage.ITEM_URL;

public class ItemTest extends BaseTest {

    @Test
    public void addItemToCartTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        itemPage.clickAddToCart();
        assertTrue(itemPage.isCheckoutFrameDisplayed(), "Checkout popup was not displayed");
    }

    @Test
    public void addSeveralItemsToCartTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        itemPage.setQuantity(3);
        itemPage.setSize("M");
        itemPage.setColor("Orange");
        itemPage.clickAddToCart();
        assertEquals(itemPage.getQuantityFromCartFrame(), 3, "Quantity doesn't match to values in the cart frame");
        assertEquals(itemPage.getSizeFromCartFrame(), "M", "Size doesn't match to values in the cart frame");
        assertEquals(itemPage.getColorFromCartFrame(), "Orange", "Color doesn't match to values in the cart frame");
    }

    @Test
    public void selectColorTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        itemPage.setColor("Blue");
        assertEquals("http://prestashop.qatestlab.com.ua/en/tshirts/1-faded-short-sleeve-tshirts.html#/size-s/color-blue", itemPage.getUrl(), "Images are not filtered based on color");
    }

}
