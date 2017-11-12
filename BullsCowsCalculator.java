package ConsoleGame;

import java.util.ArrayList;

class BullsCowsCalculator extends ArrayList<Integer> {
    private static int bulls;
    private static int cows;

    static int getBulls() {
        return bulls;
    }

    private static void setBulls(int bulls) {
        BullsCowsCalculator.bulls = bulls;
    }

    private static void setCows(int cows) {
        BullsCowsCalculator.cows = cows;
    }

    static ArrayList<Integer> bullsAndCows(ArrayList<Integer> userchislo, ArrayList<Integer> compchislo, int sizechislo){
        setBulls(0);
        setCows(0);
        for (int i = 0; i < sizechislo; i++) {
            for (int j = 0; j < sizechislo; j++) {
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
