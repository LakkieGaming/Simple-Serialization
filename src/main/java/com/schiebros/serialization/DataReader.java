package com.schiebros.serialization;

import java.nio.ByteBuffer;

public class DataReader {

	public static final boolean readBoolean(byte[] values) {
		return values[0] != 0;
	}
	
	// whole numbers
	
	public static final char readChar(byte[] values) {
		return ByteBuffer.wrap(values, 0, 2).getChar();
	}
	
	public static final short readShort(byte[] values) {
		return ByteBuffer.wrap(values, 0, 2).get();
	}

	public static final int readInt(byte[] values) {
		return ByteBuffer.wrap(values, 0, 4).getInt();
	}
	
	public static final long readLong(byte[] values) {
		return ByteBuffer.wrap(values, 0, 8).getLong();
	}
	
	// floating points numbers
	
	public static final float readFloat(byte[] values) {
		return Float.intBitsToFloat(readInt(values));
	}
	
	public static final double readDouble(byte[] values) {
		return Double.longBitsToDouble(readLong(values));
	}
	
	// reading inline
	public static final byte readInlineByte(byte[] values, int offset) {
		return ByteBuffer.wrap(values, offset, 1).get();
	}
	
	public static final boolean readInlineBoolean(byte[] values, int offset) {
		return values[offset] != 0 ? true : false;
	}
	
	public static final char readInlineChar(byte[] values, int offset) {
		return ByteBuffer.wrap(values, offset, 2).getChar();
	}
	
	public static final short readInlineShort(byte[] values, int offset) {
		return ByteBuffer.wrap(values, offset, 2).getShort();
	}
	
	public static final int readInlineInt(byte[] values, int offset) {
		return ByteBuffer.wrap(values, offset, 4).getInt();
	}
	
	public static final float readInlineFloat(byte[] values, int offset) {
		return Float.intBitsToFloat(readInlineInt(values, offset));
	}
	
	public static final long readInlineLong(byte[] values, int offset) {
		return ByteBuffer.wrap(values, offset, 8).getLong();
	}
	
	public static final double readInlineDouble(byte[] values, int offset) {
		return Double.longBitsToDouble(readInlineLong(values, offset));
	}
	
}
