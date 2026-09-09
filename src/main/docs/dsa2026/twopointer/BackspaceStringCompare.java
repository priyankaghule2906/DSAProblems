package dsa2026.twopointer;

public class BackspaceStringCompare {

    public static void main(String[] args) {
        System.out.println(backspaceCompare("y#fo##f","y#f#o##f"));
    }
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;

        while (i >= 0 || j >= 0) {
            i = nextValidCharIndex(s, i);
            j = nextValidCharIndex(t, j);

            // If one string is exhausted and the other isn't, they differ
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) return false;
            } else if (i >= 0 || j >= 0) {
                return false;
            }

            i--;
            j--;
        }

        return true;
    }

    private static int nextValidCharIndex(String str, int index) {
        int skip = 0;
        while (index >= 0) {
            if (str.charAt(index) == '#') {
                skip++;
                index--;
            } else if (skip > 0) {
                skip--;
                index--;
            } else {
                break;
            }
        }
        return index;
    }
}
