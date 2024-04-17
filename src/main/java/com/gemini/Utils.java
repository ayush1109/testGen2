package com.gemini;

import com.gemini.gpog.RequiredDirectories;
import com.gemini.gpog.RequiredFiles;
import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.*;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class Utils {

    private static List<String> allSentences;

    public static List<String> clickCommands = Arrays.asList("Press", "Tap", "Choose", "Hit", "Click");

    public static List<String> typeCommands = Arrays.asList("Enter", "Input", "Write", "Insert", "Set", "Type");

    public static List<String> featureFileNames = new ArrayList<>();


    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    public static CompilationUnit createEnhancedCompilationUnit(String name) throws IOException {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(name)));
        cu.setImports(getAllImports());
        return cu;
    }

    public static void setTypeDeclaration(CompilationUnit c, String className) throws IOException {
        // create the type declaration and class
        String classToExtend = "";
        ClassOrInterfaceType typeForExtends = new ClassOrInterfaceType(classToExtend);
        List<ClassOrInterfaceType> extendsList = new ArrayList<ClassOrInterfaceType>();
        extendsList.add(typeForExtends);
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(null, ModifierSet.PUBLIC, null, false, StringUtils.capitalize(className), null, null, null, null);

        ASTHelper.addTypeDeclaration(c, type);
    }

    public static void createConstructor(CompilationUnit c, String className) {
        ConstructorDeclaration declaration = new ConstructorDeclaration(ModifierSet.PUBLIC, className);

        BlockStmt block = new BlockStmt();
        declaration.setBlock(block);

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("WebDriver", 0), "driver"));
        declaration.setParameters(parameters);

        ASTHelper.addStmt(block, new NameExpr("super(driver)"));

        ASTHelper.addMember(c.getTypes().get(0), declaration);

    }

    public static void setTypeDeclarationStepDefinition(CompilationUnit c, String className) throws IOException {
        // create the type declaration and class
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(null, ModifierSet.PUBLIC, null, false, className, null, null, null, null);

        ASTHelper.addTypeDeclaration(c, type);
    }

    private static List<ImportDeclaration> getAllImports() throws IOException {
        List<ImportDeclaration> imports = new LinkedList<>();

        //adding imports for implementation class
        imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.By"), false, false));
        imports.add(new ImportDeclaration(new NameExpr("com.gemini.gpog.annotation.LocatorType"), false, false));
        return imports;
    }

    private static String refactorValue(String value) {
        String updatedValue = "";
        updatedValue = value.replace(": //", "(\"//") + "\")";
        updatedValue = updatedValue.replace("cssSelector: ", "cssSelector(\"");
        return updatedValue;
    }

    public static void setStepDefinitionVariable(CompilationUnit c, String value, String locatorName) {
        //setting the variable for step definition
        String locatorValue = refactorValue(value);
        String annotationValue = "";
        if(locatorName.contains("-"))
        annotationValue = "\"" + locatorName.split("-")[locatorName.split("-").length - 1] + "\"";
        String annotationType = "LocatorType";
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        MemberValuePair mvp = new MemberValuePair("value", new NameExpr(annotationValue.toLowerCase()));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        VariableDeclarator v = new VariableDeclarator();
        v.setId(new VariableDeclaratorId(locatorName.split("-")[0].replace(".", "").replace("?", "")));
        v.setInit(new ObjectCreationExpr(null, new ClassOrInterfaceType(null, locatorValue), null));
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.STATIC,
                ASTHelper.createReferenceType("By", 0), v);
        f.setAnnotations(list_espr);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

    public class JavaClassAsBytes extends SimpleJavaFileObject {

        protected ByteArrayOutputStream bos =
                new ByteArrayOutputStream();

        public JavaClassAsBytes(String name, Kind kind) {
            super(URI.create("string:///" + name.replace('.', '/')
                    + kind.extension), kind);
        }

        public byte[] getBytes() {
            return bos.toByteArray();
        }

        @Override
        public OutputStream openOutputStream() {
            return bos;
        }
    }

    public class InMemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {

        private Map<String, JavaClassAsBytes> compiledClasses;

        public InMemoryFileManager(StandardJavaFileManager standardManager) {
            super(standardManager);
            this.compiledClasses = new Hashtable<>();
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location,
                                                   String className, JavaFileObject.Kind kind, FileObject sibling) {

            JavaClassAsBytes classAsBytes = new JavaClassAsBytes(className, kind);
            compiledClasses.put(className, classAsBytes);

            return classAsBytes;
        }

        public Map<String, JavaClassAsBytes> getBytesMap() {
            return compiledClasses;
        }
    }

    public class InMemoryClassLoader extends ClassLoader {

        private InMemoryFileManager manager;

        public InMemoryClassLoader(ClassLoader parent, InMemoryFileManager manager) {
            super(parent);
            this.manager = requireNonNull(manager, "manager must not be null");
        }
    }

    public class JavaSourceFromString extends SimpleJavaFileObject {

        private String sourceCode;

        public JavaSourceFromString(String name, String sourceCode) {
            super(URI.create("" + name.replace("\\", "/") + Kind.SOURCE.extension),
                    Kind.SOURCE);
            this.sourceCode = requireNonNull(sourceCode, "sourceCode must not be null");
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return sourceCode;
        }
    }

    public static void compileJavaFile(String javaFileName) {
        // Get the Java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {
            System.out.println("Java Compiler is not available. Make sure you are using a JDK.");
            return;
        }

        // Compilation options (output directory, classpath, etc.)
        // For simplicity, the example uses the default output directory (current working directory)
        // You may need to adjust the options based on your project structure.
        String[] compileOptions = {"-d", "target/classes"};
        // Create a compilation task
        int compilationResult = compiler.run(null, null, null, javaFileName);

        // Check the result of compilation
        if (compilationResult == 0) {
            System.out.println("Compilation successful!");
        } else {
            System.out.println("Compilation failed with result code: " + compilationResult);
        }
    }

    public void savePageObjectsOnFileSystem(String directoryName, String className, CompilationUnit c,
                                            boolean stepGeneration) throws IOException {

        String data = "";
        String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "java" + File.separator + directoryName + StringUtils.capitalize(className);
        File f = new File(fileNameToCreate + ".java");


        if (BooleanUtils.isTrue(stepGeneration)) {
            data = StringUtils.replace(c.toString(), "xpath = ", "").toString();
        } else {
            data = c.toString();
        }
//        FileWriter writer = new FileWriter(f);
//        writer.write(data);
//        writer.flush();


//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
//        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
//
//        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(fileNameToCreate + ".java", c.toString()));
//
//        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, sourceFiles);
//
//        boolean result = task.call();


        FileUtils.writeStringToFile(f, data);


    }

