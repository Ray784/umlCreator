package com.draw.uml.service;

import java.util.Scanner;
import java.util.logging.Logger;

import com.draw.uml.model.UserInput;
import com.draw.uml.model.enums.UmlToolType;

public class UserInputService {
    private final Logger logger = Logger.getLogger(UserInputService.class.getName());

    /**
     * Obtains the path/directory of the Java file(s) from the user.
     * 
     * @param scanner the Scanner object used for user input
     * @return the path/directory of the Java file(s) entered by the user
     */
    private String obtainJavaSourcePath(Scanner scanner) {
        System.out.print("Enter the path/directory of the Java file(s): ");
        return scanner.nextLine();
    }

    /**
     * Obtains the path/directory to save the UML diagram from the user.
     * 
     * @param scanner the Scanner object used for user input
     * @return the path/directory to save the UML diagram entered by the user
     */
    private String obtainOutputFilePath(Scanner scanner) {
        System.out.print("Enter the path/directory to save the UML diagram: ");
        return scanner.nextLine();
    }

    /**
     * Obtains the UML tool type selected by the user.
     * 
     * @param scanner the Scanner object used for user input
     * @return the UML tool type selected by the user
     */
    private UmlToolType obtainDrawingTool(Scanner scanner) {
        System.out.println("Select the UML tool: ");
        for(UmlToolType umlToolType: UmlToolType.values())
            System.out.println(umlToolType.getOrdinal() + ". " + umlToolType);
        System.out.print("Enter choice: ");
        int umlToolOrdinal = scanner.nextInt();
        return UmlToolType.fromOrdinal(umlToolOrdinal);
    }

    /**
     * Obtains the user input for generating the UML diagram.
     * 
     * @return the UserInput object containing the user input
     */
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