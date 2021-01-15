
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week12
    
    This class is InvalidArguementsException.
*/

public class InvalidArgumentsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentsException() {
		super("Incorrect number of arguments supplied to command.");
	}

	public InvalidArgumentsException(String message) {
		super(message);
	}
}
