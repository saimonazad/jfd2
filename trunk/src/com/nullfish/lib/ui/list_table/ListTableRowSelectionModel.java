/*
 * Created on 2004/05/24
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.list_table;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ListTableRowSelectionModel extends DefaultListSelectionModel
		implements
			ListCursorModelListener {
	/**
	 * カーソル位置
	 */
	private int cursorIndex;
	
	private ListTable listTable;

	public ListTableRowSelectionModel(ListTable listTable) {
		this.listTable = listTable;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.ui.list_table.ListCursorModelListener#cursorChanged(com.nullfish.lib.ui.list_table.ListCursorModel, int, int)
	 */
	public void cursorChanged(ListCursorModel listCursorModel) {
		try {
			setValueIsAdjusting(true);
			ListTableModel listTableModel = listTable.getListTableModel();
			clearSelection();
			int rowCount = listTableModel.getRowCount();
			if(rowCount == 0) {
				return;
			}
			
			int rowIndex = listCursorModel.getSelectedIndex() % rowCount;
			
			setSelectionInterval(rowIndex, rowIndex);
		} finally {
			setValueIsAdjusting(false);
		}
	}
}