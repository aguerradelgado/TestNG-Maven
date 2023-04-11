package test_cases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class history_02 {
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
    void search_article() throws InterruptedException
    {
        driver.findElement(By.id("searchInput")).sendKeys("Selenium");

        Thread.sleep(1000); // wait for 1 second
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div/button")).click();

        Thread.sleep(2000); // wait for 2 seconds

        // to compare for attest
        String exp_url = "https://en.wikipedia.org/wiki/Selenium";

        // current URL
        String act_url = driver.getCurrentUrl();
        System.out.println(act_url);

        Assert.assertEquals(exp_url, act_url);

    } // end search article

    @Test(priority = 2)
    void contents_tab()
    {
        driver.findElement(By.id("toc-Chemical_compounds")).click();

        // saving section title
        String section_title = driver.findElement(By.id("Chemical_compounds")).getText();

        // checking if matches
        Assert.assertEquals("Chemical compounds", section_title);
    } // end contents tab

    @Test(priority = 3)
    void view_history() throws InterruptedException
    {

        // go to top of page
        Thread.sleep(3000); // wait 3 seconds
        driver.findElement(By.id("toc-mw-content-text")).click();

        // select view history
        Thread.sleep(3000); // wait 3 seconds
        driver.findElement(By.id("ca-history")).click();

    } // end of revision history

    @Test(priority = 4)
    void filter_revs() throws InterruptedException
    {
        Thread.sleep(3000); // wait 3 seconds

        // opens Filter Revisions Dropdown
        driver.findElement(By.id("mw-history-searchform")).click();

        Thread.sleep(1000); // wait 1 second

        // searches for specific tag
        driver.findElement(By.xpath("//*[@id=\"tagfilter\"]/div/span/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ooui-php-4\"]")).sendKeys("blanking", Keys.ENTER);

    } // end filter revs


} // end of class_two
