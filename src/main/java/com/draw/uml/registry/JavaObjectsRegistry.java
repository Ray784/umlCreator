package com.draw.uml.registry;

import java.util.Map;

import com.draw.uml.model.java.JavaObject;

import java.util.Collection;
import java.util.HashMap;

public class JavaObjectsRegistry {
   private Map<String, JavaObject> javaObjects = new HashMap<>();

    public void registerJavaObject(JavaObject javaObject) {
        javaObjects.put(javaObject.getName(), javaObject);
    }

    public JavaObject getJavaObject(String name) {
        return javaObjects.get(name);
    }

    public boolean contains(String name) {
        return javaObjects.containsKey(name);
    }

    public Collection<JavaObject> getJavaObjects() {
        return javaObjects.values();
    }
}