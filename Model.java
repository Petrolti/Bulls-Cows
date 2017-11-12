package ConsoleGame;

import java.util.ArrayList;

class Model extends ArrayList<Integer> {
    private static int bulls;
    private static int cows;

    static int getBulls() {
        return bulls;
    }

    private static void setBulls(int bulls) {
        Model.bulls = bulls;
    }

    private static void setCows(int cows) {
        Model.cows = cows;
    }

    static ArrayList<Integer> bullsAndCows(ArrayList<Integer> userchislo, ArrayList<Integer> compchislo){
        setBulls(0);
        setCows(0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (userchislo.get(i).equals(compchislo.get(j))) {
                    if (i == j) setBulls(++bulls);
                    else setCows(++cows);
                }
            }
        }
        ArrayList<Integer> bc = new ArrayList<Integer>();
        bc.add(0, bulls);
        bc.add(1, cows);
        return bc;
    }

    static void printBC(){
        System.out.println(" " + bulls + "B " + cows + "C");
    }
}
