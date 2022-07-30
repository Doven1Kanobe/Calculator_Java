import java.util.*;

public class Main {
    private static NavigableMap<Integer, String> units;

    public static boolean romanExpression(String value) {
        boolean valueRoman = true;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != 'X' && value.charAt(i) != 'V' && value.charAt(i) != 'I') {
                valueRoman = false;
                break;
            }
        }
        return valueRoman;
    }

    public static boolean numRoman(String fisValue, String secValue) {
        try {
            if (romanExpression(fisValue) != romanExpression(secValue)) {
                throw new Exception("Используются одновременно разные системы счисления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        return (romanExpression(fisValue) && romanExpression(secValue));
    }

    public static int calculator(int fisValue, int secValue, String operation, boolean roman) {
        int result = 0;
        try {
            if (fisValue > 10 || secValue > 10) throw new Exception("Одно или два числа больше 10");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        try {
            switch (operation) {
                case "+":
                    result = fisValue + secValue;
                    break;
                case "-":
                    result = fisValue - secValue;
                    break;
                case "*":
                    result = fisValue * secValue;
                    break;
                case "/":
                    result = fisValue / secValue;
                    break;
                default:
                    throw new Exception("Неверный оператор");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        try {
            if (result <= 0 && roman == true) throw new Exception("В римской системе нет отрицательных чисел");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        return result;
    }

    public static void main(String[] args) {
        NavigableMap<Integer, String> nums = new TreeMap<Integer, String>();
        nums.put(100, "C");
        nums.put(90, "XC");
        nums.put(50, "C");
        nums.put(40, "XL");
        nums.put(10, "X");
        nums.put(9, "IX");
        nums.put(5, "V");
        nums.put(4, "IV");
        nums.put(1, "I");
        units = Collections.unmodifiableNavigableMap(nums);

        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        var expression = in.nextLine();
        try {
            if((expression.split(" ")).length > 3) throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        var fisValue = expression.split(" ")[0];
        var operation = expression.split(" ")[1];
        var secValue = expression.split(" ")[2];

        if (numRoman(fisValue, secValue)) {
            System.out.println(Converter.arabicToRoman(calculator(Converter.romanToArabic(fisValue, nums), Converter.romanToArabic(secValue, nums), operation, true), units));
        }
        else {
            try {
                System.out.println(calculator(Integer.parseInt(fisValue), Integer.parseInt(secValue), operation, false));
            }
            catch (Exception ex){
                System.out.println("Выражение введено не верно");
                System.exit(1);
            }
        }

    }
}