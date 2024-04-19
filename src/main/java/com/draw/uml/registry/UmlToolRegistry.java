package com.draw.uml.registry;

import java.util.EnumMap;
import java.util.Map;

import com.draw.uml.model.enums.UmlToolType;
import com.draw.uml.strategy.DrawIODrawingStrategy;
import com.draw.uml.strategy.DrawingStrategy;
import com.draw.uml.strategy.PlantUmlDrawingStrategy;

public class UmlToolRegistry {
    private Map<UmlToolType, DrawingStrategy> drawingStrategyRegistry = new EnumMap<>(UmlToolType.class);

    public UmlToolRegistry() {
        registerDrawingStrategy(UmlToolType.DRAWIO, new DrawIODrawingStrategy());
        registerDrawingStrategy(UmlToolType.PLANT_UML, new PlantUmlDrawingStrategy());
    }

    public void registerDrawingStrategy(UmlToolType umlToolType, DrawingStrategy drawingStrategy) {
        drawingStrategyRegistry.put(umlToolType, drawingStrategy);
    }

    public DrawingStrategy getDrawingStrategy(UmlToolType umlToolType) {
        return drawingStrategyRegistry.get(umlToolType);
    }

    public void unregisterDrawingStrategy(UmlToolType umlToolType) {
        drawingStrategyRegistry.remove(umlToolType);
    }
}
