package Learning.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CheckBoxElementLearning {

    public static void main(String[] args) throws InterruptedException {
        //LOCATOR
        By familyAndFriendsCheckBox = By.id("ctl00_mainContent_chk_friendsandfamily");
        By seniorCitizenCheckBox = By.id("ctl00_mainContent_SeniorCitizenDiv");
        By indianArmedForceCheckBox = By.id("ctl00_mainContent_chk_IndArm");
        By studentCheckBox = By.id("ctl00_mainContent_chk_StudentDiscount");
        By UnaccompaniedMinorCheckBox = By.id("ctl00_mainContent_chk_Unmr");
        By allCheckBox = By.xpath("//input[@type='checkbox']");



        //SETUP

        String url = "https://rahulshettyacademy.com/dropdownsPractise/";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // SCRIPT
        WebElement el = driver.findElement(familyAndFriendsCheckBox);
        //SELECT checkbox
        el.click();
        System.out.println("Check is selected: " + el.isSelected());
        Assert.assertTrue(el.isSelected());
        // DESELECT checkbox
        el.click();
        System.out.println("Check is selected: " + el.isSelected());
        Assert.assertFalse(el.isSelected());

        List<WebElement> els = driver.findElements(allCheckBox);
        // GET SIZE OF THE LIST
        int checkboxSize = els.size();
        System.out.println("Checkbox in the webpage: " + checkboxSize);
        Assert.assertEquals(checkboxSize, 6, "Assertion is not equals.");

        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();

    }

}
