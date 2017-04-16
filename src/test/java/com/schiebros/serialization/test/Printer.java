package com.schiebros.serialization.test;

import com.schiebros.serialization.object.SSArray;
import com.schiebros.serialization.object.SSField;
import com.schiebros.serialization.object.SSObject;

public class Printer {
	
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
	
	public static String intsTostring(int[] ints) {
		String result = "{ ";
		for (int i = 0; i < ints.length; i++) {
			if (i == 0) {
				result += ints[i];
			} else {
				result += ", " + ints[i];
			}
		}
		result += " }";
		return result;
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

	public static void printObject(SSObject object) {
		System.out.println("---------------------");
		System.out.println(new String(object.name));
		System.out.println("---------------------");
		System.out.println("Object Type: " + object.type);
		System.out.println("Object Name: " + new String(object.name));
		System.out.println("Object Field Count: " + object.fieldCount);
		for (SSField field : object.getFields()) {
			System.out.println();
			System.out.println("\tField Type: " + field.type);
			System.out.println("\tField Name: " + new String(field.name));
			System.out.println("\tField Value: " + field.getInt());
		}
		System.out.println();
		System.out.println("Object Array Count: " + object.arrayCount);
		for (SSArray array : object.getArrays()) {
			System.out.println();
			System.out.println("\tArray Type: " + array.type);
			System.out.println("\tArray Name: " + new String(array.name));
			System.out.println("\tArray Value: " + intsTostring(array.getInt()));
		}
		System.out.println();
		System.out.println("Printed object " + new String(object.name));
		System.out.println("---------------------");
		System.out.println("End");
		System.out.println("---------------------");
	}

}
