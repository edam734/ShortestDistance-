package com.eduardo.shortestPath.main;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TestRecursion {

	public static void main(String[] args) {
		int[] array = { 45, 5, 8, 3, 4, 1, 6, 9 };
		Recursionaire r = new Recursionaire(array);
		int min = r.findMin(0, Integer. MAX_VALUE);
//		System.out.println(min);
		
		// ou
		
		IntStream stream = Arrays.stream(array);
//		System.out.println(stream.min().getAsInt());
		System.out.println("batatas: ");
		stream.map(e -> {
			System.out.println(e);
			return e;
		}).min();
	}

	private static class Recursionaire {
		
		int[] array;
		
		public Recursionaire(int[] array) {
			this.array = array;
		}
		
		public int findMin(int pos, int minVal) {
			
			if (pos < array.length) {				
				int val = array[pos];
				if (val < minVal) {
					minVal = val;
				}
				return findMin(pos + 1, minVal);
			}
			return minVal;
			
		}
	}
}
