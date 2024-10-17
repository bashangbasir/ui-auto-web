package Learning.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CalendarElementLearning {

    public static void main(String[] args) throws InterruptedException {
        //LOCATOR
        By datePicker = By.id("ui-datepicker-div");
        By departureDropdownLocator = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
        By arrivalDropdownLocator = By.id("ctl00_mainContent_ddl_destinationStation1_CTXT");
        String departureDropdownValueLocator = "//div[@id='ctl00_mainContent_ddl_originStation1_CTNR']//a[@value='%s']";
        String arrivalDropdownValueLocator = "//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='%s']";
        By todayDate = By.cssSelector(".ui-datepicker-today");
        By returnDateCalendar = By.cssSelector("#Div1.picker-second");

        //SETUP

        String url = "https://rahulshettyacademy.com/dropdownsPractise/";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // SCRIPT
        //1. Choose departure and arrival destination
        driver.findElement(departureDropdownLocator).click();
        driver.findElement(By.xpath(String.format(departureDropdownValueLocator, "BLR"))).click();
        driver.findElement(By.xpath(String.format(arrivalDropdownValueLocator,"DED"))).click();
        //2. Choose date of departure
        WebElement datePickerElement = driver.findElement(datePicker);
        datePickerElement.findElement(todayDate).click();
        //3. Check the arrival should disable since it is one way - need to use getAttribute() of element  since it was done using style
        WebElement arrivalDate = driver.findElement(returnDateCalendar);
        boolean isDisable = arrivalDate.getAttribute("style").contains("opacity: 0.5");
        Assert.assertTrue(isDisable);

        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();

    }
}
