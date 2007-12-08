/*
 * Created on 2004/08/26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.container2;

import java.awt.Point;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTabbedPane;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDComponent;
import com.nullfish.app.jfd2.dnd.AbstractJFDDropTargetListener;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TabbedPaneDropTargetListener extends AbstractJFDDropTargetListener {
	private JTabbedPane pane;
	
	public TabbedPaneDropTargetListener(JTabbedPane pane) {
		this.pane = pane;
	}
	
	public JFD getJFD(DropTargetDropEvent dtde) {
		Point point = dtde.getLocation();
		int index = pane.indexAtLocation(point.x, point.y);
		if(index < 0) {
			return null;
		}
		
		JFDComponent c = ((JFDContainer)pane.getComponentAt(index)).getComponent();
		return c instanceof JFD ? (JFD)c : null;
	}
}
