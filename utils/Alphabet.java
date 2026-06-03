package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alphabet {

    public static class Dictionaries {
        public Map<Integer, String> numToChar;
        public Map<String, Integer> charToNums;

        public Dictionaries(Map<Integer, String> numToChar, Map<String, Integer> charToNums) {
            this.numToChar = numToChar;
            this.charToNums = charToNums;
        }
    }

    public static Dictionaries dictionaries() {
        Map<Integer, String> numToChar = new HashMap<>();
        numToChar.put(0, "A");
        numToChar.put(1, "B");
        numToChar.put(2, "C");
        numToChar.put(3, "D");
        numToChar.put(4, "E");
        numToChar.put(5, "F");
        numToChar.put(6, "G");
        numToChar.put(7, "H");
        numToChar.put(8, "I");
        numToChar.put(9, "J");
        numToChar.put(10, "K");
        numToChar.put(11, "L");
        numToChar.put(12, "M");
        numToChar.put(13, "N");
        numToChar.put(14, "O");
        numToChar.put(15, "P");
        numToChar.put(16, "Q");
        numToChar.put(17, "R");
        numToChar.put(18, "S");
        numToChar.put(19, "T");
        numToChar.put(20, "U");
        numToChar.put(21, "V");
        numToChar.put(22, "W");
        numToChar.put(23, "X");
        numToChar.put(24, "Y");
        numToChar.put(25, "Z");
        numToChar.put(26, ".");
        numToChar.put(27, ",");
        numToChar.put(28, " ");

        Map<String, Integer> charToNums = new HashMap<>();
        for (Map.Entry<Integer, String> entry : numToChar.entrySet()) {
            charToNums.put(entry.getValue(), entry.getKey());
        }

        return new Dictionaries(numToChar, charToNums);
    }

    public static int[][] segment(String text, int n) {
        text = text.toUpperCase();
        Dictionaries dicts = dictionaries();
        Map<String, Integer> chars = dicts.charToNums;
        
        ArrayList<Integer> textNums = new ArrayList<>();
        for(int x = 0; x< text.length(); x++){
            char txtChar  = text.charAt(x);
            String key = String.valueOf(txtChar);

            if (chars.containsKey(key)){
                textNums.add(chars.get(key));
            }
        }

        int total = textNums.size();
        int rows = total / n;

        if (total % n != 0){
            rows++;
        }

        int[][] segmentedList = new int[rows][n];


        int idx = 0;
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<n; j++){
                if (idx<total){
                    segmentedList[i][j] = textNums.get(idx);
                    idx++;
                } else{
                    //28 = ' '
                    segmentedList[i][j] = 28;
                }
            }
        }

        return segmentedList;
    }

    public static String restructureText(int[][] list) {
        Dictionaries dicts = dictionaries();
        Map<Integer, String> numsMap = dicts.numToChar;
        StringBuilder charsNew = new StringBuilder();
        for (int[] ls : list) {
            for (Integer num : ls) {
                charsNew.append(numsMap.get(num));
            }
        }
        return charsNew.toString();
    }
}
