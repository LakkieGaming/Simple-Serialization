package com.schiebros.serialization.test;

import com.schiebros.serialization.DataReader;
import com.schiebros.serialization.DataWriter;
import com.schiebros.serialization.object.SSField;

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

		int intField = 823;
		System.out.println("Creating field \"testfield\" with value of " + intField);
		SSField field = new SSField("testfield".toCharArray(), DataWriter.getBytes(intField));
		System.out.println("Exporting field data");
		byte[] fieldData = field.runExport();
		
		System.out.println("Creating new field");
		SSField field2 = new SSField();
		System.out.println("Reading field from data");
		field2.runImport(fieldData);
		System.out.println("Field copy data: ");
		printBytes(field2.data);
		System.out.println("Field Length: " + field2.nameLength);
		System.out.println("Field Name: " + new String(field2.name));
		System.out.println("Field Data: " + DataReader.readInt(field2.data));
		System.out.println("Field Size: " + field2.size + " bytes");
		
	}

}
