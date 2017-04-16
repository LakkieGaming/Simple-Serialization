package com.schiebros.serialization.object;

import java.util.LinkedList;
import java.util.List;

import com.schiebros.serialization.ArrayReader;
import com.schiebros.serialization.ArrayWriter;

public class SSHolder extends SSVariable {

	public short objectCount;
	public List<SSObject> objects;
	public short fieldCount;
	public List<SSField> fields;
	public short arrayCount;
	public List<SSArray> arrays;

	public SSHolder(char[] name) {
		super((short) name.length, name, SSVariableType.HOLDER.getTypeID());
		this.objects = new LinkedList<SSObject>();
		this.fields = new LinkedList<SSField>();
		this.arrays = new LinkedList<SSArray>();
		this.objectCount = 0;
		this.fieldCount = 0;
		this.arrayCount = 0;
		// nameLength + name + type + objectCount + fieldCount + arrayCount
		this.size = (short) (2 + this.nameLength * 2 + 1 + 2 + 2 + 2);
	}

	public SSHolder() {
		super((short) -1, null, SSVariableType.HOLDER.getTypeID());
	}

	public void addObject(SSObject object) {
		objects.add(object);
		this.size += object.size;
		this.objectCount++;
	}

	public void removeObject(SSObject object) {
		objects.remove(object);
		this.size -= object.size;
		this.objectCount--;
	}

	public void addField(SSField field) {
		fields.add(field);
		this.size += field.size;
		this.objectCount++;
	}

	public void removeField(SSField field) {
		fields.remove(field);
		this.size -= field.size;
		this.objectCount--;
	}

	public void addArray(SSArray array) {
		arrays.add(array);
		this.size += array.size;
		this.objectCount++;
	}

	public void removeArray(SSArray array) {
		arrays.remove(array);
		this.size -= array.size;
		this.objectCount--;
	}
	
	public List<SSObject> getObjects() {
		return objects;
	}
	
	public List<SSField> getFields() {
		return fields;
	}
	
	public List<SSArray> getArrays() {
		return arrays;
	}

	public byte[] runExport() {
		byte[] buffer = new byte[this.size];
		int index = 0;
		index = ArrayWriter.writeInlineBytes(index, type, buffer);
		index = ArrayWriter.writeInlineBytes(index, nameLength, buffer);
		index = ArrayWriter.writeInlineArray(index, name, buffer);
		index = ArrayWriter.writeInlineBytes(index, objectCount, buffer);
		for (SSObject object : objects) {
			index = ArrayWriter.writeInlineArray(index, object.runExport(), buffer);
		}
		index = ArrayWriter.writeInlineBytes(index, fieldCount, buffer);
		for (SSField field : fields) {
			index = ArrayWriter.writeInlineArray(index, field.runExport(), buffer);
		}
		index = ArrayWriter.writeInlineBytes(index, arrayCount, buffer);
		for (SSArray array : arrays) {
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
		this.size = (short) (2 + this.nameLength * 2 + 1 + 2 + 2 + 2);
		this.objectCount = ArrayReader.readInlineShort(data, offset);
		offset += 2;
		while (identifyType(data, offset) == SSVariableType.OBJECT) {
			SSObject object = new SSObject();
			object.runImport(data, offset);
			this.objects.add(object);
			offset += object.size;
		}
		this.fieldCount = ArrayReader.readInlineShort(data, offset);
		offset += 2;
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
