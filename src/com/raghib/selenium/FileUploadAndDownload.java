package com.raghib.selenium;

/**
 * Reference:-
 * https://veerasundar.com/blog/how-to-auto-reload-external-code-changes-in-eclipse-project/
 * 
 * Go to **Window -> Preferences -> General -> Workspace **and check the option 'Refresh using native hooks or polling'.
 * Uncheck "Refresh on access".
 */

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//UDEMY - SECTION-38
/*C:\STUDY_DATA\TESTING\New folder_2\1-B_SELANIUM\0.1Udemy - Selenium WebDriver with Java -Basics to Advanced+Frameworks\38.File Uploading (AUTO IT) & Downloading with Selenium\140XDPHH */

public class FileUploadAndDownload extends BaseClass {

	public static void main(String[] args) throws InterruptedException, IOException {
		//FileUploadAndDownload.OnlyFileUploadUsingSendKeys();
		FileUploadAndDownload.FileUploadAndDownloadUsingSendKeys();
		
		//FileUploadAndDownload.OnlyFileUploadUsingAUTOIT();
		//FileUploadAndDownload.FileUploadAndDownloadUsingAUTOIT();
	}
	
	public static ChromeOptions chromeOptions;
	public static FirefoxOptions firefoxOptions;
	public static WebDriver driver;
	
	public static String browserFirefox = "firefox";
	public static String browserChrome = "chrome";
	public static String browserVersion = "116";
	
	public static String urlPath = "https://www.ilovepdf.com/pdf_to_jpg";
	public static String uploadButtonxpathLocatorForSendKeys = "//input[@type='file']";		
	public static String convertPdfToJpg = "//button[@id='processTask']";

	public static String urlPath1 = "https://pdf2jpg.net/";
	public static String uploadButtonxpathLocatorForClick = "//button[@id='advanced_pdf_file']";
	public static String convertPdfToJpg1 = "//input[@id='convert_pdf_to_jpg_button']";
	public static String downloadFile = "//a[@onclick='highlightShareButtons()']";
	
	public static String chromeDriverPath = System.getProperty("user.dir");
	public static String uploadFilePath = System.getProperty("user.dir");
	public static String downloadFilePath = System.getProperty("user.dir");

	public static Map<String, Object> mapObject = new HashMap<String, Object>();	
	public static Duration duration = Duration.ofSeconds(30);
		
	public static void OnlyFileUploadUsingSendKeys() throws InterruptedException {
		// Chrome Browser
		driver = BaseClass.getDriver(browserChrome, browserVersion);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(urlPath);

		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForSendKeys))
				.sendKeys(uploadFilePath + "\\UploadFile\\Shakreen.pdf");
		Thread.sleep(5000);
		BaseClass.quitDriver();
	}
	
	public static void FileUploadAndDownloadUsingSendKeys() throws InterruptedException, IOException {

		mapObject.put("profile.default_content_setting.popups", 0);
		mapObject.put("download.default_directory", downloadFilePath);

		// Chrome Browser
		driver = BaseClass.getDriver(browserChrome, browserVersion, mapObject);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(urlPath);

		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForSendKeys))
				.sendKeys(uploadFilePath + "\\UploadFile\\Shakreen.pdf");

		WebDriverWait waitObject = new WebDriverWait(driver, duration);
		waitObject.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(convertPdfToJpg)));

		driver.findElement(By.xpath(convertPdfToJpg)).click();
		Thread.sleep(5000);

		File fileObject = new File(downloadFilePath + "\\" + "Shakreen_page-0001.jpg");
		System.out.println("fileObject : " + fileObject);
		Thread.sleep(50000);

		DeleteFile();

		Thread.sleep(10000);
		BaseClass.quitDriver();
	}	
		
	public static void OnlyFileUploadUsingAUTOIT() throws InterruptedException, IOException {
		driver = BaseClass.getDriver(browserChrome, browserVersion);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(urlPath1);
		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForClick)).click();
		Thread.sleep(50000);
		Runtime.getRuntime().exec(uploadFilePath + "\\AUTOITFolder\\fileupload.exe");
		Thread.sleep(50000);
		BaseClass.quitDriver();
	}

	public static void FileUploadAndDownloadUsingAUTOIT() throws InterruptedException, IOException {
		
		mapObject.put("profile.default_content_setting.popups", 0);
		mapObject.put("download.default_directory", downloadFilePath);
		
		driver = BaseClass.getDriver(browserChrome, browserVersion, mapObject);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(urlPath1);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForClick)).click();
		Thread.sleep(50000);
		Runtime.getRuntime().exec(uploadFilePath + "\\AUTOITFolder\\fileupload.exe");
		Thread.sleep(10000);
		
		WebDriverWait waitObject = new WebDriverWait(driver, duration);
		waitObject.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(convertPdfToJpg1)));

		driver.findElement(By.xpath(convertPdfToJpg1)).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(downloadFile)).click();
		
		//DeleteFile();
		
		Thread.sleep(10000);
		BaseClass.quitDriver();		 
	}
	

	public static void DeleteFile() throws InterruptedException, IOException {
		File fileObject = new File(downloadFilePath+"\\"+"Shakreen_page-0001.jpg");
		System.out.println("fileObject inside delete file method : " + fileObject);
		Thread.sleep(50000);
		System.out.println("fileObject.exists() : "+fileObject.exists());
		if (fileObject.exists()) {
			System.out.println("File Download Successfully!");			
			System.out.println("fileObject.delete() : "+fileObject.delete());
			if (fileObject.delete()) {
				System.out.println("File Deleted!");
			}
		} else {
			System.out.println("File Download Failed!");
		}
	}
}