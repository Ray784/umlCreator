package com.draw.uml.strategy;

import com.draw.uml.registry.JavaObjectsRegistry;

/**
 * The DrawIODrawingStrategy class implements the DrawingStrategy interface
 * and provides the implementation for drawing UML diagrams using the Draw.io tool.
 */
public class DrawIODrawingStrategy implements DrawingStrategy {
    
    /**
     * Initializes the drawing strategy.
     * 
     * @param filePath The file path where the UML diagram will be saved.
     * @param umlBuilder The StringBuilder object used to build the UML diagram.
     * @return A string representing the initialization status.
     */
    @Override
    public String init(String filePath, StringBuilder umlBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    /**
     * Draws the UML diagram using the provided JavaObjectsRegistry and umlBuilder.
     * 
     * @param javaObjectsRegistry The JavaObjectsRegistry containing the Java objects to be represented in the UML diagram.
     * @param umlBuilder The StringBuilder object used to build the UML diagram.
     */
    @Override
    public void draw(JavaObjectsRegistry javaObjectsRegistry, StringBuilder umlBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    /**
     * Finishes the drawing process and performs any necessary cleanup.
     * 
     * @param umlBuilder The StringBuilder object used to build the UML diagram.
     */
    @Override
    public void finish(StringBuilder umlBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'finish'");
    }
}
