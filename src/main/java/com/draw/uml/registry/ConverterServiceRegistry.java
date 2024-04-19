package com.draw.uml.registry;

import java.util.EnumMap;
import java.util.Map;

import org.jboss.forge.roaster.model.source.JavaSource;

import com.draw.uml.model.enums.JavaType;
import com.draw.uml.service.converter.ClassConverterService;
import com.draw.uml.service.converter.ConverterService;
import com.draw.uml.service.converter.EnumConverterService;
import com.draw.uml.service.converter.InterfaceConverterService;

/**
 * The ConverterServiceRegistry class is responsible for managing and providing converter services for different Java types.
 */
public class ConverterServiceRegistry {
    private Map<JavaType, ConverterService<? extends JavaSource<?>>> converterServiceMap = new EnumMap<>(JavaType.class);

    /**
     * Constructs a new ConverterServiceRegistry and initializes the converter service map with default converter services.
     */
    public ConverterServiceRegistry() {
        converterServiceMap.put(JavaType.CLASS, new ClassConverterService());
        converterServiceMap.put(JavaType.INTERFACE, new InterfaceConverterService());
        converterServiceMap.put(JavaType.ENUM, new EnumConverterService());
    }

    /**
     * Retrieves the converter service for the specified Java class.
     *
     * @param clazz the Java class for which to retrieve the converter service
     * @param <T>   the type of the Java source
     * @return the converter service for the specified Java class
     */
    @SuppressWarnings("unchecked")
    public <T extends JavaSource<T>> ConverterService<T> getConverterService(Class<?> clazz) {
        return (ConverterService<T>) converterServiceMap.get(JavaType.getTypeFromClass(clazz));
    }

    /**
     * Registers a new converter service for the specified Java class.
     *
     * @param clazz            the Java class for which to register the converter service
     * @param converterService the converter service to register
     */
    public void registerConverterService(Class<?> clazz, ConverterService<?> converterService) {
        converterServiceMap.put(JavaType.getTypeFromClass(clazz), converterService);
    }

    /**
     * Unregisters the converter service for the specified Java type.
     *
     * @param type the Java type for which to unregister the converter service
     */
    public void unregisterConverterService(JavaType type) {
        converterServiceMap.remove(type);
    }
}