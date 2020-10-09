package com.bl.dsa;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyHashMapTest {

	@Test
	public void givenASentence_WhenWordsAreAddedToList_ShouldReturnWordFrequency() {
		MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
		String sentence = "To be or not to be";
		String[] words = sentence.toLowerCase().split(" ");
		for (String word : words) {
			System.out.println(word);
			Integer value = myHashMap.get(word);
			if (value == null)
				value = 1;
			else
				value = value + 1;
			myHashMap.add(word, value);
		}

		int frequency = myHashMap.get("to");
		assertEquals(2, frequency);
		System.out.println(myHashMap.toString());

	}

}