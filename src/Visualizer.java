import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;

public class Visualizer extends JFrame {
    private int WINDOW_HEIGHT = 720;
    private int WINDOW_WIDTH = 1280;
    private int NUM_BARS;
    private int BAR_WIDTH;
    private int[] arr;
    private JSlider slider;
    private JLabel slider_label;
    private JButton start;
    private JComboBox algorithmSelect;
    private int chosenAlgo = -1;
    private JPanel[] bar_panels;
    private JPanel wrapper;
    private JPanel arrayWrapper;
    private GridBagConstraints c;
    private boolean letgo = true;
    private JComboBox speed;
    private int sortDelay = 1;
    private JButton resetArrayButton;
    private JComboBox caseDropDown;
    private JLabel analysis;
    private JPanel computationPanel;
    private JLabel comparisonsLabel;
    private JLabel accessesLabel;
    private int numComparisons;
    private int numArrayAccesses;


    public int getNUM_BARS()
    {
        return NUM_BARS;
    }

    public void drawArray(int[] array)
    {
        bar_panels = new JPanel[array.length+1];
        arrayWrapper.removeAll();
        for(int i = 0; i <= array.length; i++)
        {
            bar_panels[i] = new JPanel();
            if(i == 0)
            {
                Dimension dimension = new Dimension(0, 300);
                bar_panels[i].setPreferredSize(dimension);
                bar_panels[i].setBackground(Color.GRAY);
            }
            else
            {
                Dimension dimension = new Dimension((WINDOW_WIDTH / array.length) - 2, array[i - 1]);
                bar_panels[i].setPreferredSize(dimension);
                bar_panels[i].setBackground(Color.BLACK);
            }
            bar_panels[i].setVisible(true);
            arrayWrapper.add(bar_panels[i], c);
        }
        arrayWrapper.setVisible(true);
        //repaint();
        validate();
    }

