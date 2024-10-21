package Learning.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    public static void main(String[] args) throws InterruptedException {

        String username = "rahulshettyacademy";
        String password = "learning";
        String userTypeGroup = "Consultant";

        // SETUP
        driver = new ChromeDriver();
        driver.get(url);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        //SCRIPT

        WebElement footer = driver.findElement(footerDiv);
        List<WebElement> columns = footer.findElements(By.cssSelector("table.gf-t tr td ul"));
        System.out.println(columns.size());

        for (int i = 0; i < columns.size(); i++) {
            System.out.println(columns.get(i).findElement(By.cssSelector("li.gf-li")).getText());
        }


        Map<String, String> discountLinks =  getTextAndLinks(columns.get(0));
        List<String> newsLinks =  getLinksOnly(columns.get(1));
        List<String> contactsLink =  getLinksOnly(columns.get(2));
        Map<String, String> socialsLinks =  getTextAndLinks(columns.get(3));

        System.out.println(discountLinks);
        System.out.println(newsLinks);
        System.out.println(contactsLink);
        System.out.println(socialsLinks);

        //CLEAN UP
        Thread.sleep(3500);
        driver.quit();
    }

    private static Map<String, String> getTextAndLinks(WebElement el){
        return el.findElements(links)
                .stream().skip(1).collect(Collectors.toUnmodifiableMap(
                        element -> element.getText(), element -> element.getAttribute("href")
                ));
    }

    private static List<String> getLinksOnly(WebElement el){
        return el.findElements(links)
                .stream().skip(1).map(element -> element.getAttribute("href")).toList();
    }
}