//    private static void shiftClassFileToTarget(String fileName, String directoryName) throws IOException {
////        String srcDir = "C:\\Users\\ayush.garg\\Downloads\\Demo\\Demo\\src\\main\\java\\com\\gemini\\Dashboard_index.java";
//        String srcDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
//                + File.separator + "java" + File.separator + "com" + File.separator + "gemini" + File.separator + StringUtils.capitalize(fileName) + ".java";
////        String tarDir = "C:\\Users\\ayush.garg\\Downloads\\Demo\\Demo\\target\\classes\\com\\gemini\\Dashboard_index.class";
//        String tarDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes"
//                 + File.separator + "com" + File.separator + "gemini" + File.separator  + StringUtils.capitalize(fileName) + ".class";
//        FileUtils.copyFile(new File(srcDir), new File(tarDir));
//    }

    public static void shiftClassFileToTarget(String fileName, String directoryName) throws IOException {
//        String srcDir = "C:\\Users\\ayush.garg\\Downloads\\Demo\\Demo\\src\\main\\java\\com\\gemini\\Dashboard_index.java";
        String srcDir = fileName + ".class";
//        String tarDir = "C:\\Users\\ayush.garg\\Downloads\\Demo\\Demo\\target\\classes\\com\\gemini\\Dashboard_index.class";
        FileUtils.copyFile(new File(srcDir), new File(directoryName));
    }


    public static void correctXpaths(String directory, String fileName) throws IOException {
        String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "java" + File.separator + directory + StringUtils.capitalize(fileName);
        // String fileNameToCreate = System.getProperty("user.dir") +poName;

        String tarDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes"
                + File.separator + "com" + File.separator + "gemini" + File.separator + "locator" + File.separator + StringUtils.capitalize(fileName) + ".class";

        File f = new File(fileNameToCreate + ".java");

        String content = FileUtils.readFileToString(f, StandardCharsets.UTF_8);

        String updatedContent = StringUtils.replace(content, "new By", "By")
                .replace("\")()", "\")")
                .replace("static", "public static");
        //temprary
//                .replace("static", "@LocatorType(value = \"button\")\npublic static");

        FileUtils.writeStringToFile(f, updatedContent);
        compileJavaFile(fileNameToCreate + ".java");
        shiftClassFileToTarget(fileNameToCreate, tarDir);
        deleteClassFiles(fileNameToCreate);

    }

    public static void deleteClassFiles(String fileName) throws IOException {
        String srcDir = fileName + ".class";
        FileUtils.delete(new File(srcDir));
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

//    private static ArrayList<String> createSentences(List<String> synoym, String data, String toReplace) {
//        ArrayList<String> sentences = new ArrayList<>();
//        synoym.forEach(s -> {
//             sentences.add(StringUtils.replace(data, toReplace, s));
//        });
//        return sentences;
//    }

    private static ArrayList<String> extractWordsForDictonary(String data) {

        ArrayList<String> dictionary = new ArrayList<>();
        clickCommands = Arrays.asList("Press", "Tap", "Choose", "Select", "Hit", "Click");
        typeCommands = Arrays.asList("Enter", "Input", "Write", "Insert", "Set", "Type");
        String[] field2Replacements = {"password", "username", "Gmail", "userfield", "sample", "demo"};
        String[] parts = data.split(" ");
        int valueOfA = 0;
        int valueofAin = 0;
        String listToUse = "";
        for (int i = 0; i < parts.length; i++) {

            if (parts[i].split("_")[1].equals("A")) {
                valueOfA = i;
            } else if (parts[i].split("_")[1].equals("AIN")) {

                valueofAin = i;
            }
        }
        if (clickCommands.contains(parts[valueOfA].split("_")[0])) {
            listToUse = "clickCommands";
        } else listToUse = "typeCommands";


        if (listToUse == "clickCommands") {
            for (String field1Replacement : clickCommands) {
                for (String field2Replacement : field2Replacements) {
                    String partOne = parts[valueOfA].split("_")[0];
                    String partTwo = parts[valueofAin].split("_")[0];
                    dictionary.add(data.replace(partOne, field1Replacement).replace(partTwo, field2Replacement));

                }
            }
        } else {
            for (String field1Replacement : typeCommands) {
                for (String field2Replacement : field2Replacements) {
                    String partOne = parts[valueOfA].split("_")[0];
                    String partTwo = parts[valueofAin].split("_")[0];
                    dictionary.add(data.replace(partOne, field1Replacement).replace(partTwo, field2Replacement));

                }
            }
        }

        return dictionary;
    }

    static boolean
    createTrainingFile(String trainingData) throws IOException {
        allSentences = new ArrayList<>();
        Path path = Paths.get("src/main/java/com/gemini/training_data_custom.txt");

        ArrayList<String> output = extractWordsForDictonary(trainingData);

        AtomicBoolean flag = new AtomicBoolean(true);
        output.forEach(data -> {
            try {
                if (Files.exists(path)) {
                    FileUtils.writeStringToFile(path.toFile(), data, true);
                } else {
                    Files.createFile(path);
                    FileUtils.writeStringToFile(path.toFile(), data, true);
                }
                FileUtils.writeStringToFile(path.toFile(), "\n", true);
            } catch (IOException e) {
                flag.set(false);
            }
        });
        return flag.get();
    }

    static List<TokenWithTag> testModel(String testSentence, String modelFile) throws IOException {
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
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public static void copyGPOGDirectoriesToProject() throws IOException {

        RequiredDirectories.stream().forEach(directory -> {
            File sourceDirectory = null;
            String fileNameToCreate = null;
            try {
                if (directory.getFolder().equals("gpog")) {
                    sourceDirectory = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\gpog\\" + directory.name());
                    fileNameToCreate = readProperties("projectPath") + File.separator + "src" + File.separator + "test"
                            + File.separator + "java" + File.separator + "com" + File.separator + "gemini" + File.separator + "gpog" + File.separator + directory.name();
                } else {
                    sourceDirectory = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\" + directory.name());
                    fileNameToCreate = readProperties("projectPath") + File.separator + "src" + File.separator + "test"
                            + File.separator + "java" + File.separator + "com" + File.separator + "gemini" + File.separator + directory.name();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            File destinationDirectory = new File(fileNameToCreate);
            try {
                FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void copyGPOGFilesToProject() {
        RequiredFiles.stream().forEach(file -> {
            File sourceDirectory = null;
            String fileNameToCreate = null;
            try {
                if (file.getFolder().equals("gpog")) {
                    sourceDirectory = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\gpog\\pageobjectgenerator\\" + file.name() + ".java");
                    fileNameToCreate = readProperties("projectPath") + File.separator + "src" + File.separator + "test"
                            + File.separator + "java" + File.separator + "com" + File.separator + "gemini" + File.separator + "gpog" + File.separator + "pageobjectgenerator" + File.separator + file.name() + ".java";
                } else {
                    for (String featureName: featureFileNames
                         ) {
                        sourceDirectory = new File(System.getProperty("user.dir") + "\\" + featureName + ".feature");
                        fileNameToCreate = readProperties("projectPath") + File.separator + "src" + File.separator + "test"
                                + File.separator + "resources" + File.separator + "features" + File.separator + featureName + ".feature";
                        File destinationDirectory = new File(fileNameToCreate);
                        FileUtils.copyFile(sourceDirectory, destinationDirectory);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            File destinationDirectory = new File(fileNameToCreate);
            try {
                FileUtils.copyFile(sourceDirectory, destinationDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void writePOMFile() throws IOException {
        File pomFile = new File(readProperties("projectPath") + "\\" + "pom.xml");

        String POMContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\POMContent.txt"));

        POMContent = POMContent.replace("${project-name}", readProperties("projectPath").split("/")[1]);

        FileWriter fileWriter = new FileWriter(pomFile);

        fileWriter.write(POMContent);

        fileWriter.close();

    }

    public static void createRunnerFile() throws IOException {
        File runnerFile = new File(readProperties("projectPath") + "/src/test/java/com/gemini/Runner.java");

        String runnerContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\RunnerContent.txt"));

        FileWriter fileWriter = new FileWriter(runnerFile);

        fileWriter.write(runnerContent);

        fileWriter.close();
    }

    public static void createSerenityFile() throws IOException {
        File serenityFile = new File(readProperties("projectPath") + "/src/test/resources/serenity.conf");

        String serenityContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\serenityContent.txt"));

        serenityContent = serenityContent.replace("${base-url}", "\"" + readProperties("url") + "\"");

        FileWriter fileWriter = new FileWriter(serenityFile);

        fileWriter.write(serenityContent);

        fileWriter.close();
    }

    public static void createConfigFile() throws IOException {
        File configFile = new File(readProperties("projectPath") + "/src/test/resources/config.properties");

        String configContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\configContent.txt"));

        FileWriter fileWriter = new FileWriter(configFile);

        fileWriter.write(configContent);

        fileWriter.close();
    }

    public static String replaceStringsInDoubleQuotes(String input) {
        // Regular expression to match strings within double quotes
        String regex = "\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Replace strings within double quotes with (.*?)
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, "\\\"(.*?)\\\"");
        }
        matcher.appendTail(result);

        return result.toString().replace("\"", "\\\"");
    }

    public static void executeMethod(org.eclipse.jdt.core.dom.MethodDeclaration node, List<String> values) throws Exception {
        String methodBody = node.getBody().toString();
        String className = node.resolveBinding().getDeclaringClass().getQualifiedName();
        String fileName = className.replaceAll("\\.", "\\\\");
        String methodName = node.getName().getFullyQualifiedName();
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "java" + File.separator + fileName;
            String tarDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes"
                    + File.separator + fileName + ".class";

            compileJavaFile(fileNameToCreate + ".java");
            shiftClassFileToTarget(fileNameToCreate, tarDir);
            deleteClassFiles(fileNameToCreate);
            clazz = Class.forName(className);
        }
        Object instance = clazz.getDeclaredConstructor().newInstance();

        Class<?>[] parameterTypes = new Class<?>[values.size()];
        for (int i = 0; i < values.size(); i++) {
            parameterTypes[i] = values.get(i).getClass();
        }

        Method method = clazz.getMethod(methodName, parameterTypes);
        try {

            if (values.size() == 6) {
                method.invoke(instance, values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5));
            } else if (values.size() == 5) {
                method.invoke(instance, values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
            } else if (values.size() == 4) {
                method.invoke(instance, values.get(0), values.get(1), values.get(2), values.get(3));
            } else if (values.size() == 3) {
                method.invoke(instance, values.get(0), values.get(1), values.get(2));
            } else if (values.size() == 2) {
                method.invoke(instance, values.get(0), values.get(1));
            } else if (values.size() == 1) {
                method.invoke(instance, values.get(0));
            } else method.invoke(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
