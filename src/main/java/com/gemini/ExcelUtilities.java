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

    public static HashMap<String, LinkedHashMap<Integer, List<String>>> read(String fileLocation) throws IOException {
        HashMap<String, LinkedHashMap<Integer, List<String>>> data = new LinkedHashMap<>();

        try (FileInputStream file = new FileInputStream(fileLocation);
             ReadableWorkbook wb = new ReadableWorkbook(file)) {
            wb.getSheets().forEach(sheet -> {
                LinkedHashMap<Integer, List<String>> stepData = new LinkedHashMap<>();
                featureName = sheet.getName();
                try (Stream<Row> rows = sheet.openStream()) {
                    rows.forEach(r -> {
                        stepData.put(r.getRowNum(), new ArrayList<>());
                        data.put(sheet.getName(), stepData);
                        for (Cell cell : r) {
                            if (cell == null) continue;
                            data.get(sheet.getName()).get(r.getRowNum()).add(cell.getRawValue());
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
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
        return lastKey == null ? 0 : lastKey;
    }

    public static List<FeaturePOJO> getTestCases(HashMap<String, LinkedHashMap<Integer, List<String>>> data) {
        List<FeaturePOJO> featurePOJOList = new ArrayList<>();

        for (Map.Entry<String, LinkedHashMap<Integer, List<String>>> entry : data.entrySet()) {
            List<Testcase> testcaseList = new ArrayList<>();
            Testcase testcase = new Testcase();
            List<String> steps = new ArrayList<>();
            FeaturePOJO featurePOJO = new FeaturePOJO();

            for (int i = 2; i <= testCaseSize(entry.getValue()); i++) {
                if (entry.getValue().get(i) == null || entry.getValue().get(i).stream().allMatch(Objects::isNull)) continue;
                if (entry.getValue().get(i).get(0) != null) {
                    Testcase testcase1 = new Testcase();
                    List<String> steps1 = new ArrayList<>();
                    testcase1.setSno(entry.getValue().get(i).get(0));
                    testcase1.setScenarioName(entry.getValue().get(i).get(1));

                    steps1.add(entry.getValue().get(i).get(2) + "----When");
                    try {
                        if (entry.getValue().get(i).get(3) == null) {
                            throw new NullPointerException();
                        }
                        steps1.add(entry.getValue().get(i).get(3) + "----Then");
                    } catch (Exception e) {
                    }
                    testcase1.setSteps(steps1);
                    testcase = testcase1;
                    steps = steps1;
                    testcaseList.add(testcase);

                } else {
                    steps.add(entry.getValue().get(i).get(2) + "----When");
                    try {
                        if (entry.getValue().get(i).get(3) == null) {
                            throw new NullPointerException();
                        }
                        steps.add(entry.getValue().get(i).get(3) + "----Then");
                    } catch (Exception e) {
                    }
                    testcase.setSteps(steps);
                }
            }
            featurePOJO.setFeatureName(entry.getKey());
            featurePOJO.setTestcase(testcaseList);
            featurePOJOList.add(featurePOJO);
        }
        System.out.println("----------------------------------------");
        System.out.println(featurePOJOList);
        return featurePOJOList;
    }


}