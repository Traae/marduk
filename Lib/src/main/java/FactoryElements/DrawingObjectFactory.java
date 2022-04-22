package FactoryElements;

import DrawingObjects.*;
import DrawingObjects.Line;
import DrawingObjects.WireframeObject;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrawingObjectFactory {
    //Text for Container, for now we will use a Map if space becomes an issue we can move to arrays with magic numbers
    Map<String, String> containerHeaders = Stream.of(new String[][] {
            { "View" , "View Container" },
            { "XOR" , "XOR View Container" },
            { "Landmark" , "Landmark View Container"},
            { "Default" , "Default View Container"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    /**
     * TODO this is a new version of the code using the Enum I made,  original code is comment out below - Traae;
     *
     * @param input the InputObject of what you mant to make
     * @param id The new Drawing Obejcts Id
     * @return the New Drawing Object
     */
    public DrawingObject create(InputObject input, String id){

        String shapeIdHandle = "";
        if(ShapeTypes.valueOf(input.getShapeType()).equals(ShapeTypes.rectangle.getValue()) ||
                ShapeTypes.valueOf(input.getShapeType()).equals(ShapeTypes.square.getValue()) ||
                ShapeTypes.valueOf(input.getShapeType()).equals(ShapeTypes.circle.getValue())||
                ShapeTypes.valueOf(input.getShapeType()).equals(ShapeTypes.hexagon.getValue()) ||
                ShapeTypes.valueOf(input.getShapeType()).equals(ShapeTypes.parallelogram.getValue())){
            shapeIdHandle = ShapeTypes.Wireframe_Object.getValue();
        }
        else{
            shapeIdHandle = input.getShapeType();
        }

        switch (ShapeTypes.enumOfString(shapeIdHandle)) {
            case IFML_Action:
                return new IFMLAction(id, input);
            case IFML_Activation_Expression:
                return new IFMLActivationExpression(id, input);
            case IFML_Container:
                return new IFMLContainer(id, input);
            case IFML_Event:
                return new IFMLEvent(id, input);
            case IFML_Module:
                return new IFMLModule(id, input);
            case IFML_Parameter:
                return new IFMLParameterParallelogram(id, input);
            case IFML_View_Component:
                return new IFMLViewComponent(id, input);
            case IFML_View_Component_Part:
                return new IFMLViewComponentPart(id, input);
            case IFML_Line:
                return new IFMLLine(id, input);
            case Wireframe_Object:
                return new WireframeObject(id, input);
            case Line:
                return new Line(id, input);
            default:
                return null;

        }
    }


}
