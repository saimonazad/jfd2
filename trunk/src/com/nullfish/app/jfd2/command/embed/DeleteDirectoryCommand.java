/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import java.text.MessageFormat;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.vfs.FileSystem;
import com.nullfish.lib.vfs.Manipulation;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.impl.filelist.FileListFileSystem;

/**
 * �f�B���N�g���폜�R�}���h
 * 
 * @author shunji
 */
public class DeleteDirectoryCommand extends Command {
	public static final String OK = "ok";

	public static final String CANCEL = "cancel";

	private Manipulation manipulation;
	
	JFD jfd;
	JFDModel model;
	VFile selectedFile;
	int selectedIndex;
	VFile[] markedFiles;

	public void doExecute() throws VFSException {
		JFDDialog dialog = null;
		
		jfd = getJFD();
		model = jfd.getModel();
		try {
			model.lockAutoUpdate(this);
			
			selectedFile = model.getSelectedFile();
			selectedIndex = model.getSelectedIndex();
			markedFiles = model.getMarkedFiles();
			
			if(markedFiles.length != 0) {
				return;
			}
			
			if(selectedFile.equals(model.getCurrentDirectory())
					|| selectedFile.equals(model.getCurrentDirectory().getParent())) {
				return;
			}
			
			if(selectedFile.isFile(this)) {
				return;
			}
			
			FileSystem fileSystem = model.getCurrentDirectory().getFileSystem();
			if(fileSystem instanceof FileListFileSystem) {
				removeFileList((FileListFileSystem)fileSystem, selectedFile);
				return;	
			}
			
			dialog = jfd.createDialog();
	
			Object[] params= {
				selectedFile.getName()
			};
			dialog.addMessage(MessageFormat.format(JFDResource.MESSAGES.getString("message_delete_file"), params));
	
			//	�{�^��
			dialog.addButton(OK, JFDResource.LABELS.getString("ok"), 'y', true);
			dialog.addButton(CANCEL, JFDResource.LABELS.getString("cancel"), 'n',
					false);
	
			dialog.pack();
			dialog.setVisible(true);
	
			String answer = dialog.getButtonAnswer();
			if (answer == null || CANCEL.equals(answer)) {
				return;
			}
			
			showProgress(1000);

			manipulation = selectedFile.getManipulationFactory().getDeleteManipulation(selectedFile);
			manipulation.prepare();
			
			Manipulation[] child = {
					manipulation
			};
			
			setChildManipulations(child);
			
			manipulation.execute();
			model.setDirectory(model.getCurrentDirectory(), selectedIndex);
		} finally {
			model.unlockAutoUpdate(this);
			if(dialog != null) {
				dialog.dispose();
			}
		}
	}

	/**
	 * �t�@�C�����X�g����t�@�C�����폜����B
	 * @param fileSystem
	 * @param selectedFile
	 * @param markedFiles
	 * @throws VFSException
	 */
	private void removeFileList(FileListFileSystem fileSystem, VFile dir) throws VFSException {
		JFDDialog dialog = null;
		try {
			dialog = getJFD().createDialog();
			
			//	���b�Z�[�W
			dialog.addMessage(JFDResource.MESSAGES.getString("message_delete_filelist_file"));
	
			//	�{�^��
			dialog.addButton(OK, JFDResource.LABELS.getString("ok"), 'y', true);
			dialog.addButton(CANCEL, JFDResource.LABELS.getString("cancel"), 'n',
					false);
	
			dialog.pack();
			dialog.setVisible(true);
	
			String answer = dialog.getButtonAnswer();
			if (answer == null || CANCEL.equals(answer)) {
				return;
			}

			if(markedFiles == null || markedFiles.length == 0) {
				markedFiles = new VFile[1];
				markedFiles[0] = selectedFile;
			}
			
			fileSystem.initFileTree(this);
			fileSystem.removeFile(dir);
			
			fileSystem.save();
			getJFD().getModel().refresh();
		} finally {
			if(dialog != null) {
				dialog.dispose();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}