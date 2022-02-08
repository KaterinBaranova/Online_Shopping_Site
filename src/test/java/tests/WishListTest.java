package tests;

import org.testng.annotations.Test;
import pages.ItemPage;
import pages.SignInPage;
import pages.WishlistPage;

import static java.util.UUID.randomUUID;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


public class WishListTest extends BaseTest {

    @Test
        public void createUpdateDeleteWishlist() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signInLink();
        signInPage.signInWithCredentials(LOGIN, PASSWORD);
        // create wishlist
        WishlistPage wishlistPage = signInPage.openWishListPage();
        String wishlistName = String.valueOf(randomUUID()).substring(0, 8);
        wishlistPage.create(wishlistName);
        assertTrue(wishlistPage.isWishlistPresent(wishlistName), "Specified Wishlist was not found");
        // update wishlist
         ItemPage itemPage = wishlistPage.openFirstTopSellingItem();
         itemPage.clickAddToWishlist();
         assertEquals(itemPage.getFancyBoxText(), "Added to your wishlist.", "FancyBox text was not valid");
        //delete wishlist (note there is a bug on the app and it can't be deleted)
        wishlistPage.get();
        assertTrue(wishlistPage.deletelWishlist(wishlistName), "The wishlist was not removed");
        }
    }


