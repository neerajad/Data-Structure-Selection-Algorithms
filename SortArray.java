package neeraja.algo;

import java.util.Random;
import java.util.Scanner;

public class SortArray {

	/**
	 * This method creates a random array
	 * @return randomly generated array
	 */
	public static int[] generateRandomArr(int size) {
		
		int[] randomArr = new int[size];
		Random random = new Random();
		int index = 0;
		
		for (int i = 0; i < size; i++) {
			randomArr[index] = random.nextInt(size);
			index++;
		}
		return randomArr;
	}
	
	/**
	 * Display the sorted array
	 * @param sortedArr
	 */
	public static StringBuffer displaySortedArr(int[] sortedArr) {
		
		StringBuffer sb = new StringBuffer("int[] arr = {");
		
		for (int i = 0 ; i< sortedArr.length; i++) {
			sb.append(sortedArr[i] + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("};");
		
		return sb;
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the array size : ");
		int arraySize = scanner.nextInt();
		
		//Generate a random array of size 10000 to sort. Change 10 000 to 100,000, 32768 or 1000,000 to generate random array of those size
		int[] arr = generateRandomArr(arraySize);
		
		// Sort array by quick sort
		QuickSortAlgo quickSortAlgo = new QuickSortAlgo();
		quickSortAlgo.quickSort(arr);
		
		RandomizedAlgo randomizedAlgo = new RandomizedAlgo();
		randomizedAlgo.findMedianElement(arr);
		
		WorstCaseOnAlgo worstCaseOnAlgo = new WorstCaseOnAlgo();
		worstCaseOnAlgo.findMedianElement(arr);
		
		scanner.close();
		
	}
	
}
