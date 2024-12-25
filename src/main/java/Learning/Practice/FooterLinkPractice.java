package Learning.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FooterLinkPractice {

    //LOCATOR
    private static By footerDiv = By.cssSelector("div#gf-BIG");
    private static By links = By.cssSelector("li.gf-li a");

    // OTHERS
    private static String url = "https://rahulshettyacademy.com/AutomationPractice/";
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testLink() throws InterruptedException {

        String username = "rahulshettyacademy";
        String password = "learning";
        String userTypeGroup = "Consultant";

        // SETUP
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
        Actions actions = new Actions(driver);

        //SCRIPT

        WebElement footer = driver.findElement(footerDiv);
        List<WebElement> columns = footer.findElements(By.cssSelector("table.gf-t tr td ul"));
//        System.out.println(columns.size());
//
//        for (int i = 0; i < columns.size(); i++) {
//            System.out.println(columns.get(i).findElement(By.cssSelector("li.gf-li")).getText());
//        }


        Map<String, String> discountLinks =  getTextAndLinks(columns.get(0));
        List<String> newsLinks =  getLinksOnly(columns.get(1));
        List<String> contactsLink =  getLinksOnly(columns.get(2));
        Map<String, String> socialsLinks =  getTextAndLinks(columns.get(3));

//        System.out.println(discountLinks);
//        System.out.println(newsLinks);
//        System.out.println(contactsLink);
//        System.out.println(socialsLinks);

        List<WebElement> discount = columns.get(0).findElements(By.cssSelector("li.gf-li"));

        //start with index = 1 since first link not relevant
        for (int i = 1; i < discount.size(); i++) {
            // open in new tab
            WebElement link = discount.get(i).findElement(By.tagName("a"));
            link.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));

        }

        Thread.sleep(5000);

        Iterator<String> it = driver.getWindowHandles().iterator();
        it.next(); //skip first one
        while(it.hasNext()){
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());
        }

        //CLEAN UP
        Thread.sleep(3500);
        driver.quit();
    }

    private static Map<String, String> getTextAndLinks(WebElement el){
        return el.findElements(links)
                .stream().skip(1).collect(Collectors.toMap(
                        element -> element.getText(), element -> element.getAttribute("href")
                ));
    }

    private static List<String> getLinksOnly(WebElement el){
        return el.findElements(links)
                .stream().skip(1).map(element -> element.getAttribute("href")).toList();
    }
}

