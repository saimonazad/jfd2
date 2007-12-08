/*
 * Created on 2005/01/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel.translators;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.VFile;

/**
 * 選択中ファイル変換クラス。
 * 
 * @author shunji
 */
public class SelectedFileTranslator implements CommandTranslator {
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.CommandTranslator#translate(java.lang.String, com.nullfish.app.jfd2.JFDView)
	 */
	public String[] translate(String[] original, JFD jfd) {
		VFile selectedFile = jfd.getModel().getSelectedFile();
		if(selectedFile == null) {
			return original;
		}
		
		String path = selectedFile.getName();
		if(path.indexOf(" ") >= 0) {
			path = "\"" + path + "\"";
		}

		for(int i=0; i<original.length; i++) {
			original[i] = original[i].replaceAll("\\$C", path);
		}
		return original;
	}
}
