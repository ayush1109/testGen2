package org.example;//package org.example;//package org.example;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import opennlp.tools.postag.*;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;

public class CustomPOSTagger {
    static String modelFile = "";

    public static void main(String[] args) {
        try {

////            DO NOT DELETE THIS CODE
//            List<String> sentences = readSentencesFromFile("src/main/java/org/example/training_data_browser.txt");
//            //  Create a training file
//            createTrainingFile(sentences, "src/main/java/org/example/training_data-browser.txt");
//
////            Train a custom part-of-speech tagging model
//            trainModel("src/main/java/org/example/training_data_url.txt", "custom-pos-model-url.bin");
//

            String sampleString = "open into mis";
            if (sampleString.startsWith("open")) {
                modelFile = "custom-pos-model-browser.bin";
            } else if (sampleString.startsWith("click")){
                modelFile = "custom-pos-model.bin";
            }
            else if (sampleString.startsWith("load"))
            {
                modelFile="custom-pos-model-url.bin";
            }

            //  Test the model
            testModel(sampleString, modelFile);

            List<TokenWithTag> result = testModel(sampleString, modelFile);
            HashMap<String, String> maps = new HashMap<>();
            // Display the result
            System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
            for (TokenWithTag tokenWithTag : result) {
                System.out.println(tokenWithTag.token + "\t:\t" + tokenWithTag.tag + "\t:\t" + tokenWithTag.probability);
                maps.put(tokenWithTag.tag, tokenWithTag.token);
            }
            String action = maps.get("A");
            switch (action) {
                case "open":
                    Actions.initialiseBrowser(maps.get("AIN"));
                    break;

                case "load":
                    Actions.openWebsite(maps.get("AD"));
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readSentencesFromFile(String filePath) throws IOException {
        List<String> sentences = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
        }
        return sentences;
    }

    private static void createTrainingFile(List<String> sentences, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String sentence : sentences) {
                writer.write(sentence);
                writer.newLine();
            }
        }
    }

    private static void trainModel(String trainingFile, String modelFile) throws IOException {
        try (InputStream dataIn = new FileInputStream(trainingFile)) {
            ObjectStream<String> lineStream = new PlainTextByLineStream(new InputStreamFactory() {
                @Override
                public InputStream createInputStream() throws IOException {
                    return dataIn;
                }
            }, StandardCharsets.UTF_8);

            ObjectStream<POSSample> sampleStream = new WordTagSampleStream(lineStream);

            TrainingParameters params = TrainingParameters.defaultParams();
            params.put(TrainingParameters.ITERATIONS_PARAM, "100");

            POSModel model = POSTaggerME.train("en", sampleStream, params, new POSTaggerFactory());
            model.serialize(new FileOutputStream(modelFile));
        }
    }

    private static List<TokenWithTag> testModel(String testSentence, String modelFile) throws IOException {
        List<TokenWithTag> result = new ArrayList<>();
        try (InputStream tokenModelIn = new FileInputStream("src/main/resources/en-token.bin");
             InputStream posModelIn = new FileInputStream(modelFile)) {

            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String[] tokens = tokenizer.tokenize(testSentence);

            POSModel posModel = new POSModel(posModelIn);
            POSTaggerME posTagger = new POSTaggerME(posModel);

            String[] tags = posTagger.tag(tokens);
            double[] probs = posTagger.probs();

            for (int i = 0; i < tokens.length; i++) {
                TokenWithTag tokenWithTag = new TokenWithTag(tokens[i], tags[i], probs[i]);
                result.add(tokenWithTag);
            }
        }
        return result;
    }
}






