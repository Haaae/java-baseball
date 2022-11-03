package baseball;

import java.util.List;
import java.util.stream.Stream;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    static final int START  = 1;
    static final int EXIT   = 2;
    static final int LENGTH_OF_BASEBALL_NUMBER = 3;
    static final int BALL_COUNT = 1;
    static final int STRIKE_COUNT = 0;

    public static void main(String[] args) {
        int gameStatus = START;
        while (gameStatus == START) {
            try {
                playNumberBaseball();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            gameStatus = askRestartOrExit();
        }
    }

    private static void playNumberBaseball() throws IllegalArgumentException {
        String computer = getComputerNumberInString();
        boolean userGetRightAnswer = false;
        while (userGetRightAnswer == false) {
            String user = getUserAnswerInString();
            List<Integer> scoreOfStrikeAndBall = compareComputerAndUser(computer, user);
            userGetRightAnswer = isRightAnswer(scoreOfStrikeAndBall);
            printResult(scoreOfStrikeAndBall);
        }
    }

    private static String getComputerNumberInString() {
        String computer = "";
        while(computer.length() < LENGTH_OF_BASEBALL_NUMBER) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (computer.contains(String.valueOf(randomNumber))) {
                computer += randomNumber;
            }
        }
        return computer;
    }

    private static String getUserAnswerInString() throws IllegalArgumentException {
        String user = Console.readLine();
        isValidAnswer(user);
        return user;
    }

    private static void isValidAnswer(String answer) throws IllegalArgumentException {
        boolean answerIsValid = isValidValue(answer) && isValidLength(answer) && isValidOfDuplication(answer);
        if (!answerIsValid) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isValidLength(String answer) {
        return answer.length() == LENGTH_OF_BASEBALL_NUMBER;
    }

    private static boolean isValidOfDuplication(String answer) {
        Long distinctCount = Stream.of(answer).distinct().count();
        return answer.length() == distinctCount;
    }

    private static boolean isValidValue(String answer) {
        boolean result = true;
        try {
            Integer.valueOf(answer);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    private static List<Integer> compareComputerAndUser(String computer, String user) {}
    private static boolean isRightAnswer(List<Integer> scoreOfStrikeAndBall) {}
    private static void printResult(List<Integer> scoreOfStrikeAndBall) {}

    private static int askRestartOrExit() {
        int result = EXIT;
        return result;
    }
}
