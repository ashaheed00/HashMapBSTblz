package com.bl.dsa;

import java.util.ArrayList;
import java.util.stream.Stream;

public class MyLinkedHashMap<K, V> {
	private final int ARRAY_LENGTH;
	ArrayList<MyLinkedList<K>> hashArray;

	public MyLinkedHashMap() {
		ARRAY_LENGTH = 10;
		hashArray = new ArrayList<>(ARRAY_LENGTH);
		initializeHashArray();
	}

	public void initializeHashArray() {
		Stream.iterate(0, i -> i + 1).limit(ARRAY_LENGTH).forEach(i -> hashArray.add(null));
	}

	private int getArrayIndex(K key) {
		int hashCode = Math.abs(key.hashCode());
		int arrayIndex = hashCode % ARRAY_LENGTH;
		return arrayIndex;
	}

	public V get(K key) {
		int arrayIndex = this.getArrayIndex(key);
		MyLinkedList<K> myLinkedList = this.hashArray.get(arrayIndex);
		if (myLinkedList == null)
			return null;
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
		return (myMapNode == null) ? null : myMapNode.getValue();
	}

	public void add(K key, V value) {
		int arrayIndex = this.getArrayIndex(key);
		MyLinkedList<K> myLinkedList = this.hashArray.get(arrayIndex);
		if (myLinkedList == null) {
			myLinkedList = new MyLinkedList<>();
			this.hashArray.set(arrayIndex, myLinkedList);
		}
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
		if (myMapNode == null) {
			myMapNode = new MyMapNode<>(key, value);
			myLinkedList.append(myMapNode);
		} else {
			myMapNode.setValue(value);
		}
	}

	/* Ability to delete a specific key */
	public void deleteKey(K key) {
		int arrayIndex = this.getArrayIndex(key);
		MyLinkedList<K> myLinkedList = this.hashArray.get(arrayIndex);
		if (myLinkedList == null) {
			System.out.println("Word not present");
		} else {
			MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
			if (myMapNode == null)
				System.out.println("Word is not present");
			else {
				myLinkedList.delete(key);
				System.out.println("Word deleted successfully");
			}
		}
	}

	@Override
	public String toString() {
		return "MyLinkedHashMap => " + hashArray;
	}

}
