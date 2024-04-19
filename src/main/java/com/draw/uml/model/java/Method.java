package com.draw.uml.model.java;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
/**
 * Represents a method in a Java class.
 */
public class Method {
    private String name;
    private List<Attribute> parameters;
    private AttributeType returnType;

    /**
     * Generates the method signature.
     *
     * @return The method signature as a string.
     */
    public String toMethodSignature() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("(");
        if (parameters != null && !parameters.isEmpty())
            sb.append(
                parameters
                    .stream()
                    .map(Attribute::toAttributeSignature)
                    .collect(Collectors.joining(", ")));
        sb.append(")").append(": ");
        if(returnType != null)
            sb.append(returnType.toTypeSignature());
        return sb.toString();
    }
}
