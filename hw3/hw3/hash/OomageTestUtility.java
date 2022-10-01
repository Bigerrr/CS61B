package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<Oomage>[] map = new List[M];
        for (Oomage om: oomages) {
            int index = (om.hashCode() & 0x7fffffff) % M;
            if(map[index] == null) {
                map[index] = new ArrayList<>();
            }
            if (!map[index].contains(om)) {
                map[index].add(om);
            }
        }
        int N = oomages.size();
        for (int i = 0; i < M; i++) {
            int size = map[i].size();
            if (size < N / 50 || size > N / 2.5) {
                System.out.printf("N: %d, size: %d", N, size);
                return false;
            }
        }
        return true;
    }
}
