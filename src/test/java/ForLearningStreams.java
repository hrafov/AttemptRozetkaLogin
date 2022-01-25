import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.nio.file.WatchEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ForLearningStreams {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
    }
    @Test
    public void brokenImageTest()  {
        this.driver.get("http://google.com");
        System.out.println(LocalDateTime.now());
        List<String> list = this.driver.findElements(By.xpath("//*[@href]"))
               .stream()
               .parallel()
               .map(e -> e.getAttribute("href"))
               .filter(src -> LinkUtil.getResponseCode(src) != 200)
               .collect(Collectors.toList());
        System.out.println(LocalDateTime.now());
//        Assert.assertEquals(list.size(), 0, list.toString());
        //Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

//    @DataProvider(name = "gender")
//    public Object[] testData() {
//        return new Object[] {"male", "female"};
//    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
