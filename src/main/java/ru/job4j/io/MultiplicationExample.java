package ru.job4j.io;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class MultiplicationExample {

	private List<Integer> digits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	
	public String printMultiplicationTable() {
		StringJoiner joiner = new StringJoiner(System.lineSeparator());
		for (Integer i : digits) {
			StringJoiner nextString = new StringJoiner(" ");
			for (Integer j : digits) {
				if (i * j <= 9) {
					nextString.add(i * j + " ");
				} else {
					nextString.add(String.valueOf(i * j));
				}
			}
			joiner.add(nextString.toString());
		}
		return joiner.toString();
	}
	
}
