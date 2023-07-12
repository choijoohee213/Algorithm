class Solution {
	public static void main(String[] args) throws Exception {
		int[] arr = {3,7,8,1,5,9,6,2,4};
		selectionSort(arr);
		insertionSort(arr);
		bubbleSort(arr);
		quickSort(arr, 0, arr.length-1);
		mergeSort(arr, new int[arr.length], 0, arr.length-1);
	}

	private static void mergeSort(int[] arr, int[] result, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(arr,result, start, mid);
			mergeSort(arr,result,  mid+1, end);

			int left = start, right = mid + 1;
			int idx = left;

			while(left<=mid || right<=end) {
				if(right > end || (left <= mid && arr[left] < arr[right])) {
					result[idx++] = arr[left++];
				} else {
					result[idx++] = arr[right++];
				}
			}

			for (int i = start; i <= end; i++) {
				arr[i] = result[i];
			}
		}

		if(start == 0 && end == arr.length-1) {
			print("병합 정렬", arr);
		}
	}

	private static void quickSort(int[] arr, int pivot, int end) {
		if(pivot >= end) return;

		int left = pivot + 1, right = end;
		while(true) {
			while(left <= end && arr[pivot] >= arr[left]) {
				left++;
			}

			while(right > pivot && arr[pivot] <= arr[right]) {
				right--;
			}

			if (left > right) {
				int tmp = arr[right];
				arr[right] = arr[pivot];
				arr[pivot] = tmp;
				quickSort(arr, pivot, right-1);
				quickSort(arr, right+1, end);
				break;
			} else {
				int tmp = arr[left];
				arr[left] = arr[right];
				arr[right] = tmp;
			}
		}

		if(pivot == 0 && end == arr.length-1) {
			print("퀵 정렬", arr);
		}
	}

	private static void bubbleSort(int[] arr) {
		for (int i = arr.length-1; i >=0; i--) {
			for (int j = 0; j < i; j++) {
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}

		print("버블정렬", arr);
	}

	private static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i-1; j >=0; j--) {
				if(arr[j] > arr[i]) {
					int tmp = arr[j];
					arr[j] = arr[i];
					arr[i] = tmp;
				} else {
					break;
				}
			}
		}

		print("삽입 정렬", arr);
	}

	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			int minIdx = i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[minIdx] > arr[j]) {
					minIdx = j;
				}
			}
			int tmp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = tmp;
		}

		print("선택 정렬", arr);
	}

	private static void print(String sortName, int[] arr) {
		System.out.println(sortName);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println();
	}
}