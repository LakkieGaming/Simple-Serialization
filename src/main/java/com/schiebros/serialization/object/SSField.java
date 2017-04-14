package com.schiebros.serialization.object;

import com.schiebros.serialization.ArrayReader;
import com.schiebros.serialization.ArrayWriter;
import com.schiebros.serialization.DataReader;

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
		index = ArrayWriter.writeInlineBytes(index, nameLength, buffer);
		index = ArrayWriter.writeInlineArray(index, name, buffer);
		index = ArrayWriter.writeInlineBytes(index, type, buffer);
		// writing SSField data
		index = ArrayWriter.writeInlineBytes(index, dataLength, buffer);
		index = ArrayWriter.writeInlineArray(index, data, buffer);
		return buffer;
	}

	public void runImport(byte[] data) {
		int index = 0;
		// reading SSVariable data
		this.nameLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.name = ArrayReader.readInlineArray(index, new char[this.nameLength], data);
		index += this.nameLength * 2;
		this.type = ArrayReader.readInlineByte(data, index);
		index += 1;
		// reading SSField data
		this.dataLength = ArrayReader.readInlineShort(data, index);
		index += 2;
		this.data = ArrayReader.readInlineArray(index, new byte[this.dataLength], data);
		index += this.dataLength;
		this.size = (short) (2 + name.length * 2 + 1 + 2 + data.length);
	}
	
	

}
