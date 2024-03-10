package adminconsumer.menu;

import java.util.Scanner;

import adminconsumer.consume.UserServiceConsumer;
import adminconsumer.util.Helper;
import userstoreproducer.service.IUserService;

public class Menu {

	private IUserService userService;
	private Scanner sc;
	
	public Menu(IUserService userService) {
		this.userService = userService;
		this.sc = new Scanner(System.in); 
		
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>>ADMIN PORTAL");
	}
	
	/**
	 * Admin main menu method
	 */
	public int DisplayMenu() {
		System.out.println("\nMain menu");
		System.out.println("\t1. Add user");
		System.out.println("\t2. List Users");
		return Helper.takeInput(2, sc);
	}
	
	/**
	 * Will control user inputs and call relavant methods according to user inputs.
	 */
	public void startTemplate() {
		int selection = DisplayMenu();
		UserServiceConsumer consumer = new UserServiceConsumer(userService);
		
		switch(selection) {
		case 1:
			consumer.addUser();
			startTemplate();
			break;
		case 2: 
			consumer.getUsers();
			startTemplate();
			break;
		default:
			startTemplate();
		}
	}
}
