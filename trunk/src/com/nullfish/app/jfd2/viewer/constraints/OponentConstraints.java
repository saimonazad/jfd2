/*
 * Created on 2004/12/29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.viewer.constraints;

import com.nullfish.app.jfd2.JFDComponent;
import com.nullfish.app.jfd2.ui.container2.ContainerPosition;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.app.jfd2.viewer.FileViewerConstraints;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OponentConstraints extends FileViewerConstraints {
	public static final String NAME = "oponent";
	
	/**
	 * @param name
	 */
	public OponentConstraints() {
		super(NAME);
	}

	
	/**
	 * 
	 * @param jfd
	 * @return
	 */
	public ContainerPosition getPosition(JFDComponent component) {
		JFDOwner owner = component.getJFDOwner();
		if(owner == null) {
			return null;
		}
		
		ContainerPosition position = owner.getComponentPosition(component);
		return position.getOpenent();
	}
}
