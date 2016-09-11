package strings;

public class KMP {

	/*
	* 1. search for pattern in a text
	*/
	public static int[] kmp(String text, String pattern) {
		StringBuilder cat = new StringBuilder(pattern);
		cat.append("#"); // Any charachter that does not belong to the String alphabet
		cat.append(text);

		int n = text.length();
		int m = pattern.length();
		int total = n + m + 1;

		int[] res = new int[total];
		res[m] = -1;

		int i = 1, j = 0;
		while(i < total){
			if(cat.charAt(i) == cat.charAt(j)) res[i++] = ++j;
			else if(j == 0)	i++;
			else j = res[j - 1];
			if(j == m) j = res[j - 1]; // Match found at index (i - res[i - 1] - m - 1)
		}

		// After res[m](-1), res[i] is the length of the longest suffix in the text substring from (0, (i - m - 1)) matching prefix in the pattern
		return res;
	}

	/*
	* 2. calcuate KMP values of a String
	* useful in getting the smallest period (P) of a String with length (n)
	* P = n - kmp[kmp.length - 1]
	* P = (n % p == 0) ? P : n
	*/
	public static int kmp(String text) {
		int[] res = new int[text.length()];

		int i = 1, j = 0;
		while(i < text.length()){
			if(text.charAt(i) == text.charAt(j)) res[i++] = ++j;
			else if(j == 0)	i++;
			else j = res[j - 1];
		}

		// res[i] is the length of the longest suffix in the text substring from (0, i) matching prefix in itself
		return res[text.length() - 1];
	}
}
