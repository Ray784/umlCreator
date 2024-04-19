package com.draw.uml.model.java;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Method {
    private String name;
    private List<Attribute> parameters;
    private AttributeType returnType;

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
