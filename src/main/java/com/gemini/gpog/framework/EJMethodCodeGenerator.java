package com.gemini.gpog.framework;

import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.reporting.Reporting;
import io.netty.handler.logging.LogLevel;
import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.stmt.BlockStmt;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class EJMethodCodeGenerator implements Framework {

    @Override
    public void generateElementVerifyNotSelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {

        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsNotSelected");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tassertFalse(\"" + meaningFulName + " is selected\", wait.until(ExpectedConditions.elementToBeSelected(" + field.getName() +")))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies if checkbox is selected\")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify element Selected or not\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));
        ASTHelper.addStmt(block, new NameExpr(""));

        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("return " + field.getName() +".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifySelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {

        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsSelected");

        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is selected\", wait.until(ExpectedConditions.elementToBeSelected(" + field.getName() +")))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies if checkbox is selected\")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify element Selected or not\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));
        ASTHelper.addStmt(block, new NameExpr(""));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("return " + field.getName() +".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateInputTypeInto(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextInto" + meaningFulName);
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO, parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() + "))"));
        ASTHelper.addStmt(block, new NameExpr("\ttypeInto(" + "element" + "," + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into" + meaningFulName + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateInputVerifyValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyValueFrom" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("WebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() +"));\n\t\tassertTrue(\"Actual value: \" + element" + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + "element" + "." + "getAttribute(\"value\")" + "))"));
        ASTHelper.addStmt(block, new NameExpr("LOGGER.info(\"User verifies value: \"" + " + " + "typeText + \"for " + meaningFulName + "\")"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("return " + "element" + ".getAttribute(\"value\")")));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not verify value for " + field.getName() + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyAttributeValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "verifyAttributeValueFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeName"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(" + "StringUtils.equalsIgnoreCase(" + "attributeValue" + "," +field.getName()+ "." + "getAttribute(attributeName)" + "))"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not verify attributeValue for " + field.getName() + "\")"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " +field.getName()+ ".getAttribute(attributeName)"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("return " +field.getName()+ ".getAttribute(attributeName)")));

        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    //    @Override
//    public void generateMethodForIsSelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
//        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
//        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsSelected");
//
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
//        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
//
//            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
//            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not selected\", wait.until(ExpectedConditions.elementToBeSelected(" + field.getName() +")))"));
//            ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies if checkbox is selected\")"));
//            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "reporting.reportSteps(\"Failure\",\"Unable to verify element Selected or not\", \"\")"));
//            ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t\t}"));
//
//        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("assertTrue(" + field.getName() +"." + "isSelected" + "(" + "))")));
//        ASTHelper.addMember(c.getTypes().get(0), method);
//    }

    @Override
    public void generateInputClear(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clear" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "element" + "." + "clear" + "(" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User deletes the value for " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not clear " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateInputTypeAndEnter(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextAndEnterFor" + meaningFulName);

        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO, parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "$(element).typeAndEnter(" + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses enter\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));

        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateInputTypeAndTab(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextAndTabFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO, parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "typeInto(" + "element" + "," + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tnew SerenityActions(getDriver()).sendKeys(Keys.TAB)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses Tab\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateInputVerifyClear(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyValueClearedFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("assertTrue(\"Actual value: \" + "+ field.getName() + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "" + "\"\", " +field.getName()+ "." + "getAttribute(\"value\")" + "))"));
        ASTHelper.addStmt(block, new NameExpr("LOGGER.info(\"User verifies value is cleared for " + meaningFulName + "\")"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("return " +field.getName()+ ".getAttribute(\"value\")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementHoverOver(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "hoverOver" + meaningFulName);
        List<Parameter> parameters = new LinkedList<>();
        method.setParameters(parameters);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("SerenityActions serenityActions = new SerenityActions(getDriver())"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsEnabled();\n\t\t\t" + "serenityActions" + "." + "moveToElement(" + field.getName() +")" + "." + "build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully hovers over\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("serenityActions" + "." + "moveToElement(" + field.getName() +")" + "." + "build().perform()")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyPresence(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {


        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "Exists");
        BlockStmt block = new BlockStmt();
        method.setBody(block);


        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(getDriver().findElements(" + field.getName() +").size()>0){"));
        ASTHelper.addStmt(block, new NameExpr("\t\t" + "LOGGER.info(\"" + meaningFulName + " is present on Screen\");\n\t\t\t}\t\n\t\t\t else{\n\t\t\t\t" + "Assert.fail(\"" + meaningFulName + " element is not present on Screen\")"));
        ASTHelper.addStmt(block, new NameExpr("\t\t" + "LOGGER.info(\"" + meaningFulName + " element is not present on Screen\");}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\"" + meaningFulName + " element is not present on Screen\")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateVerifyCountChildElements(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyChildElementsCount" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "count"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("List<WebElement> allChildElements = new ArrayList<>()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebElementFacade parentElement = " + field.getName() +";\n\t\t\tallChildElements = parentElement.findElements(By.xpath(\"*\"));\n\t\t\tAssert.assertEquals(count, allChildElements.size())"));
        ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "LOGGER.info(\"" + field.getName() + " has\" + allChildElements.size() + \"child elements\");\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\"Actual child count - \" + allChildElements.size())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateVerifyCountElements(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyCountOfElementsFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "count"));
        method.setParameters(parameters);

        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("List<WebElement> allElements = new ArrayList<>()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tallElements = getDriver().findElements(" + field.getName() +");\n\t\t\tAssert.assertEquals(count, allElements.size())"));
        ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "LOGGER.info(\"" + field.getName() + " count is \" + allElements.size());\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\"Actual count - \" + allElements.size())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementScrollIntoView(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollTo" + meaningFulName + "Element");
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"arguments[0].scrollIntoView();\", " + "$(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\" Successfully scrolled to " + field.getName() + " element \");}\t\n\t\t\tcatch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t\t" + "Assert.fail(\" Unable to scroll to " + field.getName() + " element \")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateTypeGetter(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getTextFrom" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("String text = \"\""));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "text = element" + "." + "getText" + "(" + ");\n\t\t\t" + "LOGGER.info(\"Successfully fetched the text of " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not get text of " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "text"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("return " + field.getName() +"." + "getText" + "(" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementScrollAndClick(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollClick" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "JavascriptExecutor" + " " + "js" + "=" + "(" + "JavascriptExecutor" + ")" + "getDriver()"));
        ASTHelper.addStmt(block, new NameExpr("\tjs" + "." + "executeScript(\"arguments[0].scrollIntoView()\"" + "," + "$(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\t"+ field.getName() + "." + "click" + "(" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "LOGGER.info(\"User is able to scroll and click on the " + field.getName() + " element\"" + ")"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("js" + "." + "executeScript(\"arguments[0].scrollIntoView()\"" + "," + field.getName() + "Element" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateDropdownSelect(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "select" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));

        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" +field.getName()+ "." + "selectByVisibleText" + "(" + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to select \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not select \" + typeText)"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));

        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("new Select (" + field.getName() +")" + "." + "selectByVisibleText" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateDeselects(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "deselect" + meaningFulName + "Element");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));

        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" +field.getName()+ "." + "deselectByVisibleText" + "(" + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to deselect \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not deselect \" + typeText)"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));

        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("new Select (" + field.getName() +")" + "." + "deselectByVisibleText" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodClickable(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "elementIsClickable" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\")))).until(ExpectedConditions.elementToBeClickable(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not clickable\", " +field.getName()+ "." + "isClickable" + "(" + "))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies " + field.getName() + " is clickable\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to click\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));

        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("assertTrue(" + field.getName() + "Element" + "." + field.getName() + "." + "isClickable" + "(" + "))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyEnabled(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {

        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsEnabled");

        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));

        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() +"))"));
        ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not enabled\", " + "element" + "." + "isEnabled" + "(" + "))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies the given " + field.getName() + " element is enabled\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Element is not enabled\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));

        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyVisible(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsDisplayed");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not visible\", " +field.getName()+ "." + "isDisplayed" + "(" + "))"));
        ASTHelper.addStmt(block, new NameExpr("LOGGER.info(\"User verifies " + field.getName() + " element is displayed\"" + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyText(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "Text");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual text: \" + " +field.getName()+ "." + "getText" + "(" + "), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," +field.getName()+ "." + "getText" + "(" + ")))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User gets the text of " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));

        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("assertTrue(" + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + field.getName() + "Element" + "." + "getText" + "(" + ")))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyTextContains(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "ContainsText");

        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));

        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual text: \" + "+ field.getName() + "." + "getText" + "(" + "), " + "StringUtils.containsIgnoreCase("+ field.getName() + "." + "getText" + "(" + "), " + "typeText))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies " + field.getName() + " contains \"" + " + typeText)"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));

        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("assertTrue(" + "StringUtils.containsIgnoreCase(" + field.getName() + "." + "getText" + "(" + "))), " + "typeText")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementVerifyAttributesContains(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyAttributeContainsValueFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attribute"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual value: \" + "+ field.getName() + "." + "getAttribute(attribute), " + "StringUtils.contains(" +field.getName()+ "." + "getAttribute(attribute)" + ", typeText" + "))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User verifies " + field.getName() + " contains \"" + " + typeText)"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual value: \" + "+ field.getName() + "." + "getAttribute(attribute), " + "StringUtils.contains(" +field.getName()+ "." + "getAttribute(attribute)" + ", typeText" + "))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateLaunchBrowser(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "open");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver(" + ")" + "." + "get" + "(" + "EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\")" + ")"));
        ASTHelper.addStmt(block, new NameExpr("LOGGER.info(\"User launches the application\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not launch the application with URL: \" + EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\"));\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not launch the application with URL: \" + EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\"));\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\")" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementClick(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clickOn" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "elementIsClickable" + meaningFulName + "()"));
        ASTHelper.addStmt(block, new NameExpr("\t" +field.getName()+ "." + "click" + "(" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User click on the " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not click " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\" User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementDoubleClick(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "doubleClickOn" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "elementIsClickable" + meaningFulName + "()"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Actions" + " " + "action" + "=" + "new" + " " + "Actions(" + "driver" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\taction" + "." + "doubleClick(" +field.getName()+ ")" + "." + "perform" + "(" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User double click on the element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateElementUpload(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "uploadFileTo" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "fileName"));
        method.setParameters(parameters);
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\data\\";
        loggerUtils.log(LogLevel.INFO, parameters.toString());
        //path of file
        filePath = filePath.replace("\\", "\\\\");
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" +field.getName()+ "." + "sendKeys" + "(" + '"' + filePath + '"' + "+" + "fileName" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User uploads the file\")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr(field.getName() + "Element" + "." + "sendKeys" + "(" + '"' + filePath + '"' + "+" + "fileName" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    @Override
    public void generateSwitchToActiveElement(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchActiveElement");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().activeElement()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully switched to active element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not switch to active element\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to active element\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().switchTo().activeElement()")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateSwitchToParentFrame(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchParentFrame");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().parentFrame()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully switched to parent frame\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not switch to parent frame\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to parent frame\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().switchTo().parentFrame()")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateSwitchToFrame(CompilationUnit c, boolean argumentType, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchFrame");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        if (argumentType) {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrId"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().frame(nameOrId)"));
            ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully switched to frame\" + nameOrId" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not switch to frame: \" + nameOrId);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to frame: \" + nameOrId);\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().switchTo().frame(nameOrId)")));
        } else {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "index"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().frame(index)"));
            ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully switched to frame\" + index" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not switch to frame: \" + index);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to frame: \" + index);\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().switchTo().frame(index)")));
        }
        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateSwitchWindow(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchWindow");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrHandle"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().window(nameOrHandle)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully switched to window\" + nameOrHandle" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not switch to window: \" + nameOrHandle);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to window: \" + nameOrHandle);\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().switchTo().window(nameOrHandle)")));

        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateWait(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "wait");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "duration"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitABit(duration)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User waits for \" + duration + \" milliseconds\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not wait for : \" + duration + \" milliseconds\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not wait for : \" + duration + \" milliseconds\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("waitABit(duration)")));
        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    @Override
    public void generateClickAndHold(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clicksAndHold");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\tnew WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\")))).until(ExpectedConditions.elementToBeClickable(locator))"));
        ASTHelper.addStmt(block, new NameExpr("\tnew SerenityActions(getDriver()).moveToElement($(locator)).clickAndHold().build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully clicks and holds \" + locator + \" element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not click and hold: \" + locatorName);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not click and hold: \" + locatorName);\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("new SerenityActions(getDriver()).moveToElement(locator).clickAndHold().build().perform()")));

        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateRightClick(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "rightClickOn" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("SerenityActions serenityActions = new SerenityActions(getDriver())"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "serenityActions" + "." + "moveToElement(" + field.getName() +")" + "." + "contextClick().build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User right clicks on " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateSwitchToDefaultContent(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchDefaultContent");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().defaultContent()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully switched to default content\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not switch to default element\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to default element\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().switchTo().defaultContent()")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    @Override
    public void generateQuit(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "tearDown");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().quit()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully closed driver\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not close driver\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not close driver\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "quit()")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateNavigateForward(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "forwardNavigation");

        BlockStmt block = new BlockStmt();
        method.setBody(block);

        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().forward()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully navigated Forward\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not navigate forward\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not navigate forward\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\")" + ")")));

        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateNavigateTo(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "navigateTo");
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO, parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().to(url)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully navigated to URL: \" + url" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not navigate to URL: \" + url);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not navigate to URL: \" + url);\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver().navigate().to(url)")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateVerifyUrl(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyURL");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "expectedURL"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString actualURL = getURL()"));
        ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"Actual URL: \" + getURL(), actualURL.equals(expectedURL))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"URL verified successfully\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Actual URL: \" + getURL());\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Actual URL: \" + getURL())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\")" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateGetUrl(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getURL");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("String currentUrl = \"\""));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tcurrentUrl = getDriver().getCurrentUrl()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully gets current \"+currentUrl" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get \"+currentUrl);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get current URL\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn currentUrl"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "EnvironmentSpecificConfiguration.from(ConfiguredEnvironment.getEnvironmentVariables()).getProperty(\"webdriver.base.url\")" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateGetBrowserSize(CompilationUnit c,LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object", 0), "browserSize");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("Integer sizeOfBrowser = null"));
        ASTHelper.addStmt(block, new NameExpr("try { \n\t\t\tObject size = getDriver().manage().window().getSize()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Browser Size is  \"+sizeOfBrowser)"));
        ASTHelper.addStmt(block, new NameExpr("\tif(size!=null)"));
        ASTHelper.addStmt(block, new NameExpr("\tsizeOfBrowser = Integer.valueOf(size.toString())"));
        ASTHelper.addStmt(block, new NameExpr("\n\t\t} catch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not get size of browser\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not get size of browser\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        ASTHelper.addStmt(block, new NameExpr("return sizeOfBrowser"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("Generated : browserSize()")));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    @Override
    public void generateGetBrowserLocation(CompilationUnit c,LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object", 0), "browserPosition");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("Integer positionOfBrowser = null"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\tObject position = getDriver().manage().window().getPosition()"));
        ASTHelper.addStmt(block, new NameExpr("if(position!=null){\t"));
        ASTHelper.addStmt(block, new NameExpr("\tpositionOfBrowser = Integer.valueOf(position.toString());\n\t\t\tLOGGER.info(\"Browser Position is \"+positionOfBrowser);\n\t}"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get browser position\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get browser position\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn positionOfBrowser"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateGetWindowHandle(CompilationUnit c,LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object", 0), "windowHandle");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("String windowHandle = null"));
        ASTHelper.addStmt(block, new NameExpr("try { \n\t\t\twindowHandle = getDriver().getWindowHandle()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Window Handle is \"+windowHandle)"));
        ASTHelper.addStmt(block, new NameExpr("\tif(windowHandle==null){\n\t\t\tthrow new NullPointerException()"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get window handle\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get window handle\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        ASTHelper.addStmt(block, new NameExpr("return windowHandle"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateGetWindowHandles(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "windowHandles");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("String windowHandles = null"));
        ASTHelper.addStmt(block, new NameExpr("try {\n\t\t\twindowHandles = getDriver().getWindowHandles().toString()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Window Handles fetched successfully.\")"));
        ASTHelper.addStmt(block, new NameExpr("\tif(windowHandles==null){\n\t\t\tthrow new NullPointerException()"));
        ASTHelper.addStmt(block, new NameExpr("\t} \n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get window handles\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get window handles\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        ASTHelper.addStmt(block, new NameExpr("return windowHandles"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateGetPageSource(CompilationUnit c,LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "pageSource");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("String pageSource = null"));
        ASTHelper.addStmt(block, new NameExpr("try { \n\t\t\tpageSource = getDriver().getPageSource()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Page Source is  \"+pageSource)"));
        ASTHelper.addStmt(block, new NameExpr("\tif(pageSource==null){\n\t\t\tthrow new NullPointerException()"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get page source\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        ASTHelper.addStmt(block, new NameExpr("return pageSource"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateCloseCurrentTab( CompilationUnit c,LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "closeTab");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().close()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully closes current tab.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to close current tab\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodSwitchToAlert(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertSwitch");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is unable to switch to alert.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to switch to Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodAcceptAlert(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertAccept");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().accept()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully accepts alert.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to accept Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodDismissAlert(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertDismiss");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().dismiss()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully dismiss alert.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to dismiss Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodAlertInput(CompilationUnit c, LoggerUtils loggerUtils) {;
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "inputForAlert");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "input"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().sendKeys(input)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully enters \" + input + \"into alert.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to enter \" + input + \"into Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodScrollToTop (CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollUp");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollTo(0, -document.body.scrollHeight)\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to scroll up successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to scroll to top of page\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodScrollToBottom(CompilationUnit c, LoggerUtils loggerUtils) {;
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollDown");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t JavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(0,document.body.scrollHeight)\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to scroll down successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to scroll to bottom of page\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodPageScroll(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollPage");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "y"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(\"+x+\",\"+y+\")\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to scroll page\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    @Override
    public void generateMethodScrollElementToPosition(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "elementScroll");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "y"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(\"+x+\",\"+y+\")\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodRefresh(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "refreshPage");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().refresh()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully refreshes page.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to refresh page\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    @Override
    public void generateMethodGetLogs(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("LogEntries", 0), "getLogs");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("LogEntries logs = null"));
        ASTHelper.addStmt(block, new NameExpr("try {\n\t\t\tnew WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\")))).until(driver -> ((JavascriptExecutor) driver).executeScript(\"return document.readyState\").equals(\"complete\"));\n\t\t\tlogs = getDriver().manage().logs().get(LogType.BROWSER)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Log messages are present in console.\")"));
        ASTHelper.addStmt(block, new NameExpr("} catch (Exception e) { \n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Unable to get logs.\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User gets an exception: \" + e)"));
        ASTHelper.addStmt(block, new NameExpr(("} \n\t\treturn logs")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodNoErrorLogs(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyNoErrorLogs");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("LogEntries logs = getLogs()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tfor (LogEntry log : logs\n\t\t\t) {\n\t\t\tassertFalse(\"Error Log messages are present in console.\" + log.getMessage(), StringUtils.equalsIgnoreCase(\"SEVERE\", log.getLevel().getName()))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Error Log messages are present in console.\" + log.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t} catch (Exception e) { \n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Unable to get logs.\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User gets an exception: \" + e) "));
        ASTHelper.addStmt(block, new NameExpr(("\t}")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodClearConsole(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clearConsole");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor)getDriver();\n\t\t\texecutor.executeScript(\"console.clear();\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Cleared the console.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to clear console\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodNoLogs(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyNoLogs");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("LogEntries logs = getLogs()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tAssert.assertNotEquals(\"Log messages are present in console.\", 0, logs.getAll().size())"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Error Log messages are present in console.\")"));
        ASTHelper.addStmt(block, new NameExpr("} catch (Exception e) { \n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Unable to get logs.\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User gets an exception: \" + e) "));
        ASTHelper.addStmt(block, new NameExpr(("\t}")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodTakeSnapshot(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "takeScreenshot");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tTakesScreenshot scrShot =((TakesScreenshot)getDriver());\n\t\t\tFile SrcFile=scrShot.getScreenshotAs(OutputType.FILE);\n\t\t\tFile DestFile=new File(filePath);\n\t\t\tFileUtils.copyFile(SrcFile, DestFile)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully takes snapshot.\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetLocatorWithName(CompilationUnit c, LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("By", 0), "getLocator");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("By locator = null"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString className = locatorName.split(\"\\\\.\")[0];\n\t\t\tClass<?> clazz = Class.forName(\"locators.\" + className);\n\t\t\tField loc = clazz.getField(locatorName.split(\"\\\\.\")[1]);\n\t\t\tlocator = (By) loc.get(className)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully gets locator- \" + locator)"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get locator\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\treturn locator"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    @Override
    public void generateMethodClickUsingJS(CompilationUnit c,LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clickUsingJS");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor)getDriver();\n\t\t\texecutor.executeScript(\"arguments[0].click();\", $(locator))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to click element\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to click using JS\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodDragAndDrop(CompilationUnit c,LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "dragAndDrop");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "from"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "to"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy fromLocator = getLocator(from);\n\t\t\tBy toLocator = getLocator(to);\n\t\t\tnew SerenityActions(getDriver()).dragAndDrop($(fromLocator), $(toLocator)).build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to drag and drop element \")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to drag and drop element\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodFileUpload(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "fileUpload");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\t$(locator).sendKeys(filePath)"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to upload file to element\")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to upload file\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodMinimizeBrowser(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "minimizeGivenBrowser");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().minimize()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Browser Minimization successful.\");" + "\n\t\t}\n\t\tcatch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Unable to minimize browser.\");\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not minimize browser\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodMaximizeBrowserToDefault(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "maximizeBrowserToDefault");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().maximize()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Browser Maximization to default successful\");" + "\n\t\t} catch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t\tLOGGER.info(\"Unable to maximize browser to default\");\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not maximize browser to default\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodSetBrowserPosition(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "setPositionOfBrowser");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "y"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().setPosition(new Point(x,y))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully set position of browser\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not set position of browser\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodSetBrowserSize(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "setSizeOfBrowser");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "width"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "height"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDimension newDimension = new Dimension(width, height);\n\t\t\tgetDriver().manage().window().setSize(newDimension)\t\t"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully set size of browser\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not set size of browser\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodVerifyTitle(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyTitle");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "expectedTitle"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString actualTitle = getTitle()"));
        ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"Actual title: \" + actualTitle, actualTitle.equals(expectedTitle))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User successfully verifies title\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Actual Title: \" + getTitle());\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.TITLE" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetTitle(CompilationUnit c, LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getTitle");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("String title = \"\""));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttitle = getDriver().getTitle()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Title fetched successfully: \"+title" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Unable to get current title\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn title"));
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("getDriver()" + "." + "getTitle()")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodSelect(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {

        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "select" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" +field.getName()+ "." + "click" + "(" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User selects the " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not select " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetRowCount(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {

        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.INT_TYPE, "getRowCountOf" + meaningFulName + "Table");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("\tList<WebElement> listOfRows = new ArrayList<>()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebElement tableBody = getDriver().findElement(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("\tlistOfRows = tableBody.findElements(By.tagName(\"tr\"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Row count of " + field.getName() + " table is \"+listOfRows.size());}\t\n\t\tcatch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(\"failed to get row count of " + field.getName() + " table \")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Failed to get row count of " + field.getName() + " table\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addStmt(block, new NameExpr("\treturn listOfRows.size()"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetColumnCount(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.INT_TYPE, "getColumnCountOf" + meaningFulName + "Table");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("\tList<WebElement> headers = new ArrayList<>()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebElement tableBody = getDriver().findElement(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("\tList<WebElement>listOfRows = tableBody.findElements(By.tagName(\"tr\"))"));
        ASTHelper.addStmt(block, new NameExpr("\theaders = listOfRows.get(0).findElements(By.tagName(\"td\"))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Column count of " + field.getName() + " table is \"+headers.size());}\t\n\t\tcatch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Failed to get column count of " + field.getName() + " table\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(\"failed to get column count of " + field.getName() + " table \")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addStmt(block, new NameExpr("\treturn headers.size()"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodValidateRowCount(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "validateRowCountOf" + meaningFulName + "Table");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "expectedRowCount"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("\tint actualRowCount = getRowCountOf" + meaningFulName + "Table()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(actualRowCount == (expectedRowCount))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"verification of Row count is successful\");}\t\n\t\tcatch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Actual: \"+actualRowCount+ \",Expected: \"+expectedRowCount);"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(\"failed to verify row count\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Actual: \"+actualRowCount+  \",Expected: \"+expectedRowCount);"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    @Override
    public void generateMethodValidateColumnCount(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "validateColumnCountOf" + meaningFulName + "Table");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "expectedColumnCount"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("\tint actualColumnCount = getColumnCountOf" + meaningFulName + "Table()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(actualColumnCount == (expectedColumnCount))"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"verification of column count is successful\");}\t\n\t\tcatch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Actual: \"+actualColumnCount+ \",Expected: \"+expectedColumnCount);"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(\"failed to verify column count\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Actual: \"+actualColumnCount+ \",Expected: \"+expectedColumnCount);"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodIsColumnNamePresent(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "isColumnNamePresent");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "expectedColumnName"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("\tList<WebElement> headers = new ArrayList<>()"));
        ASTHelper.addStmt(block, new NameExpr("\tList<String> columnNames = new ArrayList<>()"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\theaders = TABLE.findElements(By.tagName(\"th\"))"));
        ASTHelper.addStmt(block, new NameExpr("\t\tfor(int i=0;i<headers.size();i++) {columnNames.add(headers.get(i).getText());}"));
        ASTHelper.addStmt(block, new NameExpr("\t\t\tif(columnNames.contains(expectedColumnName)){\t" + "LOGGER.info(\"verification of column name is successful\");}\n\t\t\telse{Assert.fail(\"failed to verify column name\");}\t\n\t\t}catch(Exception e){"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(\"failed to verify column name\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(\"Failed to verify column name\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodRightClickWebElement(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "rightClickOn" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + field.getName() +".contextClick()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Right clicked on element successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Could not do right click on element \" + " + field.getName() +");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodChangeFocus(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "changeFocusTo" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\texecutor.executeScript(\"arguments[0].focus();\", " + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"User is able to change focus to " + meaningFulName + " successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"User is unable to change focus to " + meaningFulName + "\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodIsDisabled(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsDisabled");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("Boolean isElementDisabled = false;\n\t\t\ttry{\n\t\t\tif(" + field.getName() +".getAttribute(\"disabled\") != null)\n\t\t\t{"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"" + meaningFulName + " element is disabled\")"));
        ASTHelper.addStmt(block, new NameExpr("\t}}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"" + meaningFulName + " is not disabled\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodIsImage(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsImage");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tAssert.assertEquals(\"img\", " + field.getName() +".getTagName());\n\t\t\tLOGGER.info(\"User successfully verifies " + meaningFulName + " is a image\")"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"User is unable to verify " + meaningFulName + " is a image\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetColValue(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "getColValueFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString colText=" + field.getName() +".getText()"));
        ASTHelper.addStmt(block, new NameExpr("\tif(colText.isEmpty())\n\t\t\t{\n\t\t\t\tAssert.fail(\"Unable to fetch column value for " + meaningFulName + "\");\n\t\t\t\tLOGGER.info(\"Unable to fetch column value for " + meaningFulName + "\");\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Unable to fetch column value for " + meaningFulName + "\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetRowValue(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "getRowValueFor" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString rowText=" + field.getName() +".getText()"));
        ASTHelper.addStmt(block, new NameExpr("\tif(rowText.isEmpty())\n\t\t\t{\n\t\t\t\tAssert.fail(\"Unable to fetch row value for " + meaningFulName + "\");\n\t\t\t\tLOGGER.info(\"Unable to fetch row value for " + meaningFulName + "\");\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Unable to fetch row value for " + meaningFulName + "\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    @Override
    public void generateMethodGetAllValuesFromTable(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "getAllCellValuesFor" + meaningFulName + "Table");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBoolean flag=true;\n\t\t\tWebElement element = " + field.getName() +";\n\t\t\tList<WebElement>rowsTable = element.findElements(By.tagName(\"tr\"));\n\t\t\tint rowsCount = rowsTable.size();\n\t\t\tfor (int row = 0; row < rowsCount; row++) {\n\t\t\tList < WebElement > columnRows = rowsTable.get(row).findElements(By.tagName(\"td\"));\n\t\t\tint columnCount = columnRows.size();\n\t\t\tfor (int column = 0; column < columnCount; column++) {\n\t\t\tString cellText = columnRows.get(column).getText();\n\t\t\tif(cellText.isEmpty())\n\t\t\t{ \n\t\t\t\tflag=false;\n\t\t\t\tbreak"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\t}\n\t\t\tif(flag)\n\t\t\t{\n\t\t\t\tLOGGER.info(\"User successfully fetches all cell values for " + meaningFulName + " table\");\n\t\t\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\tAssert.fail(\"Unable to fetch all cell values for " + meaningFulName + " table\");\n\t\t\t\tLOGGER.info(\"Unable to fetch all cell values for " + meaningFulName + " table\");\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Unable to fetch all values for " + meaningFulName + " table\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    //////////
    public void generateMethodCopy(CompilationUnit c,LoggerUtils loggerUtils)  {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "copy");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function copy using keyboard actions (Ctrl+C)"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().keyDown(Keys.CONTROL).sendKeys(\"c\").keyUp(Keys.CONTROL).build().perform();\n\t\t\tLOGGER.info(\"Successfully copied\")"));
        ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"Could not perform copy operation\");\n\t\t\tAssert.fail(\"Could not perform copy operation\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not perform copy operation\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("withAction().keyDown(Keys.CONTROL).sendKeys(\"c\").keyUp(Keys.CONTROL).build().perform()")));
    }
    public void generateMethodPressEnter(CompilationUnit c,LoggerUtils loggerUtils)  {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "pressEnter");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for switching tabs"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().sendKeys(Keys.ENTER).build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Enter pressed successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Unable to press enter\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to press enter\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("withAction().sendKeys(Keys.ENTER).build().perform()")));

    }
    public void generateMethodPaste(CompilationUnit c,LoggerUtils loggerUtils)  {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "paste");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for switching tabs"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().keyDown(Keys.CONTROL).sendKeys(\"v\").keyUp(Keys.CONTROL).build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Paste Operation performed successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Could not perform paste operation\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not perform paste operation\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("withAction().keyDown(Keys.CONTROL).sendKeys(\"v\").keyUp(Keys.CONTROL).build().perform()")));


    }
    public void generateMethodSelectAll(CompilationUnit c,LoggerUtils loggerUtils){
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "selectAll");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for select all"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().keyDown(Keys.CONTROL).sendKeys(\"a\").keyUp(Keys.CONTROL).build().perform()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(\"Select All Operation performed successfully\")"));
        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Could not perform select all operation\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not perform select all operation\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("withAction().keyDown(Keys.CONTROL).sendKeys(\"a\").keyUp(Keys.CONTROL).build().perform()")));
    }

    public void generateMethodIsFileDownloaded(CompilationUnit c,LoggerUtils loggerUtils) {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("boolean", 0), "isFileDownloaded");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "fileName"));
        method.setParameters(parameters);
        loggerUtils.log(LogLevel.INFO,parameters.toString());
        ASTHelper.addStmt(block, new NameExpr("//This function is for File Download"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tFile dir = new File(System.getProperty(\"user.home\") + \"/Downloads\");\n\t\t\tFile[] files = dir.listFiles()"));
        ASTHelper.addStmt(block, new NameExpr("\tlong startTime = System.currentTimeMillis();\n\t\t\twhile ((System.currentTimeMillis() - startTime) < Long.parseLong(HelperFunctions.readProperties(\"timeOut\"))){\n\t\t\t\t" + "for (File file : files) {\n\t\t\t\t\tif (file.getName().equalsIgnoreCase(fileName)) {;\n\t\t\t\t\tLOGGER.info(\"File downloaded successfully\");\n\t\t\t\t\treturn true"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\tcatch(" + "Exception e" + "){\n\t\t\tLOGGER.info(\"User gets an exception: \"+e);\n\t\t\tLOGGER.info(\"Unable to verify if file is downloaded\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify is file is downloaded\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t}"));
        ASTHelper.addStmt(block, new NameExpr("return false"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("Generated : isFileDownloaded()")));

    }

    public void generateMethodAttributeGetter(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getAttributeFrom" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO,parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("String valueOfAttribute = \"\""));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() + "));\n\t\t\t" + "LOGGER.info(" + "\"User gets attribute \"" + " + " + "attributeValue" + " +" + "\" as \" + valueOfAttribute + \" for " + field.getName() + " element\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tvalueOfAttribute = element" + "." + "getAttribute(" + "attributeValue" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not get value for attribute \" + attributeValue)"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "valueOfAttribute"));
        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr("return " + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public void generateMethodIsNotSelected(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting)  {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsNotSelected");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO,parameters.toString());
        method.setParameters(parameters);

        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tassertFalse(\"" + meaningFulName + " is selected\", wait.until(ExpectedConditions.elementToBeSelected(" + field.getName() + ")))"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"User verifies if checkbox is selected\")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify element Selected or not\")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));

        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr("return "+ field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
    @Override
    public void generateMethodTypeSetter(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils,Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextInto" + meaningFulName);
        loggerUtils.log(LogLevel.INFO,"Name of field: " + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        loggerUtils.log(LogLevel.INFO,parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.visibilityOf(" + field.getName() + "))"));
        ASTHelper.addStmt(block, new NameExpr("\ttypeInto(" + "element" + "," + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
    @Override
    public void generateMethodNavigateBack(CompilationUnit c,LoggerUtils loggerUtils)  {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "backwardNavigation");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().back()"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"User successfully navigated back\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tLOGGER.info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tLOGGER.info(\"Could not navigate back\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not navigate back\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr("getDriver().navigate().back())")));
        ASTHelper.addMember(c.getTypes().get(0), method);
        loggerUtils.log(LogLevel.INFO, String.valueOf(new NameExpr("Generated util function : navigateBack()")));

    }

    public void generateMethodDropDown(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "select" + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() +")"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" +field.getName()+ "." + "selectByVisibleText" + "(" + "typeText" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\tLOGGER.info(" + "\"User is able to select \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not select \" + typeText)"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "LOGGER.info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("}"));
        loggerUtils.log(LogLevel.INFO,String.valueOf(new NameExpr("new Select (" + field.getName() +")" + "." + "selectByVisibleText" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
    @Override
    public void generateMethodForVisibility(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting) {
        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
        loggerUtils.log(LogLevel.INFO, "Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsDisplayed");
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + field.getName() + ")"));
        ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not visible\", "+ field.getName() + "." + "isDisplayed" + "(" + "))"));
        ASTHelper.addStmt(block, new NameExpr("LOGGER.info(" + "\"User verifies " + field.getName() + " element is displayed\"" + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
}
