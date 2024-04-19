package com.draw.uml.model.enums;

import lombok.Getter;

@Getter
public enum UmlToolType {
    DRAWIO(1),
    PLANT_UML(2);

    private Integer ordinal;
    private UmlToolType(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public static UmlToolType fromOrdinal(Integer ordinal) {
        for (UmlToolType umlToolType : UmlToolType.values()) {
            if (umlToolType.ordinal.equals(ordinal)) {
                return umlToolType;
            }
        }
        return null;
    }
}
