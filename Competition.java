public class Competition {

    private History history;
    private Team [] seededTeams;

    public Competition (History h){
        history = h;
        seededTeams = history.getTeamsInOrder();
    }

    public void updateHistoryWithResultOfPlay() {
        Team firstWinner = 
           new Match(seededTeams[0], seededTeams[2], history)
                 .getWinnerAndUpdateHistory();
        Team secondWinner = 
           new Match(seededTeams[1], seededTeams[3], history)
                 .getWinnerAndUpdateHistory();
        Team finalWinner = 
           new Match(firstWinner, secondWinner, history)
                 .getWinnerAndUpdateHistory();

        System.out.println("The winner of the competition is " + finalWinner);
        System.out.println();
        
        history.display();
     }


}