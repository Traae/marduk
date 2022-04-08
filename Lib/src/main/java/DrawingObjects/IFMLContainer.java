package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;
//import
//import DrawingObjects.ShapeSVGFunctions.rectToSVG;

import static DrawingObjects.ShapeSVGFunctions.getLineElement;
import static DrawingObjects.ShapeSVGFunctions.rectToSVG;

/**
 * @author David Lindeman
 * The IFML Container object can have 4 different headers as per below
 * View, XOR, Landmark, Default
 * To cut down on the size of this file we will be storing the data that will be presented in the factory object
 * Takes in a rectangle for input object type
 */
@Getter @Setter
//@Table("IFML_Container")
public class IFMLContainer extends DrawingObject {
    String containerHeader;
    String text;
    ShapeSVGFunctions shapeSVGFunctions = new ShapeSVGFunctions();


    public IFMLContainer(String id, InputObject inObj){ //String cHeader, String defaultText,
        super(id, inObj);
        containerHeader = "";//cHeader;
        text = "";//defaultText; //text starts out as the value to the key of the map in IMFLFactory's variable "containerHeaders"
    }

    public String generateShape(){
        String headerTxtSvg = super.txtToSVG(containerHeader,
                super.getX() - inObject.getParams()[0]*.1,
                super.getY() - inObject.getParams()[1]*.1);

        String containerBox = rectToSVG(super.inObject);
        String headerLine = getLineElement(
                Double.toString(super.inObject.getXCord()),
                Double.toString(super.inObject.getXCord() + super.inObject.getParams()[0]),
                Double.toString(super.inObject.getYCord()*.25),
                Double.toString(super.inObject.getYCord()*.25)
                );

        //this could be refactored for the second text box to just be a line 15% down the top of the input rectangle however this doesnt work because our lines have set x,y cords
        return headerTxtSvg + "\n" + headerLine + "\n" + containerBox; //the shape SVG should be the combination of the two boxes, this may need to be changed depending on how we have to format the text
        //add text to the shapeSVG

    }
}
