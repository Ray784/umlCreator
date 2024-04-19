package com.draw.uml.service;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaUnit;

import com.draw.uml.exception.FileOperationException;
import com.draw.uml.model.UserInput;
import com.draw.uml.model.java.JavaObject;
import com.draw.uml.registry.ConverterServiceRegistry;
import com.draw.uml.registry.JavaObjectsRegistry;
import com.draw.uml.util.FileUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The ExtractorService class is responsible for extracting Java objects from source files.
 */
@Getter
@Setter
@AllArgsConstructor
public class ExtractorService {
    private UserInput userInput; 
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Extracts Java objects from source files.
     * 
     * @return The JavaObjectsRegistry containing the extracted Java objects.
     * @throws FileOperationException If there is an error during file operations.
     */
    public JavaObjectsRegistry extract() throws FileOperationException {
        List<Path> javaFiles = FileUtil.findFilePathsByExtension(userInput.getJavaSourcePath(), ".java");
        JavaObjectsRegistry javaObjectsRegistry = new JavaObjectsRegistry();
        for (Path path : javaFiles) {
            JavaObject javaSource = extractSource(path);
            if (javaSource != null)
                javaObjectsRegistry.registerJavaObject(javaSource);
        }
        return javaObjectsRegistry;
    }

    /**
     * Extracts a Java object from a source file.
     * 
     * @param path The path of the source file.
     * @return The extracted JavaObject.
     * @throws FileOperationException If there is an error during file operations.
     */
    private JavaObject extractSource(Path path) throws FileOperationException {
        logger.info("Extracting Java file: " + path.getFileName());
        String completeJavaSource = FileUtil.readFile(path);
        JavaUnit javaUnit = Roaster.parseUnit(completeJavaSource);
        ConverterServiceRegistry converterServiceRegistry = new ConverterServiceRegistry();
        return converterServiceRegistry.getConverterService(javaUnit.getGoverningType().getClass()).convert(javaUnit.getGoverningType());
    }
}