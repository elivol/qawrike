package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Now this abstract class describes working with footer methods
 * which are the same for any page
 * @author elivolov@gmail.com
 */
public abstract class AbstractPage {
    private WebDriver driver;

    /**
     * Constructor
     * @param driver selenium WebDriver
     */
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * WebDriver getter
     * @return selenium WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * This method checks any url and icon's partial name in footer social list
     * @param url url for checking
     * @param partialIconName icon's partial name
     * @return true if urls are equal and icon's name contains partialIconName, otherwise false
     */
    public boolean checkURLAndIconInSocialBlock(String url, String partialIconName) {
        List<WebElement> elements = driver.findElement(By.className("wg-footer__social-list")).findElements(By.tagName("a"));
        for (WebElement element:
             elements) {
            if (element.getAttribute("href").equals(url))
                if (element.findElement(By.tagName("use")).getAttribute("xlink:href").contains(partialIconName))
                    return true;
        }
        return false;
    }
}
