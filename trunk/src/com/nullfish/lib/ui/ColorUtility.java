/*
 * Created on 2004/02/29
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * �F�֌W�̃��[�e�B���e�B�N���X�B
 * �����񂩂�F�ւ̕ϊ����s���B
 * 
 * @author shunji
 */
public class ColorUtility {
	/**
	 * �����񂩂�Color�N���X���쐬���郁�\�b�h�B
	 * �ȉ���2�`�����w��\�B
	 * 1�A�F����
	 * 	black, white, red�ȂǁB
	 * 	Color�N���X��static�t�B�[���h�����w��\�B
	 * 
	 * 2�A�J���[�R�[�h
	 * 	#�Ŏn�܂�8����16�i���Ƃ��Ďw�肷��B
	 * 	��F#FF10FF00
	 * 	�񌅂��A�A���t�@�A�ԁA�΁A�ɑΉ�����B
	 */
	public static Color stringToColor(String colorCode) {
		if (colorCode == null || colorCode.length() == 0) {
			return null;
		}
		
		if(colorCode.startsWith("#")) {
			int alpha = 0;
			int r = 0;
			int g = 0;
			int b = 0;
			try {
				alpha = Integer.valueOf(colorCode.substring(1, 3), 16).intValue();
				r = Integer.valueOf(colorCode.substring(3, 5), 16).intValue();
				g = Integer.valueOf(colorCode.substring(5, 7), 16).intValue();
				b = Integer.valueOf(colorCode.substring(7, 9), 16).intValue();
			} catch (Exception e) {
				return null;
			}
			return (new Color(r, g, b, alpha));
		} else {
			return colorName2Color(colorCode);
		}
	}
	
	public static Color colorName2Color(String colorName) {
		try {
			Field field = Class.forName("java.awt.Color").getField(colorName);
			return (Color)field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String color2String(Color color) {
		return "#" + Integer.toHexString(color.getRGB());
	}
	
	public static Color getComplementaryColor(Color c) {
	    int newRed = getComplement(c.getRed());
	    int newGreen = getComplement(c.getGreen());
	    int newBlue = getComplement(c.getBlue());

	    return new Color(newRed, newGreen, newBlue, c.getAlpha());
	}

	private static int getComplement(int colorVal) {
	    int maxDiff = colorVal >= 128 ? -colorVal : 255 - colorVal;
	    int diff = (int) Math.round(maxDiff / 2.0);
	    int newColorVal = colorVal + diff;

	    return newColorVal;
	}
}
