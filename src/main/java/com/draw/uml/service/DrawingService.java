package com.draw.uml.service;

import java.util.logging.Logger;

import com.draw.uml.exception.FileOperationException;
import com.draw.uml.model.enums.FileSystemEntityType;
import com.draw.uml.registry.JavaObjectsRegistry;
import com.draw.uml.strategy.DrawingStrategy;
import com.draw.uml.util.FileUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The DrawingService class provides functionality to draw UML diagrams based on Java objects.
 */
 @Getter
 @Setter
 @AllArgsConstructor
public class DrawingService {
    private final JavaObjectsRegistry javaObjectsRegistry;
    private final DrawingStrategy drawingStrategy;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Initializes the DrawingService by creating the necessary directories if they don't exist.
     *
     * @param directoryPath The path to the directory where the UML diagrams will be saved.
     * @throws FileOperationException If an error occurs while creating the directories.
     */
    private void init(String directoryPath) throws FileOperationException {
        logger.info("Initializing Drawing Service");
        directoryPath = FileUtil.getAbsolutePath(directoryPath);
        FileSystemEntityType fileType = FileUtil.getFileSystemEntityType(directoryPath);
        if(fileType.equals(FileSystemEntityType.UNKNOWN))
            FileUtil.createDirectoryIfNotExist(directoryPath);
        else if(fileType.equals(FileSystemEntityType.FILE))
            FileUtil.createDirectoryIfNotExist(FileUtil.getParentPath(directoryPath));
    }

    /**
     * Finishes the drawing process by invoking the finish method of the drawing strategy and writing the UML diagram to a file.
     *
     * @param umlFilePath The path to the file where the UML diagram will be saved.
     * @param umlBuilder The StringBuilder containing the UML diagram.
     * @throws FileOperationException If an error occurs while writing the UML diagram to the file.
     */
    private void finish(String umlFilePath, StringBuilder umlBuilder) throws FileOperationException {
        drawingStrategy.finish(umlBuilder);
        FileUtil.writeToFile(umlFilePath, umlBuilder.toString());
    }

    /**
     * Draws the UML diagram based on the Java objects in the specified directory.
     *
     * @param directoryPath The path to the directory containing the Java objects.
     * @throws FileOperationException If an error occurs while drawing the UML diagram or writing it to a file.
     */
    public void draw(String directoryPath) throws FileOperationException {
        init(directoryPath);
        StringBuilder umlBuilder = new StringBuilder();
        String umlFilePath = drawingStrategy.init(directoryPath, umlBuilder);
        drawingStrategy.draw(javaObjectsRegistry, umlBuilder);
        finish(umlFilePath, umlBuilder);
    }
}