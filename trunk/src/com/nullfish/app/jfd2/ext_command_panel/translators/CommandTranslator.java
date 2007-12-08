/*
 * Created on 2005/01/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel.translators;

import com.nullfish.app.jfd2.JFD;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface CommandTranslator {
	/**
	 * •ÏŠ·‚·‚éB
	 * 
	 * @param original	Œ³•¶Í
	 * @param jfd	jFD2
	 * @return	•ÏŠ·Ï‚İ–½—ß
	 */
	public String[] translate(String[] original, JFD jfd);
}
