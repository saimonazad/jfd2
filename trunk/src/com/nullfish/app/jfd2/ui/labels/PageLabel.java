/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.ui.labels;

import javax.swing.JLabel;

import com.nullfish.lib.ui.list_table.ListTableModel;
import com.nullfish.lib.ui.list_table.ListTableModelListener;

/**
 * �y�[�W�ԍ��\�����x���B
 * @author shunji
 */
public class PageLabel extends JLabel {
	ListTableModel model;
	
	public PageLabel() {
		super("   /   ");
	}
	
	public void setListTableModel(ListTableModel model) {
		this.model = model;
		model.addListTableModelListener(new ListTableModelListenerImpl());
	}
	
	private class ListTableModelListenerImpl implements ListTableModelListener {
		private StringBuffer text = new StringBuffer();
		
		public void pageChanged(ListTableModel model) {
			text.setLength(0);
			
			String pageStr = Integer.toString(model.getPage() + 1);
			for(int i=0; i< 3 - pageStr.length(); i++) {
				text.append(" ");
			}
			text.append(pageStr);
			
			text.append("/");
			
			String countStr = Integer.toString(model.getPageCount());
			for(int i=0; i< 3 - countStr.length(); i++) {
				text.append(" ");
			}
			text.append(countStr);
			
			setText( text.toString() );
		}
	}
}
