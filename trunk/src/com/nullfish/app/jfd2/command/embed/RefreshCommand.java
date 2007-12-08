/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �t�@�C���}�[�N�R�}���h
 * 
 * @author shunji
 */
public class RefreshCommand extends Command {
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();
		VFile currentDir = model.getCurrentDirectory();
		VFile selectedFile = model.getSelectedFile();
		int selectedIndex = model.getSelectedIndex();
		
		if(selectedFile.exists(this)) {
			model.setDirectoryAsynchIfNecessary(currentDir, selectedFile, jfd);
		} else {
			model.setDirectoryAsynchIfNecessary(currentDir, selectedIndex, jfd);
		}
    }

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
