import java.util.Objects;

public class TennisGame2 implements TennisGame
{
    public int P1point = 0;
    public int P2point = 0;
    
    public String P1res = "";
    public String P2res = "";
    private PlayerGame2 playerGame21;
    private PlayerGame2 playerGame22;

    public TennisGame2(String player1Name, String player2Name) {
        this.playerGame21 = new PlayerGame2(player1Name);
        this.playerGame22 = new PlayerGame2(player2Name);
    }

    public String getScore(){
        String score = "";
        if (P1point == P2point && P1point < 4)
        {
            if (P1point==0)
                score = "Love";
            if (P1point==1)
                score = "Fifteen";
            if (P1point==2)
                score = "Thirty";
            score += "-All";
        }
        if (P1point==P2point && P1point>=3)
            score = "Deuce";

        if (P1point > 0 && P2point==0)
        {
            if (P1point==1)
                P1res = "Fifteen";
            if (P1point==2)
                P1res = "Thirty";
            if (P1point==3)
                P1res = "Forty";

            P2res = "Love";
            score = P1res + "-" + P2res;
        }
        if (P2point > 0 && P1point==0)
        {
            if (P2point==1)
                P2res = "Fifteen";
            if (P2point==2)
                P2res = "Thirty";
            if (P2point==3)
                P2res = "Forty";
            
            P1res = "Love";
            score = P1res + "-" + P2res;
        }

        if (P1point>P2point && P1point < 4)
        {
            if (P1point==2)
                P1res="Thirty";
            if (P1point==3)
                P1res="Forty";
            if (P2point==1)
                P2res="Fifteen";
            if (P2point==2)
                P2res="Thirty";
            score = P1res + "-" + P2res;
        }
        if (P2point>P1point && P2point < 4)
        {
            if (P2point==2)
                P2res="Thirty";
            if (P2point==3)
                P2res="Forty";
            if (P1point==1)
                P1res="Fifteen";
            if (P1point==2)
                P1res="Thirty";
            score = P1res + "-" + P2res;
        }

        if (P1point > P2point && P2point >= 3)
        {
            score = "Advantage player1";
        }
        
        if (P2point > P1point && P1point >= 3)
        {
            score = "Advantage player2";
        }
        
        if (P1point>=4 && P2point>=0 && (P1point-P2point)>=2)
        {
            score = "Win for player1";
        }
        if (P2point>=4 && P1point>=0 && (P2point-P1point)>=2)
        {
            score = "Win for player2";
        }
        return score;
    }

    public void P1Score(){
        P1point++;
        playerGame21.setPoint(playerGame21.getScore() + 1);
    }
    
    public void P2Score(){
        P2point++;
        playerGame22.setPoint(playerGame22.getScore() + 1);
    }

    public void wonPoint(Player player) {
        if (Objects.equals(player.getName(), "player1")) {
            P1Score();
        } else {
            P2Score();
        }
    }

    static class PlayerGame2 implements Player {
        private final String name;
        private int point;

        PlayerGame2(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int getScore() {
            return point;
        }

        @Override
        public void incrementScore() {

        }

        public void setPoint(int point) {
            this.point = point;
        }
    }
}