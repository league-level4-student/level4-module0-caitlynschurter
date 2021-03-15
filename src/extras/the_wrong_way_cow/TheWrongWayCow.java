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
		// ignore imaginary cows; locate wrong way cow (find where there's only 1 cow in a certain direction)
		// return where 'c' is located: [x][y]

		int westCow = 0;
		int eastCow = 0;
		int northCow = 0;
		int southCow = 0;
		
		//change i and j to x and y
		for (int i = 0; i < field.length - 1; i++) {
			for (int j = 0; j < field.length - 1; j++) {
				if (field[i][j] == 'c') {
					// west
					if (i < field.length - 2) {
						if (field[i + 1][j] == 'o') {
							if(field[i+2][j] == 'w') {
								westCow++;
							} 
						}
					}
					
					//east
					if(i > 1) {
						if(field[i-1][j] == 'o') {
							if(field[i-2][j] == 'w') {
								eastCow++;
							}
						}
					}
					//north
					if(j < field.length -2) {
						if(field[i][j+1] == 'o') {
							if(field[i][j+2] == 'w') {
								northCow++;
							}
						}
					}
					//south
					if(j < 1) {
						if(field[i][j-1] == 'o') {
							if(field[i][j-2] == 'w') {
							southCow++;	
							}
						}
					}
				}
			}
		}
		
		

		return null;
	}
}
