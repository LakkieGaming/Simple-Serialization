package com.schiebros.serialization.test;

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

		System.out.println("Integer");
		printBytes(b0);
		
		System.out.println("Long");
		printBytes(b1);
		
		System.out.println("Short");
		printBytes(b2);

	}

}
