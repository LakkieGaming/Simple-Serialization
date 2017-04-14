package com.schiebros.serialization.object;

public class SSField extends SSVariable {

	public short dataLength;
	public byte[] data;

	public SSField(short nameLength, char[] name, short size, short dataLength, byte[] data) {
		super(nameLength, name, SSVariableType.FIELD.getTypeID(), size);
		this.dataLength = dataLength;
		this.data = data;
		// nameLength + name, type, dataLength, data
		this.size = (short) (2 + name.length * 2 + 1 + 2 + data.length);
	}

	public byte[] runExport() {
		return null;
	}

	public SSVariable runImport() {
		return null;
	}

}
