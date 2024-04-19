package com.draw.uml.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.draw.uml.exception.FileOperationException;
import com.draw.uml.model.enums.FileSystemEntityType;

public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    private FileUtil() {}

    public static void checkIfDirectory(String directoryPath) throws FileOperationException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory())
            throw new FileOperationException("Provided path is not a directory.");
    }

    public static void createDirectoryIfNotExist(String directoryPath) throws FileOperationException {
        logger.log(Level.INFO, "Creating directory: {0}", directoryPath);
        File directory = new File(directoryPath);
        if (!directory.exists() && !directory.mkdirs())
            throw new FileOperationException("Failed to create directory.");
    }

    public static FileSystemEntityType getFileSystemEntityType(String path) {
        File file = new File(path);
        if(file.exists()) {
            if(file.isFile())
                return FileSystemEntityType.FILE;
            else if(file.isDirectory())
                return FileSystemEntityType.DIRECTORY;
        }
        return FileSystemEntityType.UNKNOWN;
    }

    public static String getAbsolutePath(String filePath) {
        Path path = Path.of(filePath);
        return path.toAbsolutePath().toString();
    }

    public static String getParentPath(String filePath) {
        Path path = Path.of(filePath);
        return getAbsolutePath(path.getParent().toString());
    }

    public static void removeFileIfExist(String filePath) throws FileOperationException {
        logger.log(Level.INFO, "Removing file: {0}", filePath);
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                logger.severe(e.getMessage());
                throw new FileOperationException("Failed to remove existing file.");
            }
        }
    }

    public static void createFileIfNotExist(String filePath) throws FileOperationException {
        logger.log(Level.INFO, "Creating file: {0}", filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                logger.severe(e.getMessage());
                throw new FileOperationException("Failed to create file.");
            }
        }
    }

    public static void writeToFile(String filePath, String content) throws FileOperationException {
        logger.log(Level.INFO, "Writing to file: {0}", filePath);
        try {
            Files.write(new File(filePath).toPath(), content.getBytes());
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new FileOperationException("Failed to write to file.");
        }
    }

    public static String readFile(Path path) throws FileOperationException {
        logger.log(Level.INFO, "Reading file: {0}", path.getFileName());
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileOperationException("Error while reading file: " + path.toString());
        }
    }

    public static List<Path> findFilePathsByExtension(String directoryPath, String fileExtension) throws FileOperationException {
        Path path = Path.of(directoryPath);
        if(!Files.exists(path))
            throw new FileOperationException("Source directory not found");
        List<Path> javaFiles = null;
        try (Stream<Path> pathsStream = Files.walk(path)) {
            javaFiles = pathsStream
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(fileExtension))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileOperationException("Error while reading files");
        }
        return javaFiles;
    }
}
