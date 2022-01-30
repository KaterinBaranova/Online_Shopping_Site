package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import setup.Action;

import static tests.BaseTest.BASE_URL;

public abstract class BasePage <T extends LoadableComponent<T>> extends LoadableComponent<T> {
    private final Action action;
    private final String pageUrl;
    public WebDriver driver;

    BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
        this.action = new Action(driver);
        PageFactory.initElements(this.driver, this);
    }

    Action getActions() {
        return action;
    }

    @Override
    public void load() {
        if (pageUrl.contains("http")) {
            driver.get(pageUrl);
        } else {
            driver.get(BASE_URL + pageUrl);
        }
    }

     @Override
        protected void isLoaded() throws Error {
         if (!this.driver.getCurrentUrl().contains(BASE_URL + pageUrl) && getActions().isPageReady()) {
             throw new Error(action.getCurrentUrl() + " is not loaded");
         }
     }

}

