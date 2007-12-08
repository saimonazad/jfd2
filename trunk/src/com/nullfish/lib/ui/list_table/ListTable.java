/*
 * Created on 2004/05/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.list_table;

import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListModel;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ListTable extends JTable {
	private ListTableModel listTableModel;

	/**
	 * ���E�I�����f��
	 */
	private ListTableRowSelectionModel rowSelectionModel = new ListTableRowSelectionModel(
			this);

	/**
	 * �J�����I�����f��
	 */
	private ListTableColumnSelectionModel columnSelectionModel = new ListTableColumnSelectionModel(
			this);

	private int columnCount;
	
	private boolean autoAdjustColumnCount = false;
	
	private int preferredColumnWidth;
	
	/**
	 * �R���X�g���N�^
	 *  
	 */
	public ListTable() {
		super();
		listTableModel = new ListTableModel();
		setModel(listTableModel);
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				revalidateRowCount();
				revalidateColumnCount();
			}
		});

		setEnabled(false);
		setSelectionModel(rowSelectionModel);
		getColumnModel().setSelectionModel(columnSelectionModel);

		setColumnSelectionAllowed(true);
		setRowSelectionAllowed(true);

		addMouseListener(new SelectionChanger(this));
	}

	/**
	 * ���X�g���f�����擾����B
	 * 
	 * @return
	 */
	public ListModel getListModel() {
		return listTableModel.getListModel();
	}

	/**
	 * ���X�g���f�����Z�b�g����B
	 * 
	 * @param listModel
	 */
	public void setListModel(ListModel listModel) {
		listTableModel.setListModel(listModel);
	}

	public void setListCursorModel(ListCursorModel cursorModel) {
		listTableModel.setListCursorModel(cursorModel);
		cursorModel.addListCursorModelListener(rowSelectionModel);
		cursorModel.addListCursorModelListener(columnSelectionModel);
	}

	/**
	 * �J���������Z�b�g����B
	 * 
	 * @param columnCount
	 */
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
		
		if(autoAdjustColumnCount) {
			return;
		}
		
		listTableModel.setColumnCount(columnCount);
		rowSelectionModel.cursorChanged(listTableModel.getListCursorModel());
		columnSelectionModel.cursorChanged(listTableModel.getListCursorModel());
	}
	
	/**
	 * �ʏ탂�[�h�i�����J�������ύX���Ȃ��j�ł̃J���������擾����B
	 */
	public int getPreferredColumnCount() {
		return columnCount;
	}

	public ListTableModel getListTableModel() {
		return listTableModel;
	}

	/**
	 * ���݂̃R���|�[�l���g�̃T�C�Y���烍�E����ݒ肵�Ȃ����B
	 *  
	 */
	private void revalidateRowCount() {
		if (listTableModel != null) {
			listTableModel.setRowCount(getHeight() / (getRowHeight()));

			ListCursorModel cursorModel = listTableModel.getListCursorModel();
			rowSelectionModel.cursorChanged(cursorModel);
			columnSelectionModel.cursorChanged(cursorModel);
		}
	}

	private void revalidateColumnCount() {
		if(!autoAdjustColumnCount) {
			setColumnCount(columnCount);
			return;
		}
		
		if(preferredColumnWidth <= 0) {
			return;
		}
		
		int tempColumnCount = getWidth() / preferredColumnWidth;
		listTableModel.setColumnCount(tempColumnCount);
		rowSelectionModel.cursorChanged(listTableModel.getListCursorModel());
		columnSelectionModel.cursorChanged(listTableModel.getListCursorModel());
	}
	
	/**
	 * ���E�̍������Z�b�g����B
	 */
	public void setRowHeight(int rowHeight) {
		super.setRowHeight(rowHeight);
		revalidateRowCount();
	}

	/**
	 * ���X�g���̎w��C���f�b�N�X�Ԗڂ̃Z����I������B
	 * 
	 * @param index
	 */
	public void setSelectedIndex(int index) {
		listTableModel.getListCursorModel().setSelectedIndex(index);
	}

	/**
	 * ���X�g���̑I���C���f�b�N�X���擾����B
	 * 
	 * @return
	 */
	public int getSelectedIndex() {
		return listTableModel.getListCursorModel().getSelectedIndex();
	}

	/**
	 * �}�E�X�ŃN���b�N���ꂽ�ʒu�����ɁA�J�[�\���̃C���f�b�N�X��ύX����B
	 * 
	 * @param point
	 */
	private void selectIndexFromPoint(Point point) {
		int column = columnAtPoint(point);
		int row = rowAtPoint(point);
		int page = listTableModel.getPage();
		int rowCount = getRowCount();
		int columnCount = getColumnCount();

		int index = (page * rowCount * columnCount) + (column * rowCount) + row;

		listTableModel.getListCursorModel().setSelectedIndex(index);
	}

	public void clearSelection() {
		//	�������Ȃ��B
	}

	private static class SelectionChanger extends MouseAdapter {
		ListTable table;

		public SelectionChanger(ListTable table) {
			this.table = table;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mousePressed(MouseEvent e) {
			table.selectIndexFromPoint(e.getPoint());
		}
	}

	public void setAutoAdjustColumnCount(boolean autoAdjustColumnCount) {
		this.autoAdjustColumnCount = autoAdjustColumnCount;
		revalidateColumnCount();
	}

	public void setPreferredColumnWidth(int preferredColumnWidth) {
		this.preferredColumnWidth = preferredColumnWidth;
		revalidateColumnCount();
	}
}