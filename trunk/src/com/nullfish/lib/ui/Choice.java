/*
 * Created on 2004/06/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import javax.swing.KeyStroke;

/**
 * ChooserPanel���ł̑I������\���N���X
 * 
 * @author shunji
 */
public class Choice {
	/**
	 * ����
	 */
	String name;
	
	/**
	 * �\�����x��
	 */
	String label;
	
	/**
	 * �j�[���j�b�N�L�[
	 */
	char mnemonic;
	
	/**
	 * �I���\�t���O
	 */
	boolean active = true;
	
	public Choice(String name, String label) {
		this(name, label, (char)-1, true);
	}
	
	public Choice(String name, String label, char mnemonic) {
		this(name, label, mnemonic, true);
	}
	
	public Choice(String name, String label, char mnemonic, boolean active) {
		this.name = name;
		this.label = label;
		this.mnemonic = mnemonic;
		this.active = active;
	}
	
	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * @return Returns the mnemonic.
	 */
	public char getMnemonic() {
		return mnemonic;
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	public KeyStroke getKeyStroke() {
		if(mnemonic == -1) {
			return null;
		}
			
		return KeyStroke.getKeyStroke(mnemonic);
	}
	
	/**
	 * @return Returns the active.
	 */
	public boolean isActive() {
		return active;
	}
}
