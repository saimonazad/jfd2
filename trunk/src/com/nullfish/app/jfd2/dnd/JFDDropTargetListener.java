/*
 * Created on 2004/08/26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dnd;

import java.awt.dnd.DropTargetDropEvent;

import com.nullfish.app.jfd2.JFD;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JFDDropTargetListener extends AbstractJFDDropTargetListener {
	private JFD jfd;
	
	public JFDDropTargetListener(JFD jfd) {
		this.jfd = jfd;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.dnd.AbstractJFDDropTargetListener#getJFD()
	 */
	public JFD getJFD(DropTargetDropEvent dtde) {
		return jfd;
	}

	
}
