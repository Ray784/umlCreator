package com.draw.uml.model.java;

import java.util.List;

import com.draw.uml.model.enums.JavaType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a Java object in the UML diagram.
 */
@Getter
@Builder
@Setter
@ToString
public class JavaObject {
    private JavaType type;
    private String packageName;
    private String name;
    private List<String> parents;
    private List<Attribute> attributes;
    private List<Method> methods;
}