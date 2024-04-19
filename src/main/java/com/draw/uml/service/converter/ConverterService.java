package com.draw.uml.service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.AttributeType;
import com.draw.uml.model.java.JavaObject;
import com.draw.uml.model.java.Method;

/**
 * Abstract class representing a Converter Service for converting JavaSource objects to JavaObject objects.
 * @param <T> the type of JavaSource object to be converted
 */
public abstract class ConverterService<T extends JavaSource<T>> {

    /**
     * Converts a JavaSource object to a JavaObject object.
     * @param javaSource the JavaSource object to be converted
     * @return the converted JavaObject object
     */
    public JavaObject convert(T javaSource) {
        return javaSource != null? JavaObject
            .builder()
            .type(getType())
            .packageName(javaSource.getPackage())
            .attributes(convertFields(javaSource))
            .methods(convertMethods(javaSource))
            .parents(convertParents(javaSource))
            .name(javaSource.getName())
            .build(): null;
    }

    /**
     * Converts a list of ParameterSource objects to a list of Attribute objects.
     * @param parameterSources the list of ParameterSource objects to be converted
     * @return the converted list of Attribute objects
     */
    protected List<Attribute> convertMethodParameters(List<ParameterSource<T>> parameterSources) {
        return parameterSources != null? parameterSources
            .stream()
            .map(parameterSource 
                -> Attribute
                    .builder()
                    .name(parameterSource.getName())
                    .type(convertType(parameterSource.getType()))
                    .build())
            .toList(): List.of();
    }
    
    /**
     * Converts a Type object to an AttributeType object.
     * @param typeSource the Type object to be converted
     * @return the converted AttributeType object
     */
    protected AttributeType convertType(Type<T> typeSource) {
        return typeSource != null? AttributeType
            .builder()
            .mainType(typeSource.getName())
            .typeArgs(typeSource.getTypeArguments().stream().map(Object::toString).collect(Collectors.joining(",")))
            .build(): null;
    }

    /**
     * Extracts the name from a canonical name.
     * @param canonicalName the canonical name
     * @return the extracted name
     */
    protected String getNameFromCanonicalName(String canonicalName) {
        return canonicalName.substring(canonicalName.lastIndexOf(".") + 1);
    }

    /**
     * Converts the fields of a JavaSource object to a list of Attribute objects.
     * @param javaSource the JavaSource object whose fields are to be converted
     * @return the converted list of Attribute objects
     */
    protected abstract List<Attribute> convertFields(T javaSource);

    /**
     * Converts the parent classes/interfaces of a JavaSource object to a list of strings.
     * @param javaSource the JavaSource object whose parent classes/interfaces are to be converted
     * @return the converted list of strings
     */
    protected abstract List<String> convertParents(T javaSource);

    /**
     * Converts the methods of a JavaSource object to a list of Method objects.
     * @param javaSource the JavaSource object whose methods are to be converted
     * @return the converted list of Method objects
     */
    protected abstract List<Method> convertMethods(T javaSource);

    /**
     * Gets the JavaType of the ConverterService.
     * @return the JavaType of the ConverterService
     */
    protected abstract JavaType getType();

    /**
     * Converts a list of MethodSource objects to a list of Method objects.
     * @param methodSources the list of MethodSource objects to be converted
     * @return the converted list of Method objects
     */
    protected List<Method> convertMethods(List<MethodSource<T>> methodSources) {
        return methodSources != null? methodSources
            .stream()
            .map(methodSource 
                -> Method
                    .builder()
                    .name(methodSource.getName())
                    .parameters(convertMethodParameters(methodSource.getParameters()))
                    .returnType(convertType(methodSource.getReturnType()))
                    .build())
            .toList(): List.of();
    }

    /**
     * Converts a list of FieldSource objects to a list of Attribute objects.
     * @param fieldSources the list of FieldSource objects to be converted
     * @return the converted list of Attribute objects
     */
    protected List<Attribute> convertFields(List<FieldSource<T>> fieldSources) {
        return fieldSources != null? fieldSources
            .stream()
            .map(fieldSource 
                -> Attribute
                    .builder()
                    .name(fieldSource.getName())
                    .type(convertType(fieldSource.getType()))
                    .build())
            .toList(): List.of();
    }
}