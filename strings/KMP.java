package strings;

public class KMP {

	public static int[] kmp(String text, String pattern) {
		StringBuilder cat = new StringBuilder(pattern);
		cat.append("#"); // Any charachter that does not belong to the String alphabet
		cat.append(text);

		int n = text.length();
		int m = pattern.length();

		int[] res = new int[cat.length()];
		res[m] = -1;

		int i = 1, j = 0;
		while(i < cat.length()){
			if(cat.charAt(i) == cat.charAt(j)) res[i++] = ++j;
			else if(j == 0)	i++;
			else j = res[j - 1];
			if(j == m) j = res[j - 1]; // Match found at index (i - res[i - 1] - m - 1)
		}

		// After res[m](-1), res[i] is the length of the longest suffix in the text substring from (0, (i - m - 1)) matching prefix in the pattern
		return res;
	}
}
