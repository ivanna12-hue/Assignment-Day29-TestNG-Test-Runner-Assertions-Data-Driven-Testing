package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageValidationTest {

    // Tugas A: Hard Assert
    @Test
    public void validateHomePageTitle() {
        String actualTitle = "Swag Labs";   // from driver.getTitle()
        String expectedTitle = "Swag Labs";

        System.out.println("Validating Home Page Title...");
        Assert.assertEquals(actualTitle, expectedTitle, "Home Page title mismatch!");
    }

    // Tugas B: Soft Assert
    @Test
    public void validateHomePageElements() {
        SoftAssert softAssert = new SoftAssert();

        String buttonText = "Add to cart";   // UI element
        String footerText = "© 2025 Swag Labs";
        String footerLink = "https://www.saucedemo.com";

        System.out.println("Validating multiple elements on Home Page...");

        softAssert.assertEquals(buttonText, "Add to cart", "Button text mismatch!");
        softAssert.assertTrue(footerText.contains("Swag Labs"), "Footer text mismatch!");
        softAssert.assertTrue(footerLink.startsWith("https"), "Footer link should use HTTPS!");


        softAssert.assertAll();
    }
}
