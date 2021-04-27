import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GenerateBars extends JPanel {

    //SortingAlgorithms sort = new SortingAlgorithms(100);

    public static Thread sortingThread;

    private int WINDOW_HEIGHT = 720;
    private int WINDOW_WIDTH = 1280;
    private int NUM_BARS;
    private double BAR_WIDTH;
    private int[] arr;
    private JSlider slider;
    private JLabel slider_label;
    private JButton start;
    private JComboBox algorithmSelect;
    private int chosenAlgo = -1;

    public int[] getArray()
    {
        return arr;
    }

    public void generatePage()
    {
        slider = new JSlider(JSlider.HORIZONTAL, 1, 800, 100);
        //slider.setSize(1000, 100);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                slider_label.setText(Integer.toString(source.getValue()));
                NUM_BARS = source.getValue();
                BAR_WIDTH = (double) WINDOW_WIDTH / NUM_BARS;
                arr = generateArray();
                repaint();
            }
        });
        slider_label = new JLabel();
        slider_label.setText(Integer.toString(slider.getValue()));
        slider.setVisible(true);
        slider_label.setVisible(true);

        start = new JButton("Begin sorting");
        String[] algorithms = {"Insertion Sort", "Selection Sort", "Bubble Sort", "Merge Sort", "Quick Sort", "Count Sort"};
        algorithmSelect = new JComboBox(algorithms);

        JPanel panel = new JPanel(new GridLayout(0, 4));
        panel.setBounds(0, 0, 800, 100);
        panel.setSize(800, 100);
        panel.setVisible(true);

        panel.add(start, BorderLayout.PAGE_START);
        panel.add(slider_label);
        panel.add(slider);
        panel.add(algorithmSelect);
        add(panel);

        int num_bars = slider.getValue();
        if(num_bars <= 0)
        {
            throw new IllegalArgumentException("number of bars must be greater than 0");
        }
        NUM_BARS = num_bars;
        BAR_WIDTH = WINDOW_WIDTH / NUM_BARS;
        arr = generateArray();



        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("action performed");
                JButton button = (JButton) e.getSource();
                //System.out.println(button.getModel().isArmed());
                //button.getModel().setArmed(false);
                switch(algorithmSelect.getSelectedIndex())
                {
                    case 0:
                        System.out.println("Insertion Sort");
                        chosenAlgo = 0;
                        break;
                    case 1:
                        System.out.println("Selection Sort");
                        chosenAlgo = 1;
                        break;
                    case 2:
                        System.out.println("Bubble Sort");
                        chosenAlgo = 2;
                        break;
                    case 3:
                        System.out.println("Merge Sort");
                        chosenAlgo = 3;
                        break;
                    case 4:
                        System.out.println("Quick Sort");
                        chosenAlgo = 4;
                        break;
                    case 5:
                        System.out.println("Counting Sort");
                        chosenAlgo = 5;
                        break;
                }
                //repaint();
            }
        });
        //repaint();
    }

    public GenerateBars()
    {
        generatePage();
        /*
        int num_bars = slider.getValue();
        if(num_bars <= 0)
        {
            throw new IllegalArgumentException("number of bars must be greater than 0");
        }
        NUM_BARS = num_bars;
        BAR_WIDTH = WINDOW_WIDTH / NUM_BARS;
        arr = generateArray();

         */
    }

    public int[] generateArray()
    {
        int[] list = new int[NUM_BARS];
        for(int i = 0; i < list.length; i++)
        {
            list[i] = i + 1;
        }
        return shuffle(list);
    }

    public int[] shuffle(int[] arr)
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

    /*
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        switch(chosenAlgo)
        {
            case -1:

                break;
            case 0:
                //System.out.println("Insertion Sort");
                //SortingAlgorithms.insertionSort();
                System.out.println("running test");
                chosenAlgo = -1;
                SortingAlgorithms.test();
                break;
            case 1:
                System.out.println("Selection Sort");
                chosenAlgo = -1;
                break;
            case 2:
                System.out.println("Bubble Sort");
                chosenAlgo = -1;
                break;
            case 3:
                System.out.println("Merge Sort");
                chosenAlgo = -1;
                break;
            case 4:
                System.out.println("Quick Sort");
                chosenAlgo = -1;
                break;
            case 5:
                System.out.println("Counting Sort");
                chosenAlgo = -1;
                break;
        }

        chosenAlgo = -1;




        for(int i = 0; i < NUM_BARS; i++)
        {
            //int x = (int) BAR_WIDTH * i;
            double x = BAR_WIDTH * i;
            //int height = WINDOW_HEIGHT - NUM_BARS;
            //int y = height - arr[i];

            int y = WINDOW_HEIGHT - 39;
            int height = (-1 * arr[i]) - 10;

            if(SortingAlgorithms.done)
            {
                g.setColor(Color.GREEN);
            }
            else if(arr[i] == SortingAlgorithms.temp)
            {
                g.setColor(Color.BLUE);
            }
            else if(i == SortingAlgorithms.tallest_index)
            {
                g.setColor(Color.GREEN);
            }
            else if(arr[i] == SortingAlgorithms.swap1 || i == SortingAlgorithms.swap2Index)
            {
                g.setColor(Color.MAGENTA);
            }
            else if(arr[i] == SortingAlgorithms.jvalue || i == SortingAlgorithms.j)
            {
                g.setColor(Color.RED);
            }
            else
            {
                g.setColor(Color.BLACK);
            }
            g.fillRect((int) x, y, (int) BAR_WIDTH, height);


            Rectangle2D rect = new Rectangle2D.Double(x, (double) y, BAR_WIDTH, (double) height);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.fill(rect);
            g2.draw(rect);


        }
    }
    */

}
