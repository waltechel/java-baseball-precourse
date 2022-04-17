package baseball;

import java.util.Random;
import java.util.Scanner;

public class Application {

	private static String input;
	private static String answer;
	private static Scanner sc;

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
		sc = new Scanner(System.in);
		answer = getAnswer();
		System.out.println(answer);
		input = "input";
	}

	private static boolean isInputOkay(String input) throws IllegalArgumentException {
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
		int input = sc.nextInt();
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
		Random r = new Random();
		String candi = r.nextInt(999) + "";
		return candi;
	}

	private static String getInput() {
		System.out.print("숫자를 입력해주세요 : ");
		return input = sc.next();
	}
}
