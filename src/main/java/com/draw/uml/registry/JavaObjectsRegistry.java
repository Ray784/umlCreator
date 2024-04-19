package com.draw.uml.registry;

import java.util.Map;

import com.draw.uml.model.java.JavaObject;

import java.util.Collection;
import java.util.HashMap;

/**
 * The JavaObjectsRegistry class represents a registry for Java objects.
 * It allows registering, retrieving, and checking the existence of Java objects.
 */
public class JavaObjectsRegistry {
   private Map<String, JavaObject> javaObjects = new HashMap<>();

    /**
     * Registers a Java object in the registry.
     * 
     * @param javaObject The Java object to be registered.
     */
    public void registerJavaObject(JavaObject javaObject) {
        javaObjects.put(javaObject.getName(), javaObject);
    }

    /**
     * Retrieves a Java object from the registry based on its name.
     * 
     * @param name The name of the Java object to retrieve.
     * @return The Java object with the specified name, or null if not found.
     */
    public JavaObject getJavaObject(String name) {
        return javaObjects.get(name);
    }

    /**
     * Checks if the registry contains a Java object with the specified name.
     * 
     * @param name The name of the Java object to check.
     * @return true if the registry contains the Java object, false otherwise.
     */
    public boolean contains(String name) {
        return javaObjects.containsKey(name);
    }

    /**
     * Retrieves all the Java objects stored in the registry.
     * 
     * @return A collection of all the Java objects in the registry.
     */
    public Collection<JavaObject> getJavaObjects() {
        return javaObjects.values();
    }
}