/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFD2;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.ui.table.RendererMode;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * カラム数設定コマンド
 * 
 * @author shunji
 */
public class SetColumnCountCommand extends Command {
	/**
	 * カラム数を表すパラメータ名
	 */
	public static final String CONFIG_COLUMN_COUNT = "count";
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		int columnCount = ((Integer)getParameter(CONFIG_COLUMN_COUNT)).intValue();
		int currentCount = getJFD().getColumnCount();
		if(columnCount != currentCount) {
			jfd.setColumnCount(columnCount);
		} else {
			RendererMode currentMode = (RendererMode)jfd.getLocalConfigulation().getParam(JFD2.CONFIG_RENDERER_MODE, RendererMode.TYPE_1);
				
			RendererMode nextMode = RendererMode.getNextMode(currentMode);
			jfd.setRendererMode(nextMode);
			jfd.getLocalConfigulation().setParam(JFD2.CONFIG_RENDERER_MODE, nextMode);
		}
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
