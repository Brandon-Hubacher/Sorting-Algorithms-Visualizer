public class BubbleSort implements Runnable {
    private int[] arr;
    private Visualizer frame;
    private int wait;

    public BubbleSort(int[] array, Visualizer frame, int wait) {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        bubbleSort();
        Sorting.isSorting = false;
    }

    public void bubbleSort() {
        boolean isSorted = false;
        int firstSorted = arr.length - 1;
        int other = -1;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < firstSorted; i++) {
                frame.drawSorting(arr, arr[i], -1, other, 0, 0);
                frame.drawSorting(arr, arr[i], arr[i + 1], other, 0, 0);

                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    isSorted = false;
                    frame.drawSorting(arr, arr[i + 1], arr[i], other, 1, 5);
                }
            }
            other = firstSorted;
            firstSorted--;
        }
        Sorting.sorted = true;
    }

    private void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
