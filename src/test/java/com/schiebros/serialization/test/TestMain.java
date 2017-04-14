package com.schiebros.serialization.test;

import com.schiebros.serialization.ArrayWriter;
import com.schiebros.serialization.object.SSArray;
import com.schiebros.serialization.object.SSType;

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
	
	public static void printFloats(float[] floats) {
		System.out.print("{ ");
		for (int i = 0; i < floats.length; i++) {
			if (i == 0) {
				System.out.print(floats[i]);
			} else {
				System.out.print(", " + floats[i]);
			}
		}
		System.out.println(" }");
	}
	
	public static void printBytesAsArray(byte[] bytes) {
		System.out.print("{ ");
		for (int i = 0; i < bytes.length; i++) {
			if (i == 0) {
				System.out.print(bytes[i]);
			} else {
				System.out.print(", " + bytes[i]);
			}
		}
		System.out.println(" }");
	}

	public static void main(String[] args) {

		float[] rawData = new float[] { 43.42f, 31f, 82238.238278F };
		String name = "testarray";
		System.out.println("Creating array with name: " + name);
		byte[] buffer = new byte[rawData.length * 4];
		ArrayWriter.writeInlineArray(0, rawData, buffer);
		SSArray rawArray = new SSArray(name.toCharArray(), buffer, SSType.FLOAT);
		System.out.println("Created array " + name);
		System.out.println("Serializing array");
		byte[] arrayData = rawArray.runExport();
		System.out.println("Serialized array");
		
		System.out.println("Deserializing array");
		SSArray array = new SSArray();
		array.runImport(arrayData);
		System.out.println("Array Name Length: " + array.nameLength);
		System.out.println("Array Name: " + new String(array.name));
		System.out.println("Array Length: " + array.getArrayLength());
		System.out.println("Array Data:");
		printFloats(array.getFloat());
		
	}

}
