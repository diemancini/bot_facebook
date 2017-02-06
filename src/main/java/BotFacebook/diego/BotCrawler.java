package BotFacebook.diego;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BotCrawler {
	private ChromeOptions options;
	//private String chromeDriverPath = "/Users/diego/Documents/Programação/bot_centos_java/workspace/diego/chromedriver";
	private String chromeDriverPath = "/home/dmancini/Documents/chromedriver";
	
	public BotCrawler() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		this.options = new ChromeOptions();
		this.options.setExperimentalOption("prefs", prefs);
	}
	
	public WebDriver loginFacebook(String login, String password) throws IOException {
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver", this.chromeDriverPath);
		driver = new ChromeDriver(this.options);
		driver.get("https://www.facebook.com/login.php?login_attempt=1");
		driver.findElement(By.id("email")).sendKeys(login);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("loginbutton")).click();
	    
		return driver;
	}
	
	public void getMostRecentsContacts(WebDriver driver) {
		if (driver != null) {
			List<WebElement> recentsContacts = driver.findElements(By.cssSelector("li._42fz div._55lr"));
			for (int i = 0; i < recentsContacts.size(); i++) {
				//String name = recentsContacts.get(i)
				System.out.println(recentsContacts.get(i).getText());
			}
			driver.close();
		}
		else {
			System.out.println("Não foi possível buscar os contatos! :(");
		}
	}
	
	public void getFriendsList(WebDriver driver) throws IOException {
		driver.findElement(By.cssSelector("div[class='_1k67 _4q39'] a._2s25")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("span[class='_gs6']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> friendsList = driver.findElements(By.cssSelector("ul[class='uiList _262m _4kg'] li._698 div[class='fsl fwb fcb']"));
		this.putFileFriendsList(friendsList);
	}
	
	public void putFileFriendsList(List<WebElement> friendsList) throws IOException {
		//FileWriter file = new FileWriter("/Users/diego/Documents/friendsList.txt");
		FileWriter file = new FileWriter("/home/dmancini/Documents/friendsList.txt");
	    PrintWriter putFile= new PrintWriter(file);
	 
		for (int i = 0; i < friendsList.size(); i++) {
			putFile.printf(friendsList.get(i).getText()+"\n");
		}
		
		file.close();
	}

}
