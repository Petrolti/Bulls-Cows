package ConsoleGame;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator extends ArrayList<Integer> {
    public ArrayList<Integer> RandomGenerator() {
        ArrayList<Integer> cc = new ArrayList<Integer>();
        final Random random = new Random();
        while (cc.size() < 4) {
            int n = random.nextInt(10);
            if (!cc.contains(n)) {
                cc.add(n);
            }
        }
        return cc;
    }
}
