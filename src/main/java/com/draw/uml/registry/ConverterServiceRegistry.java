package com.draw.uml.registry;

import java.util.EnumMap;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.service.converter.ClassConverterService;
import com.draw.uml.service.converter.ConverterService;
import com.draw.uml.service.converter.EnumConverterService;
import com.draw.uml.service.converter.InterfaceConverterService;

public class ConverterServiceRegistry {
    private Map<JavaType, ConverterService<? extends JavaSource<?>>> converterServiceMap = new EnumMap<>(JavaType.class);

    public ConverterServiceRegistry() {
        converterServiceMap.put(JavaType.CLASS, new ClassConverterService());
        converterServiceMap.put(JavaType.INTERFACE, new InterfaceConverterService());
        converterServiceMap.put(JavaType.ENUM, new EnumConverterService());
    }

    @SuppressWarnings("unchecked")
    public <T extends JavaSource<T>> ConverterService<T> getConverterService(Class<?> clazz) {
        return (ConverterService<T>) converterServiceMap.get(JavaType.getTypeFromClass(clazz));
    }

    public void registerConverterService(Class<?> clazz, ConverterService<?> converterService) {
        converterServiceMap.put(JavaType.getTypeFromClass(clazz), converterService);
    }

    public void unregisterConverterService(JavaType type) {
        converterServiceMap.remove(type);
    }
}