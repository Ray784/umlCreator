package com.draw.uml.service.converter;

import java.util.List;

import org.jboss.forge.roaster.model.source.EnumConstantSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.Method;

/**
 * This class is responsible for converting JavaEnumSource objects to UML attributes and methods.
 */
public class EnumConverterService extends ConverterService<JavaEnumSource> {

    /**
     * Converts the fields of a JavaEnumSource object to a list of UML attributes.
     *
     * @param javaSource The JavaEnumSource object to convert.
     * @return The list of UML attributes.
     */
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

    /**
     * Gets the JavaType of the converted objects.
     *
     * @return The JavaType.ENUM.
     */
    @Override
    protected JavaType getType() {
        return JavaType.ENUM;
    }

    /**
     * Converts the parent classes/interfaces of a JavaEnumSource object to a list of fully qualified names.
     *
     * @param javaClass The JavaEnumSource object to convert.
     * @return The list of fully qualified names.
     */
    @Override
    protected List<String> convertParents(JavaEnumSource javaClass) {
        return List.of();
    }

    /**
     * Converts the methods of a JavaEnumSource object to a list of UML methods.
     *
     * @param javaSource The JavaEnumSource object to convert.
     * @return The list of UML methods.
     */
    @Override
    protected List<Method> convertMethods(JavaEnumSource javaSource) {
        return List.of();
    }
}