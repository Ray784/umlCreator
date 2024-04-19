package com.draw.uml.service.converter;

import java.util.List;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.Method;

public class InterfaceConverterService extends ConverterService<JavaInterfaceSource> {

    @Override
    protected List<Attribute> convertFields(JavaInterfaceSource javaSource) {
        return convertFields(javaSource.getFields());
    }

    @Override
    protected JavaType getType() {
        return JavaType.INTERFACE;
    }

    @Override
    protected List<Method> convertMethods(JavaInterfaceSource javaSource) {
        return convertMethods(javaSource.getMethods());
    }

    @Override
    protected List<String> convertParents(JavaInterfaceSource javaClass){
        return javaClass.getInterfaces()
        .stream()
        .map(this::getNameFromCanonicalName)
        .toList();
    }

}
