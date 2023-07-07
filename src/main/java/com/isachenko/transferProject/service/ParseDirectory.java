package com.isachenko.transferProject.service;

import java.io.File;
import java.util.List;

public class ParseDirectory {

    public static void getFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        getFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".txt")) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
}