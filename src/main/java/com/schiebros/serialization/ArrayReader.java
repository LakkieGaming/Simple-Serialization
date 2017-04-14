package com.schiebros.serialization;

public class ArrayReader extends DataReader {

	public static final char[] readInlineArray(int offset, char[] buffer, byte[] data) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readInlineChar(data, offset);
			offset += 4;
		}
		return buffer;
	}
	
	public static final short[] readInlineArray(int offset, short[] buffer, byte[] data) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readInlineShort(data, offset);
			offset += 4;
		}
		return buffer;
	}
	
	public static final int[] readInlineArray(int offset, int[] buffer, byte[] data) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readInlineInt(data, offset);
			offset += 4;
		}
		return buffer;
	}
	
	public static final float[] readInlineArray(int offset, float[] buffer, byte[] data) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readInlineFloat(data, offset);
			offset += 4;
		}
		return buffer;
	}
	
	public static final long[] readInlineArray(int offset, long[] buffer, byte[] data) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readInlineLong(data, offset);
			offset += 4;
		}
		return buffer;
	}

	public static final double[] readInlineArray(int offset, double[] buffer, byte[] data) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = readInlineDouble(data, offset);
			offset += 4;
		}
		return buffer;
	}
	
}
