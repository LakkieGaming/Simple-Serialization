package net.battlemania.serialization.object;

public enum SSType {

	BOOLEAN((byte) 1, 1), CHAR((byte) 2, 2), SHORT((byte) 3, 2), INT((byte) 4, 4), FLOAT((byte) 5, 4), LONG((byte) 6,
			8), DOUBLE((byte) 7, 8), BYTE((byte) 8, 1);

	private final int size;
	private final byte id;

	private SSType(byte id, int size) {
		this.size = size;
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public byte getID() {
		return this.id;
	}
	
	public static SSType getTypeFromID(byte id) {
		for (SSType type : SSType.values()) {
			if (type.getID() == id) {
				return type;
			}
		}
		return null;
	}
	
}
