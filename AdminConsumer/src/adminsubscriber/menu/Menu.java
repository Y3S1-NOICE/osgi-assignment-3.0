package adminsubscriber.menu;

import java.util.Scanner;

import adminsubscriber.consume.UserServiceConsumer;
import adminsubscriber.util.Helper;
import userstore.service.IUserService;

public class Menu {

	private IUserService userService;
	private Scanner sc;
	
	public Menu(IUserService userService) {
		this.userService = userService;
		this.sc = new Scanner(System.in); 
	}
	
	public int DisplayMenu() {
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>ADMIN PORTAL\n");
		System.out.println("Choose from below");
		System.out.println("\t1. Add user");
		System.out.println("\t2. List Users");
		System.out.println("\t3. Remove user");
		return Helper.takeInput(3, sc);
	}
	
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
		case 3:
			System.out.println("remove user");
			startTemplate();
			break;
		default:
			startTemplate();
		}
	}
}
