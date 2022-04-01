package IFML;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

import static FactoryElements.ShapeSVGFunctions.activationExpressionSVG;

/**
 * @author David Lindeman
 * This object will require a special SVG shape construction as it has 5 sides and is not a traditional shape
 */
@Getter @Setter
@Table("IFML_Activation_Expression")
public class IFMLActivationExpression extends DrawingObject {
    String text;

    public IFMLActivationExpression(String id, InputObject inObj){
        super(id, inObj);
        text = "";
        super.inObject.setColor("#B7BDBB"); //light blue/grey, there are two colors to this shape the other is white
        generateShape();
    }

    public void generateShape(){
        super.shapeSVG = activationExpressionSVG(super.inObject);
        super.setSvgData(super.shapeSVG + txtToSVG(text,
                super.inObject.getXCord() - super.inObject.getParams()[0]*.5,
                super.inObject.getYCord() + super.inObject.getParams()[1]*1.5));
        System.out.println("Activation Expression generateShape");
    }

}
