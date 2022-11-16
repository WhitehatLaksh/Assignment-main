package unitTests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Wingify {
    private static ChromeDriver driver;
    WebElement p = null;
    private String str;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty( "webdriver.chrome.driver", "C:\\chromedriver.exe" );
        driver = new ChromeDriver();
        String baseUrl = "https://sakshingp.github.io/assignment/login.html";
        driver.get( baseUrl );
    }

    @Test
    public void MainForm() throws InterruptedException {
        JavascriptExecutor js = driver;
//        CountDownLatch waiter = new CountDownLatch(1);
//        waiter.await(3000, TimeUnit.MILLISECONDS); // wait for all elements to load

        driver.manage().window().maximize(); // command to maximize the window
        driver.findElement( By.id( "username" ) ).sendKeys( "Lakshay" );
        driver.manage().timeouts().setScriptTimeout( 100, TimeUnit.SECONDS );
        driver.findElement( By.id( "password" ) ).sendKeys( "Wingify" );
        driver.manage().timeouts().setScriptTimeout( 100, TimeUnit.SECONDS );
        System.out.println( "Pressing Login button" );
        driver.findElement( By.id( "log-in" ) ).click();
        CountDownLatch waiter = new CountDownLatch( 1 );
        waiter.await( 3000, TimeUnit.MILLISECONDS );
        driver.manage().timeouts().setScriptTimeout( 2000, TimeUnit.SECONDS );
        js.executeScript( "window.scrollBy(0,120)" ); // scrolling down to confirm message sent
        System.out.println( "Going to list all values" );
        //      System.out.println( "Now clicking Amount button to sort" );
        List<WebElement> beforeSortedAmount = driver.findElementsByXPath( "//*[@id=\"transactionsTable\"]/tbody/tr/td[5]/span" );
        String[] beforeSortAmountList = new String[beforeSortedAmount.size()];
        for (int i = 0; i < beforeSortedAmount.size(); i++) {
            beforeSortAmountList[i] = (beforeSortedAmount.get( i ).getText().trim().replace( "USD", "" ));
        }
        System.out.println( "Before Sort Amount" );
        Print( beforeSortAmountList );
        driver.manage().timeouts().setScriptTimeout( 60, TimeUnit.SECONDS );
        driver.findElement( By.id( "amount" ) ).click();
        driver.manage().timeouts().setScriptTimeout( 60, TimeUnit.SECONDS );
        List<WebElement> afterSortAmount = driver.findElementsByXPath( "//*[@id=\"transactionsTable\"]/tbody/tr/td[5]/span" );
        String[] afterSortAmountList = new String[beforeSortedAmount.size()];
        for (int i = 0; i < afterSortAmount.size(); i++) {
            afterSortAmountList[i] = (afterSortAmount.get( i ).getText().trim().replace( "USD", "" ));
        }
        System.out.println( "After Sort Amount" );
        Print( afterSortAmountList );
        driver.manage().timeouts().setScriptTimeout( 30, TimeUnit.SECONDS );

    }




    private void Print(String[] beforeSortAmountList) {
        for(int i = 0;i<beforeSortAmountList.length;i++){
         System.out.println(beforeSortAmountList[i]);
        }
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit(); // closing browser
    }
}







