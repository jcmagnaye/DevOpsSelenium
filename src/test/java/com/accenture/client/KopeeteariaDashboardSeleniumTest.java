package com.accenture.client;

//import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

class KopeeteariaDashboardSeleniumTest {
	
	private ChromeDriver driver;
	private String baseUrl = "http://localhost:8080/koopeetearia-angular";

	
	@BeforeClass
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
    	driver = new ChromeDriver() ;
    	// Maximize window
        driver.manage().window().maximize();
        
        // Navigate to site URL
        driver.get(baseUrl);
        
        Thread.sleep(3000);
	}
    
	@Test
	public void addOrderTest() throws InterruptedException {
		System.out.println("Testing AddOrder");
        placeOrder("Latte", "4.5", true);
        Thread.sleep(3000);
        placeOrder("Cappuccino", "5.5", false);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("Latte"));
	}
	
	@Test
	public void editOrderTest() throws InterruptedException{
		System.out.println("Testing editOrder");
		// edit feature
        Thread.sleep(3000);
        driver.findElementById("update").click();
        // update feature
        driver.findElementById("orderName1").clear(); //clear text box
        Thread.sleep(3000);
        driver.findElementById("orderName1").sendKeys("Today's Brew");
        Thread.sleep(3000);
        driver.findElementById("price1").clear();// clear text box
        Thread.sleep(3000);
        driver.findElementById("price1").sendKeys("3.5");
        Thread.sleep(3000);
        driver.findElementById("upDiscount").click();
        Thread.sleep(3000);
        driver.findElementById("updateOrder").click();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getPageSource().contains("Today's Brew"));
	}
	
	@Test
	public void deleteOrderTest() throws InterruptedException {
		System.out.println("Testing deleteOrder");
		Thread.sleep(3000);
		driver.findElementById("deleteOrder").click();
		Assert.assertFalse(driver.getPageSource().contains("Today's Brew"));
	}

    
    public void placeOrder(String orderName, String orderPrice, boolean selectDiscount) {
    	driver.findElementById("orderName").sendKeys(orderName);
        driver.findElementById("price").sendKeys(orderPrice);
        // TODO: Remove [disabled] attribute in order.component.html to allow update for this field
        if (selectDiscount) {
        	driver.findElementById("ordDiscounted").click();
		}
        
        driver.findElementById("addOrder").click();
    }
	
}
