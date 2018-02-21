package functionalTest;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.Log4Test;
import utils.PropertyLoader;

public class MainTest {


    String EarthDateLink = PropertyLoader.loadProperty("EarthDateLink");
    String SolDateLink = PropertyLoader.loadProperty("SolDateLink");

    @BeforeSuite
    public static void loadDataFromNasaAPI(){

        Log4Test.info("TEST start");
        Log4Test.info("*************");

    }

    @BeforeMethod
    public void logStartTest(){
        Log4Test.startTest(getClass());
    }

    @AfterMethod
    public void testResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS){
            Log4Test.info("success : " + getClass().getName().toString());
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            Log4Test.error(" FAILED : " + getClass().getName().toString());
        }
    }

    @AfterSuite
    public static void closeTest(){
       Log4Test.info("*************");
       Log4Test.info("TEST finish");

    }

}
