package com.horizonhobby.ecom;

public class App {
	
	public static void main(String[] args) {
		boolean success = isPalindromeUsingForLoop("malayalam");
		System.out.println( "Hello World!"+success ); 
		/*
		System.out.println(
		 * "Reverse number is :-"+reverseNumberForLoop(12345));
		 */
		reverseNumberRecursive(1234);
	}

	public static boolean isPalindromeUsingForLoop(String somePallendromeString) {
		boolean palenDrome = false;
		char[] wArray = somePallendromeString.toCharArray();

		if (wArray.length % 2 == 0) {
			for (int i = 0; i < wArray.length / 2 - 1; i++) {
				if (wArray[i] != wArray[wArray.length - i - 1]) {
					palenDrome = false;
					break;
				} else {
					palenDrome = true;
				}
			}
		} else {
			for (int i = 0; i < (wArray.length - 1) / 2 - 1; i++) {
				if (wArray[i] != wArray[wArray.length - i - 1]) {
					palenDrome = false;
					break;
				} else {
					palenDrome = true;
				}
			}
		}
		return palenDrome;
	}

	public static boolean isPalindromeRecursive(String s) {
		int length = s.length();
		if (length < 2)
			return true;
		else
			return s.charAt(0) != s.charAt(length - 1) ? false : isPalindromeRecursive(s.substring(1, length - 1));
	}

	public static boolean istPalindromWhileLoop(String somePallendromeString) {
		char[] word = somePallendromeString.toCharArray();
		int i1 = 0;
		int i2 = word.length - 1;
		while (i2 > i1) {
			if (word[i1] != word[i2]) {
				return false;
			}
			++i1;
			--i2;
		}
		return true;
	}

	public static boolean istPalindromStringBuffer(String somePallendromeString) {
		if (somePallendromeString.equals(new StringBuffer(somePallendromeString).reverse().toString())) {
			return true;
		} else {
			return false;
		}

	}

	public static int reverseNumberForLoop(int someNumberToReverse) {

		// Captured input would be stored in number num
		int num = someNumberToReverse;
		System.out.println("Input number is: " + someNumberToReverse);
		int reversenum = 0;

		/*
		 * for loop: No initialization part as num is already initialized and no
		 * increment/decrement part as logic num = num/10 already decrements the value
		 * of num
		 */
		for (; num != 0;) {
			reversenum = reversenum * 10;
			reversenum = reversenum + num % 10;
			num = num / 10;
		}
		return reversenum;
	}

	public static int reverseNumberWhileLoop(int someNumberToReverse) {
		// Captured input would be stored in number num
		int num = someNumberToReverse;
		int reversenum = 0;
		System.out.println("Input number is: " + someNumberToReverse);

		// While Loop: Logic to find out the reverse number
		while (num != 0) {
			reversenum = reversenum * 10;
			reversenum = reversenum + num % 10;
			num = num / 10;
		}

		System.out.println("Reverse of input number is: " + reversenum);
		return reversenum;
	}

	public static void reverseNumberRecursive(int someNumberToReverse) {
		if (someNumberToReverse < 10) {
			System.out.println(someNumberToReverse);
			return;
		} else {
			System.out.print(someNumberToReverse % 10);
			// Method is calling itself: recursion
			reverseNumberRecursive(someNumberToReverse / 10);
		}
	}

}
