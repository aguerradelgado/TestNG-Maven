package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class commons_04 {
    private WebDriver driver;
    @BeforeClass
    public void open_site() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\alexp\\Downloads\\geckodriver-v0.33.0-win32\\geckodriver.exe");
        FirefoxOptions option = new FirefoxOptions();
        option.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");


        driver = new FirefoxDriver(option);
        driver.get("https://www.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000); // wait for 5 seconds
        driver.quit();
    }

    @Test
    void nav_2_commons() throws InterruptedException {
        String start_url = driver.getCurrentUrl();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='vector-page-tools-dropdown-checkbox']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[1]/div/div[2]/nav[2]/div/div" +
                "/div/div/div[5]/div[2]/ul/li[1]")).click();
        Thread.sleep(1200);
        String end_url = driver.getCurrentUrl();

        boolean url_no_change = start_url.equals(end_url);

        Assert.assertFalse(url_no_change);
    }

    @Test
    void picture_n_screenshot() throws IOException, InterruptedException {
        driver.findElement(By.id("mainpage-potd")).click();
        Thread.sleep(1200);

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File des = new File("C:\\Users\\alexp\\Pictures\\screenshot_potd.png");
        FileHandler.copy(src, des);

        driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/button[1]")).click();
        Thread.sleep(1200);

    }

    @Test
    void twitter_link()
    {
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/" +
                "div/table[2]/tbody/tr/td[1]/div[1]/div[2]/div[2]/ul/li[1]/a")).click();
        String url = driver.getCurrentUrl();
        boolean is_twitter = url.contains("twitter.com");

        Assert.assertTrue(is_twitter);
    }
}
