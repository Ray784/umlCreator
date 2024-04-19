package com.draw.uml.strategy;

import com.draw.uml.exception.FileOperationException;
import com.draw.uml.registry.JavaObjectsRegistry;

public interface DrawingStrategy {
    public String init(String filePath, StringBuilder umlBuilder) throws FileOperationException;
    public void draw(JavaObjectsRegistry javaObjectsRegistry, StringBuilder umlBuilder);
    public void finish(StringBuilder umlBuilder);
}