import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt"; // имя входного файла
        int[][] pairs = readPairsFromFile(fileName); // считываем пары чисел из файла

        // Вычисляем максимальную сумму
        long maxSum = calculateMaxSum(pairs);
        System.out.println(maxSum);
    }

    private static int[][] readPairsFromFile(String fileName) {
        int[][] pairs = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int n = Integer.parseInt(reader.readLine().trim()); // количество пар

            pairs = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] line = reader.readLine().split(" ");

                pairs[i][0] = Integer.parseInt(line[0]);
                pairs[i][1] = Integer.parseInt(line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pairs;
    }

    private static long calculateMaxSum(int[][] pairs) {
        long sumWithMax = 0;

        // Начальная сумма, выбирая максимальные числа из каждой пары
        for (int[] pair : pairs) {
            sumWithMax += Math.max(pair[0], pair[1]);
        }

        // Проверяем, делится ли сумма на 3
        if (sumWithMax % 3 != 0) {
            return sumWithMax; // Если не делится, возвращаем сумму
        }

        // Ищем минимальное изменение для устранения деления на 3
        long minAdjustment = Long.MAX_VALUE;

        for (int[] pair : pairs) {
            long chosenMax = Math.max(pair[0], pair[1]);
            long alternative = Math.min(pair[0], pair[1]);

            long adjustment = chosenMax - alternative; // Разница между выбранным и альтернативным значениями

            // Проверяем, можно ли использовать это изменение
            if (adjustment % 3 != 0) {
                minAdjustment = Math.min(minAdjustment, adjustment);
            }
        }

        // Если не найдено подходящее изменение, возвращаем начальную сумму
        if (minAdjustment == Long.MAX_VALUE) {
            return sumWithMax;
        }

        // Возвращаем итоговую сумму с учётом минимального изменения
        return sumWithMax - minAdjustment;
    }
}


