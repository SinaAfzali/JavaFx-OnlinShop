package com.example.OnlineShop;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatingTransactionReceipt {

    public CreatingTransactionReceipt(String text,String code,int type) throws IOException {
        String s="D:\\Programming\\intellij\\OnlineShop\\receipt\\";
        s+="NonShop_";
        if (type==1)s+="PurchaseRecords_";
        else if (type==2)s+="SalesRecords_";
        s+=code;
        s+=".txt";

        String filePath = s;

        FileWriter fileWriter = new FileWriter(filePath);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(text);

        bufferedWriter.close();

        DownloadReceipt(s);
    }


    public void DownloadReceipt(String s) {

        Thread downloadThread = new Thread(() -> {
            try {
                Path filePath = Paths.get(s);

                byte[] fileBytes = Files.readAllBytes(filePath);

                Platform.runLater(() -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("ذخیره فایل");
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
                    fileChooser.setInitialFileName(filePath.getFileName().toString());
                    File selectedFile = fileChooser.showSaveDialog(Methods.stage);
                    if (selectedFile != null) {
                        FileOutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream(selectedFile);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            outputStream.write(fileBytes);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            outputStream.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            } catch (IOException ex) {
                // نمایش پیام خطا به کاربر
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("خطا در دانلود");
                    alert.setHeaderText(null);
                    alert.setContentText("خطا در دانلود فایل رخ داده است.");
                    alert.showAndWait();
                });
                ex.printStackTrace();
            }
        });
        downloadThread.start();

    }

}
