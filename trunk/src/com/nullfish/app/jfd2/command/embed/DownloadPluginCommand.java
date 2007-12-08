/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �t�@�C���}�[�N�R�}���h
 * 
 * @author shunji
 */
public class DownloadPluginCommand extends Command {
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFDDialog dialog = null;
		try {
			String pluginDirPath = (String)getJFD().getCommonConfigulation().getParam("plugin_dir", null);
			VFile pluginDir = VFS.getInstance(getJFD()).getFile(pluginDirPath);
			
			dialog = DialogUtilities.createOkCancelDialog(getJFD(), JFDResource.LABELS.getString("import_plugin"));
			
			String[] message = {
					JFDResource.MESSAGES.getString("input_plugin_path")
			};
			dialog.setMessage(message);
			dialog.addTextField("path", "", true);
			
			dialog.pack();
			dialog.setVisible(true);
			
			String buttonAnswer = dialog.getButtonAnswer();
			//if(buttonAnswer == null || JFDDialog buttonAnswer)
		} finally {
			dialog.dispose();
		}
    }

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
