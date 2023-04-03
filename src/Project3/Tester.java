package Project3;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserAuthenticate thisUser = new UserAuthenticate("tevinH", "password1");
		if(thisUser.isValid) {
			System.out.println("Valid");
		}
		else {
			System.out.println("Invalid");
		}
		
		
		new LoginWindow();
		

	}
	

}
