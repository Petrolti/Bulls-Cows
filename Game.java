package ConsoleGame;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    public static void main(String[] args) throws IOException {
        //Генерация случайного числа из четырех различных цифр (сс = computer chislo)
        ArrayList<Integer> cc = new ArrayList<Integer>();
        final Random random = new Random();
        while (cc.size() < 4) {
            int n = random.nextInt(10);
            if (!cc.contains(n)) {
                cc.add(n);
            }
        }
        System.out.print("Загадано четырехзначное число ");
        for (Integer a : cc) {
            System.out.print(a);
        }
        System.out.println(", угадайте его. Для выхода наберите: exit");
        //Создание переменной фиксации времени игры
        Long gt;
        Date gameStart = new Date();
        //Создание базы данных игры (состоит из трех таблиц с общим id = step)
        HashMap<Integer, ArrayList<Integer>> game = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> bullsAndCows = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, Long> gameTime = new HashMap<Integer, Long>();
        //Получение числа игрока с консоли (uc = user chislo)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int step = 0;
        String h;
        while ((h = br.readLine())!= null) {
            ++step;
            int bulls = 0;
            int cows = 0;
            //Считывание очередной строки из консоли
            if(h.equalsIgnoreCase("exit")){System.out.print("Игра окончена за "); step--; break;}
            ArrayList<Integer> uc = new ArrayList<Integer>();
            char[] cuc = h.toCharArray();
            for(char c : cuc){
                Integer ci = Character.getNumericValue(c);
                uc.add(ci);
            }
            //Сравнение случайного и пользовательского чисел с подсчетом быков (*B) и коров (*C)
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (uc.get(i).equals(cc.get(j))) {
                        if (i == j) bulls++; else cows++;
                    }
                }
            }
            ArrayList<Integer> bc = new ArrayList<Integer>();
            bc.add(bulls);
            bc.add(cows);
            //Вывод на экран результата сравнения чисел в формате "**** (число) *В *С"
            System.out.print(step + " попытка: ");
            for (Integer b : uc) {
                System.out.print(b);
            }
            System.out.println(" " + bulls + "B " + cows + "C");
            //Занесение полученных данных в базу данных игры
            game.put(step, uc); //новый ход, игрок ввел новое число
            bullsAndCows.put(step, bc); //вычислили число быков и коров
            //Если угадано четыре цифры (4 быка) - игра окончена
            if(bulls == 4) {
                System.out.print("Вы угадали за ");
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
        System.out.println(gth + ":" + gtm + ":" + gts + " и " + step + " попыток");
    }
}