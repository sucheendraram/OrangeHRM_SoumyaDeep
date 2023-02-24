package com.orangehrm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.TestBase;
import com.orangehrm.qa.pages.AddEmployeePage;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.pages.PimPage;
import com.orangehrm.qa.util.TestUtil;

public class AddEmployeePageTest extends TestBase{
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	PimPage pimPage;
	AddEmployeePage addEmployeePage;
	
	String sheetName = "AddEmployee";
	
	public AddEmployeePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		
		loginPage = new LoginPage();
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		pimPage = dashboardPage.clickOnpimLink();
		addEmployeePage = pimPage.clickAddButton();
	}
	
	
	@DataProvider
	public Object[][] getEmployeeTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
		
		
	@Test(priority = 1, dataProvider = "getEmployeeTestData")
	public void validateAddNewEmployee(String firstName, String middleName, String lastName) throws InterruptedException {
		
		//addEmployeePage.addNewEmployee("Pooja", "Pushpa", "Mallick");
		addEmployeePage.addNewEmployee(firstName, middleName, lastName);
		Thread.sleep(3000);
	}
	
	/*
	@Test(priority = 1)
	public void validateAddNewEmployee() throws InterruptedException {
		
		addEmployeePage.addNewEmployee("Pooja", "Pushpa", "Mallick");
		Thread.sleep(3000);
	}
	*/
	
	
	
	
	@AfterMethod
	public void tearDown()  {
		
		driver.quit();  //Why I am getting configuration error in this one?
		
	}

}
