package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class describes wrike.com page
 * @author elivolov@gmail.com
 */
public class HomePage extends AbstractPage {
    final static String URL = "wrike.com";

    /**
     * Constructor
     * @param driver selenium WebDriver
     */
    public HomePage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(URL))
            throw new IllegalStateException("This is not the home page");
    }

    /**
     * This method clicks "Get started for free" button near "Login" button
     */
    public void clickGetStartedButton() {
        getDriver().findElement(By.xpath("/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button")).click();
    }

    /**
     * This method fill email field
     * @param email test email with mask “<random_text>+wpt@wriketask.qaa”
     */
    public void inputEmail(String email) {
        WebElement emailField = getDriver().findElement(By.xpath("//*[@id=\"modal-pro\"]/form/label[1]/input"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    /**
     * This method clicks "Create my Wrike account" button
     * @return resend page at the url "wrike.com/resend/"
     * @see pages.ResendPage#ResendPage(WebDriver)
     */
    public ResendPage clickCreateAccountButton() {
        WebElement createAccountButton = getDriver().findElement(By.xpath("//*[@id=\"modal-pro\"]/form/label[2]/button"));
        createAccountButton.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains(ResendPage.URL));
        return new ResendPage(getDriver());
    }
}
