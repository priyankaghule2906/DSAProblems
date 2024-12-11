package strings;

import java.util.Arrays;

public class IsoMorphicString {


    public boolean isIsomorphic(String s, String t) {

        if(s.length()!=t.length()) return false;

        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        Arrays.fill(mapST, -1);
        Arrays.fill(mapTS, -1);

        for (int i=0;i<s.length();i++){
            char chs = s.charAt(i);
            char cht = t.charAt(i);

            if(mapST[chs] ==-1 && mapTS[cht]==-1){
                mapST[chs] = cht;
                mapTS[cht] = chs;
            } else {
                if(mapST[chs] != cht && mapTS[cht] != chs) {
                    return false;
                }
            }
        }
        return true;
    }
}
