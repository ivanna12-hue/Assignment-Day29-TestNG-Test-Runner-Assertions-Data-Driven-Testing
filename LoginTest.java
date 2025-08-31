package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set lokasi chromedriver kalau perlu: System.setProperty("webdriver.chrome.driver", "path");
        String driverPath = "/src/test/java/drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // DataProvider untuk LoginTest
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "success"},
                {"locked_out_user", "secret_sauce", "failure"},
                {"problem_user", "secret_sauce", "success"},
                {"invalid_user", "bad_password", "failure"}
        };
    }

    // Test data login
    @Test(dataProvider = "loginCredentials")
    public void testLoginScenarios(String username, String password, String expectedResult) {
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // isi username
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        usernameField.clear();
        usernameField.sendKeys(username);

        // isi password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // klik button login
        driver.findElement(By.id("login-button")).click();

        if (expectedResult.equals("success")) {
            // cek URL setelah login sukses
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("inventory.html"),
                    "User should be redirected to inventory page after successful login");
        } else {
            // cek pesan error setelah login gagal
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
            );
            Assert.assertTrue(errorMessage.isDisplayed(),
                    "Error message should be displayed for invalid login");
        }
    }
}
