import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Map<Integer, Integer> result = new HashMap<>();

        int seed = a;
        while (seed <= b) {
            Random random = new Random(seed);
            List<Integer> list = new ArrayList<>();
//            System.out.print("seed: ");
            for (int i = 0; i < n; i++) {
                int tmp = random.nextInt(k);
                list.add(tmp);
//                System.out.print(tmp + " ");
            }
            //System.out.println(list);
            result.put(seed, Collections.max(list));
            seed++;
        }

        Map.Entry<Integer, Integer> min = null;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        System.out.println(min.getKey());
        System.out.println(min.getValue());
    }
}
