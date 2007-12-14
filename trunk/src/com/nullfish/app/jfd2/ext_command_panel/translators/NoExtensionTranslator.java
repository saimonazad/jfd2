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
 * 選択中ファイルの拡張子無し変換クラス。
 * 
 * @author shunji
 */
public class NoExtensionTranslator implements CommandTranslator {
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.CommandTranslator#translate(java.lang.String, com.nullfish.app.jfd2.JFDView)
	 */
	public String[] translate(String[] original, JFD jfd) {
		VFile selectedFile = jfd.getModel().getSelectedFile();
		if(selectedFile == null) {
			return original;
		}
		
		String fileName = WindowsUtil.escapeFileName(selectedFile.getName());
		int periodIndex = fileName.lastIndexOf(".");
		
		for(int i=0; i<original.length; i++) {
			if(periodIndex >= 0) {
				original[i] = original[i].replaceAll("\\$X", fileName.substring(0, periodIndex));
			} else {
				original[i] = original[i].replaceAll("\\$X", fileName);
			}
		}
		
		return original;
	}
}
