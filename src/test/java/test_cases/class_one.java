package test_cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class class_one {
    public static WebDriver driver;

    @Test
    void open_site()
    {
        System.setProperty("web-driver.chrome.driver", "C:\\SELENIUM\\Drivers\\chromedriver.exe");

        // bypass local only setting - allow other sites
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(option);
        driver.get("https://en.wikipedia.org/wiki/Wiki/");
        driver.manage().window().maximize();
    }
    @Test
    void mul()
    {
        int a = 20, b = 4;
        Assert.assertEquals(80, a * b);
    }

    @Test
    void factorial()
    {
        int fact = 1, n = 5;
        for(int i = 1; i <= 5; i++) {
            fact = fact * i;
        }
        Assert.assertEquals(120, fact);
    }
    @Test
    void sub()
    {
        int a = 60, b = 50;
        Assert.assertEquals(10, a - b);
    }
} //end class_one
