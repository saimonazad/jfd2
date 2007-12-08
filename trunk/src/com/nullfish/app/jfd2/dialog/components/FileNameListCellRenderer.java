/*
 * Created on 2004/06/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.dialog.components;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import com.nullfish.lib.vfs.VFile;

/**
 * JList�̃Z�������_���R���|�[�l���g�B
 * �t�@�C�����݂̂�\������
 * 
 * @author shunji
 */
public class FileNameListCellRenderer extends DefaultListCellRenderer {
	/**
	 * �t�@�C������\�����郌���_���R���|�[�l���g��Ԃ��B
	 * �\���l��JFDFile�łȂ���΂Ȃ�Ȃ��B
	 */
	public Component getListCellRendererComponent(JList list,
                                              Object value,
                                              int index,
                                              boolean isSelected,
                                              boolean cellHasFocus) {
    	JLabel rtn = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if(value != null) {
	    	rtn.setText(((VFile)value).getName());
		}
    	
    	return rtn; 
    }
}
