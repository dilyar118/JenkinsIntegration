package TestNG_Testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BasicTesting {



    public static WebDriver driver;

    public static String read_pro(String key){

        //Users/dilyaraerken/IdeaProjects/TestNG_Practicing/Configuration.properties
        String filePath = System.getProperty("user.dir")  + File.separator + "Configuration.properties";
        System.out.println(filePath);
        Properties pro = new Properties();

        try{

            FileInputStream file = new FileInputStream(filePath);
            pro.load(file);
        }

        catch(Exception e) {
            System.out.println("The current File Path is " + filePath);
        }

        return pro.getProperty(key);
    }

    public static void browser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver","/Users/dilyaraerken/Downloads/WebDrivers/chromedriver");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--headless");

        if(BasicTesting.read_pro("headlessMode").equalsIgnoreCase("yes")){

            driver = new ChromeDriver();
        }

        driver = new ChromeDriver();
        driver.get(BasicTesting.read_pro("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("hr");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("hr");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String title = driver.getTitle();
        System.out.println(title);




    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(BasicTesting.read_pro("url"));
        BasicTesting.browser();
        Thread.sleep(5000);
        driver.quit();

    }




}
