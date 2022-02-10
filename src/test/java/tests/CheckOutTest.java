package tests;

import org.testng.annotations.Test;
import pages.CartHoverPage;
import pages.CheckOutPage;
import pages.ItemPage;


import static org.testng.Assert.*;
import static pages.ItemPage.ITEM_URL;

public class CheckOutTest extends BaseTest {
    @Test
    public void changeQuantityInCartTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get("http://prestashop.qatestlab.com.ua/en/blouses/2-blouse.html#/size-s/color-black");
        itemPage.clickAddToCart();
        itemPage.closeCartFrame();
        CartHoverPage cartHoverPage = new CartHoverPage(driver);
        cartHoverPage.checkOut();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.pressIncreaseQuantity("Blouse");
        checkOutPage.pressIncreaseQuantity("Blouse");
        checkOutPage.pressDecreaseQuantity("Blouse");
        /*assertEquals(checkOutPage.getItemsInCart(), 2, "Incorrect number of items in cart");*/
    }

    @Test
    public void removeLastItemFromCartTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get("http://prestashop.qatestlab.com.ua/en/blouses/2-blouse.html#/size-s/color-black");
        itemPage.clickAddToCart();
        itemPage.closeCartFrame();
        CartHoverPage cartHoverPage = new CartHoverPage(driver);
        cartHoverPage.checkOut();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.pressDecreaseQuantity("Blouse");
       /* assertTrue(checkOutPage.isCartEmpty(), "The cart was not empty");*/
    }

  /*  @Test
    public void buyItemsFromCartTest() {
        ItemPage itemPage = new ItemPage(driver);
        driver.get(ITEM_URL);
        itemPage.clickAddToCart();
        CheckOutPage checkOutPage = itemPage.clickCheckout();
        checkOutPage.purchase();

    }*/
}
