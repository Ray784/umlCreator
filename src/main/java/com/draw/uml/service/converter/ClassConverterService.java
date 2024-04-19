package com.draw.uml.service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.Method;

public class ClassConverterService extends ConverterService<JavaClassSource> {
    @Override
    protected List<Attribute> convertFields(JavaClassSource javaSource) {
        return convertFields(javaSource.getFields());
    }

    @Override
    protected JavaType getType() {
        return JavaType.CLASS;
    }

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

    @Override
    protected List<Method> convertMethods(JavaClassSource javaSource) {
        return convertMethods(javaSource.getMethods());
    }
}
