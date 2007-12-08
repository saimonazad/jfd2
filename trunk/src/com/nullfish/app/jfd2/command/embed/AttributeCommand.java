/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.lib.vfs.Permission;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * ëÆê´ê›íËÉRÉ}ÉìÉh
 * 
 * @author shunji
 */
public class AttributeCommand extends Command {
	public static final String PERMISISON = "permission";
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		VFile currentDir = jfd.getModel().getCurrentDirectory();

		JFDDialog dialog = null;
		
		try {
			VFile[] files = jfd.getModel().getMarkedFiles();
			
			if(files == null || files.length == 0) {
				files = new VFile[1];
				files[0] = jfd.getModel().getSelectedFile();
				if(files[0].equals(currentDir) || files[0].equals(currentDir.getParent())) {
					return;
				}
			}
			
			dialog = DialogUtilities.createOkCancelDialog(jfd, "jFD2");
			dialog.addTextField(PERMISISON, files[0].getPermission(this).getPermissionString(), true);
			dialog.pack();
			dialog.setVisible(true);
			
			String buttonAnswer = dialog.getButtonAnswer();
			if(buttonAnswer == null || buttonAnswer.equals("")) {
				return;
			}
			
			String permissionStr = dialog.getTextFieldAnswer(PERMISISON);
			for(int i=0; i<files.length; i++) {
				Permission perm = files[i].getPermission(this);
				perm.initFromString(permissionStr);
				files[i].setPermission(perm, this);
			}
		} catch (VFSException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
