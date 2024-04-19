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

public abstract class ConverterService<T extends JavaSource<T>> {

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
    
    protected AttributeType convertType(Type<T> typeSource) {
        return typeSource != null? AttributeType
            .builder()
            .mainType(typeSource.getName())
            .typeArgs(typeSource.getTypeArguments().stream().map(Object::toString).collect(Collectors.joining(",")))
            .build(): null;
    }

    protected String getNameFromCanonicalName(String canonicalName) {
        return canonicalName.substring(canonicalName.lastIndexOf(".") + 1);
    }

    protected abstract List<Attribute> convertFields(T javaSource);

    protected abstract List<String> convertParents(T javaSource);

    protected abstract List<Method> convertMethods(T javaSource);

    protected abstract JavaType getType();

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