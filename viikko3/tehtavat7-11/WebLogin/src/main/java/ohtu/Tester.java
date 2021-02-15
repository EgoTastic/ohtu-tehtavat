package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        Random r = new Random();
        
        String rnduser = ("miika"+r.nextInt(10000));
        String rndpass = rnduser + "99";

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(rnduser);
        element = driver.findElement(By.name("password"));
        element.sendKeys(rndpass);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(rndpass);
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();

        sleep(3);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        sleep(2);
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        sleep(2);
        element.click();
        sleep(3);
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
