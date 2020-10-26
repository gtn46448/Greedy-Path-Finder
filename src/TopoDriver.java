import java.awt.Graphics;
import java.util.Scanner;

/**
 * Driver that reads in a topographical map file and displays that map.
 *   @author Dave Reed
 *   @version 2/24/19
 */
public class TopoDriver {
    public static void main(String[] args) {
        System.out.print("Enter the map file name: ");
        Scanner input = new Scanner(System.in);
        String filename = input.next();

        TopoMap map = new TopoMap(filename);
        
        DrawingPanel panel = new DrawingPanel(map.getNumCols(), map.getNumRows());
        Graphics g = panel.getGraphics();

        map.draw(g);
        
        PathFinder pathFinder = new PathFinder(map);
        
        pathFinder.drawAllPaths(g);
        input.close();
    }    
}