    public Visualizer()
    {
        //super();
        arrayWrapper = new JPanel();
        slider = new JSlider(JSlider.HORIZONTAL, 5, 300, 100);
        slider.setPreferredSize(new Dimension(300, 30));
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if(!Sorting.isSorting)
                {
                    if(!source.getModel().getValueIsAdjusting())
                    {
                        slider_label.setText(Integer.toString(source.getValue()));
                        NUM_BARS = source.getValue();
                        BAR_WIDTH = WINDOW_WIDTH / NUM_BARS;
                        String arrayCase = caseDropDown.getSelectedItem().toString();
                        if(arrayCase.equalsIgnoreCase("best"))
                        {
                            Sorting.array = Sorting.generateBestCaseArray();
                        }
                        else if(arrayCase.equalsIgnoreCase("worst"))
                        {
                            Sorting.array = Sorting.generateWorstCaseArray();
                        }
                        else if(arrayCase.equalsIgnoreCase("average"))
                        {
                            Sorting.array = Sorting.generateAverageCaseArray();
                        }
                        else
                        {
                            Sorting.array = Sorting.generateRandomArray();
                        }

                        drawArray(Sorting.array);
                        validate();
                    }
                }
                else
                {
                    System.out.println("can't change size until sorting is done");
                }
            }
        });

        slider_label = new JLabel();
        slider_label.setText(Integer.toString(slider.getValue()));
        slider_label.setPreferredSize(new Dimension(30, 10));
        slider.setVisible(true);
        slider_label.setVisible(true);

        start = new JButton("Begin sorting");
        start.setPreferredSize(new Dimension(120, 25));

        String[] algorithms = {"Insertion Sort", "Selection Sort", "Bubble Sort", "Merge Sort", "Quick Sort", "Count Sort", "Heap Sort"};
        algorithmSelect = new JComboBox(algorithms);
        algorithmSelect.setPreferredSize(new Dimension(120, 25));

        String[] speedArr = {"Very fast", "fast", "medium", "slow"};
        speed = new JComboBox(speedArr);
        speed.setPreferredSize(new Dimension(120, 25));
        speed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox dropDown = (JComboBox) e.getSource();
                String str = dropDown.getSelectedItem().toString();
                switch(str)
                {
                    case "Very fast":
                        sortDelay = 1;
                        break;
                    case "fast":
                        sortDelay = 10;
                        break;
                    case "medium":
                        sortDelay = 100;
                        break;
                    case "slow":
                        sortDelay = 1000;
                        break;
                    default:
                        break;
                }
            }
        });
        String[] caseArr = {"Random", "Best", "Worst", "Average"};
        caseDropDown = new JComboBox(caseArr);
        caseDropDown.setPreferredSize(new Dimension(120, 25));
        caseDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Sorting.isSorting)
                {
                    JComboBox dropDown = (JComboBox) e.getSource();
                    String str = dropDown.getSelectedItem().toString();
                    arrayWrapper.removeAll();
                    switch(str)
                    {
                        case "Best":
                            Sorting.array = Sorting.generateBestCaseArray();
                            break;
                        case "Worst":
                            Sorting.array = Sorting.generateWorstCaseArray();
                            break;
                        case "Average":
                            Sorting.array = Sorting.generateAverageCaseArray();
                            break;
                        default:
                            Sorting.array = Sorting.generateRandomArray();
                            break;
                    }
                    drawArray(Sorting.array);
                    repaint();
                }
                else
                {
                    System.out.println("cant change CASE until sorting is over");
                }
            }
        });

        resetArrayButton = new JButton("Reset Array");
        resetArrayButton.setPreferredSize(new Dimension(120, 25));
        resetArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Sorting.isSorting)
                {
                    JButton button = (JButton) e.getSource();
                    numComparisons = 0;
                    numArrayAccesses = 0;
                    comparisonsLabel.setText(numComparisons+" comparisons");
                    accessesLabel.setText(numArrayAccesses+" array accesses");
                    arrayWrapper.removeAll();
                    Sorting.array = Sorting.generateRandomArray();
                    drawArray(Sorting.array);
                    repaint();
                    //validate();
                }
                else
                {
                    System.out.println("must wait for sorting to finish");
                }
            }
        });

        computationPanel = new JPanel(new GridLayout(1, 2));
        computationPanel.setPreferredSize(new Dimension(300, 30));
        comparisonsLabel = new JLabel(numComparisons+" comparisons");
        comparisonsLabel.setSize(30, 20);
        accessesLabel = new JLabel(numArrayAccesses+" array accesses");
        accessesLabel.setSize(30, 20);
        computationPanel.add(comparisonsLabel);
        computationPanel.add(accessesLabel);

        //analysis = new JLabel("This will be the analysis");
        //analysis.setPreferredSize(new Dimension(200, 100));



        wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout());
        wrapper.setBounds(0, 0, 800, 700);
        wrapper.setVisible(true);
        arrayWrapper.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(0, 1, 0, 0);
        c.anchor = GridBagConstraints.SOUTH;
        arrayWrapper.setSize(500, 500);

        wrapper.add(start);
        wrapper.add(resetArrayButton);
        wrapper.add(slider_label);
        wrapper.add(slider);
        wrapper.add(algorithmSelect);
        wrapper.add(speed);
        wrapper.add(caseDropDown);
        wrapper.add(arrayWrapper);
        wrapper.add(computationPanel);
        //wrapper.add(analysis);
        add(wrapper);

        int num_bars = slider.getValue();
        if(num_bars <= 0)
        {
            throw new IllegalArgumentException("number of bars must be greater than 0");
        }
        NUM_BARS = num_bars;
        BAR_WIDTH = WINDOW_WIDTH / NUM_BARS;
        //arr = generateArray();



        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("action is: "+algorithmSelect.getSelectedItem());
                JButton button = (JButton) e.getSource();
                System.out.println();
                new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception {
                        Sorting.start((String) algorithmSelect.getSelectedItem());
                        return null;
                    }
                }.execute();
                validate();

            }
        });
    }

    public void updateComparisonsAndArrayAccesses(int c, int a)
    {
        numComparisons += c;
        numArrayAccesses += a;
        comparisonsLabel.setText(numComparisons+" comparisons");
        accessesLabel.setText(numArrayAccesses+" array accesses");
        validate();
    }

    public void drawSorting(int[] array, int current, int comparisons, int arrayAccesses)
    {
        drawSorting(array, current, -1, -1, comparisons, arrayAccesses);
    }

    public void drawSorting(int[] array, int current, int compare, int comparisons, int arrayAccesses)
    {
        drawSorting(array, current, compare, -1, comparisons, arrayAccesses);
    }

    public void drawSorting(int[] array, int current, int compare, int other, int comparisons, int arrayAccesses)
    {
        bar_panels = new JPanel[array.length + 1];
        arrayWrapper.removeAll();

        for(int i = 0; i < bar_panels.length; i++)
        {
            //bar_panels[i] = new JPanel();
            if(i == 0)
            {
                Dimension invisDimension = new Dimension(0, 300);
                bar_panels[0] = new JPanel();
                bar_panels[0].setPreferredSize(invisDimension);
                bar_panels[0].setBackground(Color.GRAY);
                bar_panels[0].setVisible(true);
                arrayWrapper.setVisible(true);
                arrayWrapper.add(bar_panels[0], c);
            }
            else
            {
                Dimension dimension = new Dimension((WINDOW_WIDTH / array.length) - 2, array[i - 1]);
                bar_panels[i] = new JPanel();
                bar_panels[i].setPreferredSize(dimension);
                if(algorithmSelect.getSelectedItem().toString().equalsIgnoreCase("Heap Sort"))
                {
                    int layer = (int) (Math.log(i) / Math.log(2));
                    if(layer == 0)
                    {
                        bar_panels[i].setBackground(Color.GRAY);
                    }
                    else if(layer == 1)
                    {
                        bar_panels[i].setBackground(Color.MAGENTA);
                    }
                    else if(layer == 2)
                    {
                        bar_panels[i].setBackground(Color.PINK);
                    }
                    else if(layer == 3)
                    {
                        bar_panels[i].setBackground(Color.CYAN);
                    }
                    else if(layer == 4)
                    {
                        bar_panels[i].setBackground(Color.YELLOW);
                    }
                    else if(layer == 5)
                    {
                        bar_panels[i].setBackground(Color.DARK_GRAY);
                    }
                    else if(layer == 6)
                    {
                        bar_panels[i].setBackground(Color.ORANGE);
                    }
                    else if(layer == 7)
                    {
                        bar_panels[i].setBackground(Color.cyan);
                    }
                    else if(layer == 8)
                    {
                        bar_panels[i].setBackground(Color.lightGray);
                    }
                }
                /*
                if(Sorting.sorted)
                {
                    bar_panels[i].setBackground(Color.GREEN);
                }


                else*/ if(array[i - 1] == current)
                {
                    bar_panels[i].setBackground(Color.BLUE);
                }
                else if(array[i - 1] == compare)
                {
                    bar_panels[i].setBackground(Color.RED);
                }
                else if(i - 1 == other)
                {
                    bar_panels[i].setBackground(Color.GREEN);
                }
                else if(!algorithmSelect.getSelectedItem().toString().equalsIgnoreCase("Heap Sort"))
                {
                    bar_panels[i].setBackground(Color.BLACK);
                }
                bar_panels[i].setVisible(true);
                arrayWrapper.add(bar_panels[i], c);
            }
        }
        //Sorting.sorted = false;
        updateComparisonsAndArrayAccesses(comparisons, arrayAccesses);
        repaint();
        validate();
        try {
            Thread.currentThread().sleep(sortDelay);
            //SortingAlgorithms.pause();
            //Sorting.sortingThread.sleep(sortDelay);
        } catch(InterruptedException e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        //validate();
    }
}
