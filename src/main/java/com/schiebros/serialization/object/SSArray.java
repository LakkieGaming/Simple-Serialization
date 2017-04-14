package com.schiebros.serialization.object;

public class SSArray extends SSVariable {

	public SSArray(short nameLength, char[] name, byte type) {
		super(nameLength, name, type);
	}

	public byte[] runExport() {
		return null;
	}

	public void runImport(byte[] data) {
		
	}

}
