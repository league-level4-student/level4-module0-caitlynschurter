package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int x = randGen.nextInt(width);
		int y = randGen.nextInt(height);
		Cell cell = maze.getCell(x, y);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(cell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		
		//C. if has unvisited neighbors,
		if(unvisitedNeighbors.size() > 0) {
			//C1. select one at random.
			int idx = randGen.nextInt(unvisitedNeighbors.size());
			Cell newCell = unvisitedNeighbors.get(idx);
			//C2. push it to the stack
			uncheckedCells.push(newCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, newCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			currentCell.hasBeenVisited();
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if(unvisitedNeighbors.size() > 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
				Cell poppedCell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = poppedCell;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() > c2.getX() && c1.getY() == c2.getY()) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		
		else if (c1.getX() < c2.getX() && c1.getY() == c2.getY()) {
			c2.setWestWall(false);
			c1.setEastWall(false);
		}
		
		else if(c1.getY() > c2.getY() && c1.getX() == c2.getX()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		
		else if (c1.getY() < c2.getY() && c1.getX() == c2.getX()){
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		/**check to see if neighboring cells are visited**/
		ArrayList<Cell> list = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		
		if(x > 0) { //west
			Cell west = maze.getCell(x-1, y);
			if(!west.hasBeenVisited()) {
				list.add(west);
			}
		}
		
		if(y > 0) { //north
			Cell north = maze.getCell(x, y-1);
			if(!north.hasBeenVisited()) {
				list.add(north);
			}
		}
		
		if(x < width-1) { //east
			Cell east = maze.getCell(x+1, y);
			if(!east.hasBeenVisited()) {
				list.add(east);
			}
		}
		
		if(y < height-1) { //south
			Cell south = maze.getCell(x, y+1);
			if(!south.hasBeenVisited()) {
				list.add(south);
			}
		}

		return list;
	}
}
