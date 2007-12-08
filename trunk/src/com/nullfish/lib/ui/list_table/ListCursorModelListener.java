/*
 * Created on 2004/05/23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.list_table;

/**
 * @author shunji
 */
public interface ListCursorModelListener {
	/**
	 * 選択状態が変更された際に呼び出される。
	 * 
	 * @param model
	 * @param oldIndex
	 * @param newIndex
	 */
	public void cursorChanged(ListCursorModel model);
}