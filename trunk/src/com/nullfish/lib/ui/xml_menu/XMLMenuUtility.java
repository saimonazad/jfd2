/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.xml_menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

import org.jdom.Element;

import com.nullfish.lib.command.CommandCallable;
import com.nullfish.lib.resource.ResourceManager;
import com.nullfish.lib.ui.KeyStrokeUtility;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class XMLMenuUtility {
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
	
	public static void initMenuItemFromNode(
			JMenuItem item, 
			Element node, 
			ResourceManager resource, 
			CommandCallable callable) {
		//	�R�}���h
		String command = node.getAttributeValue(ATTR_COMMAND);
		if(command != null && command.length() > 0) {
			item.setAction(new CallCommandAction(callable, command));
		}
			
		//	�e�L�X�g
		String text = node.getAttributeValue(ATTR_TEXT);
		String label = node.getAttributeValue(ATTR_LABEL);
		if(label != null && label.length() > 0 && resource != null) {
			text = resource.getResource(label);
		}
		
		if(text != null) {
			item.setText(text);
		}
		
		//	�A�N�Z�����[�^�L�[
		Element keyNode = node.getChild(KeyStrokeUtility.KEY_NODE);
		if(keyNode != null) {
			item.setAccelerator(KeyStrokeUtility.node2KeyStroke(keyNode));
		}
		
		//	�j�[���j�b�N
		String mnemonicStr = node.getAttributeValue(ATTR_MNEMONIC);
		if(mnemonicStr != null && mnemonicStr.length() > 0) {
			item.setMnemonic(mnemonicStr.charAt(0));
		}
	}
	
	
	private static class CallCommandAction extends AbstractAction {
		CommandCallable callable;
		
		String command;
		
		public CallCommandAction(CommandCallable callable, String command) {
			this.callable = callable;
			this.command = command;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(callable != null && command != null && command.length() > 0) {
				callable.callCommand(command);
			}
		}
	}
}
