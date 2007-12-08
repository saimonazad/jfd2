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
 * JListのセルレンダラコンポーネント。
 * ファイル名のみを表示する
 * 
 * @author shunji
 */
public class FileNameListCellRenderer extends DefaultListCellRenderer {
	/**
	 * ファイル名を表示するレンダラコンポーネントを返す。
	 * 表示値はJFDFileでなければならない。
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
