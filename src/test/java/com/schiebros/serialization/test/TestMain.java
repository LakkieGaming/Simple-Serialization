package com.schiebros.serialization.test;

import com.schiebros.serialization.object.SSArray;
import com.schiebros.serialization.object.SSField;
import com.schiebros.serialization.object.SSObject;

public class TestMain {

	public static void main(String[] args) {

		SSObject template = new SSObject("testobj".toCharArray());
		SSField templateField = SSField.asInt("testfield", 9427);
		System.out.println("Field size: " + templateField.size);
		SSArray templateArray = SSArray.asIntArray("testarray", new int[] { 39, 2945, 2892, 392 });
		System.out.println("Array size: " + templateArray.size);
		template.addArray(templateArray);
		template.addField(templateField);
		System.out.println("Serializing object \"testobj\"");
		byte[] rawData = template.runExport();
		System.out.println("Serialized object");
		
		SSObject object = new SSObject();
		System.out.println("Deserializing object...");
		object.runImport(rawData);
		System.out.println("Deserialized object - Printing\n");
		Printer.printObject(object);
		System.out.println();
		System.out.println("testfield value: " + object.getField("testfield").getInt());
		System.out.println("testarray value 2: " + object.getArray("testarray").getInt()[2]);
		
	}

}
