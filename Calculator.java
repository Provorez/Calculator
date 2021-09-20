package Task1.Calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        calculate(string);
    }

    public static void calculate(String string) {
        String operation = string.replace(" ", "");


        if (isCorrectString(operation)) {

            List<String> listOfSymbols = new LinkedList<>(Arrays.asList(operation.split("[0-9]+")));
            listOfSymbols.remove(0); //почему то вставляется пустая строка, не нашел как этого избежать, поэтому удаляю так
            List<String> listOfNumbers = new LinkedList<>(Arrays.asList(operation.split("[+/*-]")));
            int result = Integer.parseInt(listOfNumbers.get(0));
            int temp = 0;

            for (int i = 0; i < listOfSymbols.size(); i++) {
                if (listOfSymbols.get(i).equals("*")) {
                    temp = Integer.parseInt(listOfNumbers.get(i)) * Integer.parseInt(listOfNumbers.get(i + 1));
                    listOfNumbers.set(i + 1, Integer.toString(temp));

                    listOfNumbers.remove(i);
                    listOfSymbols.remove(i);
                    i = i - 1;
                    temp = 0;
                } else if (listOfSymbols.get(i).equals("/")) {
                    temp = Integer.parseInt(listOfNumbers.get(i)) / Integer.parseInt(listOfNumbers.get(i + 1));
                    listOfNumbers.set(i + 1, Integer.toString(temp));

                    listOfNumbers.remove(i);
                    listOfSymbols.remove(i);
                    i = i - 1;
                    temp = 0;
                }

            }
            for (int i = 0; i < listOfSymbols.size(); i++) {
                if (listOfSymbols.get(i).equals("+")) {
                    result = result + Integer.parseInt(listOfNumbers.get(i + 1));
                } else if (listOfSymbols.get(i).equals("-")) {
                    result = result - Integer.parseInt(listOfNumbers.get(i + 1));
                }
            }
            System.out.println(result);

        } else System.out.println("Введено неверное выражение");

    }

    public static boolean isCorrectString(String string) {
        Pattern pattern = Pattern.compile("(\\d([+*-/]))+\\d$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

}
