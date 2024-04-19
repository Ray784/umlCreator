package com.draw.uml.service.converter;

import java.util.List;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.model.java.Attribute;
import com.draw.uml.model.java.Method;

/**
 * This class is responsible for converting Java interface source code into UML elements.
 */
public class InterfaceConverterService extends ConverterService<JavaInterfaceSource> {

    /**
     * Converts the fields of the Java interface source code into a list of attributes.
     *
     * @param javaSource The Java interface source code to convert.
     * @return The list of attributes converted from the Java interface source code.
     */
    @Override
    protected List<Attribute> convertFields(JavaInterfaceSource javaSource) {
        return convertFields(javaSource.getFields());
    }

    /**
     * Gets the type of the Java source code, which is an interface.
     *
     * @return The JavaType.INTERFACE enum value.
     */
    @Override
    protected JavaType getType() {
        return JavaType.INTERFACE;
    }

    /**
     * Converts the methods of the Java interface source code into a list of methods.
     *
     * @param javaSource The Java interface source code to convert.
     * @return The list of methods converted from the Java interface source code.
     */
    @Override
    protected List<Method> convertMethods(JavaInterfaceSource javaSource) {
        return convertMethods(javaSource.getMethods());
    }

    /**
     * Converts the parent interfaces of the Java interface source code into a list of parent interface names.
     *
     * @param javaClass The Java interface source code to convert.
     * @return The list of parent interface names converted from the Java interface source code.
     */
    @Override
    protected List<String> convertParents(JavaInterfaceSource javaClass){
        return javaClass.getInterfaces()
        .stream()
        .map(this::getNameFromCanonicalName)
        .toList();
    }

}
