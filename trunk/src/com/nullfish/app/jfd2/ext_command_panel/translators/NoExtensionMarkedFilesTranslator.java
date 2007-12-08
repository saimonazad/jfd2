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
 * マークファイルファイル拡張子無し変換クラス。
 * 
 * @author shunji
 */
public class NoExtensionMarkedFilesTranslator implements CommandTranslator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.ext_command_panel.CommandTranslator#translate(java.lang.String,
	 *      com.nullfish.app.jfd2.JFDView)
	 */
	public String[] translate(String[] original, JFD jfd) {
		VFile[] markedFiles = jfd.getModel().getMarkedFiles();

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < 10 && i < markedFiles.length; i++) {
			String name = markedFiles[i].getName();
			int periodIndex = name.indexOf('.');
			if(periodIndex < 0) {
				name = name.substring(0, periodIndex);
			}
			
			if (name.indexOf(' ') >= 0) {
				buffer.append("\"");
				buffer.append(name);
				buffer.append("\"");
			} else {
				buffer.append(name);
			}

			buffer.append(" ");
		}

		for (int i = 0; i < original.length; i++) {
			original[i] = original[i].replaceAll("\\$XT", buffer.toString());
		}

		return original;
	}
}
