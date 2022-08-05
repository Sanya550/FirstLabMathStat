package com.example.idealjavafx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SimpleTableDemo extends JPanel {
    private boolean DEBUG = false;

    double kvantil1 = HelloController.kvantil;
    ArrayList arrayList1 = HelloController.arrayList;
    int size = arrayList1.size();
    public SimpleTableDemo() {
        super(new GridLayout(1, 0));
        List list = Helper.data();
        String[] columnNames = {"Характеристика",
                "INF",
                "Значення",
                "SUP","Сер.квадр"};

        double q1 = kvantil1 * (double) list.get(3) / Math.sqrt(size);
        q1 = BigDecimal.valueOf(q1).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q2 = kvantil1 * ((double) list.get(2) / Math.sqrt(2 * size));
        q2 = BigDecimal.valueOf(q2).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q3 = kvantil1 * (double) list.get(3) / Math.sqrt(2 * size);
        q3 = BigDecimal.valueOf(q3).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q5 = kvantil1 * Math.sqrt((double) 6 * (size - 2) / ((size + 1) * (size + 3)));
        q5 = BigDecimal.valueOf(q5).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q7 = kvantil1 * (24.0 / size) * (1 - (225.0 / (15 * size + 124)));
        q7 = BigDecimal.valueOf(q7).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q9 = kvantil1 * Math.sqrt((double) list.get(8) / (29 * size)) * Math.pow(Math.pow(Math.abs((1 + Math.pow((double) list.get(8), 2))), 3), 0.25);
        q9 = BigDecimal.valueOf(q9).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q10 = kvantil1 * (double) list.get(10) * (Math.sqrt((1 + 2 * Math.pow((double) list.get(10), 2)) / (2 * size)));
        q10 = BigDecimal.valueOf(q10).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();



        Object[][] data = {
                {"Середнє арифметичне", (double) list.get(0) - q1, list.get(0), (double) list.get(0) + q1,BigDecimal.valueOf((double) list.get(3) / Math.sqrt(size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"Дисперсія незсунена", (double) list.get(1) - q2, list.get(1), (double) list.get(1) + q2,BigDecimal.valueOf(((double) list.get(2) / Math.sqrt(2 * size))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"Дисперсія зсунена", "-", list.get(2), "-","-"},
                {"Середнє квадратичне", BigDecimal.valueOf((double) list.get(3) - q3).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), list.get(3), (double) list.get(3) + q3,BigDecimal.valueOf((double) list.get(3) / Math.sqrt(2 * size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"Усічене  середнє", "-", list.get(4), "-","-"},
                {"Асиметрія незсунена", (double) list.get(5) - q5, list.get(5), (double) list.get(5) + q5,BigDecimal.valueOf(Math.sqrt((double) 6 * (size - 2) / ((size + 1) * (size + 3)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"Асиметрія зсунена", "-", list.get(6), "-","-"},
                {"Ексцес незсунений", (double) list.get(7) - q7, list.get(7), (double) list.get(7) + q7,BigDecimal.valueOf((24.0 / size) * (1 - (225.0 / (15 * size + 124)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"Ексцес зсунений", "-", list.get(8), "-","-"},
                {"Контрексцес", (double) list.get(9) - q9, list.get(9), BigDecimal.valueOf((double) list.get(9) + q9).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),BigDecimal.valueOf(Math.sqrt((double) list.get(8) / (29 * size)) * Math.pow(Math.pow(Math.abs((1 + Math.pow((double) list.get(8), 2))), 3), 0.25)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"Пірсона", (double) list.get(10) - q10, list.get(10), BigDecimal.valueOf((double) list.get(10) + q10).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),BigDecimal.valueOf((double) list.get(10) * (Math.sqrt((1 + 2 * Math.pow((double) list.get(10), 2)) / (2 * size)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()},
                {"MED", "-", list.get(11), "-","-"},
                {"MAD", "-", list.get(12), "-","-"},
                {"Непараметричний коефіцієнт варіації","-", list.get(13), "-","-"},
                {"MED Уолша", "-", list.get(14), "-","-"}
        };

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Основні характеристики");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        SimpleTableDemo newContentPane = new SimpleTableDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
