package com.gemini.gpog.framework;

import com.gemini.gpog.pageobjectgenerator.LocatorsModel;
import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class HelperFunctions {

    public static String getMeaningFullName(String tempVarName) {
        String res = tempVarName.toLowerCase();
        res = res.replaceAll("\\s", "");
        res = res.replaceAll("&amp", "");
        res = res.replaceAll("#", "");

        StringBuilder meaningfulName = new StringBuilder();
        for (String name : res.split("_")) {
            meaningfulName.append(StringUtils.capitalize(name));
        }

        return meaningfulName.toString();
    }

    public static CompilationUnit createEnhancedCompilationUnit(String name, String type, String locator) throws IOException {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(name)));
        cu.setImports(HelperFunctions.getAllImports(type, locator));
        return cu;
    }

    public static void setTypeDeclaration(CompilationUnit c, String className) throws IOException {
        // create the type declaration and class
        String classToExtend = "";
        if (readProperties("framework").contains("GEMJAR")) {
            classToExtend = classToExtend + "DriverAction";
        } else if (StringUtils.containsIgnoreCase(className, "StepDefinitions")) {
            classToExtend = classToExtend + "StepsObjectsSub";
        } else if (StringUtils.equalsIgnoreCase("StepsObjectsSub", className)) {
            classToExtend = classToExtend + "PageObject";
            /////////
        } else if (StringUtils.containsIgnoreCase(className, "Utils")) {
            classToExtend = classToExtend + "PageObject";
        } else if (StringUtils.equalsIgnoreCase("EJ", LocatorsModel.getEnv())) {
            classToExtend = classToExtend + className + "Objects";
            //////////// Temporary-----------have doubt
        } else {
            classToExtend = classToExtend + "PageObject";
        }
        ClassOrInterfaceType typeForExtends = new ClassOrInterfaceType(classToExtend);
        List<ClassOrInterfaceType> extendsList = new ArrayList<ClassOrInterfaceType>();
        extendsList.add(typeForExtends);
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(null, ModifierSet.PUBLIC, null, false, className, null, extendsList, null, null);

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

    private static List<ImportDeclaration> getAllImports(String type, String locator) throws IOException {
        List<ImportDeclaration> imports = new LinkedList<>();

        if (StringUtils.equalsIgnoreCase(type, "PageObject")) {
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium"), false, true));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.FindBy"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
        } else {
            //adding imports for implementation class
            if (readProperties("framework").contains("GEMJAR")) {
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.reporting.GemTestReporter"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.reporting.STATUS"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.ui.utils.DriverAction"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.utils.GemJarUtils"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.chrome.ChromeOptions"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("io.github.bonigarcia.wdm.WebDriverManager"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.firefox.FirefoxDriver"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.utils.GemJarGlobalVar"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.WebElementFacade"), false, false));
            } else if (StringUtils.equalsIgnoreCase(type, "EJ")) {
                imports.add(new ImportDeclaration(new NameExpr("frontend.pageobjects.SamplePageObjects"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.WebElementFacade"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.slf4j.Logger"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.slf4j.LoggerFactory"), false, false));
            } else {
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.WebElementFacade"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.PageObject"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.io.FileUtils"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
            }
            imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.io.FileUtils"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.Serenity"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("static org.junit.Assert.assertTrue"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.junit.Assert"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("static org.junit.Assert.assertFalse"), false, false));
            if (!locator.equalsIgnoreCase("Utils"))
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.locator" + "." + locator), false, false));
//                imports.add(new ImportDeclaration(new NameExpr("static locators" + "." + pageobjectgenerator.Settings.LOCATOR_FILE_NAME + "." + "driver"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.ui.ExpectedConditions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.ui.WebDriverWait"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.time.Duration"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.WebElement"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.JavascriptExecutor"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.lang3.StringUtils"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.interactions.Actions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.WebDriver"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.Keys"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.Objects"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.*"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.SerenityActions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.By"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.lang.reflect.Field"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.List"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.ArrayList"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.logging.LogEntries"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.logging.LogType"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.logging.LogEntry"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.model.environment.ConfiguredEnvironment"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.model.environment.EnvironmentSpecificConfiguration"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("com.gemini.gpog.logger.EnableSlf4jLogging"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("com.gemini.gpog.logger.LoggerUtils"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("com.gemini.gpog.reporting.EnableSerenityReporting"), false, false));
//            imports.add(new ImportDeclaration(new NameExpr("reporting.EnableGemReporting"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("com.gemini.gpog.reporting.Reporting"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("com.gemini.gpog.framework.HelperFunctions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.PageObject"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.netty.handler.logging.LogLevel"), false, false));

        }

        return imports;
    }

    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    public static CompilationUnit createEnhancedCompilationUnitStepDefinitions(String name, String type, String locator) {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(name)));
        cu.setImports(HelperFunctions.getAllImportsStepDefinitions(type, locator));
        return cu;
    }

    private static List<ImportDeclaration> getAllImportsStepDefinitions(String type, String locator) {
        List<ImportDeclaration> imports = new LinkedList<>();

        if (StringUtils.equalsIgnoreCase(type, "PageObject")) {
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium"), false, true));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.FindBy"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
        } else if (StringUtils.equalsIgnoreCase(type, "objectSubs")) {
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.PageObject"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("frontend.pages.SamplePage"), false, false));

        } else if (StringUtils.equalsIgnoreCase(type, "EJStepDefinition")) {
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.And"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.Before"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.Given"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.Then"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.When"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("frontend.pages.UtilsImplementation"), false, false));
            if(!StringUtils.containsIgnoreCase(locator, "utils"))
            imports.add(new ImportDeclaration(new NameExpr("frontend.pages." + locator + "Page"), false, false));
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
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.*"), false, false));

        }
        return imports;
    }

    public static void setStepDefinitionVariable(CompilationUnit c, String classname, String locator, String claas) {
        //setting the variable for step definition
        String className = "";
        VariableDeclarator v = new VariableDeclarator();
        String firstLetter = locator.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + locator.substring(1);
        if (StringUtils.equalsIgnoreCase("slf4j", claas)) className = "EnableSlf4jLogging";
        else if (StringUtils.equalsIgnoreCase("JLC", claas)) className = "EnableJLCLogging";
        if (StringUtils.equalsIgnoreCase("Gemjar", claas)) className = "EnableGemReporting";
        else if (StringUtils.equalsIgnoreCase("Serenity", claas)) className = "EnableSerenityReporting";
        if (LocatorsModel.getEnv().contains("EJ")) {
            v.setId(new VariableDeclaratorId(locator));
            List<Expression> list_espr = new LinkedList<Expression>();
            list_espr.add(0, ASTHelper.createNameExpr(claas + ".class"));

            v.setInit(new ObjectCreationExpr(ASTHelper.createNameExpr("LoggerFactory"), new ClassOrInterfaceType("getLogger"), list_espr));
        } else {
            v.setId(new VariableDeclaratorId(nameOfFile));
            v.setInit(new ObjectCreationExpr(null, new ClassOrInterfaceType(null, className), null));
        }
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE,
                ASTHelper.createReferenceType(classname, 0), v);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

    public static void correctLoggerStatement(String file) throws IOException {
        String fileName = "src/main/java/frontend/pages/" + file + ".java";

        String content = FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);

        String updatedContent = StringUtils.replace(content, "new getLogger", "getLogger");

        FileUtils.writeStringToFile(new File(fileName), updatedContent);
    }


}
