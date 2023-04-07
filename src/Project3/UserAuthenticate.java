package Project3;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UserAuthenticate {
	
	/**
	 * @param isValid
	 */
	protected boolean isValid = false;
	
	
	public UserAuthenticate(String username, String password) {
		super();
		this.isValid = authenticateUser(username, password);
	}

	public boolean authenticateUser(String username, String password) {
		boolean state = false;
		Scanner userFile;
		try {
			userFile = new Scanner(new File("./users.dat"));
			while(userFile.hasNext()) {
				String [] userInfo =  userFile.nextLine().split(" ");
				if(userInfo[2].equals(username) && userInfo[3].equals(password)) {
					state = true;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return state;

	}
	

}
