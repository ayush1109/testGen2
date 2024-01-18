package com.gemini;

import edu.stanford.nlp.util.CollectionUtils;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class ExcelUtilities {

    static String featureName;

    public static void main(String[] args) throws IOException {
//        Map<Integer, List<String>> data = read("C:\\Users\\ayush.garg\\Downloads\\GemGen Test Scenarios.xlsx");
        Map<Integer, List<String>> data = readBySheetNumber("C:\\Users\\ayush.garg\\Downloads\\GemGen Test Scenarios.xlsx", 1);
        System.out.println(data);
//        getTestCases(data);
    }

    public static Map<Integer, List<String>> read(String fileLocation) throws IOException {
        Map<Integer, List<String>> data = new HashMap<>();

        try (FileInputStream file = new FileInputStream(fileLocation);
             ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            featureName = sheet.getName();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    data.put(r.getRowNum(), new ArrayList<>());

                    for (Cell cell : r) {
                        if(cell == null) continue;
                        data.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        }

        return data;
    }

    public static Map<Integer, List<String>> readBySheetNumber(String fileLocation, int number) throws IOException {
        Map<Integer, List<String>> data = new HashMap<>();

        try (FileInputStream file = new FileInputStream(fileLocation);
             ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Optional<Sheet> optionalSheet = wb.getSheet(number);
            Sheet sheet = optionalSheet.orElse(null);
            featureName = sheet.getName();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    data.put(r.getRowNum(), new ArrayList<>());

                    for (Cell cell : r) {
                        if(cell == null) continue;
                        data.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        }

        return data;
    }

    public static List<Testcase> getTestCases(Map<Integer, List<String>> data) {
        List<Testcase> testcaseList = new ArrayList<>();
        Testcase testcase = new Testcase();
        List<String> steps = new ArrayList<>();

        for (int i = 2; i <= data.size(); i++) {
            if (data.get(i) == null || data.get(i).stream().allMatch(Objects::isNull)) continue;
            if (data.get(i).get(0) != null) {
                Testcase testcase1 = new Testcase();
                List<String> steps1 = new ArrayList<>();
                testcase1.setFeatureName(featureName);
                testcase1.setSno(data.get(i).get(0));
                testcase1.setScenarioName(data.get(i).get(1));

                steps1.add(data.get(i).get(2));
                steps1.add(data.get(i).get(3));
                testcase1.setSteps(steps1);
                testcase = testcase1;
                steps = steps1;
                testcaseList.add(testcase);

            }
            else {
                steps.add(data.get(i).get(2));
                steps.add(data.get(i).get(3));
                testcase.setSteps(steps);
            }
        }
        System.out.println("----------------------------------------");
        System.out.println(testcaseList);
        return testcaseList;
    }


}
