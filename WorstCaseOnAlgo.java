package neeraja.algo;

import java.util.ArrayList;

public class WorstCaseOnAlgo {
	private int COMPCOUNT;
	private int pivot;
	private ArrayList<Integer> lesserElementsArr = new ArrayList<Integer>();
	private ArrayList<Integer> equalElementsArr = new ArrayList<Integer>();
	private ArrayList<Integer> greaterElementsArr = new ArrayList<Integer>();
	int trackCount = 0;
	int[] mainArray;
	int medianElement;
	
	public int[] findMedianElement(int[] inputArr) {
		
		int[] sortedArray = new int[inputArr.length];
		int  k =  inputArr.length / 2;
		this.mainArray = inputArr;
		
		// If n < 25, sort the array using insertion sort, and return the kth element. Otherwise, continue.
		if (inputArr.length < 25) {
			sortedArray = sortArrayByInsertionSort(inputArr, inputArr.length);
			System.out.println(SortArray.displaySortedArr(inputArr));
			System.out.println("Algorithm SELECT3: n = " + inputArr.length + ", k = " + k +  ", A[" + k + "] = " + sortedArray[k - 1] + ", Number of Key-Comparisons = " + COMPCOUNT);
		} else {
			select3Algo(inputArr, k);
			System.out.println("Algorithm SELECT3: n = " + inputArr.length + ", k = " + k +  ", A[" + k + "] = " + medianElement + ", Number of Key-Comparisons = " + COMPCOUNT);
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

	private void select3Algo(int[] inputArr, int k) {
		
		ArrayList<ArrayList<Integer>> subsetRowsArr = partitionArray(inputArr);
		int[] medianArr = getMedianArray(subsetRowsArr);
		
		if (medianArr.length == 1) {
			pivot = medianArr[0];
			
			inputArr = mainArray;
			partitionArray(inputArr, pivot);
			
			int n1 = lesserElementsArr.size();
			int n2 = equalElementsArr.size();
			
			if (k <= n1) {
				this.mainArray = convertArrayList(lesserElementsArr);
				select3Algo(mainArray, k);
			} else if (k <= (n1 + n2)) {
				medianElement = pivot;
			} else {
				this.mainArray = convertArrayList(greaterElementsArr);
				select3Algo(mainArray, k - (n1 + n2));
			}
		} else {
			select3Algo(medianArr, k);
		}
	}
	
	private ArrayList<ArrayList<Integer>> partitionArray(int[] inputArr) {
		ArrayList<ArrayList<Integer>> subsetRowsArr = new ArrayList<>();
		ArrayList<Integer> subset = new ArrayList<Integer>();
		int k = 0;
		
		for (int  i = 0; i < inputArr.length; i++) {
			
			if (k == 5) {
				subsetRowsArr.add(subset);
				
				subset = new ArrayList<Integer>();
				k = 0;
			}
			subset.add(inputArr[i]);
			k++;
		}
		subsetRowsArr.add(subset);
		return subsetRowsArr;
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

	private int[] getMedianArray(ArrayList<ArrayList<Integer>> subsetRowsArr) {
		int[] medianArr = new int[subsetRowsArr.size()];
		int k = 0;
		
		for (ArrayList<Integer> intArr : subsetRowsArr) {
			int[] sortedArr = sortArrayByInsertionSort(convertArrayList(intArr), intArr.size());
			int medianElement = 0;
			//if (sortedArr.length > 1) {
				double arrLen = sortedArr.length;
				medianElement = sortedArr[(int) Math.ceil(arrLen/2) - 1];
			//} else {
			//	medianElement = sortedArr[0];
			//}
			
			medianArr[k] = medianElement;
			k++;
		}
		
		return medianArr;
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
	
	private int[] convertArrayList(ArrayList<Integer> list) {
		int[] intArr = new int[list.size()];
		int  i = 0;
		
		for (Integer element : list) {
			intArr[i] = element;
			i++;
		}
		return intArr;
	}
}
