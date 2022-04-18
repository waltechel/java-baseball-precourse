package baseball;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {

	private static String input;
	private static String answer;
	

	public static void main(String[] args) {
		gameInit();
		playgame();
	}

	private static void playgame() {
		while (!isOver(input, answer) && isInputOkay(getInput())) {
			int ball = getBall(input, answer);
			int strike = getStrike(input, answer);
			print(ball, strike);
		}
	}

	private static void gameInit() {
		answer = getAnswer();
		// System.out.println(answer);
		input = "input";
	}

	private static boolean isInputOkay(String input) throws IllegalArgumentException {

		if (overthan1000(input)) {
			throw new IllegalArgumentException();
		}

		try {
			int n = Integer.parseInt(input);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return true;
	}

	private static int getStrike(String input, String answer) {
		int ret = 0;
		ret = calculate(input.charAt(0), answer.charAt(0), ret);
		ret = calculate(input.charAt(1), answer.charAt(1), ret);
		ret = calculate(input.charAt(2), answer.charAt(2), ret);
		return ret;
	}

	private static int calculate(char a, char b, int ret) {
		return a == b ? ret + 1 : ret;
	}

	private static int getBall(String input, String answer) {
		int ret = 0;
		ret = calculate(input.charAt(0), answer.charAt(1), ret);
		ret = calculate(input.charAt(0), answer.charAt(2), ret);
		ret = calculate(input.charAt(1), answer.charAt(0), ret);
		ret = calculate(input.charAt(1), answer.charAt(2), ret);
		ret = calculate(input.charAt(2), answer.charAt(0), ret);
		ret = calculate(input.charAt(2), answer.charAt(1), ret);
		return ret;
	}

	private static void print(int ball, int strike) {
		if (ball == 0 && strike == 0) {
			System.out.println("낫싱");
		}
		System.out.println((ball != 0 ? (ball + "볼 ") : "") + ((strike != 0 && strike < 3) ? (strike + "스트라이크") : ""));
	}

	private static boolean isOver(String input, String answer) {
		return input.equals(answer) && checkRegame();
	}

	private static boolean checkRegame() {
		System.out.println("3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
		int input = Integer.parseInt(Console.readLine());
		if (input == 1) {
			answer = getAnswer();
		}
		return input == 2;
	}

	private static String getAnswer() {
		String ret = getCandi();
		while (!isAnswerOkay(ret)) {
			ret = getCandi();
		}
		return ret;
	}

	private static boolean isAnswerOkay(String candi) {
		return noZeroInCandi(candi) && noUnder100(candi) && notDuplicatedValue(candi);
	}

	private static boolean notDuplicatedValue(String candi) {
		int ret = 0;
		ret = calculate(candi.charAt(0), candi.charAt(1), ret);
		ret = calculate(candi.charAt(1), candi.charAt(2), ret);
		ret = calculate(candi.charAt(0), candi.charAt(2), ret);
		return ret == 0;
	}

	private static boolean overthan1000(String candi) {
		if (Integer.parseInt(candi) >= 1000) {
			return true;
		}
		if (candi.length() > 3) {
			return true;
		}
		return false;
	}

	private static boolean noUnder100(String candi) {
		if (Integer.parseInt(candi) < 100) {
			return false;
		}
		if (candi.length() != 3) {
			return false;
		}
		return true;
	}

	private static boolean noZeroInCandi(String candi) {
		if (candi.contains("0")) {
			return false;
		}
		return true;
	}

	private static String getCandi() {
		String candi = getRandomValue();
		return candi;
	}

	private static String getRandomValue() {
		List<Integer> list = Randoms.pickUniqueNumbersInRange(1, 9, 3);
		while (list.size() != 3) {
			list = Randoms.pickUniqueNumbersInRange(1, 9, 3);
		}
		String candi = "" + list.get(0) + list.get(1) + list.get(2);
		return candi;
	}

	private static String getInput() {
		System.out.print("숫자를 입력해주세요 : ");
		return input = Console.readLine();
	}

	public static String getNothingValueisNotChanged() {
		String candi = getRandomValue();
		while (!isNothing(candi, answer)) {
			candi = getRandomValue();
		}
		return candi;
	}

	private static boolean isNothing(String candi, String answer) {
		return getBall(candi, answer) == 0 && getStrike(candi, answer) == 0;
	}

	public static String getOneBallOneStrikeValueisNotChanged() {
		String candi = getRandomValue();
		while (!isOneBallOneStrike(candi)) {
			candi = getRandomValue();
		}
		return candi;
	}

	private static boolean isOneBallOneStrike(String candi) {
		return getBall(candi, answer) == 1 && getStrike(candi, answer) == 1;
	}

	public static String getAnswerValueisNotChanged() {
		return answer;
	}

}
