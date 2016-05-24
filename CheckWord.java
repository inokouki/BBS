package kadai4.option;

public class CheckWord {

	public static boolean checkNgWord (String word) {

		if (word.matches(".*" + "死ね" + ".*")) {
			return false;
		}

		return true;
	}
}