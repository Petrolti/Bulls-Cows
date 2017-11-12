package ConsoleGame;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

import static ConsoleGame.BullsCowsCalculator.*;
import static ConsoleGame.View.*;
import static ConsoleGame.Controller.*;

public class Model {
    static Long gettime;
    static int step = 0;
    static int sizechislo = 4;
    static String stroka;
    static ArrayList<Integer> user = new ArrayList<Integer>();
    static ArrayList<Integer> comp = new ArrayList<Integer>();
    //Создание базы данных игры (состоит из трех таблиц с общим id = step)
    static HashMap<Integer, ArrayList<Integer>> game = new HashMap<Integer, ArrayList<Integer>>();
    static HashMap<Integer, ArrayList<Integer>> bullsAndCows = new HashMap<Integer, ArrayList<Integer>>();
    static HashMap<Integer, Long> gameTime = new HashMap<Integer, Long>();

    public static void main(String[] args) throws IOException {
        //Генерация случайного числа из четырех различных цифр (сс = computer chislo)
        ArrayList<Integer> comp = new RandomGenerator().startRG();
        printMessage1("Загадано четырехзначное число с неповторяющимися цифрами ");
        for (Integer a : comp) {
            System.out.print(a); //для отладки выводится загаданное число, в дальнейшем заменить на "****"
        }
        printMessage2(", угадайте его. Для выхода наберите: exit");
        //Создание переменной фиксации времени начала игры
        Date gameStart = new Date();
        //Получение числа игрока с консоли (user = user chislo)
        while ((stroka = ScannerStrok())!= null) {
            ++step;
            user.clear(); //очистка массива числа игрока
            Model.comp.clear(); //очистка массива быков и коров
            //Считывание очередной строки из консоли
            if(stroka.equalsIgnoreCase("exit")){
                printMessage1("Игра окончена за ");
                step--;
                break;
            }
            if(userChislo(stroka) == null) continue;     //при некорректном вводе
            user = userChislo(stroka);                     //запись корректного числа игрока в таблицу
            //Сравнение случайного и пользовательского чисел с подсчетом быков (*B) и коров (*C)
            Model.comp = bullsAndCows(user, comp,sizechislo);
            //Вывод на экран результата сравнения чисел в формате "**** (число) *В *С"
            printMessage1(step + " попытка: ");
            for (Integer b : user) {
                printMessage1("" + b);
            }
            printBC();
            //Занесение полученных данных в базу данных игры
            game.put(step, user); //новый ход, игрок ввел новое число
            bullsAndCows.put(step, Model.comp); //вычислили число быков и коров
            //Если угадано четыре цифры (4 быка) - игра окончена
            if(getBulls() == sizechislo) {
                printMessage1("Вы угадали за ");
                break;
            }
        }
        closeController();

        Date gameStop = new Date();
        gettime = gameStop.getTime() - gameStart.getTime();
        gameTime.put(step, gettime);                 //зафиксировали время игры в базе данных
        long gth = gettime /(60*60*1000);
        long gtm = gettime /(60*1000) - gth*60;
        long gts = gettime /1000 - gtm*60 - gth*60*60;
        printMessage2(gth + ":" + gtm + ":" + gts + " и " + step + " попыток");
    }
}