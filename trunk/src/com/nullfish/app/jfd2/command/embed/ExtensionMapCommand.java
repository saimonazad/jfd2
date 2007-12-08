/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import java.io.IOException;
import java.text.MessageFormat;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.config.DefaultConfig;
import com.nullfish.app.jfd2.ext_command.CommandExecuter;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.exception.VFSIOException;
import com.nullfish.lib.vfs.impl.local.LocalFile;

/**
 * ソートコマンド
 * 
 * @author shunji
 */
public class ExtensionMapCommand extends Command {
	public static final String PARAM_MAPPER = "extension_mapper";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();
		try {
			model.lockAutoUpdate(this);
			String fileName = model.getSelectedFile().getSecurePath();
			if(System.getProperty("os.name").indexOf("Windows") != -1 
					&& ShellCommand.isCommandToEscape(fileName)) {
				for(int i=0; i<ShellCommand.ESCAPE_REGEXS.length; i++) {
					fileName = fileName.replaceAll(ShellCommand.ESCAPE_REGEXS[i][0], "^" + ShellCommand.ESCAPE_REGEXS[i][1]);
				}
			}
			
			String mapper = (String)jfd.getCommonConfigulation().getParam(PARAM_MAPPER, DefaultConfig.getDefaultConfig().getExtensionMapping());
			
			String[] values = {
					fileName
			};
			
			MessageFormat format = new MessageFormat(mapper);
			String command = format.format(values);

			VFile currentDir = model.getCurrentDirectory();
			if(currentDir instanceof LocalFile) {
				CommandExecuter.getInstance().exec(command, CommandExecuter.USE_FILE_SHELL, ((LocalFile)currentDir).getFile());
			} else {
				CommandExecuter.getInstance().exec(command, CommandExecuter.USE_FILE_SHELL);
			}
		} catch (IOException e) {
			throw new VFSIOException(e);
		} finally {
			model.unlockAutoUpdate(this);
		}
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}