package ConsoleGame;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static ConsoleGame.Model.*;
import static ConsoleGame.View.*;

public class Controller {
    static Long gt;
    static int step = 0;
    static String h;
    static ArrayList<Integer> uc = new ArrayList<Integer>();
    static ArrayList<Integer> bc = new ArrayList<Integer>();
    //Создание базы данных игры (состоит из трех таблиц с общим id = step)
    static HashMap<Integer, ArrayList<Integer>> game = new HashMap<Integer, ArrayList<Integer>>();
    static HashMap<Integer, ArrayList<Integer>> bullsAndCows = new HashMap<Integer, ArrayList<Integer>>();
    static HashMap<Integer, Long> gameTime = new HashMap<Integer, Long>();

    public static void main(String[] args) throws IOException {
        //Генерация случайного числа из четырех различных цифр (сс = computer chislo)
        ArrayList<Integer> cc = new RandomGenerator().RandomGenerator();
        printMessage1("Загадано четырехзначное число с неповторяющимися цифрами ");
        for (Integer a : cc) {
            System.out.print(a); //для отладки выводится загаданное число, в дальнейшем заменить на "****"
        }
        printMessage2(", угадайте его. Для выхода наберите: exit");
        //Создание переменной фиксации времени начала игры
        Date gameStart = new Date();
        //Получение числа игрока с консоли (uc = user chislo)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((h = br.readLine())!= null) {
            ++step;
            uc.clear(); //очистка массива числа игрока
            bc.clear(); //очистка массива быков и коров
            //Считывание очередной строки из консоли
            if(h.equalsIgnoreCase("exit")){
                printMessage1("Игра окончена за ");
                step--;
                break;
            }
            char[] cuc = h.toCharArray();


            try {
                Integer.parseInt(h);

                if(cuc.length != 4){
                    throw new Exception();
                }

                for(char c : cuc){
                    Integer ci = Character.getNumericValue(c);
                    if(uc.contains(ci)) throw new Exception();
                    uc.add(ci);
                }
            } catch (Exception e) {
                printMessage2("Введите четырехзначное число с неповторяющимися числами!");
                continue;
            }



            //Сравнение случайного и пользовательского чисел с подсчетом быков (*B) и коров (*C)
            bc = bullsAndCows(uc,cc);
            //Вывод на экран результата сравнения чисел в формате "**** (число) *В *С"
            printMessage1(step + " попытка: ");
            for (Integer b : uc) {
                System.out.print(b);
            }
            printBC();
            //Занесение полученных данных в базу данных игры
            game.put(step, uc); //новый ход, игрок ввел новое число
            bullsAndCows.put(step, bc); //вычислили число быков и коров
            //Если угадано четыре цифры (4 быка) - игра окончена
            if(getBulls() == 4) {
                printMessage1("Вы угадали за ");
                break;
            }
        }
        br.close();
        //Вычисление времени игры
        Date gameStop = new Date();
        gt = gameStop.getTime() - gameStart.getTime();
        gameTime.put(step, gt);                 //зафиксировали время игры в базе данных
        long gth = gt/(60*60*1000);
        long gtm = gt/(60*1000) - gth*60;
        long gts = gt/1000 - gtm*60 - gth*60*60;
        printMessage2(gth + ":" + gtm + ":" + gts + " и " + step + " попыток");
    }
}