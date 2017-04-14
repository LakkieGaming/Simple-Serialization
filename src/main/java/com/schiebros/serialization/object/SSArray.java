package com.schiebros.serialization.object;

import com.schiebros.serialization.ArrayReader;
import com.schiebros.serialization.ArrayWriter;

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
		index = ArrayWriter.writeInlineBytes(index, nameLength, buffer);
		index = ArrayWriter.writeInlineArray(index, name, buffer);
		index = ArrayWriter.writeInlineBytes(index, type, buffer);
		// array data
		index = ArrayWriter.writeInlineBytes(index, dataLength, buffer);
		index = ArrayWriter.writeInlineArray(index, data, buffer);
		index = ArrayWriter.writeInlineBytes(index, typeID, buffer);
		return buffer;
	}

	public void runImport(byte[] data) {
		int index = 0;
		// variable data
		this.nameLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.name = ArrayReader.readInlineArray(index, new char[this.nameLength], data);
		index += this.nameLength * 2;
		this.type = ArrayReader.readInlineByte(data, index);
		index += 1;
		// array data
		this.dataLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.data = ArrayReader.readInlineArray(index, new byte[this.dataLength], data);
		index += this.dataLength;
		this.typeID = ArrayReader.readInlineByte(data, index);
		index += 1;
		this.size = (short) (2 + this.name.length * 2 + 1 + 2 + data.length + 1);
	}

}
