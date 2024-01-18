package com.gemini;

import opennlp.tools.postag.*;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TrainModel {

    public static void main(String[] args) throws IOException {
//                    Train a custom part-of-speech tagging model
            TrainModel.trainModel("src/main/java/com/gemini/training_data_input.txt", "src/main/resources/models/custom-pos-model-input.bin");
    }

    public static void trainModel(String trainingFile, String modelFile) throws IOException {
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
}
