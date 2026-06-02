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

    public static List<List<Integer>> segment(String text, int n) {
        text = text.toUpperCase();
        Dictionaries dicts = dictionaries();
        Map<String, Integer> chars = dicts.charToNums;
        
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            if (chars.containsKey(c)) {
                newText.append(c);
            }
        }

        List<Integer> textNums = new ArrayList<>();
        for (int i = 0; i < newText.length(); i++) {
            textNums.add(chars.get(String.valueOf(newText.charAt(i))));
        }

        List<List<Integer>> segmentedList = new ArrayList<>();
        for (int i = 0; i < textNums.size(); i += n) {
            List<Integer> segment = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i + j < textNums.size()) {
                    segment.add(textNums.get(i + j));
                } else {
                    segment.add(28); // fillvalue=28
                }
            }
            segmentedList.add(segment);
        }
        return segmentedList;
    }

    public static String restructureText(List<List<Integer>> list) {
        Dictionaries dicts = dictionaries();
        Map<Integer, String> numsMap = dicts.numToChar;
        StringBuilder charsNew = new StringBuilder();
        for (List<Integer> ls : list) {
            for (Integer num : ls) {
                charsNew.append(numsMap.get(num));
            }
        }
        return charsNew.toString();
    }
}
