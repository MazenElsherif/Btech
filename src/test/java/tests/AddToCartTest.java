package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.addToCartPage;
import PageObjects.homePage;
import PageObjects.searchPage;
import utils.JsonFileManager;

public class AddToCartTest extends testBase {
	private static JSONObject testData;

@Test

public void addToCartTest() throws InterruptedException {
	testData =JsonFileManager.readJsonFile("src//test//java//testData//data.json");

	String productname =JsonFileManager.getJsonValue(testData,"productName");
	
	new homePage(driver).navigateToSearchPage().Search(productname);
String productNameBeforeAddToCart =new searchPage(driver).addToCart();
System.out.println(productNameBeforeAddToCart);
String productNameAfterAddToCart =new addToCartPage(driver).getProductName();
System.out.println(productNameAfterAddToCart);
Assert.assertEquals(productNameBeforeAddToCart, productNameAfterAddToCart);
}


}
