package com.schiebros.serialization.test;

import com.schiebros.serialization.DataReader;
import com.schiebros.serialization.DataWriter;

public class TestMain {

	public static void printBytes(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.format("0x%x ", bytes[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("Converting 87 to bytes . . .");

		byte[] b0 = DataWriter.getBytes(87);
		byte[] b1 = DataWriter.getBytes((long) (87));
		byte[] b2 = DataWriter.getBytes((short) (87));
		byte[] b3 = DataWriter.getBytes((float) 87.3);

		System.out.println("Integer");
		printBytes(b0);
		
		System.out.println("Long");
		printBytes(b1);
		
		System.out.println("Short");
		printBytes(b2);
		
		System.out.println("Float");
		printBytes(b3);
		
		System.out.println("\nRead Int");
		System.out.println(DataReader.readInt(b0));
		
		System.out.println("Read Float");
		System.out.println(DataReader.readFloat(b3));

	}

}
