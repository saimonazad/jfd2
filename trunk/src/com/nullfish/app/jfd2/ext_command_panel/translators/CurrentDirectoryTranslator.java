/*
 * Created on 2005/01/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel.translators;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.util.WindowsUtil;
import com.nullfish.lib.vfs.VFile;

/**
 * カレントディレクトリ変換クラス。
 * 
 * @author shunji
 */
public class CurrentDirectoryTranslator implements CommandTranslator {
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.CommandTranslator#translate(java.lang.String, com.nullfish.app.jfd2.JFDView)
	 */
	public String[] translate(String[] original, JFD jfd) {
		VFile currentDir = jfd.getModel().getCurrentDirectory();
		if(currentDir == null) {
			return original;
		}
		
		for(int i=0; i<original.length; i++) {
			original[i] = original[i].replaceAll("\\$P", WindowsUtil.escapeFileName(currentDir.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\")));
		}
		return original;
	}
}
