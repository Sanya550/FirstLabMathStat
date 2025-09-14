package com.example.idealjavafx;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.String.format;

public class ExportHelper {
    public static void exportMapToFile(LinkedHashMap<Integer, Double> map) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Виберіть папку для зберігання файла");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File("C:\\Users\\Alex\\Desktop\\Data\\KPI\\Master Dyplom\\timerows"));

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = chooser.getSelectedFile();
            var nameOfFile = "map_output" + generateRandomString(5) + ".txt";
            Path outputPath = Paths.get(selectedDir.getAbsolutePath(), nameOfFile);

            try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
                for (Map.Entry<Integer, Double> entry : map.entrySet()) {
//                    writer.write(entry.getKey() + " " + entry.getValue());
                    writer.write(String.valueOf(entry.getValue()));
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, format("Файл %s успішно збережений", nameOfFile), "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, format("Файл не збережено", nameOfFile), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
