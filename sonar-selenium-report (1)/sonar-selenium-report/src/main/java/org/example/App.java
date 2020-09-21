package org.example;

/**
 * Hello world!
 *
 */
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        System.out.println( "Hello World!" );
        WebDriverManager.getInstance(CHROME).setup();
		
		
        //  For headless mode
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        // Launch Website
        driver.navigate().to("http://sonar.sirionlabs.office:9000//about");
        //Maximize the browser
        driver.manage().window().maximize();

        /*//Scroll down the webpage by 5000 pixels
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0, 5000)");*/
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      //  driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys("debashish");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("debashish");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//a[contains(@href, '/projects')]")).click();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Thread.sleep(3000);
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String userHomeFoler=System.getProperty("user.home");
        File DestFile=new File("C:\\CloudTechMasters-java\\test.png");
        System.out.println(DestFile.getName());

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

        Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\CloudTechMasters-java\\fulltest.png"));

        driver.close();

    }
}
