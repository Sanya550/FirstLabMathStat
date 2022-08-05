package com.example.idealjavafx;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class VarRow extends JPanel {
    ArrayList arrayList1 = HelloController.arrayList;
    public VarRow() {
        super(new GridLayout(1, 0));
        HashMap hashMap = Helper.varRow();
        String[] columnNames = {"Число", "Частота"};
        ArrayList arr1 = new ArrayList();
        for (int i = 0; i < arrayList1.size(); i++) {
            arr1.add(arrayList1.get(i));
        }
        int size = arr1.size();

        Object[][] data = {
                {arr1.get(0), hashMap.get(arr1.get(0))},
                {arr1.get(1), hashMap.get(arr1.get(1))},
                {arr1.get(2), hashMap.get(arr1.get(2))},
                {arr1.get(3), hashMap.get(arr1.get(3))},
                {arr1.get(4), hashMap.get(arr1.get(4))},
                {arr1.get(5), hashMap.get(arr1.get(5))},
                {arr1.get(6), hashMap.get(arr1.get(6))},
                {arr1.get(7), hashMap.get(arr1.get(7))},
                {arr1.get(8), hashMap.get(arr1.get(8))},
                {arr1.get(9), hashMap.get(arr1.get(9))},
                {arr1.get(10), hashMap.get(arr1.get(10))},
                {arr1.get(11), hashMap.get(arr1.get(11))},
                {arr1.get(12), hashMap.get(arr1.get(12))},
                {arr1.get(13), hashMap.get(arr1.get(13))},
                {arr1.get(14), hashMap.get(arr1.get(14))},
                {arr1.get(15), hashMap.get(arr1.get(15))},
                {arr1.get(16), hashMap.get(arr1.get(16))},
                {arr1.get(17), hashMap.get(arr1.get(17))},
                {arr1.get(18), hashMap.get(arr1.get(18))},
                {arr1.get(19), hashMap.get(arr1.get(19))},
                {arr1.get(20), hashMap.get(arr1.get(20))},
                {"...", "..."},
                {arr1.get(size-5),hashMap.get(arr1.get(size-5))},
                {arr1.get(size-4),hashMap.get(arr1.get(size-4))},
                {arr1.get(size-3),hashMap.get(arr1.get(size-3))},
                {arr1.get(size-2),hashMap.get(arr1.get(size-2))}

        };

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    public static void createAndShowGUI2(ArrayList arrayList, TreeMap treeMap) {
        //Create and set up the window.
        JFrame frame = new JFrame("Варіаційний ряд");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.

        VarRow newContentPane = new VarRow();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
