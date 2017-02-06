package BotFacebook.diego;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class App {
	private static String login;
	private static String password;
	
	public static void main(String[] args) {
		BotCrawler bot = new BotCrawler();
		
		if (args[0] != null && args[1] != null) {
			login = args[0];
			password = args[1];
		}
		
		try {
			WebDriver driver = bot.loginFacebook(login, password);
			bot.getFriendsList(driver);
			bot.getMostRecentsContacts(driver);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}