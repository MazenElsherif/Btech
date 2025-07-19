package PageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementActions;



public class searchPage {
private WebDriver driver;
	
	private By firstProduct= By.xpath("(//h2[@class='plpTitle'])[1]");
	public By productImage= By.xpath("(//img[@class='product-image-photo'])[1]");
	private By addToCart= By.xpath("//button[@title='Add to cart']");
	private By addToCartList= By.xpath("//a[@class='action showcart cart-icon-green']");


	public searchPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	public String addToCart() throws InterruptedException {
		ElementActions.commonWait(driver, firstProduct);
		String firstProductName =driver.findElement(firstProduct).getText();

		ElementActions.clickElement(driver, productImage);
		ElementActions.clickElement(driver, addToCart);
		ElementActions.clickElement(driver, addToCartList);
		return firstProductName;
		
	}
}
