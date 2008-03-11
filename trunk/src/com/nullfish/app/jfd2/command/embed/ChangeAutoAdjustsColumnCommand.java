/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.JFD2;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �J�������̎��������؂�ւ��R�}���h�B
 * JFD2�N���X��p
 * 
 * @author shunji
 */
public class ChangeAutoAdjustsColumnCommand extends Command {
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD2 jfd = (JFD2)getJFD();

		int columnWidth = ((Integer)jfd.getCommonConfigulation().getParam("min_column_width", Integer.valueOf(300))).intValue();
		jfd.setAdjustsColumnCountAuto(!jfd.isAdjustsColumnCountAuto(), columnWidth);
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
