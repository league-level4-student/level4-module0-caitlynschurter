//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!

		// store all 'cow's in the varaibles north, south, east, and west
		// 'most recent' variable for each direction
		// ignore imaginary cows; locate wrong way cow (find where there's only 1 cow in
		// a certain direction)
		// return where 'c' is located: [x][y]

		int westCow = 0;
		int eastCow = 0;
		int northCow = 0;
		int southCow = 0;

		int recentWest[] = null;
		int recentEast[] = null;
		int recentNorth[] = null;
		int recentSouth[] = null;

		for (int x = 0; x < field.length - 1; x++) {
			for (int y = 0; y < field.length - 1; y++) {
				if (field[x][y] == 'c') {

					// west
					if (x < field.length - 2) {
						if (field[x + 1][y] == 'o' && field[x + 2][y] == 'w') {
							westCow++;
							recentWest = new int[] { x, y };
						}
					}

					// east
					if (x > 1) {
						if (field[x - 1][y] == 'o' && field[x - 2][y] == 'w') {
							eastCow++;
							recentEast = new int[] { x, y };
						}
					}

					// north
					if (y < field.length - 2) {
						if (field[x][y + 1] == 'o' && field[x][y + 2] == 'w') {
							northCow++;
							recentNorth = new int[] { x, y };
						}
					}

					// south
					if (y < 1) {
						if (field[x][y - 1] == 'o' && field[x][y - 2] == 'w') {
							southCow++;
							recentSouth = new int[] { x, y };
						}
					}
				}
			}
		}

		if (westCow == 1) {
			return recentWest;
		}

		else if (eastCow == 1) {
			return recentEast;
		}

		else if (northCow == 1) {
			return recentNorth;
		}

		else {
			return recentSouth;
		}

	}
}
