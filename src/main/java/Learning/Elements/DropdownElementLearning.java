package Learning.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class DropdownElementLearning {

    public static void main(String[] args) throws InterruptedException {

        //LOCATOR
        By dropdownLocator = By.id("ctl00_mainContent_DropDownListCurrency");
        By departureDropdownLocator = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
        By arrivalDropdownLocator = By.id("ctl00_mainContent_ddl_destinationStation1_CTXT");
        String departureDropdownValueLocator = "//div[@id='ctl00_mainContent_ddl_originStation1_CTNR']//a[@value='%s']";
        String arrivalDropdownValueLocator = "//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='%s']";
        By autoSuggestiveDropdownLocator = By.id("autosuggest");
        By autoSuggestiveListItems = By.cssSelector("li[class='ui-menu-item'] a");
        

        //SCRIPT

        String url = "https://rahulshettyacademy.com/dropdownsPractise/";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Select dropdown - Static dropdown
        System.out.println("Static dropdown");
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        // select by option's index
        dropdown.selectByIndex(2);
        String selectedItem = dropdown.getFirstSelectedOption().getText();
        System.out.println(selectedItem);
        // select by value
        dropdown.selectByValue("INR");
        selectedItem = dropdown.getFirstSelectedOption().getText();
        System.out.println(selectedItem);
        // select by visible text
        dropdown.selectByVisibleText("USD");
        selectedItem = dropdown.getFirstSelectedOption().getText();
        System.out.println(selectedItem);


        //Dynamic dropdown
        System.out.println("Dynamic dropdown");
        driver.findElement(departureDropdownLocator).click();
        driver.findElement(By.xpath(String.format(departureDropdownValueLocator, "BLR"))).click();
        driver.findElement(By.xpath(String.format(arrivalDropdownValueLocator,"DED"))).click();

        System.out.println(driver.findElement(departureDropdownLocator).getAttribute("selectedtext"));
        System.out.println(driver.findElement(arrivalDropdownLocator).getAttribute("selectedtext"));


        //Auto suggestive dropdown
        System.out.println("Auto suggestive dropdown");
        driver.findElement(autoSuggestiveDropdownLocator).sendKeys("Mala");
        List<WebElement> items = driver.findElements(autoSuggestiveListItems);
        for(WebElement item : items){
            String country = item.getText();
            System.out.println(country);
            if(country.equals("Malaysia")){
                item.click();
                break;
            }
        }

        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();

    }
}
