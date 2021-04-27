public class HeapSort implements Runnable {
    private int[] arr;
    private Visualizer frame;
    private int wait;

    public HeapSort(int[] array, Visualizer frame, int wait) {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        heapSort();
        Sorting.isSorting = false;
    }

    private void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        frame.drawSorting(array, array[i], -1, j, 0, 0);
    }

    private String printArray(int[] array)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < array.length - 1; i++)
        {
            sb.append(array[i]+", ");
        }
        sb.append(array[array.length - 1]+"]");
        return sb.toString();
    }

    public void heapify(int[] arr, int n, int i)
    {
        int largest = i;
        //frame.drawSorting(arr,-1, -1, i, 0, 0);
        frame.drawSorting(arr, arr[i], 0, 0);
        int l = (2 * i) + 1;
        int r = (2 * i) + 2;

        if(l < n)
        {
            frame.drawSorting(arr, arr[i], arr[l], -1, 0, 0);
        }
        if(l < n && arr[l] > arr[largest])
        {
            largest = l;
            frame.drawSorting(arr, arr[i], -1, l, 0, 0);
        }

        if(r < n)
        {
            if(largest == i)
            {
                frame.drawSorting(arr, arr[i], arr[r], -1, 0, 0);
            }
            else
            {
                frame.drawSorting(arr, arr[i], arr[r], l, 0, 0);
            }
        }
        if(r < n && arr[r] > arr[largest])
        {
            largest = r;
            frame.drawSorting(arr, arr[i], -1, r, 0, 0);
        }

        if(largest != i)
        {
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }
    }

    public void heapSort()
    {
        int n = arr.length;
        for(int i = (n / 2) - 1; i >= 0; i--)
        {
            heapify(arr, n, i);
        }

        int[] output = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            output[i] = arr[i];
        }
        while(n > 0)
        {
            //arr[n - 1] = arr[0];
            //frame.drawSorting(arr, -1, -1, arr[n - 1]);
            frame.drawSorting(arr, arr[n - 1], -1, 0, 0, 0);
            swap(arr, 0, n - 1);
            //arr[n - 1] = 0;
            --n;
            heapify(arr, n, 0);
        }
    }
}
