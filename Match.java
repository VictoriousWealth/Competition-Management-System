import sheffield.*;

public class Match {
    
    private static EasyReader keyboard = new EasyReader();

    Team one, other;
    History history;

     private Player getPlayerFromOneOfTheTeams() {
        //Replace the next line for task 4
		String scorerName = keyboard.readString("Who scored? ");
		Player scorer = Player.called(scorerName);
			
		if (scorer != null) {
			if (scorer.isIn(one) || scorer.isIn(other)) {
				return scorer;
			} else {
				System.out.println("The scorer must be from " + one + " or " + other);
				return null;
			}
		} else {
			System.out.println("That is not the name of a player from any team");
			return null;
		}
    }

    public Match (Team a, Team b, History h) {
        one = a;
        other = b;
        history = h;
    }

    public Team getWinnerAndUpdateHistory() {
        final int GOAL_SCORE = 10, SNITCH_SCORE = 150;
        //Replace the rest of this method for task 5
        if  (  Math.random() > 0.5  )
            return one;
        else 
            return other;
    }


    public static void main (String[] args)  {
        Match m = new Match(
            Team.valueOf(args[0].toUpperCase()), 
            Team.valueOf(args[1].toUpperCase()), 
            null);
        System.out.println(m.getPlayerFromOneOfTheTeams());
    }

}