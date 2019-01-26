import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class lecture5 {
    WebDriver webDriver;
    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/alex1/Desktop/Кодинг/Java/naumen/puo/chromedriver//chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://els-rc.naumen.ru/");
        webDriver.manage().window().maximize();
    }

    @Test
    public void login() throws InterruptedException {
        webDriver.findElement(By.name("email")).sendKeys("otolstykh@naumen.ru");
        webDriver.findElement(By.name("password")).sendKeys("qwer1234");
        webDriver.findElement(By.id("loginbutton")).click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods={"login"})
    public void openOrganization() throws InterruptedException {
        webDriver.findElement(By.xpath(".//div[@class='slimScrollDiv']/div/ul[@class='nav'][2]/li[3]/a")).click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods={"openOrganization"})
    public void chooseOrganization() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id='orgs']/div/table/tbody/tr[1]/td[2]/a")).click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods={"chooseOrganization"})
    public void createDeliverance() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id='content']/div/div[2]/a")).click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods={"createDeliverance"})
    public void fillDeliverance() throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id='admissionType']/div[1]/label")).click();

        webDriver.findElement(By.xpath("//*[@id='representativeLastName']")).sendKeys("Чернецов");
        webDriver.findElement(By.xpath("//*[@id='representativeFirstName']")).sendKeys("Александр");
        webDriver.findElement(By.xpath("//*[@id='representativeMiddleName']")).sendKeys("Андреевич");


        webDriver.findElement(By.xpath("//*[@id='licensingProcedures']/div[2]/span")).click();

        WebElement element = webDriver.findElement(By.xpath("/html/body/ul[1]/li[1]/a"));

        if (!element.isDisplayed()) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        }

        element.click();

        webDriver.findElement(By.xpath("//*[@id='licenseReasons']/div/div[8]/div/input")).click();

        webDriver.findElement(By.xpath("//*[@id='requestAddForm']/div[8]/div[10]/button")).click();

        Thread.sleep(1000);

        webDriver.findElement(By.xpath("//*[@id='submitConfirm']/div/div/div[2]/button[1]")).click();
    }
}