package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private String[] pointsToText = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {

        this.player1Name = player1Name;
        this.player2Name = player2Name;

    }

    public void wonPoint(String playerName) {

        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;

    }

    public String getScore() {

        if (player1Score==player2Score) {

           return getScoreTie();

        } else if (player1Score>=4 || player2Score>=4) {

            return getScoreWinOrAdvantage();

        } else {

            return getScoreNoWinTieAdvantage();
            
        }
    }

    private String getScoreTie() {

        if (this.player1Score <= 3) {
            return pointsToText[player1Score] + "-All";
        } else {
            return "Deuce";
        }
    }

    private String getScoreWinOrAdvantage() {
        int scoreDifferenceForPlayer1 = this.player1Score - this.player2Score;

        if (scoreDifferenceForPlayer1 == 1) {
            return "Advantage player1";
        } else if (scoreDifferenceForPlayer1 == -1) {
            return "Advantage player2";
        } else if (scoreDifferenceForPlayer1 > 1) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String getScoreNoWinTieAdvantage() {

        return pointsToText[this.player1Score] + "-" + pointsToText[this.player2Score];
    }
}