/*
 * Created on 2004/06/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.dialog.components;

import javax.swing.plaf.basic.BasicComboBoxEditor;

import com.nullfish.app.jfd2.JFD;

/**
 * �p�X�⊮�@�\�t���R���{�{�b�N�X�G�f�B�^
 * 
 * @author shunji
 */
public class CompletionComboBoxEditor extends BasicComboBoxEditor {
	public CompletionComboBoxEditor(JFD jfd) {
		super();
		editor = new FileCompletionTextField(jfd);
		editor.setBorder(null);
	}
	
	/**
	 * @see javax.swing.ComboBoxEditor#setItem(Object)
	 */
    public void setItem(Object anObject) {
    	//	�I�����ύX���ꂽ��S�I������
        super.setItem(anObject);
        selectAll();
    }
}
