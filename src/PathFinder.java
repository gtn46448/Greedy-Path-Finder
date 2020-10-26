import java.awt.Color;
import java.awt.Graphics;

public class PathFinder { 
	public static final int highReplaceableNum = 1000000000;
	private TopoMap map;
	/** 
	 * Constructs a PathFinder object with the given topographical map. 
	 * @param topo the map in which paths are to be found
	 */ 
	public PathFinder(TopoMap topo) {
		this.map = topo;
	} 
	/** 
	 * Draws a greedy path from the west edge to the east edge of the map. 
	 * @param g the graphics object in which the path will be drawn 
	 * @param row the starting row of the path (column is assumed to be 0) 
	 * @param pathColor the color in which the path is drawn (e.g., Color.GREEN)
	 * @return the sum of all elevation changes along the path 
	 */
	public int drawGreedyPath(Graphics g, int row, Color pathColor) {
		//This might be easier as a recursive function :\
		int current;
		int path = row;
		int sum = 0;
		g.setColor(pathColor);
		g.fillRect(0, path, 1, 1);
		for(int i = 0; i < this.map.getNumCols() - 1; i++) {
			current = this.map.getElevation(path,i);
			//assumes greatest elevation change is less than 1,000,000,000
			int selector = highReplaceableNum;
			int futurePath = 0;
			if(path <= this.map.getNumRows()/2) {
				for(int j = 1; j > -2; j--) {
					if (path + j >= 0 && path + j < this.map.getNumRows()) {
						int test = this.map.getElevation(path + j, i + 1);
						if(selector > Math.abs(current - test) || (j == 0 && selector == Math.abs(current - test))) {
							selector = Math.abs(current - test);
							futurePath = path + j;
							//System.out.println(j + " , " + sum + " , " + selector + " , " + (current- test));
						}
					}
				}
			} else {
				for(int j = -1; j < 2; j++) {
					if (path + j >= 0 && path + j < this.map.getNumRows()) {
						int test = this.map.getElevation(path + j, i + 1);
						if(selector > Math.abs(current - test) || (j == 0 && selector == Math.abs(current - test))) {
							selector = Math.abs(current - test);
							futurePath = path + j;
							//System.out.println(j + " , " + sum + " , " + selector + " , " + (current- test));
						}
					}
				}
			}
			path = futurePath;
			sum += Math.abs(current - this.map.getElevation(path, i + 1));
			g.fillRect(i + 1, path, 1, 1);
		}
		//System.out.println("break");
		return sum;
	} 
	
	// /**
	//  * Helper method for drawGreedyPath to help avoid redundancy
	//  *  @param highestChange the current greatest altitude change of the 3 paths
	//  */
	// public int drawGreedyPathHelper(int highestChange) {
	// 	if (path + j >= 0 && path + j < this.map.getNumRows()) {
	// 		int test = this.map.getElevation(path + j, i + 1);
	// 		if(highestChange > Math.abs(current - test) || (j == 0 && highestChange == Math.abs(current - test))) {
	// 			return Math.abs(current - test);
	// 			futurePath = path + j;
	// 			//System.out.println(j + " , " + sum + " , " + selector + " , " + (current- test));
	// 		}
	// 	}
	// 	return highestChange;
	// }
	
	/** 
	 * Draws all west->east greedy paths in green and highlights the minimal cost path in red. 
	 * @param g the graphics object in which the paths will be drawn 
	 */
	public void drawAllPaths(Graphics g) {
		//assumes smallestChange below 1,000,000,000
		int smallestChange = 1000000000;
		int redRow = 0;
		for(int i = 0; i < this.map.getNumRows(); i++) {
			int testPath = drawGreedyPath(g,i,Color.GREEN);
			//System.out.println(testPath);
			if (testPath < smallestChange) {
				smallestChange = testPath;
				redRow = i;
			}
		}
		drawGreedyPath(g,redRow,Color.RED);
	} 
}