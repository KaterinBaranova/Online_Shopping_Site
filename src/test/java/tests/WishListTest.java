package tests;

import org.testng.annotations.Test;
import pages.ItemPage;
import pages.SignInPage;
import pages.WishlistPage;

import static java.util.UUID.randomUUID;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static tests.BaseTest.*;

public class WishListTest {
    @Test
    public void createUpdateDeleteWishlistTest() {
        SignInPage signInPage = new SignInPage().get();
        signInPage.signInWithCredentials(LOGIN, PASSWORD);

        WishlistPage wishlistPage = signInPage.openWishListPage();
        String wishlistName = String.valueOf(randomUUID()).substring(0, 8);
        wishlistPage.create(wishlistName);
        assertTrue(wishlistPage.isWishlistPresent(wishlistName), "Specified Wishlist was not found");

        ItemPage itemPage = wishlistPage.openFirstTopSellingItem();
        itemPage.clickAddToWishlist();
        assertEquals(itemPage.getFancyBoxText(), "Added to your wishlist.", "FancyBox text was not valid");

        wishlistPage.get();
        assertTrue(wishlistPage.deleteWishlist(wishlistName), "The wishlist was not removed");
    }
}

