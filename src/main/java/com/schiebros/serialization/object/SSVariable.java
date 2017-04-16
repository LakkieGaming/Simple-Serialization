package com.schiebros.serialization.object;

public abstract class SSVariable {

	// exporting variables
	public byte type;
	public short nameLength;
	public char[] name;

	// non exporting variables
	public short size;
	
	public SSVariable(short nameLength, char[] name, byte type) {
		this.nameLength = nameLength;
		this.name = name;
		this.type = type;
	}

	public abstract byte[] runExport();
	
	public abstract void runImport(byte[] data);
	
	public abstract void runImport(byte[] data, int offset);

	public static enum SSVariableType {
		
		FIELD((byte)(1)), ARRAY((byte)(2)), OBJECT((byte) 3), HOLDER((byte) 4);
		
		private final byte typeID;
		
		private SSVariableType(byte type) {
			typeID = type;
		}
		
		public byte getTypeID() {
			return this.typeID;
		}
		
	}
	
}
