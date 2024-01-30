package com.gemini.gpog.framework;//package framework;
//
//import logger.LoggerUtils;
//import japa.parser.ASTHelper;
//import japa.parser.ast.CompilationUnit;
//import japa.parser.ast.body.MethodDeclaration;
//import japa.parser.ast.body.ModifierSet;
//import japa.parser.ast.body.Parameter;
//import japa.parser.ast.expr.NameExpr;
//import japa.parser.ast.stmt.BlockStmt;
//import reporting.Reporting;
//
//import java.lang.reflect.Field;
//import java.util.LinkedList;
//import java.util.List;
//
//public class SeleniumMethodCodeGenerator implements Framework {
//    @Override
//    public void generateElementVerifyNotSelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
//        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
//        loggerUtils.logInfo("Name of field: " + meaningFulName);
//        // Add the Getter method
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsNotSelected");
//        // add a body to the method
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
//        List<Parameter> parameters = new LinkedList<>();
//        method.setParameters(parameters);
//        // add a statement do the method body
//        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
//        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
//        ASTHelper.addStmt(block, new NameExpr("\tassertFalse(\"" + meaningFulName + " is selected\", wait.until(ExpectedConditions.elementToBeSelected(" + locator + "." + field.getName() + ")))"));
//        ASTHelper.addStmt(block, new NameExpr("\tloggerUtils.logInfo(" + "\"User verifies if checkbox is selected\")"));
//        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "reporting.reportSteps(\"Failure\", \"Unable to verify element Selected or not\", \"\")"));
//        ASTHelper.addStmt(block, new NameExpr("\tloggerUtils.logError(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));
//
//        loggerUtils.logInfo(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
//        loggerUtils.logInfo(String.valueOf(new NameExpr("return " + locator + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
//        ASTHelper.addMember(c.getTypes().get(0), method);
//    }
//
//    @Override
//    public void generateElementVerifySelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
//        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
//        loggerUtils.logInfo("Name of field: " + meaningFulName);
//        // Add the Getter method
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsNotSelected");
//        // add a body to the method
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
//        List<Parameter> parameters = new LinkedList<>();
//        method.setParameters(parameters);
//        // add a statement do the method body
//        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
//        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
//        ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is selected\", wait.until(ExpectedConditions.elementToBeSelected(" + locator + "." + field.getName() + ")))"));
//        ASTHelper.addStmt(block, new NameExpr("\tloggerUtils.logInfo(" + "\"User verifies if checkbox is selected\")"));
//        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "reporting.reportSteps(\"Failure\", \"Unable to verify element Selected or not\", \"\")"));
//        ASTHelper.addStmt(block, new NameExpr("\tloggerUtils.logError(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));
//
//        loggerUtils.logInfo(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
//        loggerUtils.logInfo(String.valueOf(new NameExpr("return " + locator + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
//        ASTHelper.addMember(c.getTypes().get(0), method);
//    }
//
//    public void generateInputTypeInto(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
//        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextInto" + meaningFulName);
//        loggerUtils.logInfo("Name of field: " + meaningFulName);
//        // add a body to the method
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
//        List<Parameter> parameters = new LinkedList<>();
//        loggerUtils.logInfo(parameters.toString());
//
//        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
//        method.setParameters(parameters);
//        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
//
//        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
//        ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + locator + "." + field.getName() + "))"));
//        ASTHelper.addStmt(block, new NameExpr("\ttypeInto(" + "element" + "," + "typeText" + ")"));
//        ASTHelper.addStmt(block, new NameExpr("\tloggerUtils.logInfo(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
//        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "reporting.reportSteps(\"Failure\", \"Could not enter \" + typeText + \" into " + meaningFulName + ", \"\")"));
//        ASTHelper.addStmt(block, new NameExpr("\t" + "loggerUtils.logInfo(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
//        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
//        ASTHelper.addStmt(block, new NameExpr("}"));
//        loggerUtils.logInfo(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
//
//        ASTHelper.addMember(c.getTypes().get(0), method);
//    }
//
//    public void generateInputVerifyValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
//
//        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
//        loggerUtils.logInfo("Name of field: " + meaningFulName);
//        // Add the Getter method
//        // /////////////////////////////////////////////////////////////
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyValueFrom" + meaningFulName);
//        // add a body to the method
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
//        List<Parameter> parameters = new LinkedList<>();
//        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
//        method.setParameters(parameters);
//
//        // add a statement do the method body
//        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
//
//        ASTHelper.addStmt(block, new NameExpr("try{\n\t\tWebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties(\"timeOut\"))))"));
//        ASTHelper.addStmt(block, new NameExpr("WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + locator + "." + field.getName() + "));\n\t\tassertTrue(\"Actual value: \" + element" + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + "element" + "." + "getAttribute(\"value\")" + "))"));
//        ASTHelper.addStmt(block, new NameExpr("loggerUtils.logInfo(" + "\"User verifies value: \"" + " + " + "typeText + \"for " + meaningFulName + "\")"));
//        loggerUtils.logInfo(String.valueOf(new NameExpr("return " + "element" + ".getAttribute(\"value\")")));
//        ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "reporting.reportSteps(\"Failure\", \"Could not verify value for " + field.getName() + ")\", \"\")"));
//        ASTHelper.addStmt(block, new NameExpr("\t" + "loggerUtils.logInfo(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
//        ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
//        ASTHelper.addStmt(block, new NameExpr("}"));
//        ASTHelper.addMember(c.getTypes().get(0), method);
//    }
//
//    public void generateElementVerifyAttributeValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting) {
//
//        String meaningFulName = HelperFunctions.getMeaningFullName(field.getName());
//        loggerUtils.logInfo("Name of field: " + meaningFulName);
//        // Add the Getter method
//        // /////////////////////////////////////////////////////////////
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyAttributeValueFor" + meaningFulName);
//        // add a body to the method
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
//        List<Parameter> parameters = new LinkedList<>();
//        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeName"));
//        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
//        method.setParameters(parameters);
//
//        // add a statement do the method body
//        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + locator + "." + field.getName() + ")"));
//
//        ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
//        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + locator + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\ttext = getAttributeName(" + locator + "." + field.getName() + ",attributeName)"));
//        ASTHelper.addStmt(block, new NameExpr("\tif(attributeValue.equals(text))\n\t\t\t{\n\t\t\t\t" + "reporting.reportSteps(\"Verify if value of \"+attributeName+\" matches\" +attributeValue,\"Validation Successful\", STATUS.PASS);\n\t\t\t\tloggerUtils.logInfo(\"Value of \"+attributeName+\" matches \" +attributeValue)"));
//        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "reporting.reportSteps(\"Verify if value of \"+attributeName+\" matches\" +attributeValue,\"Validation Failed\", STATUS.FAIL);\n\t\t\t\tloggerUtils.logError(\"Value of \"+attributeName+\" does not matches \" +attributeValue)"));
//        ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
//        ASTHelper.addStmt(block, new NameExpr("\treporting.reportSteps(\"Get value of Value attribute for " + field.getName() + " field\",\"Unable to get attribute value\", STATUS.FAIL);\n\t\t\tloggerUtils.logError(\"Unable to get attribute value\");\n\t\t\tloggerUtils.logError(\"User gets an exception: \"+e)"));
//        ASTHelper.addStmt(block, new NameExpr("}"));
//        ASTHelper.addMember(c.getTypes().get(0), method);
//    }
//}
