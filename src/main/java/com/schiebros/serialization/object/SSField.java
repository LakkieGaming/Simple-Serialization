package com.schiebros.serialization.object;

import com.schiebros.serialization.ByteWriter;
import com.schiebros.serialization.DataWriter;

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
		ByteWriter writer = new ByteWriter();
		writer.append(DataWriter.getBytes(nameLength));
		return null;
	}

	public SSVariable runImport() {
		return null;
	}

}
