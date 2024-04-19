package com.draw.uml.registry;

import java.util.EnumMap;
import java.util.Map;

import com.draw.uml.model.enums.UmlToolType;
import com.draw.uml.strategy.DrawIODrawingStrategy;
import com.draw.uml.strategy.DrawingStrategy;
import com.draw.uml.strategy.PlantUmlDrawingStrategy;

/**
 * The UmlToolRegistry class is responsible for registering and retrieving drawing strategies for different UML tools.
 */
public class UmlToolRegistry {
    private Map<UmlToolType, DrawingStrategy> drawingStrategyRegistry = new EnumMap<>(UmlToolType.class);

    /**
     * Constructs a new UmlToolRegistry object and registers the default drawing strategies.
     */
    public UmlToolRegistry() {
        registerDrawingStrategy(UmlToolType.DRAWIO, new DrawIODrawingStrategy());
        registerDrawingStrategy(UmlToolType.PLANT_UML, new PlantUmlDrawingStrategy());
    }

    /**
     * Registers a drawing strategy for a specific UML tool type.
     *
     * @param umlToolType     the UML tool type
     * @param drawingStrategy the drawing strategy to be registered
     */
    public void registerDrawingStrategy(UmlToolType umlToolType, DrawingStrategy drawingStrategy) {
        drawingStrategyRegistry.put(umlToolType, drawingStrategy);
    }

    /**
     * Retrieves the drawing strategy for a specific UML tool type.
     *
     * @param umlToolType the UML tool type
     * @return the drawing strategy associated with the UML tool type, or null if not found
     */
    public DrawingStrategy getDrawingStrategy(UmlToolType umlToolType) {
        return drawingStrategyRegistry.get(umlToolType);
    }

    /**
     * Unregisters the drawing strategy for a specific UML tool type.
     *
     * @param umlToolType the UML tool type
     */
    public void unregisterDrawingStrategy(UmlToolType umlToolType) {
        drawingStrategyRegistry.remove(umlToolType);
    }
}
