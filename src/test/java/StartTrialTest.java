import org.apache.commons.lang.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ResendPage;

/**
 * Test running class
 * @author elivolov@gmail.com
 */
public class StartTrialTest {
    static WebDriver driver;
    static String URL = "https://wrike.com";
    final static String emailDomain = "+wpt@wriketask.qaa";

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void runTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickGetStartedButton();
        homePage.inputEmail(generateEmail());
        ResendPage resendPage = homePage.clickCreateAccountButton();

        resendPage.fillQASectionAndSubmit();
        Assert.assertTrue("Answers are not submitted.", resendPage.answersAreSubmitted());

        resendPage.resendEmail();
        Assert.assertTrue("Email is not resent", resendPage.emailIsResent());

        Assert.assertTrue("Footer has incorrect url or icon for twitter",
                resendPage.checkURLAndIconInSocialBlock("https://twitter.com/wrike", "twitter"));
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }

    private String generateEmail() {
        int length = 8;
        return RandomStringUtils.random(length, true, false).concat(emailDomain);
    }
}
