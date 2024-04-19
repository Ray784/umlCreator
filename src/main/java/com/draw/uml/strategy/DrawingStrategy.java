package com.draw.uml.strategy;

import com.draw.uml.exception.FileOperationException;
import com.draw.uml.registry.JavaObjectsRegistry;

/**
 * The DrawingStrategy interface represents a strategy for drawing UML diagrams.
 */
public interface DrawingStrategy {
    /**
     * Initializes the drawing strategy.
     *
     * @param filePath    the file path where the UML diagram will be saved
     * @param umlBuilder  the StringBuilder used to build the UML diagram
     * @return            a message indicating the success or failure of the initialization
     * @throws FileOperationException if an error occurs during file operations
     */
    public String init(String filePath, StringBuilder umlBuilder) throws FileOperationException;

    /**
     * Draws the UML diagram using the provided JavaObjectsRegistry and umlBuilder.
     *
     * @param javaObjectsRegistry  the registry containing the Java objects to be included in the UML diagram
     * @param umlBuilder           the StringBuilder used to build the UML diagram
     */
    public void draw(JavaObjectsRegistry javaObjectsRegistry, StringBuilder umlBuilder);

    /**
     * Finishes the drawing process and performs any necessary cleanup.
     *
     * @param umlBuilder  the StringBuilder used to build the UML diagram
     */
    public void finish(StringBuilder umlBuilder);
}