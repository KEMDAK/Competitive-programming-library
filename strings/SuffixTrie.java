package strings;

public class SuffixTrie {

	static final int R = 26;				// Alphabet size
	static final char baseChar = 'a';   // The character with index 0 in the Alphabet (lowercase letters)

	static class Node { Node[] next = new Node[R]; int val = -1; }

	Node root = new Node();

	void put(String s, int idx)		// O(n) where n = |s|. Can be implemented recursively
	{
		Node cur = root;
		for(char c: s.toCharArray())
		{
			Node nxt = cur.next[c - baseChar];
			if(nxt == null)
				nxt = cur.next[c - baseChar] = new Node();
			cur = nxt;
		}
		cur.val = idx;
	}

	int search(String s)
	{
		Node cur = root;
		for(char c: s.toCharArray())
		{
			Node nxt = cur.next[c - baseChar];
			if(nxt == null)
				return -1;
			cur = nxt;
		}
		return cur.val;
	}
}
