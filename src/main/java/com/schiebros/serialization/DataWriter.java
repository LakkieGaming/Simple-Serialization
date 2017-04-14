package com.schiebros.serialization;

public class DataWriter {

	public static final byte[] getBytes(boolean value) {
		return new byte[] { (byte) (value ? 1 : 0) };
	}
	
	// integer numbers
	
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
		int integerValue = Float.floatToIntBits(value);
		return getBytes(integerValue);
	}
	
	public static final byte[] getBytes(double value) {
		long longValue = Double.doubleToLongBits(value);
		return getBytes(longValue);
	}
	
	// inline methods
	public static final int writeInlineBytes(int offset, boolean value, byte[] out) {
		out[offset] = (byte) (value ? 1 : 0);
		offset++;
		return offset;
	}
	
	public static final int writeInlineBytes(int offset, char value, byte[] out) {
		out[offset] = (byte) (value >> 8 * 1);
		offset++;
		out[offset] = (byte) (value);
		offset++;
		return offset;
	}

	public static final int writeInlineBytes(int offset, short value, byte[] out) {
		out[offset] = (byte) (value >> 8 * 1);
		offset++;
		out[offset] = (byte) (value);
		offset++;
		return offset;
	}
	
	public static final int writeInlineBytes(int offset, int value, byte[] out) {
		out[offset] = (byte) (value >> 8 * 3);
		offset++;
		out[offset] = (byte) (value >> 8 * 2);
		offset++;
		out[offset] = (byte) (value >> 8 * 1);
		offset++;
		out[offset] = (byte) (value);
		offset++;
		return offset;
	}
	
	public static final int writeInlineBytes(int offset, float value, byte[] out) {
		return writeInlineBytes(offset, Float.floatToIntBits(value), out);
	}
	
	public static final int writeInlineBytes(int offset, long value, byte[] out) {
		out[offset] = (byte) (value >> 8 * 7);
		offset++;
		out[offset] = (byte) (value >> 8 * 6);
		offset++;
		out[offset] = (byte) (value >> 8 * 5);
		offset++;
		out[offset] = (byte) (value >> 8 * 4);
		offset++;
		out[offset] = (byte) (value >> 8 * 3);
		offset++;
		out[offset] = (byte) (value >> 8 * 2);
		offset++;
		out[offset] = (byte) (value >> 8 * 1);
		offset++;
		out[offset] = (byte) (value);
		offset++;
		return offset;
	}
	
	public static final int writeInlineBytes(int offset, double value, byte[] out) {
		return writeInlineBytes(offset, Double.doubleToLongBits(value), out);
	}
	
}
