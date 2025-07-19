package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class addToCartPage {
	private WebDriver driver;
	private By productNameHeader= By.xpath("//strong[@class='product-item-name']//a");

	public addToCartPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	public String getProductName() {
		String productName=driver.findElement(productNameHeader).getText();
		return productName;
	}
}
