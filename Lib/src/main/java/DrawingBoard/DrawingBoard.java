package DrawingBoard;

import FactoryElements.InputObject;
import DrawingObjects.DrawingObject;
import FactoryElements.DrawingObjectFactory;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//@Table("Drawing_Board")
@Getter @Setter
public class DrawingBoard implements Serializable {
    private final double SIZE_DEFAULT = 1000;

    String name;
    private String id;
    private ArrayList<String> users;

    Map<String, DrawingObject> objects = new HashMap<>();
    String[] objectTypes = new String[]{
        "IFML_Action",
        "IFML_Activation_Expression",
        "IFML_Container",
        "IFML_Event",
        "IFML_Module",
        "IFML_Parameter",
        "IFML_View_Component",
        "IFML_View_Component_Part",
        "IFML_Line",
        "Line",
        "Wireframe_Object"
    };
    double xMax;
    double yMax;
    DrawingObjectFactory drawingObjectFactory = new DrawingObjectFactory();
    static int idIndex = 1;

    public DrawingBoard(){}
    public DrawingBoard(double xMax, double yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        name = "diagram name";
        id = null;
    }

    public void setId(String id) {
        if (id == null){
            this.id = id;
        }
    }
    public String getId() {
        return id;
    }

    public void addObject(InputObject inObj){
        objects.put(Integer.toString(idIndex), drawingObjectFactory.create(inObj, Integer.toString(idIndex)));
        idIndex++;
    }

    public void removeObject(String id){
        objects.remove(id);
    }

    public DrawingObject getObject(String id){
        return objects.get(id);
    }

    public Set<String> getIds(){
        return objects.keySet();
    }

    public String getSVGDataFromId(String id){
        return objects.get(id).getSVGData();
    }

    /**
     * EXPERIMENT FUNCTION FOR TYLER 1
     *
     * This is the plain SVG path example from the tutorial as a control test.
     *
     * @author Traae
     * @return SVG example path string
     */
    public String TYLERreturnSVGPathExample(){
       return  "M 100 100 L 300 100 L 200 300 z";
    }

    /**
     * EXPERIMENT FUNCTION FOR TYLER 2
     *
     * This a simple svg rectangle, god I hope SVGPATH doesn't just mean draw paths.
     *
     * @author Traae
     * @return SVG example rectangle path string
     */
    public String TYLERreturnSVGRectExample(){
        return "M 20 -30 L 120 -30 L 120 -130 M 20 -130 L 120 -130 L 20 -30";             //x=\"20.0\" width=\"100.0\" height=\"100.0\" y=\"30.0\" stroke=\"#000000\"/";
    }

    /**
     * EXPERIMENT FUNCTION FOR TYLER 3
     *
     * This is the compiled path minus all the edge symbols
     *
     * @author Traae
     * @return SVG example path string
     */
    public String TYLERreturnSVGPathEdgeless(){
        String svgData = "";
        for(String key : objects.keySet()){
            svgData += "\n" + objects.get(key).getSVGData();
            svgData += "\n" + objects.get(key).txtToSVG();
        }
        svgData.replaceAll("[<>]", " ");
        return svgData;
    }

    /**
     * EXPERIMENT FUNCTION FOR TYLER 4
     *
     * This is the compiled path with all the edge symbols
     *
     * @author Traae
     * @return SVG example path string
     */
    public String TYLERreturnSVGPath(){
        String svgData = "";
        for(String key : objects.keySet()){
            svgData += "\n" + objects.get(key).getSVGData();
            svgData += "\n" + objects.get(key).txtToSVG();
        }

        return svgData;
    }

    public String returnSVGData(){
        String svgData = "";
        for(String key : objects.keySet()){
            svgData += "\n" + objects.get(key).getSVGData();
            svgData += "\n" + objects.get(key).txtToSVG();
        }

        return "<svg contentScriptType=\"text/ecmascript\" width=\"" + Double.toString(xMax) + "px\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"full \"\n" +
                "    zoomAndPan=\"magnify\" contentStyleType=\"text/css\" height=\"" + Double.toString(yMax) + "px\" preserveAspectRatio=\"xMidYMid meet\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "    version=\"1.0\">"
                + svgData
                + "\n"
                + "</svg>";
    }

    /**
     * @author
     * @return
     * Serializes all the objects in the map to JSON
     */
    public String serializeDrawingBoardToJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @author David Lindeman
     * Converts a drawing object to a JSON string using GSON
     */
    //reference for reading JSON files to java: https://attacomsian.com/blog/gson-read-json-file
    public String toJSON(DrawingObject dwObj) {
        //create Gson instance
        Gson gson = new Gson();
        //create json string to hold data
        String jsonString = gson.toJson(dwObj);
        return null;
    }

    /**
     * @author David Lindeman
     * @param jsonStr
     * @return
     * Takes a JSON string and converts it into a drawing board object
     */
    public DrawingObject fromJSON(String jsonStr){
        try {
            //create Gson instance
            Gson gson = new Gson();

            //set type for scoreboard
            Type drawingObjType = new TypeToken<DrawingObject>(){}.getType();

            //convert JSON string to scoreboard obj
            DrawingObject drawingObj = gson.fromJson(jsonStr, drawingObjType);

            return drawingObj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}