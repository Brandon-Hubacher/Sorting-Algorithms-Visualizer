import java.util.Random;

public class QuickSort implements Runnable{
    private int[] arr;
    private Visualizer frame;
    private int wait;
    public QuickSort(int[] array, Visualizer frame, int wait)
    {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        quickSort();
        Sorting.isSorting = false;
    }

    public void quickSort()
    {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] array, int left, int right)
    {
        if(left >= right)
        {
            return;
        }
        int middle = left + ((right - left) / 2);
        int pivot = array[right];
        //Random rand = new Random();
        //int pivot = rand.nextInt(right + 1) - left;
        frame.drawSorting(array, pivot, 1, 1);
        int i = left;
        int j = right - 1;
        int tallest_index = -1;

        while(i <= j && j >= 0 && i < array.length)
        {
            frame.drawSorting(array, array[right], array[i], 3, 0);
            tallest_index = j;
            frame.drawSorting(array, array[right], array[i], tallest_index);
            if(array[i] > pivot && array[j] <= pivot)
            {
                tallest_index = -1;
                frame.drawSorting(array, array[right], array[i], tallest_index);
                swap(array, i, j);

                tallest_index = j;
                frame.drawSorting(array, array[right], array[i], tallest_index, 0, 3);
                ++i;
                --j;
            }
            else if(!(array[i] > pivot) && array[j] <= pivot)
            {
                ++i;
            }
            else if(!(array[j] <= pivot) && array[i] > pivot)
            {
                --j;
            }
            else
            {
                ++i;
                --j;
            }
        }
        swap(array, i, right);
        //bars.repaint();
        //pause();
        frame.drawSorting(array, array[right], array[i], tallest_index, 0, 3);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    private void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
