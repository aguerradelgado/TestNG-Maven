package test_cases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class login_01 {
    private WebDriver driver;

    @BeforeClass
    public void open_site() {
        System.setProperty("webdriver.chrome.driver", "C:\\SELENIUM\\Drivers\\chromedriver.exe");

        // bypass local only setting - allow other sites
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(option);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
    } // set_up

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000); // wait for 5 seconds
        driver.quit();
    } // clean_up

    @Test(priority = 1)
    void open_login_page() throws InterruptedException
    {
        Thread.sleep(2000); // wait for 2 seconds
        driver.findElement(By.id("pt-login-2")).click();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://en.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Main+Page");

    } // end open login page

    @Test(priority = 2)
    void valid_user_pass() throws InterruptedException {

        // enter valid credentials
        driver.findElement(By.id("wpName1")).sendKeys("FGCUtest");
        driver.findElement(By.id("wpPassword1")).sendKeys("1234Test!");
        driver.findElement(By.id("wpLoginAttempt")).click();

        // save in variables for easy attest
        String exp_login = "FGCUtest";
        String act_login = driver.findElement(By.xpath("//*[@id=\"pt-userpage-2\"]/a/span")).getText();

        Thread.sleep(3000); // wait for 3 seconds
        driver.findElement(By.xpath("//*[@id=\"pt-userpage-2\"]/a/span")).click();

        // test
        Assert.assertEquals(exp_login, act_login);
    } // end logging in with valid credentials

    @Test(priority = 3)
    void screenshot_profile() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File des = new File("C:\\Users\\aleg1\\OneDrive\\Desktop\\profile_testng_screenshots\\profile_screenshot.png");
        FileHandler.copy(src, des);
    } // end of screenshot


} //end class_one
