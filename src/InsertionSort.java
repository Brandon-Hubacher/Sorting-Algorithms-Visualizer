public class InsertionSort implements Runnable {
    private int[] arr;
    private Visualizer frame;
    private int wait;
    public InsertionSort(int[] array, Visualizer frame, int wait)
    {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        insertionSort();
        Sorting.isSorting = false;
    }

    public void insertionSort()
    {
        int unsortedElement;
        for (int i = 1; i < arr.length; i++)
        {
            unsortedElement = arr[i]; // array access
            frame.drawSorting(arr, arr[i], 0, 1);
            int j = i - 1;

            int count = 0;
            int other = -1;

            //frame.updateComparisonsAndArrayAccesses(2, 1);
            while (j >= 0 && unsortedElement < arr[j])
            { // 2 comparisons and 1 array access
                frame.drawSorting(arr, arr[j + 1], arr[j], other, 2, 1);
                arr[j + 1] = arr[j]; // array access
                arr[j] = unsortedElement; // doing this for animation
                ++count;
                if (count == 1)
                {
                    other = i;
                }
                frame.drawSorting(arr, arr[j], arr[j + 1], other, 0, 1);
                --j;
            }
            arr[j + 1] = unsortedElement; // actual code for inserting element in insertion sort, array access
            if(j >= 0)
            {
                frame.drawSorting(arr, arr[j + 1], arr[j], other, 0, 1);
            }
        }
        Sorting.sorted = true;
    }
}
