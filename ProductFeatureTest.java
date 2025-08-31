package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductFeatureTest {

    @Test(groups = {"smoke"})
    public void testLogin() {
        System.out.println("Running Smoke Test: Login");
        Assert.assertTrue(true, "Login test passed");
    }

    @Test(groups = {"regression"})
    public void testAddToCart() {
        System.out.println("Running Regression Test: Add to Cart");
        Assert.assertTrue(true, "Add to cart test passed");
    }

    @Test(groups = {"smoke", "regression"})
    public void testCheckout() {
        System.out.println("Running Both Smoke & Regression Test: Checkout");
        Assert.assertTrue(true, "Checkout test passed");
    }
}
