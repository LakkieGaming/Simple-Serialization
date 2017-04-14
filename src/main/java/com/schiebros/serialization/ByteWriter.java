package com.schiebros.serialization;

import java.util.ArrayList;
import java.util.List;

public class ByteWriter {

	private boolean closed = false;
	private List<Byte> bytes = new ArrayList<Byte>();

	public void append(byte b) {
		if (closed) {
			return;
		}
		bytes.add(b);
	}
	
	public void append(byte[] bs) {
		if (closed) {
			return;
		}
		for (byte b : bs) {
			append(b);
		}
	}

	public void clear() {
		bytes.clear();
	}

	public byte[] flush() {
		if (closed) {
			return null;
		}
		byte[] buffer = new byte[bytes.size()];
		int i = 0;
		for (Byte b : bytes) {
			buffer[i] = b;
			i++;
		}
		bytes.clear();
		return buffer;
	}

	public void close() {
		closed = true;
	}

	public boolean isClosed() {
		return closed;
	}
	
	public static byte[] subArray(byte[] src, int start, int end) {
		byte[] buffer = new byte[end - start];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = src[i];
		}
		return buffer;
	}

}
