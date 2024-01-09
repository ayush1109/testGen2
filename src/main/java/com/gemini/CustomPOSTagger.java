package com.gemini;//package org.example;//package org.example;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import opennlp.tools.postag.*;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;

public class CustomPOSTagger {
    static String modelFile = "";

    public static void main(String[] args) {
        try {
//            Train a custom part-of-speech tagging model
//            trainModel("src/main/java/org/example/training_data.txt", "custom-pos-model-all.bin");


            String sampleString = "navagite to apply button";
                modelFile="custom-pos-model-all.bin";

            //  Test the model
            testModel(sampleString, modelFile);

            List<TokenWithTag> result = testModel(sampleString, modelFile);
            HashMap<String, String> tokenToKeys = new HashMap<>();
            // Display the result
            System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
            for (TokenWithTag tokenWithTag : result) {
                System.out.println(tokenWithTag.token + "\t:\t" + tokenWithTag.tag + "\t:\t" + tokenWithTag.probability);
            }

            tokenToKeys = MapTokenToKeys.map(result);

            System.out.println(GenerateStep.generate(tokenToKeys));


            String action = tokenToKeys.get("A");
            switch (action) {
                case "open":
                    Actions.initialiseBrowser(tokenToKeys.get("AIN"));
                    break;

                case "load":
                    Actions.openWebsite(tokenToKeys.get("AD"));
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
        try (InputStream tokenModelIn = new FileInputStream("src/main/resources/trainingmodels/en-token.bin");
             InputStream posModelIn = new FileInputStream("src/main/resources/models/" + modelFile)) {

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






