package tests;


import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;

import java.time.LocalDate;

import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.CapabilityType;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import utils.BrowserFactory;

public class testBase {
	public static WebDriver driver;
	 public static com.relevantcodes.extentreports.ExtentReports report;
	    public static com.relevantcodes.extentreports.ExtentTest logger;
	    ExtentReports extent;
	    LocalDate myObj = LocalDate.now();
    @BeforeMethod
    @Parameters({"browser"})
    public void start(@Optional("chrome") String browername,Method method) {

        
        
        if(browername.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver(chromeopthion());}
        else if (browername.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }
        else if(browername.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
        }
        driver.manage().window().maximize();
        logger=report.startTest(method.getName());

    }
    @AfterMethod
    public void end(Method method,ITestResult result) throws  IOException {

        takescreenshot(method.getName());
        if(result.getStatus()==ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Pass");
            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov"+"'><span class='label info'>Download Video</span></a>");
            logger.setDescription("DHA Report");

        }
        else if(result.getStatus()==ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov"+"'><span class='label info'>Download Video</span></a>");
        }
        else {
            logger.log(LogStatus.SKIP, "Test Skipped");
        }
       driver.quit();

    }
    @BeforeSuite
    public void startreport() {
        String reportpath=System.getProperty("user.dir")+"\\TestReport\\ExtentReportResults"+myObj+".html";
        report=new com.relevantcodes.extentreports.ExtentReports(reportpath,true);
        report.addSystemInfo("Reported By", "QA Team");
        report.loadConfig(new File(System.getProperty("user.dir")+"\\extent2.xml"));

    }
    @AfterSuite
    public void endreport() {
        report.flush();
    }
 
    public static void takescreenshot(String name) throws IOException {
        File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\TestReport\\"+name+".png"));

    }


public static ChromeOptions chromeopthion() {
    ChromeOptions option =new ChromeOptions();
    HashMap<String, Object> chromeprefs=new HashMap<String, Object>();
    
    chromeprefs.put("profile.default.content_settings.popups", 0);
    option.setExperimentalOption("prefs", chromeprefs);
    option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    option.addArguments("--disable-gpu");
    option.addArguments("--disable-dev-shm-usage");
    option.addArguments("--no-sandbox");
    option.addArguments("--remote-allow-origins=*");

    return option;
}
}
