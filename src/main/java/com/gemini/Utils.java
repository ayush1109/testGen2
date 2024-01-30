package com.gemini;

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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Utils {
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
        return value.replace(": ", "(\"") + "\")";
    }

    public static void setStepDefinitionVariable(CompilationUnit c, String value, String locatorName) {
        //setting the variable for step definition
        String locatorValue = refactorValue(value);
        String annotationValue = "";
        annotationValue= "\"" + locatorName.split("_")[1] + "\"";
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
        v.setId(new VariableDeclaratorId(locatorName.split("_")[0]));
        v.setInit(new ObjectCreationExpr(null, new ClassOrInterfaceType(null, locatorValue), null));
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.STATIC,
                ASTHelper.createReferenceType("By", 0), v);
        f.setAnnotations(list_espr);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

    public static void savePageObjectsOnFileSystem(String directoryName, String className, CompilationUnit c,
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
        FileUtils.writeStringToFile(f, data);

    }


    public static void correctXpaths(String directory, String fileName) throws IOException {
        String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "java" + File.separator + directory + StringUtils.capitalize(fileName);
        // String fileNameToCreate = System.getProperty("user.dir") +poName;
        File f = new File(fileNameToCreate + ".java");

        String content = FileUtils.readFileToString(f, StandardCharsets.UTF_8);

        String updatedContent = StringUtils.replace(content, "new By.xpath", "By.xpath")
                .replace("()", "")
                .replace("static", "public static");
                //temprary
//                .replace("static", "@LocatorType(value = \"button\")\npublic static");

        FileUtils.writeStringToFile(f, updatedContent);

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
        }
        return result;
    }
}
