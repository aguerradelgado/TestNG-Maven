package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class random_article_03 {
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

    @Test(priority = 1)
    void nav_2_random_article() throws InterruptedException {
        String start_url = driver.getCurrentUrl();
        driver.findElement(By.xpath("//*[@id='vector-main-menu-dropdown']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("n-randompage")).click();
        Thread.sleep(1200);
        String end_url = driver.getCurrentUrl();
        boolean url_no_change = start_url.equals(end_url);

        Assert.assertFalse(url_no_change);
    }

    @Test(priority = 2)
    void link_num()
    {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        int link_num = links.size();
        boolean more_than_15 = link_num > 15;

        Assert.assertTrue(more_than_15);
    }

    @Test(priority = 3)
    void link_nav() throws InterruptedException {
        String start_url = driver.getCurrentUrl();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        String bro = (links.get(70).getText());
        driver.findElement(By.linkText(bro)).click();
        Thread.sleep(1200);
        String end_url = driver.getCurrentUrl();
        boolean url_no_change = start_url.equals(end_url);

        Assert.assertFalse(url_no_change);
    }

    @Test(priority = 4)
    void title_tag() throws InterruptedException {
        List<WebElement> links = driver.findElements(By.tagName("b"));
        String bold = links.get(0).getText();
        String title = driver.findElement(By.id("firstHeading")).getText();
        boolean same_string = title.contains(bold);

        Assert.assertTrue(same_string);
    }

}
