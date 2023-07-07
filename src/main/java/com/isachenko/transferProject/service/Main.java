package com.isachenko.transferProject.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransferService transferService = new TransferService();
        Map<String, Integer> accounts = new HashMap<>();
        List<File> fileList = new ArrayList<>();
        transferService.initialize(accounts);
        System.out.print("Enter the number - ");
        int choice = new Scanner(System.in).nextInt();
        if (choice == 1) {
            transferService.parseTransfers(fileList, accounts);
            transferService.rewriteAccInfo(accounts);
            ArchiveHandling.moveToArchive(fileList);
        } else if (choice == 2) {
            transferService.readReportFile();
        } else {
            System.out.println("Please, enter either 1 or 2");
        }
    }
}