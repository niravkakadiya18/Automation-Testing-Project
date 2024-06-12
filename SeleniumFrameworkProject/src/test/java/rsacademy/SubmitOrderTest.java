package rsacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rsacademy.pageobjects.CartPage;
import rsacademy.pageobjects.CheckOutPage;
import rsacademy.pageobjects.ConfirmationPage;
import rsacademy.pageobjects.LandingPage;
import rsacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException{
		
		String productName  = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		ProductCatalogue productCatalogue = landingpage.loginApplication("keval1@gmail.com", "Ikeval123");
		List<WebElement>products = productCatalogue.getProductList();	
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
				
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		

	}

}
