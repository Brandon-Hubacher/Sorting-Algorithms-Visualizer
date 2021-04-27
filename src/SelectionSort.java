public class SelectionSort implements Runnable {
    private int[] arr;
    private Visualizer frame;
    private int wait;
    public SelectionSort(int[] array, Visualizer frame, int wait)
    {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        selectionSort();
        Sorting.isSorting = false;
    }

    public void selectionSort()
    {
        int other = -1;
        for(int i = 0; i < arr.length - 1; i++)
        {
            frame.drawSorting(arr, arr[i], -1, other, 0, 0);
            for(int j = i + 1; j < arr.length; j++)
            {
                frame.drawSorting(arr, arr[i], arr[j], other, 0, 0);
                if(arr[j] < arr[i])
                {
                    int smallerValue = arr[j];
                    arr[j] = arr[i];
                    arr[i] = smallerValue;
                    frame.drawSorting(arr, arr[j], arr[i], other, 1, 6);
                }
            }
        }
    }
}
