import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import java.util.List;

import static org.openqa.selenium.By.*;

public class TestPravdaComUa {
    WebDriver driver;
    List<WebElement> lastNews;
    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https:www.pravda.com.ua");
    }
    @Test
    public void methodGet() {
        lastNews = driver.findElements(By.className("article_news"));
        System.out.println("=== how many news: " + lastNews.size());
    }
    @Test
    public void methodGetCurrentUrl() {
        System.out.println(" current Url: " + driver.getCurrentUrl());
    }
    @Test
    public void methodGetTitle() {
        System.out.println(" title: " + driver.getTitle());
    }
    @Test
    public void methodFindElements() {
        lastNews = driver.findElements(By.className("article_news"));
        System.out.println("=== how many news: " + lastNews.size());
        for (WebElement s: lastNews) {
            System.out.println(s.getText());
        }
    }
    @Test
    public void methodFindElement() {
//        WebElement topMenuLink = driver.findElement(By.xpath("/html/body/header/div/div/div[1]/div[1]/a/span[2]"));
        WebElement topMenuLink = driver.findElement(className("options_text"));
        System.out.println("=== text: " + topMenuLink.getText());
        topMenuLink.click();
        topMenuLink.click();
    }
    @Test
    public void methodGetPageSource() {
        String pageSource = driver.getPageSource();
        System.out.println("\n=== pageSource - first 300 symbols: " + pageSource.substring(0,300));
        System.out.println("\n=== all symbols: " + pageSource.length());
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
