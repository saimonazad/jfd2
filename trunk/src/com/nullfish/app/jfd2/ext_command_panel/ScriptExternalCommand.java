/*
 * Created on 2005/01/08
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel;

import org.jdom.Element;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.command.groovy.GroovyStringCommand;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �V�F���R�}���h
 * 
 * @author shunji
 */
public class ScriptExternalCommand {
	/**
	 * ���s�X�N���v�g
	 */
	private String script;
	
	/**
	 * �m�[�h��
	 */
	public static final String NODE_NAME = "script";
	
	/**
	 * �R���X�g���N�^
	 *
	 */
	public ScriptExternalCommand() {
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.ExternalCommand#execute()
	 */
	public void execute(JFD jfd) throws VFSException {
		if(script == null || script.length() == 0) {
			return;
		}
		
		GroovyStringCommand command = new GroovyStringCommand(script);
		command.setJFD(jfd);
		command.startAsync();
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.ExternalCommand#init(org.jdom.Element)
	 */
	public void init(Element node) {
		if(node == null) {
			script = "";
		}
		script = node.getText();
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ext_command_panel.ExternalCommand#toNode()
	 */
	public Element toNode() {
		Element rtn = new Element(NODE_NAME);
		rtn.setText(script);
		return rtn;
	}
}
