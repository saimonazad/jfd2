/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.dialog.ConfigulationInfo;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.ext_command.CommandExecuter;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.util.StringHistory;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.exception.VFSIOException;
import com.nullfish.lib.vfs.impl.local.LocalFile;

/**
 * ソートコマンド
 * 
 * @author shunji
 */
public class RemoteShellCommand extends Command {
	public static final String COMMAND = "command";

	public static final String USE_SHELL = "use shell";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFDDialog dialog = null;

		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();
		try {
			model.lockAutoUpdate(this);

			showProgress();
			
			VFile selectedFile = model.getSelectedFile();
			TemporaryFilePutBackCommand putBackCommand = new TemporaryFilePutBackCommand(
					selectedFile);

			putBackCommand.setParentManipulation(this);
			putBackCommand.setJFD(jfd);

			putBackCommand.prepare();
			VFile tempFile = putBackCommand.getTemporaryFile();

			dialog = jfd.createDialog();

			//	メッセージ
			dialog.addMessage(JFDResource.MESSAGES.getString("message_shell"));

			//	ボタン
			dialog.addButton(JFDDialog.OK, JFDResource.LABELS.getString("ok"), 'o', true);
			dialog.addButton(JFDDialog.CANCEL, JFDResource.LABELS.getString("cancel"),
					'c', false);

			StringHistory history = (StringHistory) jfd.getLocalConfigulation()
					.getParam("shell_history", null);
			if (history == null) {
				history = new StringHistory(50, true);
				jfd.getLocalConfigulation().setParam("shell_history", history);
			}
			
			List historyList = history.toList();
			historyList.add(0, tempFile.getName());

			dialog.addComboBox(COMMAND, historyList, null, true, true, null);

			dialog.addCheckBox(USE_SHELL, JFDResource.LABELS
					.getString("use_shell"), 's', true, new ConfigulationInfo(
					jfd.getLocalConfigulation(), USE_SHELL), false);

			dialog.pack();
			dialog.setVisible(true);

			String answer = dialog.getButtonAnswer();
			String command = dialog.getTextFieldAnswer(COMMAND);
			if (answer == null || JFDDialog.CANCEL.equals(answer) || command == null
					|| command.length() == 0) {
				return;
			}

			history.add(command);
			VFile currentDir = jfd.getModel().getCurrentDirectory();

			File directory = ((LocalFile)tempFile.getParent()).getFile();

			dialog.applyConfig();
			Process process = CommandExecuter.getInstance().exec(command,
					dialog.isChecked(USE_SHELL)? CommandExecuter.USE_FILE_SHELL : CommandExecuter.SHELL_NONE, directory);
			
			putBackCommand.setProcess(process);
			
			putBackCommand.execute();
		} catch (IOException e) {
			throw new VFSIOException(e);
		} finally {
			model.unlockAutoUpdate(this);

			if (dialog != null) {
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