package com.schiebros.serialization;

import java.nio.ByteBuffer;

public class DataReader {

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
	
}
