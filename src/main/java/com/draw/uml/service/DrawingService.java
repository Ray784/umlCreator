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

@Getter
@Setter
@AllArgsConstructor
public class DrawingService {
    private final JavaObjectsRegistry javaObjectsRegistry;
    private final DrawingStrategy drawingStrategy;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private void init(String directoryPath) throws FileOperationException {
        logger.info("Initializing Drawing Service");
        directoryPath = FileUtil.getAbsolutePath(directoryPath);
        FileSystemEntityType fileType = FileUtil.getFileSystemEntityType(directoryPath);
        if(fileType.equals(FileSystemEntityType.UNKNOWN))
            FileUtil.createDirectoryIfNotExist(directoryPath);
        else if(fileType.equals(FileSystemEntityType.FILE))
            FileUtil.createDirectoryIfNotExist(FileUtil.getParentPath(directoryPath));
    }

    private void finish(String umlFilePath, StringBuilder umlBuilder) throws FileOperationException {
        drawingStrategy.finish(umlBuilder);
        FileUtil.writeToFile(umlFilePath, umlBuilder.toString());
    }

    public void draw(String directoryPath) throws FileOperationException {
        init(directoryPath);
        StringBuilder umlBuilder = new StringBuilder();
        String umlFilePath = drawingStrategy.init(directoryPath, umlBuilder);
        drawingStrategy.draw(javaObjectsRegistry, umlBuilder);
        finish(umlFilePath, umlBuilder);
    }
}