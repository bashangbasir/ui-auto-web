package Learning.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class EcommercePractice {

    private static By searchInput = By.cssSelector("input.search-keyword");
    private static By searchButton = By.cssSelector("button.search-button");
    private static By itemInCart = By.xpath("//div[@class='cart-info']//tr[1]/td[3]");
    private static By priceInCart = By.xpath("//div[@class='cart-info']//tr[2]/td[3]");
    private static By cartIcon = By.cssSelector("a.cart-icon");
    private static By productCard = By.cssSelector("div.products div.product");
    private static By productName = By.cssSelector("h4.product-name");
    private static By AddToCartButton = By.cssSelector("div.product-action button");
    private static By productPrice = By.cssSelector("p.product-price");
    private static By productQuantity = By.cssSelector("div.stepper-input input.quantity");
    private static By cartPreviewPanel = By.cssSelector("div.cart-preview.active");
    private static By cartItem = By.xpath("//div[@class='cart-preview active']//li[@class='cart-item']");
    private static By proceedToCheckoutButton = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    private static By promoCodeInput = By.cssSelector("input.promoCode");
    private static By applyPromoButton = By.cssSelector("button.promoBtn");
    private static By promoInfoMessage = By.cssSelector("span.promoInfo");


    

    private static String url = "https://rahulshettyacademy.com/seleniumPractise/";
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static int totalPrice = 0;
    private static int items = 0;

    @Test
    public void testEcommerce() throws InterruptedException {

        // WEBDRIVER SETUP
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // SCRIPT
        //ADD ITEM TO CART
        addItemToCart("Cucumber", 3);
        addItemToCart("Beetroot", 3);
        Assert.assertEquals(Integer.parseInt(driver.findElement(itemInCart).getText()), items);
        Assert.assertEquals(Integer.parseInt(driver.findElement(priceInCart).getText()), totalPrice);
        // CHECKOUT
        driver.findElement(cartIcon).click();
        WebElement cartPreview = driver.findElement(cartPreviewPanel);
        Assert.assertTrue(cartPreview.isDisplayed());
        //VERIFY NUMBER OF ITEM IN CART PREVIEW
        List<WebElement> cartItems = wait.until(ExpectedConditions.numberOfElementsToBe(cartItem,2));
        Assert.assertEquals(cartItems.size(), items);
        cartPreview.findElement(proceedToCheckoutButton).click();
        // PROCEED TO CHECKOUT PAGE AND VERIFY IN CHECKOUT PAGE
        wait.until(ExpectedConditions.titleIs("GreenKart - veg and fruits kart"));
        wait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/seleniumPractise/#/cart"));

        applyPromoCode("rahulshettyacademy");
        verifyCodeApplied();

        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();

    }

    public static void addItemToCart(String vegetable, int count) {
        List<WebElement> productCards = driver.findElements(productCard);
        for (WebElement card : productCards) {
            String name = card.findElement(productName).getText();
            if (name.contains(vegetable)) {
                if (count > 1) {
                    card.findElement(productQuantity).clear();
                    card.findElement(productQuantity).sendKeys(String.valueOf(count));
                }
                int pricePerItem = Integer.parseInt(card.findElement(productPrice).getText());
                int quantity = Integer.parseInt(card.findElement(productQuantity).getAttribute("value"));
                card.findElement(AddToCartButton).click();
                items = items + 1;
                totalPrice = totalPrice + (pricePerItem * quantity);
                break;
            }
        }
    }

    public static void applyPromoCode(String promoCode){
        driver.findElement(promoCodeInput).sendKeys(promoCode);
        driver.findElement(applyPromoButton).click();
    }

    public static void verifyCodeApplied(){
        WebElement promoMessageEl = wait.until(ExpectedConditions.visibilityOfElementLocated(promoInfoMessage));
        String message = promoMessageEl.getText();
        String messageColour = promoMessageEl.getAttribute("style");
        Assert.assertEquals(message, "Code applied ..!");
        Assert.assertTrue(messageColour.contains("color: green"));

    }


}
