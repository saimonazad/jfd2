/*
 * Created on 2005/01/20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2;

import java.awt.Component;

import com.nullfish.app.jfd2.ui.container2.JFDOwner;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JFDComponent {
	public void setOwner(JFDOwner owner);
	
	public JFDOwner getJFDOwner();
	
	public Component getComponent();

	public void dispose();
}
