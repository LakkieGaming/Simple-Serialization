package com.schiebros.serialization.object;

import com.schiebros.serialization.ArrayReader;
import com.schiebros.serialization.ArrayWriter;
import com.schiebros.serialization.DataReader;
import com.schiebros.serialization.DataWriter;

public class SSField extends SSVariable {

	public short dataLength;
	public byte[] data;

	public SSField(char[] name, byte[] data) {
		super((short) (name.length), name, SSVariableType.FIELD.getTypeID());
		this.dataLength = (short) data.length;
		this.data = data;
		// nameLength + name, type, dataLength, data
		this.size = (short) (2 + name.length * 2 + 1 + 2 + data.length);
	}
	
	public SSField() {
		super((short) -1, null, SSVariableType.FIELD.getTypeID());
	}
	
	public short getShort() {
		return DataReader.readShort(data);
	}
	
	public char getChar() {
		return DataReader.readChar(data);
	}
	
	public int getInt() {
		return DataReader.readInt(data);
	}
	
	public float getFloat() {
		return DataReader.readFloat(data);
	}
	
	public long getLong() {
		return DataReader.readLong(data);
	}
	
	public double getDouble() {
		return DataReader.readDouble(data);
	}

	public byte[] runExport() {
		byte[] buffer = new byte[this.size];
		int index = 0;
		// writing SSVariable data
		index = ArrayWriter.writeInlineBytes(index, type, buffer);
		index = ArrayWriter.writeInlineBytes(index, nameLength, buffer);
		index = ArrayWriter.writeInlineArray(index, name, buffer);
		// writing SSField data
		index = ArrayWriter.writeInlineBytes(index, dataLength, buffer);
		index = ArrayWriter.writeInlineArray(index, data, buffer);
		return buffer;
	}

	public void runImport(byte[] data) {
		int index = 0;
		// reading SSVariable data
		this.type = ArrayReader.readInlineByte(data, index);
		index += 1;
		this.nameLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.name = ArrayReader.readInlineArray(index, new char[this.nameLength], data);
		index += this.nameLength * 2;
		// reading SSField data
		this.dataLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.data = ArrayReader.readInlineArray(index, new byte[this.dataLength], data);
		index += this.dataLength;
		this.size = (short) (2 + name.length * 2 + 1 + 2 + this.data.length);
	}
	
	public void runImport(byte[] data, int offset) {
		// reading SSVariable data
		this.type = ArrayReader.readInlineByte(data, offset);
		offset += 1;
		this.nameLength = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		this.name = ArrayReader.readInlineArray(offset, new char[this.nameLength], data);
		offset += this.nameLength * 2;
		// reading SSField data
		this.dataLength = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		this.data = ArrayReader.readInlineArray(offset, new byte[this.dataLength], data);
		offset += this.dataLength;
		this.size = (short) (2 + name.length * 2 + 1 + 2 + this.data.length);
	}
	
	public static SSField asChar(String name, char value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}
	
	public static SSField asShort(String name, short value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}
	
	public static SSField asInt(String name, int value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}
	
	public static SSField asFloat(String name, float value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}
	
	public static SSField asLong(String name, long value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}
	
	public static SSField asDouble(String name, double value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}
	
	public static SSField asBoolean(String name, boolean value) {
		return new SSField(name.toCharArray(), DataWriter.getBytes(value));
	}

}
