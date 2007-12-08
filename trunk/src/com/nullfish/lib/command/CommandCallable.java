/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.command;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface CommandCallable {
	/**
	 * 指定された名称のコマンドを呼び出す。
	 * @param command	コマンド名称
	 */ 
	public void callCommand(String command);
	 
}
