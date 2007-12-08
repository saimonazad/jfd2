/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.xml_menu;

import org.jdom.Element;

import com.nullfish.lib.command.CommandCallable;
import com.nullfish.lib.resource.ResourceManager;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface XMLMenuElement {
	public void convertFromNode(Element node);
	
	public void setResource(ResourceManager resource);
	
	public void setCallable(CommandCallable callable);
}
