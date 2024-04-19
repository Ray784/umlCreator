package com.draw.uml.model.enums;

/**
 * The DrawIOStyle enum represents the different styles used in DrawIO diagrams.
 * Each style has a corresponding value that defines its appearance in the diagram.
 */
public enum DrawIOStyle {
    CLASS("text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;movable=0;resizable=0;deletable=0;editable=0;connectable=1"),
    CLASS_NAME("swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=30;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=0;marginBottom=0;html=1;"),
    SEPARATOR("line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;movable=0;resizable=0;deletable=0;editable=0;connectable=0"),
    IMPLEMENTS("endArrow=block;dashed=1;endFill=0;endSize=12;html=1;exitX=0.5;exitY=0;exitDx=0;exitDy=0;"),
    EXTENDS("endArrow=block;endSize=16;endFill=0;html=1"),
    REFERENCE("endArrow=block;endSize=10;endFill=1;html=1");

    private String value;

    /**
     * Constructs a DrawIOStyle enum with the specified value.
     * @param value the style value
     */
    private DrawIOStyle(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the DrawIOStyle.
     * @return the style value
     */
    public String getValue() {
        return value;
    }
}