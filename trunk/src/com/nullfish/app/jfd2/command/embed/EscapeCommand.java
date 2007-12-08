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
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.app.jfd2.viewer.FileViewer;
import com.nullfish.app.jfd2.viewer.FileViewerManager;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * エスケープキーに対応した雑多なコマンド
 * 
 * @author shunji
 */
public class EscapeCommand extends Command {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();
		
		FileViewer viewer = FileViewerManager.getInstance().getTopViewer(jfd);
		if(viewer != null) {
			viewer.close();
			return;
		}

		Command primaryCommand = jfd.getPrimaryCommand();
		if (primaryCommand != null) {
			primaryCommand.stop();
			jfd.setPrimaryCommand(null);
			return;
		}

		JFDOwner owner = jfd.getJFDOwner();
		if (owner != null && tryExit()) {
			owner.removeComponent(jfd);

			jfd.dispose();

			if (owner.getCount() == 0) {
				owner.dispose();
			}
		}
	}

	private boolean tryExit() {
		JFD jfd = getJFD();
		JFDDialog dialog = null;
		try {
			dialog = DialogUtilities.createYesNoDialog(jfd, JFDDialog.YES);
			dialog.addMessage(JFDResource.MESSAGES.getString("exits_jfd"));
			dialog.pack();
			dialog.setVisible(true);

			return JFDDialog.YES.equals(dialog.getButtonAnswer());
		} finally {
			if (dialog != null) {
				dialog.dispose();
			}
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
