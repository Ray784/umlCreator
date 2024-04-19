package com.draw.uml.model.java;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AttributeType {
    private String mainType;
    private String typeArgs;

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
