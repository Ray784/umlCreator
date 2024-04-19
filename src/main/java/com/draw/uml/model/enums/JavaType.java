package com.draw.uml.model.enums;

import org.jboss.forge.roaster.model.impl.JavaClassImpl;
import org.jboss.forge.roaster.model.impl.JavaEnumImpl;
import org.jboss.forge.roaster.model.impl.JavaInterfaceImpl;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

public enum JavaType {
    CLASS, INTERFACE, ENUM, UNKNOWN;

    public static JavaType getTypeFromClass(Class<?> clazz) {
        if(clazz.equals(JavaClassSource.class) || clazz.equals(JavaClassImpl.class))
            return CLASS;
        else if(clazz.equals(JavaEnumSource.class) || clazz.equals(JavaEnumImpl.class))
            return ENUM;
        else if(clazz.equals(JavaInterfaceSource.class) || clazz.equals(JavaInterfaceImpl.class))
            return INTERFACE;
        return UNKNOWN;
    }
}
