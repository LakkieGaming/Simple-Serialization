package net.battlemania.serialization.test;

import net.battlemania.serialization.object.SSArray;
import net.battlemania.serialization.object.SSField;
import net.battlemania.serialization.object.SSHolder;
import net.battlemania.serialization.object.SSObject;

public class TestMain {

	public static void main(String[] args) {

		// serializing
		System.out.println("Building holder");
		SSHolder templateHolder = new SSHolder("testholder".toCharArray());
		SSObject templateObject = new SSObject("testobj".toCharArray());
		SSField templateField = SSField.asInt("testfield", 9427);
		SSArray templateArray = SSArray.asIntArray("testarray", new int[] { 39, 2945, 2892, 392 });
		templateObject.addArray(templateArray);
		templateObject.addField(templateField);
		templateHolder.addObject(templateObject);
		
		SSField templateField2 = SSField.asDouble("field2", 3892.34D);
		templateHolder.addField(templateField2);
		SSArray templateArray2 = SSArray.asFloatArray("array2", new float[] { 59.3F, 9F, 39284.43F });
		templateHolder.addArray(templateArray2);
		
		System.out.println("Serializing holder");
		byte[] serializedHolder = templateHolder.runExport();
		System.out.println("Serialized holder");
		System.out.println("Holder Size: " + templateHolder.size);
		System.out.println("Serialized Holder Size: " + serializedHolder.length);
		
		SSHolder input = new SSHolder();
		input.runImport(serializedHolder);
		System.out.println("Printing deserialized holder");
		Printer.printHolder(input);
		
	}

}
