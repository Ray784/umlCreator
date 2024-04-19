package com.draw.uml.model.java;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString

/**
 * Represents an attribute in a UML class diagram.
 */
public class Attribute {
    private String name;
    private AttributeType type;

    /**
     * Returns the attribute signature.
     * The attribute signature includes the attribute name and type (if available).
     *
     * @return the attribute signature
     */
    public String toAttributeSignature() {
        StringBuilder attributeSignature = new StringBuilder();
        attributeSignature.append(name);
        if(type != null)
            attributeSignature.append(": ")
                .append(type.toTypeSignature());
        return attributeSignature.toString();
    }
}
