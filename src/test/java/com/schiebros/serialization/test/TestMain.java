package com.schiebros.serialization.test;

import com.schiebros.serialization.ArrayReader;
import com.schiebros.serialization.ArrayWriter;

public class TestMain {

	public static void printBytes(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.format("0x%x ", bytes[i]);
		}
		System.out.println("");
	}

	public static void printInts(int[] ints) {
		System.out.print("{ ");
		for (int i = 0; i < ints.length; i++) {
			if (i == 0) {
				System.out.print(ints[i]);
			} else {
				System.out.print(", " + ints[i]);
			}
		}
		System.out.println(" }");
	}

	public static void main(String[] args) {

		byte[] bufferData = new byte[100];
		int[] data = new int[] { 5, 4, 734 };
		System.out.println("Writing bytes: ");
		printInts(data);
		ArrayWriter.writeInlineArray(0, data, bufferData);
		
		System.out.println("Writing deserialized data");
		int[] buffer = new int[3];
		printInts(ArrayReader.readInlineArray(0, buffer, bufferData));

	}

}
