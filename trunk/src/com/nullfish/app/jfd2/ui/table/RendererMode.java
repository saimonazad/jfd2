/*
 * Created on 2004/07/02
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * �t�@�C���\�����̗v�f�̉��A�����Ԃ���t�̃t�H�[�}�b�g���`����N���X�B TypeSafeEnumration�ɂȂ��Ă���B
 * 
 * @author shunji
 */
public class RendererMode {
	/**
	 * ���[�h����
	 */
	private String name;
	
	/**
	 * �t�@�C���T�C�Y����
	 */
	private boolean sizeVisible;

	/**
	 * �p�[�~�b�V��������
	 */
	private boolean permissionVisible;

	/**
	 * ���t����
	 */
	private boolean dateVisible;

	/**
	 * ���t�t�H�[�}�b�g
	 */
	private DateFormat dateFormat;

	public static final RendererMode TYPE_1 = new RendererMode("1", true, true,
			true, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss "));

	public static final RendererMode TYPE_2 = new RendererMode("2", true, false,
			true, new SimpleDateFormat("yy-MM-dd HH:mm "));

	public static final RendererMode TYPE_3 = new RendererMode("3", true, false,
			false, null);

	public static final RendererMode TYPE_4 = new RendererMode("4", false, false,
			false, null);

	public static final RendererMode TYPE_1_ABS = new RendererMode("1A", true, true,
			true, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss "));

	public static final RendererMode TYPE_2_ABS = new RendererMode("2A", true, false,
			true, new SimpleDateFormat("yy-MM-dd HH:mm "));

	public static final RendererMode TYPE_3_ABS = new RendererMode("3A", true, false,
			false, null);

	public static final RendererMode TYPE_4_ABS = new RendererMode("4A", false, false,
			false, null);

	private static final RendererMode[] MODES = {
		TYPE_1,
		TYPE_2,
		TYPE_3,
		TYPE_4,
		TYPE_1_ABS,
		TYPE_2_ABS,
		TYPE_3_ABS,
		TYPE_4_ABS,
	};
		
	private RendererMode(String name, boolean sizeVisible, boolean permissionVisible,
			boolean dateVisible, DateFormat dateFormat) {
		this.name = name;
		this.sizeVisible = sizeVisible;
		this.permissionVisible = permissionVisible;
		this.dateVisible = dateVisible;
		this.dateFormat = dateFormat;
	}

	public static RendererMode fromName(String name) {
		for(int i=0; i<MODES.length; i++) {
			if(MODES[i].getName().equals(name)) {
				return MODES[i];
			}
		}
		
		return MODES[0];
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return Returns the dateFormat.
	 */
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * @return Returns the dateVisible.
	 */
	public boolean isDateVisible() {
		return dateVisible;
	}

	/**
	 * @return Returns the sizeVisible.
	 */
	public boolean isSizeVisible() {
		return sizeVisible;
	}
	
	/**
	 * @return Returns the permissionVisible.
	 */
	public boolean isPermissionVisible() {
		return permissionVisible;
	}
	
	/**
	 * �w��̃��[�h�̎��̃��[�h�����߂�B
	 * 
	 * @param mode
	 * @return
	 */
	public static RendererMode getNextMode(RendererMode mode) {
		for(int i=0; i<MODES.length; i++) {
			if(mode == MODES[i]) {
				return MODES[(i + 1) % MODES.length];
			}
		}
		
		//	�N���肦�Ȃ�
		return TYPE_1;
	}
}