package com.draw.uml.service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.Method;

/**
 * This class is responsible for converting a JavaClassSource object into a ClassModel object.
 * It extends the ConverterService class and provides implementations for the abstract methods.
 */
public class ClassConverterService extends ConverterService<JavaClassSource> {

    /**
     * Converts the fields of the JavaClassSource object into a list of Attribute objects.
     * 
     * @param javaSource The JavaClassSource object to convert.
     * @return A list of Attribute objects representing the fields of the JavaClassSource object.
     */
    @Override
    protected List<Attribute> convertFields(JavaClassSource javaSource) {
        return convertFields(javaSource.getFields());
    }

    /**
     * Returns the JavaType of the converted object, which is JavaType.CLASS.
     * 
     * @return The JavaType of the converted object.
     */
    @Override
    protected JavaType getType() {
        return JavaType.CLASS;
    }

    /**
     * Converts the interfaces and super class of the JavaClassSource object into a list of parent class names.
     * 
     * @param javaClass The JavaClassSource object to convert.
     * @return A list of parent class names.
     */
    @Override
    protected List<String> convertParents(JavaClassSource javaClass){
        List<String> parents = javaClass.getInterfaces()
            .stream()
            .map(this::getNameFromCanonicalName)
            .collect(Collectors.toList());
        String superClass = javaClass.getSuperType();
        superClass = superClass != null? getNameFromCanonicalName(superClass): "";
        if(!superClass.equalsIgnoreCase("object"))
            parents.add(superClass);
        return parents;
    }

    /**
     * Converts the methods of the JavaClassSource object into a list of Method objects.
     * 
     * @param javaSource The JavaClassSource object to convert.
     * @return A list of Method objects representing the methods of the JavaClassSource object.
     */
    @Override
    protected List<Method> convertMethods(JavaClassSource javaSource) {
        return convertMethods(javaSource.getMethods());
    }
}
