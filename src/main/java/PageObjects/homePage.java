package PageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementActions;
import utils.PropertiesReader;

public class homePage {
	private WebDriver driver;
	
	private By searchBar= By.id("search");

	Properties prop;
	public homePage(WebDriver driver)
	{
		this.driver=driver;
		prop=PropertiesReader.getProperties();
	}
	
	public homePage navigateToSearchPage()
	{
		driver.get(prop.getProperty("url"));
		return this;
	}
	public void Search(String productnamne) throws InterruptedException
	{
		ElementActions.type(driver, this.searchBar, productnamne);
		ElementActions.submit(driver, searchBar);
	}
}
