/*
 * Created on 2004/05/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.list_table;

/**
 * ���X�g�e�[�u�����̈ʒu�����߂�B
 * @author shunji
 */
public class ListTablePosition {
	/*
	 * �y�[�W�ԍ�
	 */
	private int page;
	
	/*
	 * ���E�ԍ�
	 */
	private int row;
	
	/*
	 * �J�����ԍ�
	 */
	private int column;
	
	/**
	 * @return Returns the column.
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * @param column The column to set.
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * @return Returns the page.
	 */
	public int getPage() {
		return page;
	}
	
	/**
	 * @param page The page to set.
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * @return Returns the row.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @param row The row to set.
	 */
	public void setRow(int row) {
		this.row = row;
	}
}
