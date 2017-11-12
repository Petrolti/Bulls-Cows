package ConsoleGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static ConsoleGame.View.printMessage2;
import static java.lang.Integer.parseInt;

public class Controller {
    private static String stroka;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String ScannerStrok(){
        try {
            stroka = reader.readLine();
        } catch (IOException e) {
            return null;
        }
        return stroka;
    }

    public static ArrayList<Integer> userChislo(String userstroka){
        char[] charArray = userstroka.toCharArray();
        ArrayList<Integer> userchislo = new ArrayList<Integer>();
        try {
            parseInt(userstroka);

            if(charArray.length != 4){
                throw new Exception();
            }

            for(char c : charArray){
                Integer integer = Character.getNumericValue(c);
                if(userchislo.contains(integer)) throw new Exception();
                userchislo.add(integer);
            }
        } catch (Exception e) {
            printMessage2("Введите четырехзначное число с неповторяющимися числами!");
            return null;
        }
        return userchislo;
    }

    public static void closeController(){
        try {
            if(!reader.ready()) reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
