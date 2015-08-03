package com.stakoun.mergesort;

import java.util.Scanner;

/**
 * @author Peter Stakoun
 * A practical implementation of the merge sort algorithm.
 */
public class MergeSort
{
	/**
	 * The sole MergeSort constructor.
	 */
	public MergeSort()
	{
		
	}

	/**
	 * The main method takes user input, creates a new MergeSort object, and begins the sort.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Create MergeSort object
		MergeSort mergesort = new MergeSort();
		
		// Prompt user for input
		System.out.println("Enter numeric array. Seperate elements using single spaces.");
		
		// Initialize array
		double[] array = null;
		
		// Get user input until valid
		while (array == null) {
			array = getUserInput();
		}
		
		// Sort array
		double[] sorted = mergesort.sort(array);
		
		// Print sorted array
		System.out.println("Sorted array: " + arrayToString(sorted));
	}
	

	/**
	 * The sort method recursively splits the given array into left and right sides.
	 * @param array User-defined array to sort.
	 * @return Sorted array.
	 */
	private double[] sort(double[] array)
	{
		// Get length of array
		int l = array.length;
		
		// Base case: return array if length is 1
		if (l == 1) {
			return array;
		}
		
		// Declare left side array
		double[] LS;
		// Add 1 to left side array if length of array is odd
		if (l % 2 == 1) {
			LS = new double[l/2+1];
		} else {
			LS = new double[l/2];
		}
		
		// Initialize right side
		double[] RS = new double[l/2];
		
		// Add elements to left side
		for (int i = 0; i < LS.length; i++) {
			LS[i] = array[i];
		}
		
		// Add elements to right side
		for (int i = LS.length; i < l; i++) {
			RS[i-LS.length] = array[i];
		}
		
		// Sort left and right sides and merge the results
		return merge(sort(LS), sort(RS));
	}

	/**
	 * The merge method merges the given left and right sides.
	 * @param LS
	 * @param RS
	 * @return Result of merging given arrays.
	 */
	private double[] merge(double[] LS, double[] RS)
	{
		// Initialize array
		double[] array = new double[LS.length + RS.length];
		
		// Set index for traversing left and right sides
		int lIndex = 0;
		int rIndex = 0;
		
		// Traverse left and right sides, adding the smaller element to array
		while (lIndex < LS.length && rIndex < RS.length) {
			// Add smaller element to array and increment corresponding index
			if (LS[lIndex] <= RS[rIndex]) {
				array[lIndex + rIndex] = LS[lIndex];
				lIndex++;
			} else {
				array[lIndex + rIndex] = RS[rIndex];
				rIndex++;
			}
		}
		
		// Add remaining elements in left side to array
		while (lIndex < LS.length) {
			array[lIndex + rIndex] = LS[lIndex];
			lIndex++;
		}
		
		// Add remaining elements in right side to array
		while (rIndex < RS.length) {
			array[lIndex + rIndex] = RS[rIndex];
			rIndex++;
		}
		
		// Return resulting array
		return array;
	}

	/**
	 * The getUserInput method scans for user input and return result.
	 * @return Array from user input or null if input invalid.
	 */
	private static double[] getUserInput()
	{
		// Create scanner to create array from user input
		Scanner scanner = new Scanner(System.in);
		
		// Get user input
		String line = scanner.nextLine();
		
		// Close scanner
		scanner.close();
		
		try
		{
			// Create argument array
			String[] args = line.split("\\s+");
			
			// Get number of arguments
			int l = args.length;
			
			// Throw exception if argument array empty
			if (l == 0) { throw new Exception(); }
			
			// Initialize array
			double[] array = new double[l];
			
			// Parse and add arguments to array
			for (int i = 0; i < l; i++) {
				array[i] = Double.parseDouble(args[i]);
			}
			
			// Return resulting array
			return array;
		}
		catch (Exception e)
		{
			// Throw error message if input invalid
			System.out.println("Invalid input!");
			return null;
		}
	}

	/**
	 * The arrayToString method takes in an numeric array and returns a string.
	 * @param array Numeric array to represent as a string.
	 * @return String representation of given array.
	 */
	private static String arrayToString(double[] array)
	{
		// Initialize string
		String str = "";
		
		// Add each element to string
		for (int i = 0; i < array.length; i++) {
			str += format(array[i]);
			if (i != array.length-1) {
				str += ", ";
			}
		}
		
		// Return resulting string representation of array
		return str;
	}
	
	/**
	 * The format method takes in a double and returns a string.
	 * @param double Number to format.
	 * @return String formatted string value of number.
	 */
	private static String format(double n)
	{
		// Remove decimal point if divisible by 1
		if (n % 1 == 0) {
			// Allow numbers out of integer upper and lower bounds
			if (n <= Integer.MAX_VALUE && n >= Integer.MIN_VALUE) {
				return String.valueOf((int) n);
			} else {
				return String.valueOf((long) n);
			}
		} else {
			return String.valueOf(n);
		}
	}

}
