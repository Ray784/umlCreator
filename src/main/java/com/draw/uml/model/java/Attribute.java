package com.draw.uml.model.java;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Attribute {
    private String name;
    private AttributeType type;

    public String toAttributeSignature() {
        StringBuilder attributeSignature = new StringBuilder();
        attributeSignature.append(name);
        if(type != null)
            attributeSignature.append(": ")
                .append(type.toTypeSignature());
        return attributeSignature.toString();
    }
}
