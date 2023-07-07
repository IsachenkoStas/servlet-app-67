package com.isachenko.transferProject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArchiveHandling {

    public static void moveToArchive(List<File> fileList) {
        String str_target = "src/main/java/com/isachenko/transferProject/files/archiveFiles/archive";
        Path moveFile = null;
        try {
            for (File file : fileList) {
                moveFile = Files.move(Paths.get(file.getPath()), Paths.get(str_target + file.getName()));
            }
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if (moveFile != null) {
            System.out.println("File moved successfully.");
        } else {
            System.out.println("File movement failed.");
        }
    }
}