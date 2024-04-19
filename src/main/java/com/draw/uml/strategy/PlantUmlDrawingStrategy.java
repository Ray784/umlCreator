package com.draw.uml.strategy;

import java.nio.file.Path;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.draw.uml.exception.FileOperationException;
import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.JavaObject;
import com.draw.uml.model.java.Method;
import com.draw.uml.registry.JavaObjectsRegistry;
import com.draw.uml.util.FileUtil;

/**
 * The PlantUmlDrawingStrategy class implements the DrawingStrategy interface
 * and provides methods to draw a class diagram using PlantUML syntax.
 */
public class PlantUmlDrawingStrategy implements DrawingStrategy {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String FILE_EXTENSION = ".puml";
    private static final String UML_START = "@startuml\n";
    private static final String UML_END = "@enduml\n";
    private final Random random = new Random();

    private final Map<JavaType, String> plantUmlHeaders = Map.of(
        JavaType.CLASS, "class",
        JavaType.ENUM, "enum",
        JavaType.INTERFACE, "interface"
    );

    /**
     * Initializes the PlantUML drawing strategy.
     *
     * @param directoryPath The directory path where the PlantUML file will be created.
     * @param umlBuilder    The StringBuilder to build the PlantUML content.
     * @return The absolute file path of the created PlantUML file.
     * @throws FileOperationException If there is an error creating the file.
     */
    @Override
    public String init(String directoryPath, StringBuilder umlBuilder) throws FileOperationException {
        logger.info("Initializing PlantUML drawing strategy");
        umlBuilder.append(UML_START);
        String fileName = "classDiagram_PlantUML_" + random.nextInt(1000, 9999);
        String filePath = Path.of(directoryPath, fileName + FILE_EXTENSION).toAbsolutePath().toString();
        FileUtil.createFileIfNotExist(filePath);
        return filePath;
    }

    /**
     * Draws the class diagram using PlantUML syntax.
     *
     * @param javaObjectsRegistry The registry containing the Java objects to be drawn.
     * @param umlBuilder          The StringBuilder to build the PlantUML content.
     */
    @Override
    public void draw(JavaObjectsRegistry javaObjectsRegistry, StringBuilder umlBuilder) {
        logger.info("Drawing PlantUML class diagram");
        javaObjectsRegistry.getJavaObjects()
            .forEach(javaObject -> drawJavaObject(javaObject, umlBuilder));
        javaObjectsRegistry.getJavaObjects()
            .stream()
            .filter(javaObject -> !javaObject.getType().equals(JavaType.ENUM))
            .forEach(javaObject -> drawAssociation(javaObjectsRegistry, javaObject, umlBuilder));
        javaObjectsRegistry.getJavaObjects()
            .stream()
            .filter(javaObject -> !javaObject.getType().equals(JavaType.ENUM))
            .forEach(javaObject -> drawRelation(javaObjectsRegistry, javaObject, umlBuilder));
    }

    /**
     * Draws a Java object using the PlantUML drawing strategy.
     *
     * @param javaObject   the Java object to be drawn
     * @param umlBuilder   the StringBuilder used to build the PlantUML code
     */
    private void drawJavaObject(JavaObject javaObject, StringBuilder umlBuilder) {
        logger.info("Drawing Java object: " + javaObject.getName());
        umlBuilder
            .append(plantUmlHeaders.get(javaObject.getType()))
            .append(" ")
            .append(javaObject.getName())
            .append(" {\n")
            .append(javaObject.getAttributes()
                    .stream()
                    .map(Attribute::toAttributeSignature)
                    .collect(Collectors.joining("\n")))
            .append("\n")
            .append(javaObject.getMethods()
                    .stream()
                    .map(Method::toMethodSignature)
                    .collect(Collectors.joining("\n")))
            .append("\n}\n");
    }

    /**
     * Draws associations for a Java object.
     *
     * @param javaObjectsRegistry The registry of Java objects.
     * @param javaObject The Java object for which associations are being drawn.
     * @param umlBuilder The StringBuilder used to build the UML diagram.
     */
    private void drawAssociation(JavaObjectsRegistry javaObjectsRegistry, JavaObject javaObject, StringBuilder umlBuilder) {
        logger.info("Drawing associations for Java object: " + javaObject.getName());
        javaObject.getAttributes().forEach(attribute -> {
            if(attribute.getType() != null) {
                String mainType = attribute.getType().getMainType();
                String typeArg = attribute.getType().getTypeArgs();
                String type = null;
                if (mainType != null && javaObjectsRegistry.contains(mainType))
                    type = mainType;
                else if (typeArg != null && javaObjectsRegistry.contains(typeArg))
                    type = typeArg;
                if (type != null) {
                    umlBuilder
                        .append(javaObject.getName())
                        .append(" -- ")
                        .append(type)
                        .append("\n");
                }
            }
        });
    }


    /**
     * Draws the relations for a Java object.
     *
     * @param javaObjectsRegistry The registry of Java objects.
     * @param javaObject The Java object for which relations are being drawn.
     * @param umlBuilder The StringBuilder used to build the UML diagram.
     */
    private void drawRelation(JavaObjectsRegistry javaObjectsRegistry, JavaObject javaObject, StringBuilder umlBuilder) {
        logger.info("Drawing relations for Java object: " + javaObject.getName());
        javaObject.getParents().forEach(parent -> {
            JavaObject parentObject = javaObjectsRegistry.getJavaObject(parent);
            if(parentObject != null)
                umlBuilder
                    .append(parentObject.getName())
                    .append(parentObject.getType().equals(JavaType.INTERFACE) ? " <|.. " : " <|-- ")
                    .append(javaObject.getName())
                    .append("\n");
        });
    }

    /**
     * Finishes the PlantUML drawing strategy.
     *
     * @param umlBuilder The StringBuilder to build the PlantUML content.
     */
    @Override
    public void finish(StringBuilder umlBuilder) {
        logger.info("Finishing PlantUML drawing strategy");
        umlBuilder.append(UML_END);
    }
}