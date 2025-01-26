import sheffield.*;

public class History {

    private int[][] winners;
    
    public History() {
        winners = new int[Team.count()][Team.count()];
    }

    public void archive(String fileName) {
        EasyWriter output = new EasyWriter(fileName);
        for(int r=0; r<Team.count(); r++) 
            for(int c=0; c<Team.count(); c++) {
               output.print(winners[r][c]*2);
               if  (  c < Team.count()-1  )
                    output.print(",");
               else  output.println();
            }
        output.println();
    }

    public Team[] getTeamsInOrder() {
        //Replace the following line for task 2    
		int[] totalWins = new int[Team.count()];
		for(int row = 0; row < Team.count(); row++) {
			totalWins[row] = 0;
			for(int col = 0; col < Team.count(); col++) {
				totalWins[row] += winners[row][col];
			}
		}
		
		Team[] teamNames = Team.values();
		
		// selecion sorting
		for(int index = 0; index < Team.count(); index++) {
			int largestNumber = -1;
			int indexOfLargestNumber = Team.count()+1;
			for(int indexOfCandidateLargestNumber = index+1; indexOfCandidateLargestNumber < Team.count(); indexOfCandidateLargestNumber++) {
				if (largestNumber < totalWins[indexOfCandidateLargestNumber] && totalWins[indexOfCandidateLargestNumber] != totalWins[index]) {
					largestNumber = totalWins[indexOfCandidateLargestNumber];
					indexOfLargestNumber = indexOfCandidateLargestNumber;
				}
			}
			
			if (totalWins[index]<largestNumber) {
				totalWins[indexOfLargestNumber] = totalWins[index];
				totalWins[index] = largestNumber;
				
				Team temp = teamNames[indexOfLargestNumber];
				teamNames[indexOfLargestNumber] = teamNames[index];
				teamNames[index] =  temp;
			}
		}
		
        return teamNames;
    }

    public void display() {
        final String COLUMN_BREAK = "  |  ";
        
        //The heading
        System.out.print(" ".repeat(Team.LONGEST_NAME));
        for (Team c : Team.values()) {
           System.out.print(COLUMN_BREAK);        
           System.out.print(c);
        }
        System.out.println(COLUMN_BREAK);

        //The rows
        for (Team r : Team.values())  {
           System.out.print(r);
           for (int c = 0; c<Team.count(); c++)  {
                System.out.print(COLUMN_BREAK); 
                String number = String.valueOf(winners[r.ordinal()][c]);       
                System.out.print(" ".repeat(6-number.length()));
                System.out.print(number);
                System.out.print(" ".repeat(4));
           }
           System.out.println(COLUMN_BREAK);
        }

    }

    public void initializeFrom(String fileName) {
		//Write the body of this method for task 1
		EasyReader reader =  new EasyReader("history.csv");
		for(int[] row:winners) {
			String line = reader.readString();
			String[] rowInStringType = line.split(","); 
			int i  = 0;
			for(String element:rowInStringType) {
				row[i++] = Integer.parseInt(element);
			}
		}
		
		display();
    }

    public void update(Team winner, Team loser) {
        winners[winner.ordinal()][loser.ordinal()]++;      
    }

    public static void main(String[] args) {
        History history = new History();
        history.initializeFrom("history.csv");
        for (Team h : history.getTeamsInOrder())
          System.out.println(h);
    }

}