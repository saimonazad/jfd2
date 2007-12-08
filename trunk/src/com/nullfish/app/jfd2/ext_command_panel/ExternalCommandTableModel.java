/*
 * Created on 2005/01/13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel;

import javax.swing.table.AbstractTableModel;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExternalCommandTableModel extends AbstractTableModel {
	private int setNumber = 0;
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return 13;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		int num = rowIndex + (columnIndex * 13);
		return ExternalCommandManager.getInstance().getCommand(setNumber, num);
	}

	public void setSetNumber(int setNumber) {
		this.setNumber = setNumber;
		fireTableDataChanged();
	}
	
	public int getSetNumber() {
		return setNumber;
	}
}
