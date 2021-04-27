public class CountSort implements Runnable {
    private int[] arr;
    private Visualizer frame;
    private int wait;
    public CountSort(int[] array, Visualizer frame, int wait)
    {
        this.arr = array;
        this.frame = frame;
        this.wait = wait;
        run();
    }

    @Override
    public void run() {
        countSort();
        Sorting.isSorting = false;
    }

    public void countSort()
    {
        countSort(arr, arr.length);
    }

    private void countSort(int[] input, int k)
    {
        int[] count = new int[k];

        // assign amount of particular value to the values corresponding index
        for(int val : input)
        {
            frame.drawSorting(input, val, 0, 1);
            ++count[val - 1];
        }
        // each element is equal to the cummulative sum of those before it
        for(int i = 1; i < input.length; i++)
        {
            frame.drawSorting(input, input[i], 0, 2);
            count[i] += count[i - 1];
        }
        // shift each element to the right one place
        for(int i = count.length - 1; i > 0; i--)
        {
            frame.drawSorting(input, input[i], 0, 2);
            count[i] = count[i - 1];
            ++count[i];
        }
        frame.drawSorting(input, input[0], 0, 0);

        // put values in new array in order
        int[] output = new int[input.length];
        for(int i = 0; i < input.length; i++)
        {
            int z = count[input[i] - 1];
            output[z - 1] = input[i];
            ++count[input[i] - 1];
        }

        for(int i = 0; i < input.length; i++)
        {
            input[i] = output[i];
            frame.drawSorting(input, input[i], 0, 6);
        }
    }
}
