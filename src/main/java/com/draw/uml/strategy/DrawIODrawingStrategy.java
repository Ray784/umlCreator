package com.draw.uml.strategy;

import com.draw.uml.registry.JavaObjectsRegistry;

public class DrawIODrawingStrategy implements DrawingStrategy {
    
    @Override
    public String init(String filePath, StringBuilder umlBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    @Override
    public void draw(JavaObjectsRegistry javaObjectsRegistry, StringBuilder umlBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void finish(StringBuilder umlBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'finish'");
    }
}
