package baseball;

import java.util.Random;
import java.util.Scanner;

public class Application {

	private static String input;
	private static String answer;
	private static Scanner sc;

	public static void main(String[] args) {
		// TODO: 프로그램 구현
		sc = new Scanner(System.in);
		answer = getAnswer();
		input = "input";
		System.out.println(answer);

		while (!isOver(input, answer) && isInputOkay(getInput())) {
			int ball = getBall(input, answer);
			int strike = getStrike(input, answer);
			print(ball, strike);
		}
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
		if (input.charAt(0) == answer.charAt(0)) {
			ret++;
		}
		if (input.charAt(1) == answer.charAt(1)) {
			ret++;
		}
		if (input.charAt(2) == answer.charAt(2)) {
			ret++;
		}
		return ret;
	}

	private static int getBall(String input, String answer) {
		int ret = 0;
		if (input.charAt(0) == answer.charAt(1)) {
			ret++;
		}
		if (input.charAt(0) == answer.charAt(2)) {
			ret++;
		}
		if (input.charAt(1) == answer.charAt(0)) {
			ret++;
		}
		if (input.charAt(1) == answer.charAt(2)) {
			ret++;
		}
		if (input.charAt(2) == answer.charAt(0)) {
			ret++;
		}
		if (input.charAt(2) == answer.charAt(1)) {
			ret++;
		}
		return ret;
	}

	private static void print(int ball, int strike) {
		if (ball == 0 && strike != 0 && strike < 3) {
			System.out.println(strike + "스트라이크");
		}
		if (ball == 0 && strike == 0) {
			System.out.println("낫싱");
		}
		if (ball != 0 && strike == 0) {
			System.out.println(ball + "볼");
		}
		if (ball != 0 && strike != 0) {
			System.out.println(ball + "볼 " + strike + "스트라이크");
		}
	}

	private static boolean isOver(String input, String answer) {
		return input.equals(answer) && checkRegame();
	}

	private static boolean checkRegame() {
		System.out.println("3스트라이크");
		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
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
		if (candi.contains("0")) {
			return false;
		}
		if (Integer.parseInt(candi) < 100) {
			return false;
		}
		if (candi.length() != 3) {
			return false;
		}
		if (candi.charAt(0) == candi.charAt(1)) {
			return false;
		}
		if (candi.charAt(1) == candi.charAt(2)) {
			return false;
		}
		if (candi.charAt(0) == candi.charAt(2)) {
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
