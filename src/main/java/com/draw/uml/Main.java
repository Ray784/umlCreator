package com.draw.uml;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.draw.uml.model.UserInput;
import com.draw.uml.registry.JavaObjectsRegistry;
import com.draw.uml.registry.UmlToolRegistry;
import com.draw.uml.service.DrawingService;
import com.draw.uml.service.ExtractorService;
import com.draw.uml.service.UserInputService;
import com.draw.uml.strategy.DrawingStrategy;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static void setupLogger() {
        /*
         * This method sets up the logger to display the log messages in the following format:
         * [timestamp] [log level] [message]
         * [2021-08-01 12:00:00] [INFO   ] This is an information message
         */
        LogManager.getLogManager().getLogger("").setLevel(Level.INFO);
        String logFormat = "[%1$tF %1$tT] [%4$-7s] %5$s %n";
        System.setProperty("java.util.logging.SimpleFormatter.format", logFormat);
    }

    public static void main(String[] args) {
        /*
         * This is the main method of the application. 
         * It creates the necessary services, registries, and strategies to convert Java classes to UML diagrams.
         * It also sets up the logger to display log messages in a specific format.
         * The user input is obtained and the Java classes are extracted and converted to UML diagrams.
         * The UML diagram is then saved to the specified output file path.
         * If an exception occurs during the process, the error message is logged.
         */
        setupLogger();

        logger.info("This is the Java classes to UML converter!");
        logger.info("Creating services");
        UserInputService userInputService = new UserInputService();        
        UserInput userInput = userInputService.obtainUserInput();
        ExtractorService extractorService = new ExtractorService(userInput);

        logger.info("Creating registries");
        UmlToolRegistry umlToolRegistry = new UmlToolRegistry();
        JavaObjectsRegistry javaObjectsRegistry = null;
        // Extract Java classes from the specified directory
        try {
            javaObjectsRegistry = extractorService.extract();
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        logger.info("Creating strategies");
        DrawingStrategy drawingStrategy = umlToolRegistry.getDrawingStrategy(userInput.getUmlToolType());
        DrawingService drawingService = new DrawingService(javaObjectsRegistry, drawingStrategy);
        // Draw UML diagram and save it to the specified output file path
        try {
            drawingService.draw(userInput.getOutputFilePath());
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
}