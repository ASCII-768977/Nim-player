
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week12
    
    This class saves the information of AIplayers.
*/

public class NimAIPlayer extends NimPlayer {

	private static final long serialVersionUID = 1L;

	public NimAIPlayer() {
		super();
	}

	// AI move.
	public int setStonesTaken(int location, int restStones, int limitTaken) {
		int stonesTaken;
		/*
		 * if restStones is greater than limitTaken. then random 0 to
		 * limitTaken.
		 */
		if (restStones > limitTaken) {
			stonesTaken = (int) (Math.random() * limitTaken);
			// However, AI cannot take 0 stones. so plus 1.
			if (stonesTaken == Nimsys.ZERO) {
				stonesTaken = stonesTaken + Nimsys.ONE;
			}
		} else {
			/*
			 * if restStones is less than limitTaken. if restStones is not equal
			 * 1. then take restStones - 1.
			 */
			if (restStones != Nimsys.ONE) {
				stonesTaken = restStones - Nimsys.ONE;
			} else {
				// there are only 1 stone left. AI can only takes 1.
				stonesTaken = Nimsys.ONE;
			}
		}
		return stonesTaken;
	}

	public int removeStones(int restStones, int stonesTaken) {
		return restStones - stonesTaken;
	}

}
