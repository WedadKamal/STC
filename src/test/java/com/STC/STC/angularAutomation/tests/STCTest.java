package com.STC.STC.angularAutomation.tests;

import com.STC.STC.angularAutomation.pages.STCHomePage;
import com.STC.STC.angularAutomation.utils.Log;
import com.STC.STC.angularAutomation.constants.GeneralConstants;
import com.STC.STC.angularAutomation.constants.excelIndices.STCHomePageExcellndices;
import com.STC.STC.angularAutomation.dataModels.STCHomePageDM;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class STCTest extends BaseTest{


    ArrayList STCTestData = new ArrayList();
    String actualResults;
    @BeforeClass(alwaysRun = true)
    public void navigateToSTCPage(){
        String navigatedToPageSuccessfully = STCHomePage.selectEnglishLG();
        Assert.assertEquals(navigatedToPageSuccessfully, GeneralConstants.SUCCESS, "Could not select English successfully");
    }

    @Test(description = "Validate STC functionalities",priority = 0  ,dataProvider = "STCDP" ,enabled = true)
    public void verifySTCFunctionality(STCHomePageDM STCHomePageDM) {
        //Create extent test to be logged in report using test case title
        test = extent.createTest(STCHomePageDM.getTestCaseId() + " --- " + STCHomePageDM.getTestCaseTitle());
        Log.test = test;
        Log.startTestCase(STCHomePageDM.getTestCaseId() + " --- " + STCHomePageDM.getTestCaseTitle());

        com.STC.STC.angularAutomation.pages.STCHomePage STCHomePage = new STCHomePage(driver);
        STCHomePage.countryFlag(STCHomePageDM);
        assertPackagesType(STCHomePageDM,STCHomePage);
        assertPackagesPrices(STCHomePageDM,STCHomePage);
        assertPackagesCurrency(STCHomePageDM,STCHomePage);

    }

    void assertPackagesType(STCHomePageDM STCHomePageDM,STCHomePage STCHomePage)
    {
        if(!STCHomePageDM.getLitePackage().isEmpty()){
            actualResults= STCHomePage.validatePackgesType(STCHomePageDM);
            Assert.assertEquals(actualResults,STCHomePageDM.getLitePackage(),"assertion failed for package Lite type ");
            Log.info("lite package type assertion passed successfully which is "+ actualResults);
        }

        if(!STCHomePageDM.getClassicPackage().isEmpty()){
            actualResults= STCHomePage.validatePackgesType(STCHomePageDM);
            Assert.assertEquals(actualResults,STCHomePageDM.getClassicPackage(),"assertion failed for package classic type ");
            Log.info("Classic package type assertion passed successfully which is "+ actualResults);
        }

        if(!STCHomePageDM.getPremiumPackage().isEmpty()){
            actualResults= STCHomePage.validatePackgesType(STCHomePageDM);
            Assert.assertEquals(actualResults,STCHomePageDM.getPremiumPackage(),"assertion failed for package premium type ");
            Log.info("premium package type assertion passed successfully which is "+ actualResults);
        }


    }

    void assertPackagesPrices(STCHomePageDM STCHomePageDM,STCHomePage STCHomePage)
    {
        if(!STCHomePageDM.getLitePackagePrice().isEmpty()){
            actualResults= STCHomePage.validatePackgesPrice(STCHomePageDM);
            Assert.assertEquals(actualResults,STCHomePageDM.getLitePackagePrice(),"assertion failed for package Lite price ");
            Log.info("lite package price assertion passed successfully which is "+ actualResults);
        }

        if(!STCHomePageDM.getClassicPackagePrice().isEmpty()){
            actualResults= STCHomePage.validatePackgesPrice(STCHomePageDM);
            Assert.assertEquals(actualResults,STCHomePageDM.getClassicPackagePrice(),"assertion failed for package classic price ");
            Log.info("classic package price assertion passed successfully which is "+ actualResults);
        }

        if(!STCHomePageDM.getPremiumPackagePrice().isEmpty()){
            actualResults= STCHomePage.validatePackgesPrice(STCHomePageDM);
            Assert.assertEquals(actualResults,STCHomePageDM.getPremiumPackagePrice(),"assertion failed for package premium price ");
            Log.info("premium package price assertion passed successfully which is "+ actualResults);
        }


    }

    void assertPackagesCurrency(STCHomePageDM STCHomePageDM,STCHomePage STCHomePage)
    {
        if(!STCHomePageDM.getLiteCurrency().isEmpty()){
            actualResults= STCHomePage.validatePackgesCurrency(STCHomePageDM);
            String[] currency = actualResults.split("/");
            actualResults= currency[0];
            Assert.assertEquals(actualResults,STCHomePageDM.getLiteCurrency(),"assertion failed for package Lite currency ");
            Log.info("lite package currency assertion passed successfully which is "+ actualResults);
        }

        if(!STCHomePageDM.getClassicCurrency().isEmpty()){
            actualResults= STCHomePage.validatePackgesCurrency(STCHomePageDM);
            String[] currency = actualResults.split("/");
            actualResults= currency[0];
            Assert.assertEquals(actualResults,STCHomePageDM.getClassicCurrency(),"assertion failed for package classic currency ");
            Log.info("classic package currency assertion passed successfully which is "+ actualResults);
        }

        if(!STCHomePageDM.getPremiumCurrency().isEmpty()){
            actualResults= STCHomePage.validatePackgesCurrency(STCHomePageDM);
            String[] currency = actualResults.split("/");
            actualResults= currency[0];
            Assert.assertEquals(actualResults,STCHomePageDM.getPremiumCurrency(),"assertion failed for package premium currency ");
            Log.info("premium package currency assertion passed successfully which is "+ actualResults);
        }


    }

    @DataProvider(name = "STCDP")
    public Object[][] provideSTCTD() {
        Object[][] STCHomePageDP = new Object[STCTestData.size()][1];
        for (int i = 0; i < STCTestData.size(); i++)
            STCHomePageDP[i][0] = STCTestData.get(i);

        return STCHomePageDP;
    }

    @BeforeClass
    public void STCTD() {

        ArrayList<ArrayList<Object>> resultArray = provideTestData("STCTestData");

        for (ArrayList<Object> objects : resultArray) {
            STCHomePageDM STCHomePageDM = new STCHomePageDM();
            STCHomePageDM.setTestCaseId(objects.get(STCHomePageExcellndices.TEST_CASE_ID_INDEX).toString());
            STCHomePageDM.setTestCaseTitle(objects.get(STCHomePageExcellndices.TEST_CASE_TITLE_INDEX).toString());
            STCHomePageDM.setTestScope(objects.get(STCHomePageExcellndices.TEST_SCOPE_INDEX).toString());
            STCHomePageDM.setLitePackage(objects.get(STCHomePageExcellndices.LITE_PACKAGE_TYPE).toString());
            STCHomePageDM.setClassicPackage(objects.get(STCHomePageExcellndices.CLASSIC_PACKAGE_TYPE).toString());
            STCHomePageDM.setPremiumPackage(objects.get(STCHomePageExcellndices.PREMIUM_PACKAGE_TYPE).toString());
            STCHomePageDM.setLitePackagePrice(objects.get(STCHomePageExcellndices.LITE_PACKAGE_PRICE).toString());
            STCHomePageDM.setClassicPackagePrice(objects.get(STCHomePageExcellndices.CLASSIC_PACKAGE_PRICE).toString());
            STCHomePageDM.setPremiumPackagePrice(objects.get(STCHomePageExcellndices.PREMIUM_PACKAGE_PRICE).toString());
            STCHomePageDM.setLiteCurrency(objects.get(STCHomePageExcellndices.LITE_PACKAGE_CURRENCY).toString());
            STCHomePageDM.setClassicCurrency(objects.get(STCHomePageExcellndices.CLASSIC_PACKAGE_CURRENCY).toString());
            STCHomePageDM.setPremiumCurrency(objects.get(STCHomePageExcellndices.PREMIUM_PACKAGE_CURRENCY).toString());
            STCHomePageDM.setCountryName(objects.get(STCHomePageExcellndices.COUNTY_FLAG).toString());
            STCTestData.add(STCHomePageDM);

        }

    }

}
