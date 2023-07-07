package com.isachenko.transferProject.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TransferService {

    public void initialize(Map<String, Integer> accounts) {
        try {
            BufferedReader br =
                    new BufferedReader(new FileReader("src/main/java/com/isachenko/transferProject/files/AccsInfo"));
            String s;
            while ((s = br.readLine()) != null) {
                accounts.put(s.substring(0, 11), Integer.parseInt(s.substring(13)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseTransfers(List<File> fileList, Map<String, Integer> accounts) {
        ParseDirectory.getFiles(new File("src/main/java/com/isachenko/transferProject/files/differentFiles"), fileList);
        try {
            for (File file : fileList) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                String res = null;
                while ((line = br.readLine()) != null) {
                    if (line.matches("\\d{5}\\-\\d{5}\\|\\d{5}\\-\\d{5}\\: \\d+")) {
                        int cashOut = accounts.get(line.substring(0, 11)) - Integer.parseInt(line.substring(25));
                        int cashIn = accounts.get(line.substring(12, 23)) + Integer.parseInt(line.substring(25));
                        if (accounts.get(line.substring(0, 11)) > Integer.parseInt(line.substring(25))) {
                            accounts.replace(line.substring(0, 11), cashOut);
                            accounts.replace(line.substring(12, 23), cashIn);
                            res = " Successful transactions";
                        } else {
                            res = " Unsuccessfully, invalid amount";
                        }
                    } else {
                        res = "file is not suitable, possibly incorrect data entered";
                    }
                }
                try (FileWriter reportFile =
                             new FileWriter("src/main/java/com/isachenko/transferProject/files/reportFile", true)) {
                    reportFile.write("Date: " + LocalDateTime.now() +
                            ". File name - " + file.getName() + ". Result - " + res + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteAccInfo(Map<String, Integer> accounts) {
        try (FileWriter fileWriter = new FileWriter("src/main/java/com/isachenko/transferProject/files/AccsInfo")) {
            for (Map.Entry e : accounts.entrySet()) {
                fileWriter.write(e.getKey() + ": " + e.getValue() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readReportFile() {
        try {
            FileReader readReport = new FileReader("src/main/java/com/isachenko/transferProject/files/reportFile");
            int i;
            while ((i = readReport.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}