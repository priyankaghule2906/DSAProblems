package dsa2026.twopointer;

public class DryRun {

    public static void main(String[] args) {
        DryRun dryRun = new DryRun();
        //dryRun.slide("barfoothefoobarman", {"foo", "bar"});
    }

    private void slide(String s, String[] words){
        int wordLen = words[0].length();
        for(int i=0;i<wordLen;i++){
            int start = i;
            int end = i;

            while(end + wordLen <= s.length()){
                System.out.println(s.substring(end, end+wordLen));
                end += wordLen;
            }

        }
    }
}
