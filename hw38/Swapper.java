// Soojin Choi
// APCS1 pd02
// HW38 -- Put It Together
// 2017-11-21

/*Initializes a array and fills the array with words made up of three or four letters. The method threeOrFour randomizes the length of either three or four. Then, randWord creates a random word.The populateArray takes these random words. The printArray method takes in an array and returns a String version. The last method is swap. Using the keyboard class, the user is asked to input the indexes of the two words the user wants. If the values added are invalid, the error method makes the user try again.*/

import cs1.Keyboard;

public class Swapper{
	
	private static final String MASTERLIST = "abcdefghijklmnopqrstuvwxyz";
	
	public static String randWord(){
		int theLength = threeOrFour(); 
		String collector = "";
		int counter = 0;
		while (counter < theLength){
			int x = (int)(Math.random() * 26);
			collector += MASTERLIST.substring(x, x + 1); 
			counter++;
		}
		return collector;
	}
	public static int threeOrFour(){
		if (Math.random() < .5){
			return 3;
		}
		else {
			return 4;
		}
	}
	public static void populateArray(String[][] array){
		for (int y = 0; y < array.length; y++){
			for (int x = 0; x < array[y].length; x++){
				array[y][x] = randWord();
			}
		}
	}
	public static void printArray(String[][] anArray){
		String collector = "";
		for (String[] each : anArray){
			System.out.println(arrayToString(each));
		}
	}
	public static String arrayToString(String[] array){
		String collector =  "";
		for (String each: array){
			collector += each + " ";
		}
		return collector;
	}
	public static void swap(String[][] array, int a, int b, int c, int d){
		String holder = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = holder;
	}
    public static int error(int max){
	int x = Keyboard.readInt(); 
	while (!((x <= max) && (x > 0))){
	       System.out.println("Invalid number... Try Again:");
	       x = Keyboard.readInt();
		}
	  return x;
    }
	public static void main (String[] args){
		String[][] anArray = new String[(int)(Math.random()* 9)+1][(int)(Math.random()* 9)+1];
		populateArray(anArray);
		printArray(anArray);
		int XMax = anArray.length;
		int YMax = anArray[0].length;
		System.out.println("Now to swap two of the words...(Note: Each row and column starts with 1 and increments up.)");
		System.out.println("First type in the row number the first word is in:");
		int firstX = error(XMax);
		System.out.println("Then the column number:");
		int firstY = error(YMax);
		System.out.println("Type in the row number the second word is in:");
		int replaceX = error(XMax);
		System.out.println("Then the column number:");
		int replaceY = error(YMax);
		swap(anArray, firstX - 1, firstY - 1 , replaceX - 1, replaceY - 1);
		printArray(anArray);
	}
}
