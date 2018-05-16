package org.dao;

public class Utils {

	public static int GetBitInt(int data, int length, int shift) {
		return (data & (((1 << length) - 1) << shift)) >> shift;
	}

}
