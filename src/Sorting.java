import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Sorting {
    public static Thread sortingThread;
    public static boolean isSorting;

    public static Visualizer visualizer;
    public static int[] array;
    public static boolean sorted = false;


    public static void main(String[] args)
    {
        visualizer = new Visualizer();
        visualizer.setDefaultCloseOperation(EXIT_ON_CLOSE);
        visualizer.setVisible(true);
        visualizer.setSize(1280, 720);
        visualizer.setResizable(true);
        array = generateRandomArray();
        visualizer.drawArray(array);
    }

    /*
    public Sorting()
    {
        array = generateArray();
        visualizer.drawArray(array);
    }

     */

    public static int[] generateRandomArray()
    {
        int num = visualizer.getNUM_BARS();
        int[] list = new int[num];
        for(int i = 0; i < num; i++)
        {
            list[i] = i + 1;
        }
        return shuffle(list);
    }

    public static int[] generateBestCaseArray()
    {
        int num = visualizer.getNUM_BARS();
        int[] list = new int[num];
        for(int i = 0; i < num; i++)
        {
            list[i] = i + 1;
        }
        return list;
    }

    public static int[] generateWorstCaseArray()
    {
        int num = visualizer.getNUM_BARS();
        int[] list = new int[num];
        for(int i = 0; i < num; i++)
        {
            list[i] = num - i;
        }
        return list;
    }

    public static int[] generateAverageCaseArray()
    {
        System.out.println("average case array not implemented");
        int num = visualizer.getNUM_BARS();
        int[] list = new int[num];
        for(int i = 0; i < num; i++)
        {
            if(i % 2 == 0)
            {
                list[i] = i + 1;
            }
            else
            {
                list[i] = num + 1 - i;
            }
        }
        return list;
    }



    public static int[] shuffle(int[] arr)
    {
        Random random = new Random();
        for(int i = 0; i < arr.length; i++)
        {
            int tempValue = arr[i];
            int swapIndex = random.nextInt(arr.length);
            arr[i] = arr[swapIndex];
            arr[swapIndex] = tempValue;
        }
        return arr;
    }

    public static void start(String str)
    {
        if(!isSorting)
        {
            isSorting = true;
            int[] deepCopy = new int[array.length];
            for(int i = 0; i < array.length; i++)
            {
                deepCopy[i] = array[i];
            }
            switch(str)
            {
                case "Insertion Sort":
                    System.out.println("insertion");
                    //sortingThread = new Thread(new InsertionSort(deepCopy, visualizer, 1000));
                    new InsertionSort(deepCopy, visualizer, 1000);
                    break;
                case "Selection Sort":
                    System.out.println("selection");
                    sortingThread = new Thread(new SelectionSort(deepCopy, visualizer, 1000));
                    break;
                case "Bubble Sort":
                    System.out.println("bubble");
                    sortingThread = new Thread(new BubbleSort(deepCopy, visualizer, 1000));
                    break;
                case "Merge Sort":
                    System.out.println("merge");
                    sortingThread = new Thread(new MergeSort(deepCopy, visualizer, 1000));
                    break;
                case "Quick Sort":
                    System.out.println("quick");
                    sortingThread = new Thread(new QuickSort(deepCopy, visualizer, 1000));
                    break;
                case "Count Sort":
                    System.out.println("count");
                    sortingThread = new Thread(new CountSort(deepCopy, visualizer, 1000));
                    break;
                case "Heap Sort":
                    System.out.println("heap");
                    new HeapSort(deepCopy, visualizer, 1000);
                default:
                    isSorting = false;
                    return;
            }
            //sortingThread.start();
        }
    }
}
