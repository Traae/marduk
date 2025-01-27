package Server.Files;

import java.io.Serializable;

/**
 * SVGformat object.
 *
 * A simple data class for transmission of the SVG data from the FileExporter.
 *
 * @author Traae
 */
public class SVGformatData implements fileFormat, Serializable {
    public String name;
    public String data;

    @Override
    public String getName() {
        return name + ".svg";
    }
    @Override
    public byte[] getData() {
        return data.getBytes();
    }
}
