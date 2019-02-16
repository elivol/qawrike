package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * This class describes resend page at the url "wrike.com/resend/"
 * @author elivolov@gmail.com
 */
public class ResendPage extends AbstractPage {
    final static String URL = "wrike.com/resend/";

    private Random random;
    private WebElement resendButton;

    /**
     * Constructor
     * @param driver selenium WebDriver
     */
    public ResendPage(WebDriver driver) {
        super(driver);
        random = new Random(System.currentTimeMillis());
        resendButton = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[1]/p[3]/button"));
    }

    /**
     * This method fills the Q&A section with random answers and submits them
     */
    public void fillQASectionAndSubmit() {
        chooseInterestedInSolution();
        chooseTeamMembers();
        chooseProcessForManaging();
        getDriver().findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/button")).click();
    }

    /**
     * This method checks whether answers are submitted
     * @return true if submitted, otherwise false
     */
    public boolean answersAreSubmitted() {
        try {
            WebElement surveyForm = getDriver().findElement(By.name("survey-form"));
            return new WebDriverWait(getDriver(), 5).until(ExpectedConditions.attributeContains(
                    surveyForm, "style", "display: none;"
            ));
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * This method resends email
     */
    public void resendEmail() {
        resendButton.click();
    }

    /**
     * This method checks whether email was resent
     * @return true if email was resent, otherwise false
     */
    public boolean emailIsResent() {
        try {
            return new WebDriverWait(getDriver(), 5).until(ExpectedConditions.attributeContains(
                    resendButton, "style", "opacity: 0;"
            ));
        }
        catch (Exception e) {
            return false;
        }
    }

    private void chooseInterestedInSolution() {
        int answer = getRandomInt(1, 2);
        switch (answer) {
            case 1:
                clickButtonWithText("//*[@id=\"interest_in_solution_1\"]");
                break;
            case 2:
                clickButtonWithText("//*[@id=\"interest_in_solution_2\"]");
                break;
        }
    }

    private void chooseTeamMembers() {
        int answer = getRandomInt(1, 5);
        switch (answer) {
            case 1:
                clickButtonWithText("//*[@data-code=\"1-5\"]");
                break;
            case 2:
                clickButtonWithText("//*[@data-code=\"6-15\"]");
                break;
            case 3:
                clickButtonWithText("//*[@data-code=\"16-25\"]");
                break;
            case 4:
                clickButtonWithText("//*[@data-code=\"26-50\"]");
                break;
            case 5:
                clickButtonWithText("//*[@data-code=\"50+\"]");
                break;
        }
    }

    private void chooseProcessForManaging() {
        int answer = getRandomInt(1, 3);
        switch (answer) {
            case 1:
                clickButtonWithText("//*[@data-code=\"yes\"]");
                break;
            case 2:
                clickButtonWithText("//*[@data-code=\"no\"]");
                break;
            case 3:
                clickButtonWithText("//*[@data-code=\"other\"]");
                break;
        }
    }


    private int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private void clickButtonWithText(String siblingXPath) {
        WebElement sibling = getDriver().findElement(By.xpath(siblingXPath));
        sibling.findElement(By.xpath("./following-sibling::button")).click();
    }
}
