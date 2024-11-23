package sorting.gui;

import sorting.interfaces.Sortable;
import sorting.algorithms.BubbleSort;
import sorting.algorithms.SelectionSort;
import sorting.utils.SortingUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;

public class SortingGUI extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JSpinner arraySizeSpinner;
    private JButton generateButton;
    private JButton sortButton;
    private JComboBox<String> algorithmChoice;
    private JPanel arrayVisualPanel;
    private int[] currentArray;

    public SortingGUI() {
        setTitle("Aplikasi Sorting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel Utara - Kontrol
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Komponen untuk ukuran array
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Ukuran Array:"), gbc);

        gbc.gridx = 1;
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 1, 100, 1);
        arraySizeSpinner = new JSpinner(spinnerModel);
        controlPanel.add(arraySizeSpinner, gbc);

        // Generate button
        gbc.gridx = 2;
        generateButton = new JButton("Generate Array Acak");
        controlPanel.add(generateButton, gbc);

        // Pilihan algoritma
        gbc.gridx = 3;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Pilih Algoritma:"), gbc);

        gbc.gridx = 4;
        String[] algorithms = {"Bubble Sort", "Selection Sort"};
        algorithmChoice = new JComboBox<>(algorithms);
        controlPanel.add(algorithmChoice, gbc);

        // Sort button
        gbc.gridx = 5;
        sortButton = new JButton("Urutkan");
        sortButton.setEnabled(false);
        controlPanel.add(sortButton, gbc);

        add(controlPanel, BorderLayout.NORTH);

        // Panel Tengah - Visualisasi Array
        arrayVisualPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawArray(g);
            }
        };
        arrayVisualPanel.setPreferredSize(new Dimension(600, 200));
        arrayVisualPanel.setBorder(BorderFactory.createTitledBorder("Visualisasi Array"));
        add(arrayVisualPanel, BorderLayout.CENTER);

        // Panel Selatan - Output
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        // Event Listeners
        generateButton.addActionListener(e -> generateRandomArray());
        sortButton.addActionListener(e -> performSort());

        pack();
        setLocationRelativeTo(null);
    }

    // membuat array beru dengan ukuran dari spinner
    // mengisi array dengan angka random 0-99
    private void generateRandomArray() {
        int size = (Integer) arraySizeSpinner.getValue();
        currentArray = new int[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            currentArray[i] = random.nextInt(100);
        }
        
        outputArea.setText("Array yang di-generate: \n");
        outputArea.append(arrayToString(currentArray));
        sortButton.setEnabled(true);
        arrayVisualPanel.repaint();
    }

    // memilih algoritma sorting berdasarkan pilihan user
    // membuat clone array untuk menjaga data original
    // mengukur waktu eksekusi
    // menjalankan algoritma sorting
    private void performSort() {
        if (currentArray == null) return;

        Sortable sorter;
        if (algorithmChoice.getSelectedItem().equals("Bubble Sort")) {
            sorter = new BubbleSort();
        } else {
            sorter = new SelectionSort();
        }

        int[] arrayToSort = currentArray.clone();
        long startTime = System.nanoTime();
        
        sorter.sort(arrayToSort);
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000; // mikrodetik

        outputArea.append("\nHasil pengurutan menggunakan " + 
                         algorithmChoice.getSelectedItem() + ":\n");
        outputArea.append(arrayToString(arrayToSort));
        outputArea.append("\nWaktu eksekusi: " + duration + " mikrodetik\n");

        // Update array yang ditampilkan
        currentArray = arrayToSort;
        arrayVisualPanel.repaint();
    }

    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    // menggambar representasi visual array menggunakan bar chart
    // tinggi bar proporsional dengan nilai elemen
    // update secara realtime setelah pengurutan
    private void drawArray(Graphics g) {
        if (currentArray == null) return;

        int width = arrayVisualPanel.getWidth();
        int height = arrayVisualPanel.getHeight();
        int barWidth = width / currentArray.length;
        
        // Clear panel
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Draw bars
        g.setColor(new Color(65, 105, 225)); // Royal Blue
        for (int i = 0; i < currentArray.length; i++) {
            int barHeight = (int) ((currentArray[i] / 100.0) * (height - 40));
            g.fillRect(i * barWidth + 10, 
                      height - barHeight - 20, 
                      barWidth - 2, 
                      barHeight);
            
            // Draw value on top of bar
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(currentArray[i]), 
                        i * barWidth + 10, 
                        height - barHeight - 25);
            g.setColor(new Color(65, 105, 225));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set Look and Feel
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SortingGUI().setVisible(true);
        });
    }
}