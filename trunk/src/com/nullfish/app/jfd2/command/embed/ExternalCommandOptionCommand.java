/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.command.JFDException;
import com.nullfish.app.jfd2.config.ext_command.ExternalCommandConfigFrame;
import com.nullfish.app.jfd2.ui.container2.NumberedJFD2;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �I�v�V�����\���R�}���h
 * 
 * @author shunji
 */
public class ExternalCommandOptionCommand extends Command {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		NumberedJFD2 jfd = (NumberedJFD2)getJFD();
		VFile configDir = jfd.getJFDOwner().getConfigDirectory();
		try {
			ExternalCommandConfigFrame frame = new ExternalCommandConfigFrame(jfd);
			frame.setModal(true);
			frame.load(configDir);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (VFSException e) {
			throw e;
		} catch (Exception e) {
			throw new JFDException(e, "", new Object[0]);
		}
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
