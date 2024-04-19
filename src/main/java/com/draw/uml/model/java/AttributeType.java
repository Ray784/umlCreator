package com.draw.uml.model.java;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString

/**
 * Represents the type of an attribute in a UML class diagram.
 */
public class AttributeType {
    private String mainType;
    private String typeArgs;

    /**
     * Converts the attribute type to a type signature.
     * 
     * @return The type signature of the attribute type.
     */
    public String toTypeSignature() {
        StringBuilder typeSignature = new StringBuilder();
        typeSignature.append(mainType);
        if (typeArgs != null && !typeArgs.isEmpty()) 
            typeSignature
                .append("<")
                .append(typeArgs)
                .append(">");
        return typeSignature.toString();
    }
}
