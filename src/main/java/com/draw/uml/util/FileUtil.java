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

/**
 * Utility class for file operations.
 */
public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    private FileUtil() {}

    /**
     * Checks if the provided path is a directory.
     *
     * @param directoryPath the path to check
     * @throws FileOperationException if the provided path is not a directory
     */
    public static void checkIfDirectory(String directoryPath) throws FileOperationException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory())
            throw new FileOperationException("Provided path is not a directory.");
    }

    /**
     * Creates a directory if it does not exist.
     *
     * @param directoryPath the path of the directory to create
     * @throws FileOperationException if failed to create the directory
     */
    public static void createDirectoryIfNotExist(String directoryPath) throws FileOperationException {
        logger.log(Level.INFO, "Creating directory: {0}", directoryPath);
        File directory = new File(directoryPath);
        if (!directory.exists() && !directory.mkdirs())
            throw new FileOperationException("Failed to create directory.");
    }

    /**
     * Gets the type of the file system entity (file, directory, or unknown) for the given path.
     *
     * @param path the path to check
     * @return the type of the file system entity
     */
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

    /**
     * Gets the absolute path of the given file.
     *
     * @param filePath the path of the file
     * @return the absolute path of the file
     */
    public static String getAbsolutePath(String filePath) {
        Path path = Path.of(filePath);
        return path.toAbsolutePath().toString();
    }

    /**
     * Gets the parent path of the given file.
     *
     * @param filePath the path of the file
     * @return the parent path of the file
     */
    public static String getParentPath(String filePath) {
        Path path = Path.of(filePath);
        return getAbsolutePath(path.getParent().toString());
    }

    /**
     * Removes the file if it exists.
     *
     * @param filePath the path of the file to remove
     * @throws FileOperationException if failed to remove the file
     */
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

    /**
     * Creates a file if it does not exist.
     *
     * @param filePath the path of the file to create
     * @throws FileOperationException if failed to create the file
     */
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

    /**
     * Writes content to the file.
     *
     * @param filePath the path of the file to write to
     * @param content the content to write
     * @throws FileOperationException if failed to write to the file
     */
    public static void writeToFile(String filePath, String content) throws FileOperationException {
        logger.log(Level.INFO, "Writing to file: {0}", filePath);
        try {
            Files.write(new File(filePath).toPath(), content.getBytes());
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new FileOperationException("Failed to write to file.");
        }
    }

    /**
     * Reads the content of the file.
     *
     * @param path the path of the file to read
     * @return the content of the file
     * @throws FileOperationException if failed to read the file
     */
    public static String readFile(Path path) throws FileOperationException {
        logger.log(Level.INFO, "Reading file: {0}", path.getFileName());
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileOperationException("Error while reading file: " + path.toString());
        }
    }

    /**
     * Finds file paths with the given file extension in the specified directory.
     *
     * @param directoryPath the path of the directory to search in
     * @param fileExtension the file extension to filter by
     * @return a list of file paths matching the file extension
     * @throws FileOperationException if the source directory is not found or an error occurs while reading files
     */
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
