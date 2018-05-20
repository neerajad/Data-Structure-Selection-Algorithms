package neeraja.algo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedAlgo {
	
	private int COMPCOUNT;
	private ArrayList<Integer> lesserElementsArr = new ArrayList<Integer>();
	private ArrayList<Integer> equalElementsArr = new ArrayList<Integer>();
	private ArrayList<Integer> greaterElementsArr = new ArrayList<Integer>();
	int medianElement;
	
	public int[] findMedianElement(int[] inputArr) {
		
		int[] sortedArray = new int[inputArr.length];
		int  k =  inputArr.length / 2;
		
		// If n < 25, sort the array using insertion sort, and return the kth element. Otherwise, continue.
		if (inputArr.length < 25) {
			sortedArray = sortArrayByInsertionSort(inputArr, inputArr.length);
			System.out.println("Algorithm SELECT2: n = " + inputArr.length + ", k = " + k +  ", A[" + k + "] = " + sortedArray[k - 1] + ", Number of Key-Comparisons = " + COMPCOUNT);
		} else {
			randomizedSelectAlgo(inputArr, k);
			System.out.println("Algorithm SELECT2: n = " + inputArr.length + ", k = " + k +  ", A[" + k + "] = " + medianElement + ", Number of Key-Comparisons = " + COMPCOUNT);
		}
		
		return inputArr;
	}
	
	public int[] sortArrayByInsertionSort(int[] inputArr, int size) {
		
		if (size == 1) {
			return inputArr;
		}
		inputArr = sortArrayByInsertionSort(inputArr, size - 1);
		
		int  j = size - 1;
		
		while (j > 0) {
			
			if ("less".equals(compareElements(inputArr[j], inputArr[j - 1]))) {
				swapElemets(inputArr, j, j - 1);
			}
			j--;
		}
		return inputArr;
	}

	public int randomizedSelectAlgo(int[] inputArr, int k) {
		
		if (inputArr.length <= 1) {
			medianElement = inputArr[0];
			return medianElement;
		}
		// Pick a random pivot
		int pivotIndex = ThreadLocalRandom.current().nextInt(0, inputArr.length - 1);
		int pivotElement = inputArr[pivotIndex];
		partitionArray(inputArr, pivotElement);
		
		int n1 = lesserElementsArr.size();
		int n2 = equalElementsArr.size();
		
		if (k <= n1) {
			//Integer[] integerArr = lesserElementsArr.toArray(new Integer[lesserElementsArr.size()]);
			//int[] intArray = Arrays.stream(integerArr).mapToInt(Integer::intValue).toArray();
			randomizedSelectAlgo(convertArrayList(lesserElementsArr), k);
		} else if (k <= (n1 + n2)) {
			medianElement = pivotElement;
		} else {
			//Integer[] integerArr = greaterElementsArr.toArray(new Integer[greaterElementsArr.size()]);
			//int[] intArray = Arrays.stream(integerArr).mapToInt(Integer::intValue).toArray();
			randomizedSelectAlgo(convertArrayList(greaterElementsArr), k - (n1 + n2));
		}
		return medianElement;
	}
	
	private int[] convertArrayList(ArrayList<Integer> list) {
		int[] intArr = new int[list.size()];
		int  i = 0;
		
		for (Integer element : list) {
			intArr[i] = element;
			i++;
		}
		return intArr;
	}
	
	private void partitionArray(int[] inputArr, int pivotElement) {
		
		lesserElementsArr.clear();
		greaterElementsArr.clear();
		equalElementsArr.clear();
		
		for (int  i = 0; i < inputArr.length; i++) {
			String equality = compareElements(inputArr[i], pivotElement);
			
			if ("less".equals(equality)) {
				this.lesserElementsArr.add(inputArr[i]);
			} else if ("greater".equals(equality)) {
				this.greaterElementsArr.add(inputArr[i]);
			} else {
				this.equalElementsArr.add(inputArr[i]);
			}
		}
	}
	
	public String compareElements(int firstElement, int secondElement) {
		COMPCOUNT++;
		if(firstElement < secondElement) {
			return "less";
		} else if (firstElement > secondElement) {
			return "greater";
		}
		return "equal";
	}
	
	/**
	 * This element swaps the given elements in the provided array
	 * @param array
	 * @param firstElementIndex
	 * @param secElementIndex
	 * @return
	 */
	private int[] swapElemets(int[] array, int firstElementIndex, int secElementIndex) {
		int temp = 0;
		
		temp = array[firstElementIndex];
		array[firstElementIndex] = array[secElementIndex];
		array[secElementIndex] = temp;
		
		return array;
	}
}
