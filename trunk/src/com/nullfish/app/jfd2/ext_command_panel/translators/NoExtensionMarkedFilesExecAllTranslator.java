/*
 * Created on 2005/01/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel.translators;

import java.util.ArrayList;
import java.util.List;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.VFile;

/**
 * マークファイル拡張子無し変換クラス。
 * 
 * @author shunji
 */
public class NoExtensionMarkedFilesExecAllTranslator implements CommandTranslator {
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.CommandTranslator#translate(java.lang.String, com.nullfish.app.jfd2.JFDView)
	 */
	public String[] translate(String[] original, JFD jfd) {
		VFile[] markedFiles = jfd.getModel().getMarkedFiles();
		if(markedFiles.length == 0) {
			for(int i=0; i<original.length; i++) {
				original[i] = original[i].replaceAll("\\$XM", "");
			}
			
			return original;
		}
		
		List list = new ArrayList();
		
		for(int i=0; i<original.length; i++) {
			for(int j=0; j<markedFiles.length; i++) {
				String name = markedFiles[j].getName();
				int periodIndex = name.indexOf(',');
				if(periodIndex >= 0) {
					name = name.substring(0, periodIndex);
				}
				
				list.add(original[i].replaceAll("$XM", name));
			}
		}
		
		return (String[])list.toArray(new String[0]);
	}
}
