import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithms {

    public static int[] arr;
    static GenerateBars bars;
    //GenerateBars[] bars_array;
    private static int speed;
    static int temp;
    static int swap1 = -1;
    static int swap2Index = -1;

    static int j = -1;
    static int jvalue;

    static int tallest_index = -1;

    static boolean done;

    public SortingAlgorithms(int speed)
    {
        this.bars = new GenerateBars();
        //bars.generateArray();
        arr = bars.getArray();
        this.speed = speed;
    }

    public static void test()
    {
        for(int i = 0; i < arr.length; i++)
        {
            temp = arr[i];
            bars.repaint();
            pause();
        }
    }

    public static void insertionSort()
    {
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            j = -1;
            bars.repaint();
            pause();
            j = i - 1;

            int count = 0;

            while (j >= 0 && temp < arr[j]) {
                bars.repaint();
                pause();
                arr[j + 1] = arr[j];
                jvalue = arr[j + 1];
                arr[j] = temp;
                ++count;
                if (count == 1) {
                    tallest_index = j + 1;
                }
                bars.repaint();
                pause();
                --j;
                jvalue = -1;
            }
            if (j >= 0) {
                jvalue = arr[j];
                bars.repaint();
            }
            tallest_index = -1;
            arr[j + 1] = temp;
            jvalue = -1;
            pause();
        }
        done = true;
        bars.repaint();
    }

    public void selectionSort()
    {
        tallest_index = -1;
        for(int i = 0; i < arr.length - 1; i++)
        {
            pause();
            bars.repaint();
            temp = arr[i];
            for(int j = i + 1; j < arr.length; j++)
            {
                jvalue = arr[j];
                bars.repaint();
                pause();
                if(arr[j] < arr[i])
                {
                    int smallerValue = arr[j];
                    arr[j] = arr[i];
                    arr[i] = smallerValue;
                    pause();
                    temp = arr[i];
                    bars.repaint();
                    pause();
                }
            }
        }
    }

    public void bubbleSort()
    {
        boolean isSorted = false;
        int firstSorted = arr.length - 1;

        while(!isSorted)
        {
            isSorted = true;
            for(int i = 0; i < firstSorted; i++)
            {
                temp = arr[i];
                bars.repaint();
                pause();
                jvalue = arr[i + 1];
                bars.repaint();
                pause();
                if(arr[i] > arr[i + 1])
                {
                    swap(arr, i, i + 1);
                    isSorted = false;
                }
            }
            firstSorted--;
        }
    }

    private void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void mergeSort(int[] array, int[] tmp, int leftStart, int rightEnd)
    {
        if(leftStart >= rightEnd)
        {
            return;
        }
        int middle = leftStart + ((rightEnd - leftStart) / 2);
        mergeSort(array, tmp, leftStart, middle);
        mergeSort(array, tmp, middle + 1, rightEnd);
        mergeHalves(array, tmp, leftStart, rightEnd);
        bars.repaint();
    }

    private void mergeHalves(int[] array, int[] tmp, int leftStart, int rightEnd)
    {
        int leftEnd = leftStart + ((rightEnd - leftStart) / 2);
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right <= rightEnd)
        {
            tallest_index = rightEnd + 1;
            temp = array[left];
            jvalue = array[right];
            bars.repaint();
            pause();
            if(array[left] < array[right])
            {
                tmp[index] = array[left];
                ++left;
            }
            else
            {
                tmp[index] = array[right];
                ++right;
            }
            ++index;
        }

        System.arraycopy(array, left, tmp, index, leftEnd - left + 1);
        System.arraycopy(array, right, tmp, index, rightEnd - right + 1);
        System.arraycopy(tmp, leftStart, array, leftStart, size);
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
        temp = array[right];
        bars.repaint();
        pause();
        int i = left;
        int j = right - 1;

        while(i <= j && j >= 0 && i < array.length)
        {
            jvalue = array[i];
            bars.repaint();
            pause();
            tallest_index = j;
            bars.repaint();
            pause();
            if(array[i] > pivot && array[j] <= pivot)
            {
                jvalue = -1;
                tallest_index = -1;
                swap1 = array[i];
                swap2Index = j;
                bars.repaint();
                pause();
                swap1 = swap2Index = -1;
                swap(array, i, j);

                jvalue = array[i];
                tallest_index = j;
                bars.repaint();
                pause();
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
        bars.repaint();
        pause();
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public void countSort()
    {
        countSort(arr, arr.length);
    }

    private void countSort(int[] input, int k)
    {
        int[] count = new int[k + 1];

        for(int val : input)
        {
            temp = val;
            bars.repaint();
            pause();
            count[val]++;
        }
        temp = -1;

        int index = 0;
        for(int i = 0; i < count.length; i++)
        {
            while(count[i] > 0)
            {
                input[index++] = i;
                temp = i;
                bars.repaint();
                pause();
                count[i]--;
            }
        }
    }

    private void applyCountingSort(int[] input, int k, int place)
    {
        int range = 10;
        int[] count = new int[k + 1];
        //count = (place == 0) ? new int[k + 1] : new int[10];

        for(int val : input)
        {
            temp = val;
            bars.repaint();
            pause();
            int digit = (val / place) % range;
            count[digit]++;
        }
        temp = -1;

        for(int i = 1; i < range; i++)
        {
            count[i] += count[i - 1];
        }

        int index = 0;
        for(int i = 0; i < count.length; i++)
        {
            while(count[i] > 0)
            {
                input[index++] = i;
                temp = i;
                bars.repaint();
                pause();
                count[i]--;
            }
        }
    }



    public void radixSort()
    {
        radixSort(arr, arr.length);
    }

    private void radixSort(int[] array, int n)
    {
        int max = 0;
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] > max)
            {
                max = array[i];
            }
        }
        for(int place = 1; max/place > 0; place *= 10)
        {
            countSort(array, place);
        }
    }


    public static void pause()
    {
        pause(1);
    }

    private static void pause(int speed)
    {
        if(speed == 0)
        {
            return;
        }
        long time = System.currentTimeMillis() / speed;
        long timeElapsed = 0L;
        while(timeElapsed < 1L)
        {
            //System.out.println(time);
            timeElapsed = (System.currentTimeMillis() / speed) - time;
        }
    }
}
