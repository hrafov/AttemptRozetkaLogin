import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class TestRozetka {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void checkAuthorizationThroughFacebook() throws InterruptedException {
        driver.get("https://rozetka.com.ua/");
        driver.findElement(By.
                xpath("//button[@class='header__button ng-star-inserted']")).click();
        Thread.sleep(5000);

        String mainHandle = driver.getWindowHandle();
        System.out.println("main Window ID: " + mainHandle + "\n");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");

        driver.findElement(By.
                xpath("//button[@class='button\n" +
                        "               button--large\n" +
                        "               button--gray\n" +
                        "               button--with-icon\n" +
                        "               auth-modal__social-button\n" +
                        "               auth-modal__social-button_type_facebook']")).click();

        Set <String> allHandles = driver.getWindowHandles();
        System.out.println("Windows Open After Click: " + allHandles.size());

        for (String windowHandle : allHandles) {
            if (mainHandle.equals(windowHandle)) {
                System.out.println("\t Window ID 1: \t" + windowHandle +
                        "\n\t URL:\t\t" + driver.getCurrentUrl() +
                        "\n\t Title:\t\t" + driver.getTitle());
            }
            else {
                driver.switchTo().window(windowHandle);
                System.out.println("\t Window ID 2: \t" + windowHandle +
                        "\n\t URL:\t\t" + driver.getCurrentUrl() +
                        "\n\t Title:\t\t" + driver.getTitle());
            }
        }
//TODO input valid mail and password
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("0679829142");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='login']")).click();

        Thread.sleep(10000);

        driver.switchTo().window(mainHandle);
        driver.findElement(By.xpath("//button[@class='header__button']")).click();
        System.out.println("My name: " + driver.findElement(By.xpath("//a[@class='side-menu__user-name']")).getText());
        System.out.println("My mail: " + driver.findElement(By.xpath("//p[@class='side-menu__auth-caption']")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='side-menu__auth-caption']")).getText(),
                "hrafovyevhen@gmail.com");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
