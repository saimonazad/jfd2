/*
 * Created on 2004/08/31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.labels;

import javax.swing.JLabel;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ToolTipLabel extends JLabel {
	public void setText(String text) {
		setToolTipText(text);
		super.setText(text);
	}
/*	
	public Dimension getMinimumSize() {
		Dimension rtn = super.getMinimumSize();
		rtn.width = 1;
		return rtn;
	}
*/
}
