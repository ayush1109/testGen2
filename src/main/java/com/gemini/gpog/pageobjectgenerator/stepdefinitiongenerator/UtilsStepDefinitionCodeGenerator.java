package com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator;

import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.pageobjectgenerator.Settings;
import com.gemini.gpog.pageobjectgenerator.featuregenerator.FeatureCodeGenerator;
import io.netty.handler.logging.LogLevel;
import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.*;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class UtilsStepDefinitionCodeGenerator {

    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    private static String meaningFulName = "";

    private static final String pageName = "^for the <page> page, ";

    /**
     * returns a CompilationUnit object decorated with package and some basic import
     * instructions
     *
     * @return
     */
    public static CompilationUnit createEnhancedCompilationUnit(String name, String type, String locator) {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(name)));
        cu.setImports(UtilsStepDefinitionCodeGenerator.getAllImports(type, locator));
        return cu;

    }

    /**
     * set the TypeDeclaration of a CompilationUnit i.e., whether is a class or an
     * interface
     *
     * @param c
     * @param className
     */
    public static void setTypeDeclaration(CompilationUnit c, String className) {
        // create the type declaration and class
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, className);
        ASTHelper.addTypeDeclaration(c, type);
    }

    /**
     * adds a WebDriver instance to the CompilationUnit c
     *
     * @param c CompilationUnit
     */
    public static void setWebDriverVariable(CompilationUnit c) {
        //setting the driver for the current class
        VariableDeclarator v = new VariableDeclarator();
        v.setId(new VariableDeclaratorId("driver"));
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE,
                ASTHelper.createReferenceType("WebDriver", 0), v);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

    /**
     * adds a WebDriver instance to the CompilationUnit c
     *
     * @param c CompilationUnit
     */
    public static void setStepDefinitionVariable(CompilationUnit c, String classname, String locator) {
        //setting the variable for step definition
        VariableDeclarator v = new VariableDeclarator();
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        v.setId(new VariableDeclaratorId(nameOfFile));
        v.setInit(new ObjectCreationExpr(null, new ClassOrInterfaceType(null, classname), null));
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.PUBLIC,
                ASTHelper.createReferenceType(classname, 0), nameOfFile);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

    /**
     * set the package of the CompilationUnit c
     *
     * @param c
     */
    public static void setPackage(CompilationUnit c) {
        c.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("locators")));
    }

    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    /**
     * adds Selenium imports to the compilation unit
     */
    private static List<ImportDeclaration> getAllImports(String type, String locator) {
        List<ImportDeclaration> imports = new LinkedList<>();

        if (StringUtils.equalsIgnoreCase(type, "PageObject")) {
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium"), false, true));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.FindBy"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
        } else {
            //adding imports for stepDefinition class
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.And"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.Before"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.Given"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.Then"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.When"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.By"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.concurrent.TimeUnit"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("implementation" + "." + locator + "Implementation"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("locators" + "." + locator), false, false));
            imports.add(new ImportDeclaration(new NameExpr("implementation" + "." + "UtilsImplementation"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("pog.utils" + "." + "UtilFunctions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.*"), false, false));

        }
        return imports;
    }

    // before class for driver initialisation
    public static void setBeforeClass(CompilationUnit c, String locator, LoggerUtils loggerUtils) {
        String functionName = "";
        String annotationValue = "";
        String blockToEnter = "driver";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.BEFORE_FUNCTION;
        annotationType = "Before";
        loggerUtils.log(LogLevel.INFO, "Step created for initialisation of the driver");
        NameExpr typeOfException = new NameExpr("InterruptedException");
        List<NameExpr> throws_ = new LinkedList<>();
        throws_.add(typeOfException);
        MethodDeclaration method = new MethodDeclaration(null, ModifierSet.PUBLIC, null, null, ASTHelper.VOID_TYPE, functionName, null, 0, throws_, null);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        na.setName(new NameExpr(annotationType));
        ASTHelper.addStmt(block, new NameExpr("//This function is for initialisation of driver"));
        ASTHelper.addStmt(block, new NameExpr("System" + "." + "setProperty(" + "\"webdriver.chrome.driver\"" + "," + "\"src/test/resources/drivers/chromedriver.exe\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr(locator + " index" + "=" + "PageFactory" + "." + "initElements(" + locator + "." + blockToEnter + "," + locator + "." + "class" + ")"));
        ASTHelper.addStmt(block, new NameExpr(locator + "." + blockToEnter + "." + "manage(" + ")" + "." + "window(" + ")" + "." + "maximize(" + ")"));
        ASTHelper.addStmt(block, new NameExpr(locator + "." + blockToEnter + "." + "manage(" + ")" + "." + "timeouts(" + ")" + "." + "wait(" + "5000" + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodValueThenVisibility(CompilationUnit c, Field field,
                                                                      boolean textOrVisibilityParameter,String locator, LoggerUtils loggerUtils) {

        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        if (!textOrVisibilityParameter) {
            functionName = "verify" + meaningFulName + "IsDisplayed";
            annotationValue = "\"" + "^user verifies " + meaningFulName.toLowerCase() + " is visible" + "$" + "\"";
            blockToEnter = functionName + "(" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        } else {
            functionName = "verify" + functionName + "Value";
            annotationValue = "\"" + "^" + "user verifies " + "\\\"(.*)\\\"" + meaningFulName.toLowerCase() + " value" + "$" + "\"";
            blockToEnter = functionName + "(" + "typeText" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            method.setParameters(parameters);
        }
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void openBrowser(CompilationUnit c, String locator, LoggerUtils loggerUtils) {
        String functionName = "openBrowser";
        String annotationValue = "\"^User open browser$\""; //changed the step definition
        String textToEnter = "open";
        String blockToEnter = textToEnter + "(" + ")";
        String annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void generateElementBasicFunctions(CompilationUnit c, Field field, String type, String className, LoggerUtils loggerUtils, String locator) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.equalsIgnoreCase(type, "button") || StringUtils.equalsIgnoreCase(type, "div") || StringUtils.equalsIgnoreCase(type, "span") || StringUtils.equalsIgnoreCase(type, "radio button") || StringUtils.equalsIgnoreCase(type, "checkbox") || StringUtils.equalsIgnoreCase(type, "table") || StringUtils.equalsIgnoreCase(type, "image") || StringUtils.equalsIgnoreCase(type, "link")) {
            functionName = Settings.USER_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "clickOn" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        }
        if (StringUtils.equalsIgnoreCase(type, "click")) {
            functionName = Settings.USER_SCROLL_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_SCROLL_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
            textToEnter = "scrollClick" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        }
        if (StringUtils.equalsIgnoreCase(type, "input")) {
            functionName = Settings.USER_INPUT_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_INPUT_ANNOTATION + " input for " + meaningFulName.toLowerCase() + "$" + "\"";
            textToEnter = "typeTextInto" + meaningFulName;
            blockToEnter = textToEnter + "(" + "typeText" + ")";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            annotationType = "When";

        }
        if (StringUtils.equalsIgnoreCase(type, "dropdown")) {
            functionName = Settings.USER_SELECT_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_SELECT_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "select" + meaningFulName + "Element";
            blockToEnter = textToEnter + "(" + "selectValue" + ")";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "selectValue"));
            annotationType = "When";

        }
        if (StringUtils.equalsIgnoreCase(type, "image")) {
            functionName = Settings.USER_IMAGE_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_IMAGE_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "uploadFileTo" + meaningFulName;
            blockToEnter = textToEnter + "(" + "filePath" + ")";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            annotationType = "When";
        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionAndNavigateBack(CompilationUnit c, Field field, String type, String className, LoggerUtils loggerUtils, String locator) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_CLICK_FUNCTION + meaningFulName + Settings.USER_NAVIGATE_FUNCTION;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + " " + Settings.USER_NAVIGATE_ANNOTATION + "$" + "\""; //changed the step definition
        textToEnter = "clickOn" + meaningFulName + Settings.USER_NAVIGATE_FUNCTION;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    //is selected verification step
    public static void setLinkStepDefinitionMethodThenSelected(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = "";
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "verify" + meaningFulName + "IsSelected";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + meaningFulName.toLowerCase() + " is selected" + "$" + "\"";
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    //verification of attribute value
    public static void generateElementValueVerify(CompilationUnit c, Field field, boolean valueVerification, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        if (valueVerification) {
            functionName = Settings.USER_VERIFIES_VALUE + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + "\\\"(.*)\\\"" + " is the value for " + meaningFulName.toLowerCase() + "$" + "\""; //changed the step definition
            textToEnter = "verifyValueFrom" + meaningFulName;
            blockToEnter = textToEnter + "(" + "valueOfElement" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
            annotationType = "Then";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "valueOfElement"));
        } else {
            functionName = Settings.USER_GET_ATTRIBUTE_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_GET_ATTRIBUTE_ANNOTATION + meaningFulName.toLowerCase() + " element" + "$" + "\""; //changed the step definition
            textToEnter = "getAttributeFrom" + meaningFulName;
            blockToEnter = textToEnter + "(" + "attributeValue" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
            annotationType = "When";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        }
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionHoverOver(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = "";
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "hoverOver" + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user hovers over " + meaningFulName.toLowerCase() + "$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + "" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionClickAndHold(CompilationUnit c, LoggerUtils loggerUtils) {
//        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CLICK_HOLD;
        annotationValue = "\"^user clicks and holds " + "\\\"(.*?)\\\"" + " element" + "$" + "\""; //changed the step definition
        textToEnter = "clicksAndHold";
        blockToEnter = textToEnter + "(" + "locator" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locator"));

        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionDragAndDrop(CompilationUnit c, LoggerUtils loggerUtils) {
//        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_DRAG_DROP;
        annotationValue = "\"^user drags from \\\"(.*?)\\\" and drops to " + "\\\"(.*?)\\\"" + " position$" + "\""; //changed the step definition
        textToEnter = "dragAndDrop";
        blockToEnter = textToEnter + "(" + "from, to" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "from"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "to"));

        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionFileUpload(CompilationUnit c, LoggerUtils loggerUtils) {
//        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_UPLOAD_FILE;
        annotationValue = "\"^user uploads file from \\\"(.*?)\\\" path to \\\"(.*?)\\\" element$" + "\""; //changed the step definition
        textToEnter = "fileUpload";
        blockToEnter = textToEnter + "(" + "filePath, locator" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locator"));

        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionFileDownload(CompilationUnit c, LoggerUtils loggerUtils) {
//        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_UPLOAD_FILE;
        annotationValue = "\"^user verifies file \\\"(.*?)\\\" is downloaded$" + "\""; //changed the step definition
        textToEnter = "isFileDownloaded";
        blockToEnter = textToEnter + "(" + "filePath" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionRightClick(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_RIGHT_CLICK_FUNCTION + meaningFulName;
        if (readProperties("Framework").contains("GEMJAR")) {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "elementName"));
            blockToEnter = functionName + "(" + "elementName" + ")";
        } else {
            blockToEnter = functionName + "()";
        }
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_RIGHT_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + "$" + "\"";
        //changed the step definition
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters); // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionRightClickElement(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_RIGHT_CLICK_FUNCTION + meaningFulName;
        if (readProperties("framework").contains("GEMJAR")) {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "element"));
            blockToEnter = functionName + "(" + "element" + ")";
        } else {
            blockToEnter = functionName + "()";
        }
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_RIGHT_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + "$" + "\"";
        //changed the step definition
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters); // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionElementPresence(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        functionName = "verify" + meaningFulName + "Exists";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + meaningFulName.toLowerCase() + " is present on screen$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionAttributeContainsText(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attribute"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "value"));
        functionName = "verifyAttributeContainsValueFor" + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies attribute \\\"(.*?)\\\" contains \\\"(.*?)\\\" value for " + meaningFulName.toLowerCase() + "$" + "\""; //changed the step definition
        blockToEnter = functionName + "(attribute, value" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionScrollToView(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollTo" + meaningFulName + "Element";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user scrolls to " + meaningFulName.toLowerCase() + " element$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionChecked(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        functionName = "verifyElementSelectedFor" + field.getName();
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + meaningFulName.toLowerCase() + " checkbox is selected$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionNotChecked(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "verify" + meaningFulName + "IsNotSelected";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + meaningFulName.toLowerCase() + " checkbox is not selected$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionAttributeVerification(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_VERIFIES_VALUE_ATTRIBUTE + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + "\\\"(.*)\\\"" + " attribute for " + meaningFulName.toLowerCase() + " as " + "\\\"(.*)\\\"" + "$" + "\""; //changed the step definition
        textToEnter = "verifyAttributeValueFor" + meaningFulName;
        blockToEnter = textToEnter + "(" + "attributeName" + "," + "attributeValue" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeName"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionTextGetter(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_GETTEXT_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_GETTEXT_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "getTextFrom" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionTypeTextAndEnter(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_TYPE_AND_ENTER_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_TYPE_AND_ENTER_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element and presses enter" + "$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + "typeText" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionTypeTextAndTab(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_TYPE_AND_TAB_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_TYPE_AND_TAB_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element and presses tab" + "$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + "typeText" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionClear(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator, String type) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CLEAR_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_CLEAR_ANNOTATION + " " + "text for " + meaningFulName.toLowerCase() + " " + type + " element" + "$" + "\""; //changed the step definition
        textToEnter = "clear" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionVerifyClear(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator, String
            type) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_VERIFY_CLEAR_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_VERIFY_CLEAR_ANNOTATION + " " + "value for " + meaningFulName.toLowerCase() + " " + type + " element is cleared" + "$" + "\""; //changed the step definition
        textToEnter = "verifyValueClearedFor" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "Then";
//        blockToEnter = functionName + "(" + "" + ")";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkStepDefinitionMethodGiven(CompilationUnit c, String homePage) {

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.isNotBlank(homePage)) {
            functionName = Settings.USER_HOME_PAGE;
            annotationType = "Given";
            annotationValue = "\"" + Settings.USER_HOME_PAGE_ANNOTATION + "$" + "\"";
            textToEnter = Settings.USER_HOME_PAGE;
            blockToEnter = textToEnter + "(" + ")";

        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethod(CompilationUnit c, Field field, String type, String className, LoggerUtils loggerUtils, String locator) {
        if(type.equals("a"))
        {
            type="link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        String key = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.equalsIgnoreCase(type, "button") || StringUtils.equalsIgnoreCase(type, "click") || StringUtils.equalsIgnoreCase(type, "image") || StringUtils.equalsIgnoreCase(type, "link")) {
            functionName = Settings.USER_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "clickOn" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            key = "Click on " + meaningFulName;
            loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        }
        if (StringUtils.equalsIgnoreCase(type, "click")) {
            functionName = Settings.USER_SCROLL_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_SCROLL_CLICK_ANNOTATION + " " + meaningFulName + " " + "element" + "$" + "\""; //changed the step definition
            textToEnter = "scrollClick" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            key = "Scroll click on " + meaningFulName;
            loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        }
        if (StringUtils.equalsIgnoreCase(type, "input")) {
            functionName = Settings.USER_INPUT_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_INPUT_ANNOTATION + " input for " + meaningFulName +"$" + "\"";
            textToEnter = "typeTextInto" + meaningFulName;
            blockToEnter = textToEnter + "(" + "typeText" + ")";
            key = "Type text into " + meaningFulName;
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            annotationType = "When";

        }
        if (StringUtils.equalsIgnoreCase(type, "dropdown")) {
            functionName = Settings.USER_SELECT_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_SELECT_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "select" + meaningFulName;
            blockToEnter = textToEnter + "(" + "selectValue" + ")";
            key = "Select value from " + meaningFulName;
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "selectValue"));
            annotationType = "When";

        }
        if (StringUtils.equalsIgnoreCase(type, "image")) {
            functionName = Settings.USER_IMAGE_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_IMAGE_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "uploadFileTo" + meaningFulName;
            blockToEnter = textToEnter + "(" + "filePath" + ")";
            key = "Upload file to " + meaningFulName;
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            annotationType = "When";
        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        FeatureCodeGenerator.stepsList.put(key, annotationType + " " + annotationValue);
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionAttributeGetter(CompilationUnit c, Field field, boolean valueVerification, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        if (valueVerification) {
            functionName = Settings.USER_VERIFIES_VALUE + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + "\\\"(.*)\\\"" + " is the value for " + meaningFulName + "$" + "\""; //changed the step definition
            textToEnter = "verifyValueFrom" + meaningFulName;
            blockToEnter = textToEnter + "(" + "valueOfElement" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
            annotationType = "Then";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "valueOfElement"));
        } else {
            functionName = Settings.USER_GET_ATTRIBUTE_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_GET_ATTRIBUTE_ANNOTATION + meaningFulName + " element" + "$" + "\""; //changed the step definition
            textToEnter = "getAttributeFrom" + meaningFulName;
            blockToEnter = textToEnter + "(" + "attributeValue" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
            annotationType = "When";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        }
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionDoubleCLick(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_DOUBLE_CLICK_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_DOUBLE_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "doubleClickOn" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodThenVisibility(CompilationUnit c, Field field,
                                                                 boolean textOrVisibilityParameter, String className, LoggerUtils loggerUtils, String type, String locator) {
        if (type.equals("a")) {
            type = "link";
        }
        String functionName = getMeaningFullName(field.getName());
        meaningFulName = getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        if (!textOrVisibilityParameter) {
            functionName = "verify" + meaningFulName + "IsDisplayed";
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies" + " " + meaningFulName.toLowerCase() + " " + type + " is visible" + "$" + "\"";
            blockToEnter = functionName + "(" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        } else {
            functionName = "verify" + functionName + "Text";
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies" + " " + "\\\"(.*)\\\"" + " is the text of " + meaningFulName.toLowerCase() + " " + type + "$" + "\"";
            blockToEnter = functionName + "(" + "typeText" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            method.setParameters(parameters);
        }
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodThenClickable(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {

        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CLICKABLE_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_CLICKABLE_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "elementIsClickable" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "Then";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        String key = "Check if " + meaningFulName + " is clickable";
        FeatureCodeGenerator.stepsList.put(key, annotationType + " " + annotationValue);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodCountChildElements(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {

        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_COUNT_CHILD_ELEMENTS + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_VERIFY_CHILD_ELEMENTS_COUNT_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = Settings.USER_COUNT_CHILD_ELEMENTS + meaningFulName;
        blockToEnter = textToEnter + "(count" + ")";
        annotationType = "Then";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "count"));
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodCountElements(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {

        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_COUNT_ELEMENTS + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_VERIFY_ELEMENTS_COUNT_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = Settings.USER_COUNT_ELEMENTS + meaningFulName;
        blockToEnter = textToEnter + "(count" + ")";
        annotationType = "Then";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "count"));
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodContains(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CONTAINS_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_CONTAINS_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "verify" + meaningFulName + "ContainsText";
        blockToEnter = textToEnter + "(" + "typeText" + ")";
        annotationType = "Then";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodThenEnabled(CompilationUnit c, Field field, String
            className, LoggerUtils loggerUtils, String locator, String type) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_ENABLED_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_ENABLED_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + " is enabled" + "$" + "\""; //changed the step definition
        textToEnter = "verify" + meaningFulName + "IsEnabled";
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodDeselects(CompilationUnit c, Field field, String
            className, LoggerUtils loggerUtils, String locator, String type) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_DESELECT_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_DESELECT_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + "$" + "\""; //changed the step definition
        textToEnter = "deselect" + meaningFulName + "Element";
        blockToEnter = textToEnter + "(" + "deselectValue" + ")";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "deselectValue"));
        annotationType = "When";
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    /**
     * formats a string to camelCase
     *
     * @param tempVarName
     * @return
     */
    private static String getMeaningFullName(String tempVarName) {
        String res = tempVarName.toLowerCase();
        res = res.replaceAll("\\s", "");
        res = res.replaceAll("&amp", "");
        res = res.replaceAll("#", "");

        StringBuilder meaningfulName = new StringBuilder();
        for (String name : res.split("_")
        ) {
            meaningfulName.append(StringUtils.capitalize(name));
        }

        return meaningfulName.toString();
    }

    /**
     * makes the source code of each state persistent
     *
     * @throws IOException
     */
    public static void savePageObjectsOnFileSystem(String directoryName, String className, CompilationUnit c,
                                                   boolean stepGeneration) throws IOException {

        String data = "";
        String fileNameToCreate = readProperties("projectPath") + File.separator + "src" + File.separator + "test"
                + File.separator + "java" + File.separator + directoryName + className;
        // String fileNameToCreate = System.getProperty("user.dir") +poName;
        File f = new File(fileNameToCreate + ".java");

        if (BooleanUtils.isTrue(stepGeneration)) {
            data = StringUtils.replace(c.toString(), "xpath = ", "").toString();
        } else {
            data = c.toString();
        }
        FileUtils.writeStringToFile(f, data);

    }

    public static void setLinkStepDefinitionNavigateBack(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "navigateBack";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_NAVIGATE_BACK + "$" + "\"";
        textToEnter = "backwardNavigation";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionActiveElement(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchActiveElement";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_ACTIVE_ELEMENT + "$" + "\"";
        textToEnter = "switchActiveElement";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionParentFrame(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchParentFrame";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_PARENT_FRAME + "$" + "\"";
        textToEnter = "switchParentFrame";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionDefaultContent(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchDefaultContent";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_DEFAULT_CONTENT + "$" + "\"";
        textToEnter = "switchDefaultContent";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionSwitchWindow(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchWindow";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_WINDOW + "$" + "\"";
        textToEnter = "switchWindow";
        blockToEnter = textToEnter + "(nameOrHandle" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrHandle"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetLogs(CompilationUnit c, LoggerUtils loggerUtils) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "getLogs";
        annotationType = "When";
        annotationValue = "\"" + Settings.GET_LOGS + "$" + "\"";
        textToEnter = "getLogs";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionNoLogs(CompilationUnit c, LoggerUtils loggerUtils) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "verifyNoLogs";
        annotationType = "When";
        annotationValue = "\"" + Settings.NO_LOGS + "$" + "\"";
        textToEnter = "verifyNoLogs";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionClearConsole(CompilationUnit c, LoggerUtils loggerUtils) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "clearConsole";
        annotationType = "When";
        annotationValue = "\"" + Settings.CLEAR_CONSOLE + "$" + "\"";
        textToEnter = "clearConsole";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionNoErrorLogs(CompilationUnit c, LoggerUtils loggerUtils) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "verifiesNoErrorLogs";
        annotationType = "When";
        annotationValue = "\"" + Settings.NO_ERROR_LOGS + "$" + "\"";
        textToEnter = "verifyNoErrorLogs";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionJSClick(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "clickUsingJS";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_CLICKS_JS + "$" + "\"";
        textToEnter = "clickUsingJS";
        blockToEnter = textToEnter + "(locatorName" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionWait(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "waitFor";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_WAITS + "$" + "\"";
        textToEnter = "wait";
        blockToEnter = textToEnter + "(duration*1000" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "duration"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionSwitchFrame(CompilationUnit c, boolean argumentType) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchFrame";
        annotationType = "When";
        textToEnter = "switchFrame";
        if (argumentType) {
            annotationValue = "\"" + Settings.SWITCH_FRAME_STRING + "$" + "\"";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrId"));
            blockToEnter = textToEnter + "(nameOrId" + ")";
        } else {
            annotationValue = "\"" + Settings.SWITCH_FRAME_INT + "$" + "\"";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "index"));
            blockToEnter = textToEnter + "(index" + ")";
        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionQuit(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "closeBrowser";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_CLOSES_BROWSER + "$" + "\"";
        textToEnter = "tearDown";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionNavigateForward(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "navigateForward";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_NAVIGATE_FORWARD + "$" + "\"";
        textToEnter = "forwardNavigation";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionNavigateTo(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        functionName = "navigateTo";
        annotationType = "Given";
        annotationValue = "\"" + Settings.USER_NAVIGATE_TO + "\"";
        textToEnter = "navigateTo";
        blockToEnter = textToEnter + "(url" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setClickStepDefinitionMethod(CompilationUnit c, Field field, String type, String className, LoggerUtils loggerUtils, String locator) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.equalsIgnoreCase(type, "button") || StringUtils.equalsIgnoreCase(type, "click") || StringUtils.equalsIgnoreCase(type, "image") || StringUtils.equalsIgnoreCase(type, "a")) {
            functionName = Settings.USER_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "clickOn" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);
        }

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionVerifyUrl(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        functionName = "verifyUrl";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_VERIFY_URL + "\"";
        textToEnter = "verifyURL";
        blockToEnter = textToEnter + "(url" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetUrl(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "getUrl";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_GET_URL + "$" + "\"";
        textToEnter = "getURL";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetTitle(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "getTitle";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_GET_TITLE + "$" + "\"";
        textToEnter = "getTitle";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionVerifyTitle(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "title"));
        functionName = "verifyTitle";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_VERIFY_TITLE + "\"";
        textToEnter = "verifyTitle";
        blockToEnter = textToEnter + "(title" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMethodForSelect(CompilationUnit c, Field field, String type, String
            className, LoggerUtils loggerUtils, String locator) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_SELECTS_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.USER_SELECTS_ANNOTATION + " " + meaningFulName.toLowerCase() + " " + type + "$" + "\""; //changed the step definition
        textToEnter = "select" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        String key = "Select checkbox value " + meaningFulName;
        FeatureCodeGenerator.stepsList.put(key, annotationType + " " + annotationValue);
        loggerUtils.log(LogLevel.INFO, "Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionPressEnter(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "pressEnter";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_PRESSES_ENTER + "\"";
        textToEnter = "pressEnter";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionCopy(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "copy";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_COPY + "\"";
        textToEnter = "copy";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionPaste(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "paste";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_PASTE + "\"";
        textToEnter = "paste";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetWindowFocus(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "windowHandle"));
        functionName = "windowFocus";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_WINDOW_FOCUS + "\"";
        textToEnter = "getWindowFocus";
        blockToEnter = textToEnter + "(" + "windowHandle" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionSelectAll(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "selectAll";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_SELECT_ALL + "\"";
        textToEnter = "selectAll";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMaximizeBrowserToDefault(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "maximizeBrowserToDefault";
        annotationType = "When";
        annotationValue = "\"" + Settings.MAXIMIZE_TO_DEFAULT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionMinimizeBrowser(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "minimizeGivenBrowser";
        annotationType = "When";
        annotationValue = "\"" + Settings.MINIMIZE_TO_DEFAULT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetBrowserSize(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "browserSize";
        annotationType = "When";
        annotationValue = "\"" + Settings.BROWSER_SIZE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionSetBrowserSize(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "setSizeOfBrowser";
        annotationType = "When";
        annotationValue = "\"" + Settings.SET_BROWSER_SIZE + "\"";
        blockToEnter = functionName + "(" + "width" + "," + "height" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "width"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "height"));
        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionSetBrowserPosition(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "setPositionOfBrowser";
        annotationType = "When";
        annotationValue = "\"" + Settings.SET_BROWSER_POSITION + "\"";
        blockToEnter = functionName + "(" + "x" + "," + "y" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "y"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetBrowserLocation(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "browserPosition";
        annotationType = "When";
        annotationValue = "\"" + Settings.BROWSER_POSITION + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetWindowHandle(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "windowHandle";
        annotationType = "When";
        annotationValue = "\"" + Settings.GET_WINDOW_HANDLE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetWindowHandles(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "windowHandles";
        annotationType = "When";
        annotationValue = "\"" + Settings.GET_WINDOW_HANDLES + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionGetPageSource(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "pageSource";
        annotationType = "When";
        annotationValue = "\"" + Settings.GET_PAGE_SOURCE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionCloseCurrentTab(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "closeTab";
        annotationType = "When";
        annotationValue = "\"" + Settings.CLOSE_CURRENT_TAB + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionCloseTabAndSwitch(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "closeTabAndSwitch";
        annotationType = "When";
        annotationValue = "\"" + Settings.CLOSE_TAB_AND_SWITCH + "\"";
        blockToEnter = functionName + "(" + "windowName" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "windowName"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionSwitchToAlert(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "alertSwitch";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_ALERT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionAcceptAlert(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "alertAccept";
        annotationType = "When";
        annotationValue = "\"" + Settings.ACCEPT_ALERT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionDismissAlert(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "alertDismiss";
        annotationType = "When";
        annotationValue = "\"" + Settings.DISMISS_ALERT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionAlertInput(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "inputForAlert";
        annotationType = "When";
        annotationValue = "\"" + Settings.ALERT_INPUT + "\"";
        blockToEnter = functionName + "(" + "input" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "input"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionScrollToTop(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollUp";
        annotationType = "When";
        annotationValue = "\"" + Settings.SCROLL_UP + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionScrollToBottom(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollDown";
        annotationType = "When";
        annotationValue = "\"" + Settings.SCROLL_DOWN + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionPageScroll(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollPage";
        annotationType = "When";
        annotationValue = "\"" + Settings.SCROLL_PAGE + "\"";
        blockToEnter = functionName + "(" + "x" + "," + "y" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "y"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionScrollElementToPosition(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "elementScroll";
        annotationType = "When";
        annotationValue = "\"" + Settings.SCROLL_ELEMENT + "\"";
        blockToEnter = functionName + "(" + ")";
        blockToEnter = functionName + "(" + "x" + "," + "y" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "y"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionNavigateToUrl(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "urlNavigation";
        annotationType = "When";
        annotationValue = "\"" + Settings.NAVIGATE_URL + "\"";
        blockToEnter = functionName + "(" + "url" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionRefresh(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "refreshPage";
        annotationType = "When";
        annotationValue = "\"" + Settings.REFRESH_PAGE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionTakeSnapshot(CompilationUnit c) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = "takeScreenshot";
        annotationType = "When";
        annotationValue = "\"" + Settings.TAKE_SNAPSHOT + "\"";
        MethodDeclaration method = null;
        if (readProperties("framework").contains("GEMJAR")) {
            blockToEnter = functionName + "(" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            method.setParameters(parameters);
            // add a body to the method
        } else {
            blockToEnter = functionName + "(" + "filePath" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            method.setParameters(parameters);
        }
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkStepDefinitionValidateRowCountOfTable(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        meaningFulName = getMeaningFullName(field.getName());
        List<Parameter> parameters = new LinkedList<>();
        functionName = "validateRowCountOf" + meaningFulName + "Table";
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.VERIFY_ROW_COUNT + field.getName().toLowerCase() + " table$" + "\""; //changed the step definition
        MethodDeclaration method = null;

        //Adding Parameters
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "actualRowCount"));
        blockToEnter = functionName + "(" + "actualRowCount" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);

        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionValidateColumnCountOfTable(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "validateColumnCountOf" + meaningFulName + "Table";
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.VERIFY_COLUMN_COUNT + field.getName().toLowerCase() + " table$" + "\""; //changed the step definition
        MethodDeclaration method = null;

        //Adding Parameters
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "actualColumnCount"));
        blockToEnter = functionName + "(" + "actualColumnCount" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);

        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionValidateColumnNameOfTable(CompilationUnit c, Field field, String locator, LoggerUtils loggerUtils) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "isColumnNamePresent";
        annotationType = "When";
        annotationValue = "\"" + Settings.VERIFY_COLUMN_NAME + field.getName() + " table$" + "\"";
        MethodDeclaration method = null;

        //Adding Parameters
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "columnName"));
        blockToEnter = functionName + "(" + "columnName" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);

        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetColumnValue(CompilationUnit c, Field field, String locator, LoggerUtils loggerUtils) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "getColValueFor" + meaningFulName;
        annotationType = "When";
        annotationValue = "\"" + Settings.GET_COLUMN_VALUE + " " + meaningFulName.toLowerCase() + " table$" + "\"";
        MethodDeclaration method = null;

        //Adding Parameters
        blockToEnter = functionName + "(" + "" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);

        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetAllCellsValue(CompilationUnit c, Field field, String locator, LoggerUtils loggerUtils) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "getAllCellValuesFor" + meaningFulName + "Table";
        annotationType = "When";
        annotationValue = "\"" + Settings.GET_ALL_CELLS_VALUE + " " + meaningFulName.toLowerCase() + " table$" + "\"";
        MethodDeclaration method = null;

        //Adding Parameters
        blockToEnter = functionName + "(" + "" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);

        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionChangeFocus(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "changeFocusTo" + meaningFulName;
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + Settings.CHANGE_FOCUS + field.getName().toLowerCase() + " element$" + "\""; //changed the step definition
        MethodDeclaration method = null;

        //Adding Parameters
        blockToEnter = functionName + "()";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionIsImage(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "verify" + meaningFulName + "IsImage";
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "verify " + field.getName().toLowerCase() + " element is image$" + "\""; //changed the step definition
        MethodDeclaration method = null;

        //Adding Parameters
        blockToEnter = functionName + "()";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName().toLowerCase() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionIsDisabled(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "verify" + meaningFulName + "IsDisabled";
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user verifies " + field.getName().toLowerCase() + " element is disabled" + "\""; //changed the step definition
        MethodDeclaration method = null;

        //Adding Parameters
        blockToEnter = functionName + "()";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName().toLowerCase() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }


    public static void setLinkStepDefinitionGetRowValue(CompilationUnit c, Field field, String className, LoggerUtils loggerUtils, String locator) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        meaningFulName = getMeaningFullName(field.getName());
        functionName = "verify" + meaningFulName + "ISDisplayed";
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "verify " + field.getName() + " element is displayed" + "\""; //changed the step definition
        functionName = "getRowValueFor" + meaningFulName;
        annotationType = "Then";
        annotationValue = "\"" + pageName.replace("<page>", className.toLowerCase()) + "user gets row value for " + field.getName().toLowerCase() + " table" + "\""; //changed the step definition
        MethodDeclaration method = null;

        //Adding Parameters
        blockToEnter = functionName + "()";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, method.toString());
        loggerUtils.log(LogLevel.INFO, c.getTypes().get(0).toString());
    }

}