/*
 * Created on 2004/08/12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog.components;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CloseAction extends AbstractAction {
	private Dialog dialog;
	
	public static final String NAME = "close";
	
	public CloseAction(Dialog dialog) {
		this.dialog = dialog;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
	}
}
