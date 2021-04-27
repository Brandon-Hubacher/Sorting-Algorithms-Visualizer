public class MergeSort implements Runnable {
    private int[] arr;
    private Visualizer frame;
    private int wait;
    public MergeSort(int[] array, Visualizer frame, int wait)
    {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
        Sorting.isSorting = false;
    }


    public void mergeSort(int[] array, int[] tmp, int leftStart, int rightEnd)
    {
        if(leftStart >= rightEnd)
        {
            return;
        }
        frame.drawSorting(array, -1, 1, 0);
        int middle = leftStart + ((rightEnd - leftStart) / 2);
        mergeSort(array, tmp, leftStart, middle);
        mergeSort(array, tmp, middle + 1, rightEnd);
        mergeHalves(array, tmp, leftStart, rightEnd);
        //frame.repaint();
    }

    public void mergeHalves(int[] array, int[] tmp, int leftStart, int rightEnd)
    {
        int leftEnd = leftStart + ((rightEnd - leftStart) / 2);
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right <= rightEnd)
        {
            frame.drawSorting(array, array[left], array[right], 2, 0);
            int leftVal = array[left];
            int rightVal = array[right];
            if(leftVal < rightVal)
            {
                tmp[index] = leftVal;
                ++left;
            }
            else
            {
                tmp[index] = rightVal;
                ++right;
            }
            frame.drawSorting(array, leftVal, rightVal, 1, 3);
            ++index;
        }

        System.arraycopy(array, left, tmp, index, leftEnd - left + 1);
        System.arraycopy(array, right, tmp, index, rightEnd - right + 1);
        for(int i = leftStart; i < size; i++)
        {
            array[i] = tmp[i];
            frame.drawSorting(array, array[i], 1, 2);
        }
        System.arraycopy(tmp, leftStart, array, leftStart, size);
    }
}
