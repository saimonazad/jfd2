/*
 * Created on 2004/08/26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.command.progress;

import com.nullfish.app.jfd2.command.Command;

/**
 * 経過表示画面のインターフェイス
 * @author shunji
 */
public interface ProgressViewer {
	public void addCommand(Command command);
}
