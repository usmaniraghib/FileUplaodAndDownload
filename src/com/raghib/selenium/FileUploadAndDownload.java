package com.raghib.selenium;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//UDEMY - SECTION-38
/*C:\STUDY_DATA\TESTING\New folder_2\1-B_SELANIUM\0.1Udemy - Selenium WebDriver with Java -Basics to Advanced+Frameworks\38.File Uploading (AUTO IT) & Downloading with Selenium\140XDPHH */

public class FileUploadAndDownload {

	public static void main(String[] args) throws InterruptedException, IOException {
		//FileUploadAndDownload.OnlyFileUploadUsingSendKeys();
		//FileUploadAndDownload.FileUploadAndDownloadUsingSendKeys();
		
		//FileUploadAndDownload.OnlyFileUploadUsingAUTOIT();
		FileUploadAndDownload.FileUploadAndDownloadUsingAUTOIT();
	}

	public static String urlPath1 = "https://pdf2jpg.net/";
	public static String uploadButtonxpathLocatorForClick = "//button[@id='advanced_pdf_file']";
	public static String convertPdfToJpg1 = "//input[@id='convert_pdf_to_jpg_button']";
	public static String downloadFile = "//a[@onclick='highlightShareButtons()']";
	
	public static String urlPath = "https://www.ilovepdf.com/pdf_to_jpg";
	public static String uploadButtonxpathLocatorForSendKeys = "//input[@type='file']";		
	public static String convertPdfToJpg = "//button[@id='processTask']";

	public static String chromeDriverPath = System.getProperty("user.dir");
	public static String uploadFilePath = System.getProperty("user.dir");
	public static String downloadFilePath = System.getProperty("user.dir");

	public static Map<String, Object> mapObject = new HashMap<String, Object>();
	
	public static Duration duration = Duration.ofSeconds(30);
	
	public static void OnlyFileUploadUsingSendKeys() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath + "\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(urlPath);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForSendKeys))
				.sendKeys(uploadFilePath + "\\UploadFile\\Shakreen.pdf");
		Thread.sleep(5000);
		driver.quit();
	}
	
	public static void FileUploadAndDownloadUsingSendKeys() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath + "\\Driver\\chromedriver.exe");

		mapObject.put("profile.default_content_setting.popups", 0);
		mapObject.put("download.default_directory", downloadFilePath);

		ChromeOptions optionObject = new ChromeOptions();
		optionObject.setExperimentalOption("prefs", mapObject);

		WebDriver driver = new ChromeDriver(optionObject);
		
		driver.get(urlPath);
		driver.manage().window().maximize();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForSendKeys)).sendKeys(uploadFilePath + "\\UploadFile\\Shakreen.pdf");
	
		WebDriverWait waitObject = new WebDriverWait(driver, duration);
		waitObject.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(convertPdfToJpg)));

		driver.findElement(By.xpath(convertPdfToJpg)).click();
		Thread.sleep(5000);
		
		File fileObject = new File(downloadFilePath+"\\"+"Shakreen_page-0001.jpg");
		System.out.println("fileObject : " + fileObject);
		Thread.sleep(50000);
		
		DeleteFile();
		/*
		 * if (fileObject.exists()) { System.out.println("File Download Successfully!");
		 * System.out.println("fileObject.delete() : "+fileObject.delete()); if
		 * (fileObject.delete()) { System.out.println("File Deleted!"); } } else {
		 * System.out.println("File Download Failed!"); }
		 */
		Thread.sleep(10000);
		driver.quit();
		 
	}
	
	public static void OnlyFileUploadUsingAUTOIT() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath + "\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(urlPath1);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath(uploadButtonxpathLocatorForClick)).click();
		Thread.sleep(50000);
		Runtime.getRuntime().exec(uploadFilePath + "\\AUTOITFolder\\fileupload.exe");
		Thread.sleep(50000);
		driver.quit();
	}

	public static void FileUploadAndDownloadUsingAUTOIT() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath + "\\Driver\\chromedriver.exe");

		mapObject.put("profile.default_content_setting.popups", 0);
		mapObject.put("download.default_directory", downloadFilePath);
		
		ChromeOptions optionObject = new ChromeOptions();
		optionObject.setExperimentalOption("prefs", mapObject);
		WebDriver driver = new ChromeDriver(optionObject);
		
		driver.get(urlPath1);
		driver.manage().window().maximize();
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
		
		DeleteFile();
		
		/*
		 * File fileObject = new File(downloadFilePath+"\\"+"Shakreen-page-001.jpg");
		 * System.out.println("fileObject : " + fileObject); Thread.sleep(50000); if
		 * (fileObject.exists()) { System.out.println("File Download Successfully!");
		 * System.out.println("fileObject.delete() : "+fileObject.delete()); if
		 * (fileObject.delete()) { System.out.println("File Deleted!"); } } else {
		 * System.out.println("File Download Failed!"); }
		 */
		Thread.sleep(10000);
		driver.quit();
		 
	}

	public static void DeleteFile() throws InterruptedException, IOException {
		File fileObject = new File(downloadFilePath+"\\"+"Shakreen-page-001.jpg");
		System.out.println("fileObject : " + fileObject);
		Thread.sleep(50000);
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
