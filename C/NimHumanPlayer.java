
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week12
    
    This class saves the information of HumanPlayers.
*/

public class NimHumanPlayer extends NimPlayer {

	private static final long serialVersionUID = 1L;

	public NimHumanPlayer() {
		super();
	}

	// Human move.
	public int setStonesTaken(int location, int restStones, int limitTaken) {
		int stonesTaken;
		// check wrong input such as "a","3.3".
		try {
			String number = Nimsys.in.nextLine();
			stonesTaken = Integer.parseInt(number);
		} catch (Exception e) {
			if (restStones > limitTaken) {
				System.out.println();
				System.out.println("Invalid move. You must remove between 1 and " 
							+ limitTaken + " stones.");
				System.out.println();
				System.out.printf(restStones + " stones left:");
				for (int i = Nimsys.ZERO; i <= restStones - Nimsys.ONE; i++) {
					System.out.printf(" *");
				}
				System.out.println('\n' + Nimsys.users[location].getFamilyName() 
								+ "'s turn - remove how many?");
			} else {
				System.out.println();
				System.out.println("Invalid move. You must remove between 1 and " 
							+ restStones + " stones.");
				System.out.println();
				System.out.printf(restStones + " stones left:");
				for (int i = Nimsys.ZERO; i <= restStones - Nimsys.ONE; i++) {
					System.out.printf(" *");
				}
				System.out.println('\n' + Nimsys.users[location].getFamilyName() 
								+ "'s turn - remove how many?");
			}
			return setStonesTaken(location, restStones, limitTaken);
		}
		return stonesTaken;
	}

	public int removeStones(int restStones, int stonesTaken) {
		return restStones - stonesTaken;
	}
}
