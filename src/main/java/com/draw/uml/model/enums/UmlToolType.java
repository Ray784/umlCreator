package com.draw.uml.model.enums;

import lombok.Getter;

@Getter
public enum UmlToolType {
    DRAWIO(1),
    PLANT_UML(2);

    private Integer ordinal;

    /**
     * Constructor for UmlToolType enum.
     * @param ordinal The ordinal value of the enum.
     */
    private UmlToolType(Integer ordinal) {
        this.ordinal = ordinal;
    }

    /**
     * Returns the UmlToolType enum based on the given ordinal value.
     * @param ordinal The ordinal value to search for.
     * @return The UmlToolType enum corresponding to the given ordinal value, or null if not found.
     */
    public static UmlToolType fromOrdinal(Integer ordinal) {
        for (UmlToolType umlToolType : UmlToolType.values()) {
            if (umlToolType.ordinal.equals(ordinal)) {
                return umlToolType;
            }
        }
        return null;
    }
}
