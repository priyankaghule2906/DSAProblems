package test;

import java.util.*;

public class EncryptDecrypt {

    Map<Character, String> map = new HashMap<>();
    EncryptDecrypt(){
        map.put('A', "01");
        map.put('B', "02");
        map.put('C', "03");
        map.put('D', "04");
        map.put('Z', "26");
    }


    public String encrypt(String str){
        StringBuilder stringBuilder = new StringBuilder();
        for(char ch : str.toCharArray()){
            stringBuilder = stringBuilder.append(map.get(ch));
        }
        return stringBuilder.toString();
    }


    public String decrypt(String str){

        Set<Map.Entry<Character, String>> entries = map.entrySet();

        Set<String> set = new LinkedHashSet<>();
        for(int i=0;i<str.length()-1;i++){
            StringBuilder  sb = new StringBuilder();
            StringBuilder append = sb.append(str.charAt(i)).append(str.charAt(i + 1));
            set.add(append.toString());

        }
        StringBuilder finalDecryptedStr = new StringBuilder();
        for(Map.Entry<Character, String> entry : map.entrySet()){
            // 010203
            Character key = entry.getKey();
            String value = entry.getValue();
            if(set.contains(value)){
                finalDecryptedStr.append(key);
            }

        }

        return finalDecryptedStr.toString();
    }
}
