/*
 * Created on 2004/05/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.list_table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

/**
 * ListModel���f�[�^�Ƃ��Ď��e�[�u�����f���N���X�B
 * 
 * @author shunji
 */
public class ListTableModel extends AbstractTableModel {
	/**
	 * �J�[�\���ʒu�ێ��N���X
	 */
	private ListCursorModel selectionHolder;

	/**
	 * �y�[�W�ԍ�
	 *  
	 */
	private int page;

	/**
	 * �J������
	 */
	private int columnCount = 1;

	/**
	 * ���E��
	 */
	private int rowCount;

	/**
	 * ���X�g���f��
	 */
	private ListModel listModel;

	/**
	 * ListTableModelListener�̃��X�g
	 */
	private List listeners = new ArrayList();

	/**
	 * �f�t�H���g�R���X�g���N�^
	 *  
	 */
	public ListTableModel() {
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param listModel
	 *            ���X�g���f��
	 */
	public ListTableModel(ListModel listModel, ListCursorModel selectionHolder) {
		setListModel(listModel);
		setListCursorModel(selectionHolder);
	}

	/**
	 * �J���������Z�b�g����B
	 * 
	 * @param columnCount
	 */
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
		recaliculatePage();
		fireTableStructureChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * ���E�����Z�b�g����B
	 * 
	 * @param rowCount
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		recaliculatePage();
		fireTableStructureChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return rowCount;
	}

	public void setTableSize(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		fireTableStructureChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (listModel == null) {
			return null;
		}

		int listIndex = getListPosition(page, getRowCount(), getColumnCount(),
				rowIndex, columnIndex);

		if (listIndex < listModel.getSize()) {
			return listModel.getElementAt(listIndex);
		} else {
			return null;
		}
	}

	public void addListTableModelListener(ListTableModelListener listener) {
		listeners.add(listener);
	}

	public void removeListTableModelListener(ListTableModelListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @return Returns the listModel.
	 */
	public ListModel getListModel() {
		return listModel;
	}

	/**
	 * @param listModel
	 *            The listModel to set.
	 */
	public void setListModel(ListModel listModel) {
		this.listModel = listModel;
		listModel.addListDataListener(new ListTableListDataListener(this));
		fireTableDataChanged();
	}

	/**
	 * �J�[�\�����f�����Z�b�g����B
	 * 
	 * @param selectionHolder
	 */
	public void setListCursorModel(ListCursorModel selectionHolder) {
		this.selectionHolder = selectionHolder;
		selectionHolder
				.addListCursorModelListener(new ListTableListCursorModelListener(
						this));
	}

	/**
	 * �J�[�\�����f�����擾����B
	 * 
	 * @return
	 */
	public ListCursorModel getListCursorModel() {
		return selectionHolder;
	}

	/**
	 * @return Returns the page.
	 */
	public int getPage() {
		return page;
	}

	/**
	 * �y�[�W�����擾����B
	 * 
	 * @return
	 */
	public int getPageCount() {
		if (listModel == null || rowCount == 0 || columnCount == 0) {
			return 0;
		}

		return (listModel.getSize() - 1) / (getRowCount() * getColumnCount())
				+ 1;
	}

	/**
	 * ���X�g���A�w�肳�ꂽ�C���f�b�N�X�̗v�f���X�V���ꂽ�ۂɌĂяo���B
	 * @param index
	 */
	public void itemUpdated(Object item) {
		int i=0;
		boolean found = false;
		for(; i<listModel.getSize(); i++) {
			if(item.equals(listModel.getElementAt(i))) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			return;
		}
		
		itemUpdated(i);
	}
	
	/**
	 * ���X�g���A�w�肳�ꂽ�C���f�b�N�X�̗v�f���X�V���ꂽ�ۂɌĂяo���B
	 * @param index
	 */
	public void itemUpdated(int index) {
		if(!isIndexVisible(index)) {
			return;
		}

		ListTablePosition position = listIndex2ListTablePosition(index, rowCount, columnCount);
		fireTableCellUpdated(position.getRow(), position.getColumn());
	}
	
	/**
	 * �J�[�\���ʒu����y�[�W�ԍ����Čv�Z���A�ύX���������ꍇ�� ���X�i�ɒʒm����B
	 */
	private void recaliculatePage() {
		int newPage;
		if (getRowCount() == 0 || getColumnCount() == 0) {
			newPage = 0;
		} else {
			newPage = selectionHolder.getSelectedIndex()
					/ (getRowCount() * getColumnCount());
		}

		if (page != newPage) {
			page = newPage;
			fireTableDataChanged();
			firePageChanged();
		}
	}

	private void firePageChanged() {
		for (int i = 0; i < listeners.size(); i++) {
			((ListTableModelListener) listeners.get(i)).pageChanged(this);
		}
	}

	/**
	 * �w��̃Z���̃��X�g���̏��Ԃ����߂�B
	 * 
	 * @param page
	 *            �y�[�W�ԍ�
	 * @param rowCount
	 *            ���E��
	 * @param columnCount
	 *            �J������
	 * @param row
	 *            ���E�ԍ�
	 * @param column
	 *            �J�����ԍ�
	 * @return ���X�g���̏���
	 */
	private static int getListPosition(int page, int rowCount, int columnCount,
			int row, int column) {
		return ((columnCount * rowCount) * page) + (column * rowCount) + row;
	}

	/**
	 * ���X�g���̔ԍ�����e�[�u���ł̈ʒu�����߂�B
	 * 
	 * @param listIndex
	 *            ���X�g���̔ԍ�
	 * @param rowCount
	 *            ���E��
	 * @param columnCount
	 *            �J������
	 * @return �ʒu
	 */
	private static ListTablePosition listIndex2ListTablePosition(int listIndex,
			int rowCount, int columnCount) {
		ListTablePosition position = new ListTablePosition();

		position.setPage(listIndex / (columnCount * rowCount));
		position.setRow(listIndex % rowCount);
		position.setColumn((listIndex / rowCount) % columnCount);

		return position;
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�����ݕ\������Ă��邩���肷��B
	 * @param index
	 * @return
	 */
	private boolean isIndexVisible(int index) {
		int start = getListPosition(page, rowCount, columnCount, 0, 0); 
		int end = getListPosition(page, rowCount, columnCount, rowCount - 1, columnCount - 1);
		
		return start <= index && index <= end;
	}
	
	private static class ListTableListDataListener implements ListDataListener {
		ListTableModel tableModel;

		public ListTableListDataListener(ListTableModel tableModel) {
			this.tableModel = tableModel;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
		 */
		public void contentsChanged(ListDataEvent e) {
			tableModel.fireTableDataChanged();
			tableModel.firePageChanged();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
		 */
		public void intervalAdded(ListDataEvent e) {
			tableModel.fireTableDataChanged();
			tableModel.firePageChanged();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
		 */
		public void intervalRemoved(ListDataEvent e) {
			tableModel.fireTableDataChanged();
			tableModel.firePageChanged();
		}
	}

	private static class ListTableListCursorModelListener implements
			ListCursorModelListener {
		ListTableModel tableModel;

		public ListTableListCursorModelListener(ListTableModel tableModel) {
			this.tableModel = tableModel;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.nullfish.lib.ui.list_table.ListCursorModelListener#CursorChanged(com.nullfish.lib.ui.list_table.ListCursorModel,
		 *      int, int)
		 */
		public void cursorChanged(ListCursorModel model) {
			tableModel.recaliculatePage();
		}
	}
}