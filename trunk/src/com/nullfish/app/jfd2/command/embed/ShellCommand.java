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
 * �\�[�g�R�}���h
 * 
 * @author shunji
 */
public class ShellCommand extends Command {
	public static final String OK = "ok";

	public static final String CANCEL = "cancel";

	public static final String COMMAND = "command";

	public static final String USE_SHELL = "use shell";

	public static final String MODE = "mode";
	
	public static final String MODE_SHELL = "shell";
	
	public static final String MODE_EXECUTE = "execute";
	
	public static final String ESCAPE_CHARS = " &()[]{}^=;!'+,`~";

	public static final String[][] ESCAPE_REGEXS = {
		{"\\^", "^"},
		{" ",   " "},
		{"&",   "&"},
		{"\\(", "("},
		{"\\)", ")"},
		{"\\[",   "["},
		{"\\]",   "]"},
		{"\\{", "{"},
		{"\\}", "}"},
		{"=",   "="},
		{";",   ";"},
		{"!",   "!"},
		{"'",   "'"},
		{"\\+",   "+"},
		{",",   ","},
		{"`",   "`"},
		{"~",   "~"}
	};
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

			dialog = jfd.createDialog();

			//	���b�Z�[�W
			dialog.addMessage(JFDResource.MESSAGES.getString("message_shell"));

			//	�{�^��
			dialog.addButton(OK, JFDResource.LABELS.getString("ok"), 'o', true);
			dialog.addButton(CANCEL, JFDResource.LABELS.getString("cancel"),
					'c', false);

			StringHistory history = (StringHistory) jfd.getLocalConfigulation()
					.getParam("shell_history", null);
			if (history == null) {
				history = new StringHistory(50, true);
				jfd.getLocalConfigulation().setParam("shell_history", history);
			}
			
			List historyList = history.toList();
			if(MODE_EXECUTE.equals(getParameter(MODE))) {
				String fileName = model.getSelectedFile().getAbsolutePath();
				if(System.getProperty("os.name").indexOf("Windows") != -1 
					&& isCommandToEscape(fileName)) {
					fileName = "\"" + fileName + "\"";
				}
				historyList.add(0, fileName);
			}

			dialog.addComboBox(COMMAND, historyList, null, true, true, null);

			dialog.addCheckBox(USE_SHELL, JFDResource.LABELS
					.getString("use_shell"), 's', true, new ConfigulationInfo(
					jfd.getLocalConfigulation(), USE_SHELL), false);

			dialog.pack();
			dialog.setVisible(true);

			String answer = dialog.getButtonAnswer();
			String command = dialog.getTextFieldAnswer(COMMAND);
			
			if (answer == null || CANCEL.equals(answer)) {
				return;
			}
			
			File directory = null;
			if (currentDir instanceof LocalFile) {
				directory = ((LocalFile) currentDir).getFile();
			}

			if(command == null || command.length() == 0) {
				command = (String)jfd.getCommonConfigulation().getParam("open_console_command", null);
				if(command == null | command.length() == 0) {
					return;
				}
				
				CommandExecuter.getInstance().exec(command,
						dialog.isChecked(USE_SHELL) ? CommandExecuter.USE_FILE_SHELL
										: CommandExecuter.SHELL_NONE, directory);
				dialog.applyConfig();
				return;
			}
			
			history.add(command);
			VFile currentDir = jfd.getModel().getCurrentDirectory();

			dialog.applyConfig();
			CommandExecuter.getInstance().exec(command,
					dialog.isChecked(USE_SHELL) ? CommandExecuter.USE_FILE_SHELL : CommandExecuter.SHELL_NONE, directory);
		} catch (IOException e) {
			throw new VFSIOException(e);
		} finally {
			model.unlockAutoUpdate(this);

			if (dialog != null) {
				dialog.dispose();
			}
		}
	}
	
	public static boolean isCommandToEscape(String command) {
		for(int i=0; i<ESCAPE_CHARS.length(); i++) {
			if(command.indexOf(ESCAPE_CHARS.charAt(i)) >= 0) { 
				return true;
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}