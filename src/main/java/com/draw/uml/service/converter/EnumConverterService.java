package com.draw.uml.service.converter;

import java.util.List;

import org.jboss.forge.roaster.model.source.EnumConstantSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.Method;

public class EnumConverterService extends ConverterService<JavaEnumSource> {
    @Override
    protected List<Attribute> convertFields(JavaEnumSource javaSource) {
        List<EnumConstantSource> enumConstants = javaSource.getEnumConstants();
        return enumConstants != null? enumConstants
            .stream()
            .map(enumConstant 
                -> Attribute
                    .builder()
                    .name(enumConstant.getName())
                    .type(null)
                    .build())
            .toList(): List.of();
    }

    @Override
    protected JavaType getType() {
        return JavaType.ENUM;
    }

    @Override
    protected List<String> convertParents(JavaEnumSource javaClass) {
        return List.of();
    }

    @Override
    protected List<Method> convertMethods(JavaEnumSource javaSource) {
        return List.of();
    }
}