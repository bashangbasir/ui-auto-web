package Learning.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class PracticeAssignment {

    // LOCATOR
    private static By checkBox1 = By.id("checkBoxOption1");
    private static By checkBox2 = By.id("checkBoxOption2");
    private static By checkBox3 = By.id("checkBoxOption3");
    private static By dropDownLocator = By.id("dropdown-class-example");
    private static By alertBtn = By.id("alertbtn");
    private static By nameTextBox = By.id("name");
    private static By option1Label = By.cssSelector("label[for='bmw']");
    private static By option2Label = By.cssSelector("label[for='benz']");
    private static By option3Label = By.cssSelector("label[for='honda']");


    // VARIABLE
    private static String url = "https://rahulshettyacademy.com/AutomationPractice/";
    private static WebDriver driver;
    private static WebDriverWait wait;


    @Test
    public void testPractice() throws InterruptedException {

        // WEBDRIVER SETUP

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);

        driver.get(url);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // SCRIPT
        clearCheckBox(checkBox1);
        clearCheckBox(checkBox2);
        clearCheckBox(checkBox3);

        driver.findElement(checkBox2).click();
        String opt = driver.findElement(option2Label).getText().trim();
        System.out.println("------");
        System.out.println(opt);

        Select dropdown = new Select(driver.findElement(dropDownLocator));
        dropdown.selectByVisibleText(opt);

        driver.findElement(nameTextBox).sendKeys(opt);
        driver.findElement(alertBtn).click();
        boolean isContained = driver.switchTo().alert().getText().contains(opt);




        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();

    }

    private static void clearCheckBox(By locator){
        if(driver.findElement(locator).isSelected()){
            driver.findElement(locator).click();
        }
    }

}
