/*
 * Created on 2004/05/12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.xml_menu;

import javax.swing.JMenuItem;

import org.jdom.Element;

import com.nullfish.lib.command.CommandCallable;
import com.nullfish.lib.resource.ResourceManager;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class XMLMenuItem extends JMenuItem implements XMLMenuElement {
	private ResourceManager resource;
	
	private CommandCallable callable;
	
	/**
	 * �m�[�h����
	 */
	public static final String NODE_NAME = "menuitem";
	
	/**
	 * �R�}���h����
	 */
	public static final String ATTR_COMMAND = "command";
	
	/**
	 * �\�������񑮐�
	 */
	public static final String ATTR_TEXT = "text";
	
	/**
	 * �\��������́A���\�[�X���ł̃L�[���̑���
	 */
	public static final String ATTR_LABEL = "label";
	
	/**
	 * �j�[���j�b�N�L�[���̑���
	 */
	public static final String ATTR_MNEMONIC = "mnemonic";
	
	/**
	 * �f�t�H���g�R���X�g���N�^
	 *
	 *
	 */
	public XMLMenuItem() {
		super();
	}
	
	/**
	 * XML�v�f���珉��������B
	 * @param element
	 */
	public void convertFromNode(Element element) {
		if(!NODE_NAME.equals(element.getName())) {
			// TODO:��O����
		}

		XMLMenuUtility.initMenuItemFromNode(this, element, getResource(), callable);
	}
	
	/**
	 * @return Returns the resource.
	 */
	public ResourceManager getResource() {
		return resource;
	}
	/**
	 * @param resource The resource to set.
	 */
	public void setResource(ResourceManager resource) {
		this.resource = resource;
	}
	/**
	 * @return Returns the callable.
	 */
	public CommandCallable getCallable() {
		return callable;
	}
	/**
	 * @param callable The callable to set.
	 */
	public void setCallable(CommandCallable callable) {
		this.callable = callable;
	}
}
