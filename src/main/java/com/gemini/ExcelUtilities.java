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

    public static LinkedHashMap<Integer, List<String>> read(String fileLocation) throws IOException {
        LinkedHashMap<Integer, List<String>> data = new LinkedHashMap<>();

        try (FileInputStream file = new FileInputStream(fileLocation);
             ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            featureName = sheet.getName();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    data.put(r.getRowNum(), new ArrayList<>());

                    for (Cell cell : r) {
                        if (cell == null) continue;
                        data.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        }

        return data;
    }

    public static LinkedHashMap<Integer, List<String>> readBySheetNumber(String fileLocation, int number) throws IOException {
        LinkedHashMap<Integer, List<String>> data = new LinkedHashMap<>();

        try (FileInputStream file = new FileInputStream(fileLocation);
             ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Optional<Sheet> optionalSheet = wb.getSheet(number);
            Sheet sheet = optionalSheet.orElse(null);
            featureName = sheet.getName();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    data.put(r.getRowNum(), new ArrayList<>());

                    for (Cell cell : r) {
                        if (cell == null) continue;
                        data.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        }

        return data;
    }

    private static int testCaseSize(LinkedHashMap<Integer, List<String>> data) {
        Integer lastKey = null;
        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            lastKey = entry.getKey();
        }
        return lastKey;
    }

    public static List<Testcase> getTestCases(LinkedHashMap<Integer, List<String>> data) {
        List<Testcase> testcaseList = new ArrayList<>();
        Testcase testcase = new Testcase();
        List<String> steps = new ArrayList<>();

        for (int i = 2; i <= testCaseSize(data); i++) {
            if (data.get(i) == null || data.get(i).stream().allMatch(Objects::isNull)) continue;
            if (data.get(i).get(0) != null) {
                Testcase testcase1 = new Testcase();
                List<String> steps1 = new ArrayList<>();
                testcase1.setFeatureName(featureName);
                testcase1.setSno(data.get(i).get(0));
                testcase1.setScenarioName(data.get(i).get(1));

                steps1.add(data.get(i).get(2) + "----When");
                try {
                    if (data.get(i).get(3) == null) {
                        throw new NullPointerException();
                    }
                    steps1.add(data.get(i).get(3) + "----Then");
                } catch (Exception e) {
                }
                testcase1.setSteps(steps1);
                testcase = testcase1;
                steps = steps1;
                testcaseList.add(testcase);

            } else {
                steps.add(data.get(i).get(2) + "----When");
                try {
                    if (data.get(i).get(3) == null) {
                        throw new NullPointerException();
                    }
                    steps.add(data.get(i).get(3) + "----Then");
                } catch (Exception e) {
                }
                testcase.setSteps(steps);
            }
        }
        System.out.println("----------------------------------------");
        System.out.println(testcaseList);
        return testcaseList;
    }


}