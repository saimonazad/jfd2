/*
 * Created on 2004/09/03
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog.components;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.dialog.JFDDialog;

/**
 * @author shunji
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FileComboBox extends DialogComboBox  {
	public FileComboBox(JFDDialog dialog, JFD jfd) {
		super(new CompletionComboBoxEditor(jfd), dialog);
	}
}