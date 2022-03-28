package FactoryElements;//This is our java class which will hold and handel all of our diagram elements

/**
 * @author David Lindeman
 */
public class Diagram{
    //List<DiagramElement> elements;
    int width;
    int height;
    int elementTotal;

//    public static void main(args[]){
//        System.out.println("main");
//    }

    public void addElement(){

    }

    public static AbstractElementFactory getFactory(String choice){
        //assign correct factory based on input
        if("Line".equalsIgnoreCase(choice)){
            return new LineFactory();
        }
        else if("IFML".equalsIgnoreCase(choice)){
            return new IFMLFactory();
        }
        return null;
    }

}
