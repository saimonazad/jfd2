/*
 * Created on 2004/05/23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.list_table;

/**
 * �I����ێ�����N���X�̃C���^�[�t�F�C�X�B
 * 
 * @author shunji
 */
public interface ListCursorModel {
	/**
	 * �I���C���f�b�N�X��Ԃ��B
	 * @return
	 */
	public int getSelectedIndex();
	
	/**
	 * �I���C���f�b�N�X��ݒ肷��B�B
	 * @param index �C���f�b�N�X
	 */
	public void setSelectedIndex(int index);
	
	/**
	 * ���f���̃��X�i���Z�b�g����B
	 * @param listener
	 */
	public void addListCursorModelListener(ListCursorModelListener listener); 
}
