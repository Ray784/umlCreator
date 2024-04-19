package com.draw.uml.service;

import java.util.Scanner;
import java.util.logging.Logger;

import com.draw.uml.model.UserInput;
import com.draw.uml.model.enums.UmlToolType;

public class UserInputService {
    private final Logger logger = Logger.getLogger(UserInputService.class.getName());
    private String obtainJavaSourcePath(Scanner scanner) {
        System.out.print("Enter the path/directory of the Java file(s): ");
        return scanner.nextLine();
    }

    private String obtainOutputFilePath(Scanner scanner) {
        System.out.print("Enter the path/directory to save the UML diagram: ");
        return scanner.nextLine();
    }

    private UmlToolType obtainDrawingTool(Scanner scanner) {
        System.out.println("Select the UML tool: ");
        for(UmlToolType umlToolType: UmlToolType.values())
            System.out.println(umlToolType.getOrdinal() + ". " + umlToolType);
        System.out.print("Enter choice: ");
        int umlToolOrdinal = scanner.nextInt();
        return UmlToolType.fromOrdinal(umlToolOrdinal);
    }

    public UserInput obtainUserInput() {
        logger.info("Obtaining user input");
        try(Scanner scanner = new Scanner(System.in)) {
            String javaSourcePath = obtainJavaSourcePath(scanner);
            String outputFilePath = obtainOutputFilePath(scanner);
            UmlToolType umlToolType = obtainDrawingTool(scanner);

            return UserInput.builder()
                .javaSourcePath(javaSourcePath)
                .outputFilePath(outputFilePath)
                .umlToolType(umlToolType)
                .build();
        }
    }
}