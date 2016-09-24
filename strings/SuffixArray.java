package strings;

public class SuffixArray {

	int[] SA;      // The Suffix Array
	char[] text;   // The text of the Suffix Array

	int[] LCP;     // LCP[i] stores the LCP between previous suffix "T + SA[i-1]" and current suffix "T + SA[i]"

	/**
	* Suffix Array construction  O(n log(n)).
	* s should has a terminating character (e.g. '$')
	*/
	SuffixArray(char[] s)
	{
		this.text = s;

		int n = s.length, RA[] = new int[n];
		SA = new int[n];

		for(int i = 0; i < n; ++i) { RA[i] = s[i]; SA[i] = i; }

		for(int k = 1; k < n; k <<= 1)
		{
			sort(SA, RA, n, k);
			sort(SA, RA, n, 0);
			int[] tmp = new int[n];

			for(int i = 1, r = 0, s1 = SA[0], s2; i < n; ++i)
			{
				s2 = SA[i];
				tmp[s2] = RA[s1] == RA[s2] && RA[s1 + k] == RA[s2 + k] ? r : ++r;
				s1 = s2;
			}
			for(int i = 0; i < n; ++i)
			RA[i] = tmp[i];

			if(RA[SA[n-1]] == n - 1)
			break;
		}
	}

	void sort(int[] SA, int[] RA, int n, int k)
	{
		int maxi = Math.max(256, n), c[] = new int[maxi];
		for(int i = 0; i < n; ++i)
		c[i + k < n ? RA[i + k] : 0]++;
		for(int i = 0, sum = 0; i < maxi; ++i)
		{
			int t = c[i];
			c[i] = sum;
			sum += t;
		}
		int[] tmp = new int[n];
		for(int i = 0; i < n; ++i)
		{
			int j = SA[i] + k;
			tmp[c[j < n ? RA[j] : 0]++] = SA[i];
		}

		for(int i = 0; i < n; ++i)
		SA[i] = tmp[i];
	}
	/** end of construction */

	/**
	* This function computes the Longest Common Prefix in O(n).
	*/
	void LCP() {
		if(LCP != null)
		return;

		int i, L;
		int n = text.length;
		LCP = new int[n];
		int[] Phi = new int[n];
		int[] PLCP = new int[n];

		Phi[SA[0]] = -1;
		for (i = 1; i < n; i++)
		Phi[SA[i]] = SA[i-1];
		for (i = L = 0; i < n; i++) {
			if (Phi[i] == -1) { PLCP[i] = 0; continue; }
			while (i + L < n && Phi[i] + L < n && text[i + L] == text[Phi[i] + L]) L++;
			PLCP[i] = L;
			L = Math.max(L-1, 0);
		}
		for (i = 1; i < n; i++)
		LCP[i] = PLCP[SA[i]];
	}

	/**
	* This function return an array of the positions of the occurrences of the pattern in the text in O(m log(n)).
	*/
	int[] search(String p){
		char[] pattern = p.toCharArray();

		int l = 0;
		int h = SA.length - 1;
		int left = -1;

		while(h >= l){
			int mid = (l + h) / 2;

			int res = strcmp(text, SA[mid], pattern, 0);

			if(res > 0){
				h = mid - 1;
			}
			else if(res < 0){
				l = mid + 1;
			}
			else{
				left = mid;
				h = mid - 1;
			}
		}

		if(left == -1){
			return new int[0];
		}

		l = 0;
		h = SA.length - 1;
		int right = -1;

		while(h >= l){
			int mid = (l + h) / 2;

			int res = strcmp(text, SA[mid], pattern, 0);

			if(res > 0){
				h = mid - 1;
			}
			else if(res < 0){
				l = mid + 1;
			}
			else{
				right = mid;
				l = mid + 1;
			}
		}

		int[] res = new int[right - left + 1];
		for (int i = left; i <= right; i++) {
			res[i - left] = SA[i];
		}

		return res;
	}

	/**
	* This function compares two strings starting from index i in the a and from index j in b
	*/
	int strcmp(char[] a, int i, char[] b, int j){
		for (int k=0; i+k < a.length && j+k < b.length; k++){
			if (a[i+k] != b[j+k]) return a[i+k] - b[j+k];
		}
		return 0;
	}

	/**
	* This function returns the longest repeated substring in O(n).
	*/
	String LRS() {
		int i, idx = 0, maxLCP = 0;
		int n = text.length;

		for (i = 1; i < n; i++)
		if (LCP[i] > maxLCP) {
			maxLCP = LCP[i];
			idx = i;
		}

		// maxLCP is the length of the LRS
		// returning the String itself
		return new String(text).substring(SA[idx], maxLCP);
	}

	/**
	* This function returns the longest common substring in O(n log n).
	*/
	String LCS(String p) {
		int i, j, maxLCP = 0, idx = 0;

		char[] pattern = p.toCharArray();
		int m = pattern.length;

		char[] text = (new String(this.text) + new String(pattern) + "#").toCharArray();   // append P and '#'
		int n = text.length;

		SuffixArray sa = new SuffixArray(text);
		sa.LCP();

		for (i = 1, maxLCP = -1; i < n; i++)
		if (sa.LCP[i] > maxLCP && owner(sa.SA[i], n, m) != owner(sa.SA[i-1], n, m)) {  // different owner
			maxLCP = sa.LCP[i];
			idx = i;
		}

		// The length of the LCS is maxLCP
		// returning the String itself
		return new String(text).substring(sa.SA[idx], sa.SA[idx] + maxLCP);
	}

	int owner(int idx, int n, int m) { return (idx < n-m-1) ? 1 : 2; }
	/** end of LCS */
}
