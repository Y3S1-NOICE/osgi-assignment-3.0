package userstore.model;

public class User {
	private int userId;
	private String nic;
	private String firstName;
	private String lastName;
	private String password;
	
	public User() {
	}
	public User(int userId, String nic, String firstName, String lastName, String password) {
		this.userId = userId;
		this.nic = nic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	public User(String nic, String firstName, String lastName, String password) {
		this.nic = nic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
