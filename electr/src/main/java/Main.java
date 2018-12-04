import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int distance;
    private static List<Integer> heights = new ArrayList<>();

    private static void openFile() {
        try {
            Scanner scanner = new Scanner(new FileReader("electr.in"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (!line.contains(" ")) {
                    distance = Integer.parseInt(line);
                } else {
                    String[] fields = line.split(" ");
                    for (String field : fields) {
                        heights.add(Integer.parseInt(field));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double countMaxLength(int distance) {
        int heightLength = heights.size();

        double[][] array = new double[heightLength + 1][101];

        for (int i = 2; i <= heightLength; i++) {
            for (int j = 1; j <= heights.get(i - 1); j++) {
                for (int k = 1; k <= heights.get(i - 2); k++) {
                    double tempNumber = array[i - 1][k] + Math.sqrt(distance * distance + (j - k) * (j - k));
                    array[i][j] = Math.max(array[i][j], tempNumber);
                }
            }
        }

        double wireLength = 0;

        for (int i = 1; i <= heights.get(heightLength - 1); i++) {
            wireLength = array[heightLength][i];
        }

        return wireLength;
    }

    public static void main(String[] args) {
        openFile();
        double result = countMaxLength(distance);
        System.out.println(result);
    }
}
