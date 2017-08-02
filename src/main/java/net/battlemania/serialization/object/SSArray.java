package net.battlemania.serialization.object;

import net.battlemania.serialization.ArrayReader;
import net.battlemania.serialization.ArrayWriter;

public class SSArray extends SSVariable {

	public short dataLength;
	public byte[] data;
	public byte typeID;

	public SSArray(char[] name, byte[] data, SSType type) {
		super((short) (name.length), name, SSVariableType.ARRAY.getTypeID());
		this.dataLength = (short) data.length;
		this.data = data;
		this.typeID = type.getID();
		// nameLength + name + type + dataLength + data + typeID
		this.size = (short) (2 + this.name.length * 2 + 1 + 2 + data.length + 1);
	}

	public SSArray() {
		super((short) -1, null, SSVariableType.ARRAY.getTypeID());
	}
	
	public boolean[] getBoolean() {
		boolean[] buffer = new boolean[dataLength];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public char[] getChar() {
		char[] buffer = new char[dataLength / 2];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public short[] getShort() {
		short[] buffer = new short[dataLength / 2];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public int[] getInt() {
		int[] buffer = new int[dataLength / 4];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public float[] getFloat() {
		float[] buffer = new float[dataLength / 4];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public long[] getLong() {
		long[] buffer = new long[dataLength / 8];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public double[] getDouble() {
		double[] buffer = new double[dataLength / 8];
		ArrayReader.readInlineArray(0, buffer, data);
		return buffer;
	}
	
	public int getArrayLength() {
		switch (SSType.getTypeFromID(typeID)) {
		case BOOLEAN:
			return dataLength;
		case CHAR:
			return dataLength / 2;
		case DOUBLE:
			return dataLength / 8;
		case FLOAT:
			return dataLength / 4;
		case INT:
			return dataLength / 4;
		case LONG:
			return dataLength / 8;
		case SHORT:
			return dataLength / 2;
		default:
			return -1;
		}
	}

	public byte[] runExport() {
		byte[] buffer = new byte[this.size];
		int index = 0;
		// variable data
		index = ArrayWriter.writeInlineBytes(index, type, buffer);
		index = ArrayWriter.writeInlineBytes(index, nameLength, buffer);
		index = ArrayWriter.writeInlineArray(index, name, buffer);
		// array data
		index = ArrayWriter.writeInlineBytes(index, dataLength, buffer);
		index = ArrayWriter.writeInlineArray(index, data, buffer);
		index = ArrayWriter.writeInlineBytes(index, typeID, buffer);
		return buffer;
	}

	public void runImport(byte[] data) {
		int index = 0;
		// variable data
		this.type = ArrayReader.readInlineByte(data, index);
		index += 1;
		this.nameLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.name = ArrayReader.readInlineArray(index, new char[this.nameLength], data);
		index += this.nameLength * 2;
		// array data
		this.dataLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.data = ArrayReader.readInlineArray(index, new byte[this.dataLength], data);
		index += this.dataLength;
		this.typeID = ArrayReader.readInlineByte(data, index);
		index += 1;
		this.size = (short) (2 + this.name.length * 2 + 1 + 2 + this.data.length + 1);
	}

	public void runImport(byte[] data, int offset) {
		// variable data
		this.type = ArrayReader.readInlineByte(data, offset);
		offset += 1;
		this.nameLength = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		this.name = ArrayReader.readInlineArray(offset, new char[this.nameLength], data);
		offset += this.nameLength * 2;
		// array data
		this.dataLength = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		this.data = ArrayReader.readInlineArray(offset, new byte[this.dataLength], data);
		offset += this.dataLength;
		this.typeID = ArrayReader.readInlineByte(data, offset);
		offset += 1;
		this.size = (short) (2 + this.name.length * 2 + 1 + 2 + this.data.length + 1);
	}
	
	public static SSArray asCharArray(String name, char[] data) {
		byte[] buffer = new byte[data.length * 2];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.CHAR);
	}
	
	public static SSArray asShortArray(String name, short[] data) {
		byte[] buffer = new byte[data.length * 2];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.SHORT);
	}
	
	public static SSArray asIntArray(String name, int[] data) {
		byte[] buffer = new byte[data.length * 4];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.INT);
	}
	
	public static SSArray asFloatArray(String name, float[] data) {
		byte[] buffer = new byte[data.length * 4];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.FLOAT);
	}
	
	public static SSArray asLongArray(String name, long[] data) {
		byte[] buffer = new byte[data.length * 8];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.LONG);
	}
	
	public static SSArray asDoubleArray(String name, double[] data) {
		byte[] buffer = new byte[data.length * 8];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.DOUBLE);
	}
	
	public static SSArray asBooleanArray(String name, boolean[] data) {
		byte[] buffer = new byte[data.length];
		ArrayWriter.writeInlineArray(0, data, buffer);
		return new SSArray(name.toCharArray(), buffer, SSType.BOOLEAN);
	}

}
