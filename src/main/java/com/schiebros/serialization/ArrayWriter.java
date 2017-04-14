package com.schiebros.serialization;

public class ArrayWriter extends DataWriter {

	public static final int writeInlineArray(int offset, byte[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, boolean[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, char[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, short[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, int[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, float[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, long[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
	public static final int writeInlineArray(int offset, double[] values, byte[] out) {
		for (int i = 0; i < values.length; i++) {
			offset = writeInlineBytes(offset, values[i], out);
		}
		return offset;
	}
	
}
