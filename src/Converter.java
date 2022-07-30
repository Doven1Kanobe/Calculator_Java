import java.util.*;

public class Converter {
    public static int romanToArabic(String value, NavigableMap<Integer, String> nums) {
        Set<Map.Entry<Integer, String>> entrySet = nums.entrySet();
        int result = 0;
        for (Map.Entry<Integer, String> pair : entrySet) {
            if (value.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        for (Map.Entry<Integer, String> anPair : entrySet) {
            for (int i = 0; i < value.length(); i++) {
                if ((value.charAt(i) + "").equals(anPair.getValue())) {
                    result += anPair.getKey();
                }
            }
        }
        return result;
    }

    public static StringBuilder arabicToRoman(int value, NavigableMap<Integer, String> units) {
        StringBuilder result = new StringBuilder();
        try {
            if (value >= 400) {
                throw new Exception("Число больше 399");
            } else {
                for (int key : units.descendingKeySet()) {
                    while (value >= key) {
                        value -= key;
                        result.append(units.get(key));
                    }
                }
                return result;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        return null;
    }
}
