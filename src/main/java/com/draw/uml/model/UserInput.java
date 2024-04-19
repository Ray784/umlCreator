package com.draw.uml.model;

import com.draw.uml.model.enums.UmlToolType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the user input for generating UML diagrams.
 */
@Getter
@Setter
@Builder
public class UserInput {
    private String javaSourcePath;
    private String outputFilePath;
    private UmlToolType umlToolType;
}