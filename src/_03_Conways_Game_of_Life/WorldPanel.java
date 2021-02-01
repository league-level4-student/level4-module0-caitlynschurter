package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cell;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = w / cellsPerRow;

		// 3. Initialize the cell array to the appropriate size.
		cell = new Cell[cellsPerRow][cellsPerRow];

		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int x = 0; x < cellsPerRow; x++) {
			for (int y = 0; y < cellsPerRow; y++) {
				cell[x][y] = new Cell(x, y, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		Random r = new Random();
		for (int x = 0; x < cellsPerRow; x++) {
			for (int y = 0; y < cellsPerRow; y++) {
				cell[x][y].isAlive = r.nextBoolean();
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		for (int x = 0; x < cellsPerRow; x++) {
			for (int y = 0; y < cellsPerRow; y++) {
				cell[x][y].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int x = 0; x < cellsPerRow; x++) {
			for (int y = 0; y < cellsPerRow; y++) {
				cell[x][y].draw(g);
			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int x = 0; x < cellsPerRow; x++) {
			for (int y = 0; y < cellsPerRow; y++) {
				livingNeighbors[x][y] = getLivingNeighbors(x, y);
			}
		}
		// 8. check if each cell should live or die
		for (int x = 0; x < cellsPerRow; x++) {
			for (int y = 0; y < cellsPerRow; y++) {
				cell[x][y].liveOrDie(livingNeighbors[x][y]);
			}
		}

		repaint();
	}

	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y
	public int getLivingNeighbors(int glnX, int glnY) {
		int neighbors = 0;
		for (int x = glnX - 1; x <= glnX + 1; x++) {
			for (int y = glnY - 1; y <= glnY + 1; y++) {
				if (x >= 0 && y >= 0 && x < cellsPerRow && y < cellsPerRow) {
					if (x == glnX && y == glnY) {
						continue;
					}
					if (cell[x][y].isAlive == true) {
						neighbors++;
					}
				}
			}
		}
		// System.out.println(glnX + ", " + glnY + ": " + neighbors);
		return neighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		int x = e.getX() / cellSize;
		int y = e.getY() / cellSize;

		cell[x][y].isAlive = !cell[x][y].isAlive;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
