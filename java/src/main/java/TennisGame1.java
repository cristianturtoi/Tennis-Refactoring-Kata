import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

class PlayerGame1 implements Player {
    private final String name;
    private int score;

    PlayerGame1(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void incrementScore() {
        score += 1;
    }

}

enum Score {
    LOVE("Love", 0),
    FIFTEEN("Fifteen", 1),
    THIRTY("Thirty", 2),
    FORTY("Forty", 3),
    ADVANTAGE("Advantage", 4),
    WIN("Win", 5),
    OVER("Over", 6);
    private static final Map<Integer, Score> POINTS_TO_TYPE =
            Arrays.stream(values()).collect(Collectors.toMap(Score::getPoint, Function.identity()));

    private final String name;
    private final int point;

    Score(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public static Score of(int score) {
        return POINTS_TO_TYPE.getOrDefault(score, WIN);
    }

    public int getPoint() {
        return point;
    }

    String currentScore() {
        return switch (this) {
            case LOVE, FIFTEEN, THIRTY, FORTY -> name;
            default -> throw new IllegalArgumentException("Cannot handle current score for ScoreType.FINAL");
        };
    }

    String computeEqualityScore() {
        return switch (this) {
            case LOVE, FIFTEEN, THIRTY -> name + "-All";
            default -> "Deuce";
        };
    }
}

public class TennisGame1 implements TennisGame {

    private final Player player1;
    private final Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        player1 = new PlayerGame1(player1Name);
        player2 = new PlayerGame1(player2Name);
    }

    public void wonPoint(Player player) {
//        if (player.getScore() > Score.WIN.getPoint()) {
//            return;
//        }
        if (Objects.equals(player.getName(), new PlayerGame1("player1").getName())) {
            player1.incrementScore();
        } else {
            player2.incrementScore();
        }
    }

    public String getScore() {
        if (player1.getScore() == player2.getScore()) {
            return Score.of(player1.getScore()).computeEqualityScore();
        } else if (player1.getScore() > Score.FORTY.getPoint() || player2.getScore() > Score.FORTY.getPoint()) {
            return computeFinalScore(player1.getScore(), player2.getScore());
        } else {
            return Score.of(player1.getScore()).currentScore() + "-" + Score.of(player2.getScore()).currentScore();
        }
    }

    private String computeFinalScore(int score1, int score2) {
        final Score scoreType1 = Score.of(score1);
        final Score scoreType2 = Score.of(score2);
        int scoreDifference = score1 - score2;
        if (scoreDifference == 1) {
            return "Advantage player1";
        } else if (scoreDifference == -1) {
            return "Advantage player2";
        } else if (scoreDifference < 2) {
            return "Win for player2";
        } else {
            return "Win for player1";
        }
    }
}
