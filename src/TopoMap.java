
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;

/**
 * Incomplete class for displaying greedy paths in a topographic map.
 *
 * @author Dave Reed
 * @version 2/24/19
 */
public class TopoMap {
	public static final int lowReplaceableNum = -1000000000;
	public static final int highReplaceableNum = 1000000000;
    private int[][] grid;
    private int numRows;
    private int numCols;
    private int highElevation;
    private int lowElevation;

    /**
     * Constructs a topographic map with elevations read in from a file.
     *   @param filename the file containing the elevations
     */
    public TopoMap(String filename) {
        try {
            Scanner infile = new Scanner(new File(filename));
            this.numCols = infile.nextInt();
            this.numRows = infile.nextInt();
            
            //assumes highest elevation above -1,000,000,000
            this.highElevation = lowReplaceableNum;
            //assumes lowest elevation below 1,000,000,000
            this.lowElevation = highReplaceableNum;

            this.grid = new int[this.numRows][this.numCols];
            for (int row = 0; row < this.numRows; row++) {
                for (int col = 0; col < this.numCols; col++) {
                    int test = infile.nextInt();
                	this.grid[row][col] = test;
                    if(test > this.highElevation) {
                    	this.highElevation = test;
                    }
                    if(test < this.lowElevation) {
                    	this.lowElevation = test;
                    }
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    /**
     * Accessor for the number of rows in the map. 
     *   @return the number of rows (i.e., y-coordinate range)
     */
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * Accessor for the number of columns in the map.
     *   @return the number of columns (i.e., x-coordinate range)
     */
    public int getNumCols() {
        return this.numCols;
    }

    /**
     * Accessor for the elevation at a given row/column.
     *   @param row the row (top = 0)
     *   @param col the column (left = 0)
     *   @return the elevation at that row/col
     */
    public int getElevation(int row, int col) {
        return this.grid[row][col];
    }

    /**
     * Draws the map with elevations scaled to 0..255 grayscale.
     * Note: as is, negative elevations are drawn as 0; elevations > 10,000
     *       are drawn as 10,000.
     *   @param g the Graphics object used to draw the map
     */
    public void draw(Graphics g) {
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                int val = 255 * (this.grid[row][col] - this.lowElevation) / (this.highElevation - this.lowElevation);
                val = Math.max(0, Math.min(val, 255));
                g.setColor(new Color(val, val, val));
                g.fillRect(col, row, 1, 1);
            }
        }
    }
}
