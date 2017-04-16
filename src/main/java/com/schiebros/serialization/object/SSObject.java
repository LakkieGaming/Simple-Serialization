package com.schiebros.serialization.object;

import java.util.LinkedList;
import java.util.List;

import com.schiebros.serialization.ArrayReader;
import com.schiebros.serialization.ArrayWriter;

public class SSObject extends SSVariable {

	public short fieldCount;
	private List<SSField> fields;
	public short arrayCount;
	private List<SSArray> arrays;

	public SSObject(char[] name) {
		super((short) name.length, name, SSVariableType.OBJECT.getTypeID());
		this.fields = new LinkedList<SSField>();
		this.arrays = new LinkedList<SSArray>();
		this.fieldCount = 0;
		this.arrayCount = 0;
		// nameLength + name + type + fieldCount + arrayCount
		this.size = (short) (2 + this.nameLength * 2 + 1 + 2 + 2);
	}

	public SSObject() {
		super((short) -1, null, SSVariableType.OBJECT.getTypeID());
		this.fields = new LinkedList<SSField>();
		this.arrays = new LinkedList<SSArray>();
		this.fieldCount = 0;
		this.arrayCount = 0;
	}

	public void addField(SSField field) {
		this.fields.add(field);
		this.size += field.size;
		this.fieldCount++;
	}

	public void removeField(SSField field) {
		this.fields.remove(field);
		this.size -= field.size;
		this.fieldCount--;
	}

	public void addArray(SSArray array) {
		this.arrays.add(array);
		this.size += array.size;
		this.arrayCount++;
	}

	public void removeArray(SSArray array) {
		this.arrays.remove(array);
		this.size -= array.size;
		this.arrayCount--;
	}

	public SSField getField(String name) {
		for (SSField field : getFields()) {
			if (new String(field.name).equals(name)) {
				return field;
			}
		}
		return null;
	}

	public List<SSField> getFields() {
		return this.fields;
	}

	public SSArray getArray(String name) {
		for (SSArray array : getArrays()) {
			if (new String(array.name).equalsIgnoreCase(name)) {
				return array;
			}
		}
		return null;
	}

	public List<SSArray> getArrays() {
		return this.arrays;
	}

	public byte[] runExport() {
		byte[] buffer = new byte[this.size];
		int index = 0;
		index = ArrayWriter.writeInlineBytes(index, this.type, buffer);
		index = ArrayWriter.writeInlineBytes(index, this.nameLength, buffer);
		index = ArrayWriter.writeInlineArray(index, this.name, buffer);
		index = ArrayWriter.writeInlineBytes(index, fieldCount, buffer);
		for (SSField field : this.fields) {
			index = ArrayWriter.writeInlineArray(index, field.runExport(), buffer);
		}
		index = ArrayWriter.writeInlineBytes(index, arrayCount, buffer);
		for (SSArray array : this.arrays) {
			index = ArrayWriter.writeInlineArray(index, array.runExport(), buffer);
		}
		return buffer;
	}

	public void runImport(byte[] data) {
		runImport(data, 0);
	}

	public void runImport(byte[] data, int offset) {
		this.type = ArrayReader.readInlineByte(data, offset);
		offset += 1;
		this.nameLength = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		this.name = ArrayReader.readInlineArray(offset, new char[this.nameLength], data);
		offset += this.nameLength * 2;
		this.fieldCount = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		this.size = (short) (2 + this.nameLength * 2 + 1 + 2 + 2);
		while (identifyType(data, offset) == SSVariableType.FIELD) {
			SSField field = new SSField();
			field.runImport(data, offset);
			this.fields.add(field);
			offset += field.size;
		}
		this.arrayCount = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		while (identifyType(data, offset) == SSVariableType.ARRAY) {
			SSArray array = new SSArray();
			array.runImport(data, offset);
			this.arrays.add(array);
			offset += array.size;
		}
	}

	public static SSVariableType identifyType(byte[] bytes, int offset) {
		if (offset >= bytes.length) {
			return null;
		}
		byte b = bytes[offset];
		for (SSVariableType type : SSVariableType.values()) {
			if (type.getTypeID() == b) {
				return type;
			}
		}
		return null;
	}

}
