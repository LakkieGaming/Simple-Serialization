package com.schiebros.serialization;

public class DataWriter {

	public static final byte[] getBytes(short value) {
		return new byte[] { (byte) (value >> 8 * 1), (byte) (value) };
	}

	public static final byte[] getBytes(char value) {
		return new byte[] { (byte) (value >> 8 * 1), (byte) (value) };
	}

	public static final byte[] getBytes(int value) {
		return new byte[] { (byte) (value >> 8 * 3), (byte) (value >> 8 * 2), (byte) (value >> 8 * 1), (byte) (value) };
	}

	public static final byte[] getBytes(long value) {
		return new byte[] { (byte) (value >> 8 * 7), (byte) (value >> 8 * 6), (byte) (value >> 8 * 5),
				(byte) (value >> 8 * 4), (byte) (value >> 8 * 3), (byte) (value >> 8 * 2), (byte) (value >> 8 * 1),
				(byte) (value) };
	}
	
	// floating point numbers
	
	public static final byte[] getBytes(float value) {
		System.out.println("Float value: " + value);
		int integerValue = Float.floatToIntBits(value);
		System.out.println("Float value in int: " + Float.floatToIntBits(value));
		return getBytes(integerValue);
	}
	
	public static final byte[] getBytes(double value) {
		long longValue = Double.doubleToLongBits(value);
		return getBytes(longValue);
	}
	
}
