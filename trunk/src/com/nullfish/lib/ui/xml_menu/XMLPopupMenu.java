/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.xml_menu;

import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.jdom.Element;

import com.nullfish.lib.command.CommandCallable;
import com.nullfish.lib.resource.ResourceManager;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class XMLPopupMenu extends JPopupMenu implements XMLMenuElement {
	private ResourceManager resource;
	
	private CommandCallable callable;
	
	public XMLPopupMenu() {
		
	}
	
	public XMLPopupMenu(ResourceManager resource, CommandCallable callable) {
		this.resource = resource;
		this.callable = callable;
	}
	
	/* (non-Javadoc)
	 * @see org.sexyprogrammer.lib.ui.xml_menu.XMLMenuElement#convertFromNode(org.jdom.Element)
	 */
	public void convertFromNode(Element node) {
		removeAll();
		
		List children = node.getChildren();
		for(int i=0; i<children.size(); i++) {
			Element child = (Element)children.get(i);
			if(XMLMenu.NODE_SEPARATOR.equals(child.getName())) {
				addSeparator();
			} else {
				XMLMenuElement item = null;
				if(XMLMenu.NODE_NAME.equals(child.getName())) {
					item = new XMLMenu();
				} else if(XMLMenuItem.NODE_NAME.equals(child.getName())) {
					item = new XMLMenuItem();
				}
				
				if(item != null) {
					item.setCallable(callable);
					item.setResource(resource);
					item.convertFromNode(child);
					add((JMenuItem) item);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.sexyprogrammer.lib.ui.xml_menu.XMLMenuElement#setResource(org.sexyprogrammer.lib.resource.ResourceManager)
	 */
	public void setResource(ResourceManager resource) {
		this.resource = resource;
	}
	
	/* (non-Javadoc)
	 * @see org.sexyprogrammer.lib.ui.xml_menu.XMLMenuElement#setCallable(org.sexyprogrammer.lib.command.CommandCallable)
	 */
	public void setCallable(CommandCallable callable) {
		this.callable = callable;
	}
}
