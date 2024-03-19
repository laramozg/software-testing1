package task2;

public class ShellSort {
    public static int[] sort(int[] arr) {
        int n = arr.length;

        // Начинаем с большого интервала, затем уменьшаем его
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Применяем сортировку вставками с текущим интервалом
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;

                // Перемещаем элементы с интервалом gap, если они больше текущего элемента
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                // Вставляем текущий элемент на правильную позицию
                arr[j] = temp;
            }
        }

        return arr;
    }
}
