/*
 * Created on 2005/01/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel;

import org.jdom.Element;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �O���R�}���h�C���^�[�t�F�C�X
 * @author shunji
 */
public class ExternalCommand {
	private String label = "";
	
	private String title = "";
	
	private String mode = MODE_SHELL;
	
	private ShellExternalCommand shellCommand = new ShellExternalCommand();
	private ScriptExternalCommand scriptCommand = new ScriptExternalCommand();
	private ScriptFileExternalCommand fileCommand = new ScriptFileExternalCommand();
	
	public static final String MODE_SHELL = "shell";
	
	public static final String MODE_SCRIPT = "script";
	
	public static final String MODE_SCRIPT_FILE = "script-file";
	
	/**
	 * �^�O����
	 */
	public static final String NODE_NAME = "extcommand";

	/**
	 * �^�C�g������
	 */
	public static final String ATTR_TITLE = "title";

	/**
	 * ���[�h����
	 */
	public static final String ATTR_MODE = "mode";

	/**
	 * �^�C�g�����擾����B
	 * 
	 * @return �^�C�g��
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * �\�����x�����擾����B
	 *
	 * @return �^�C�g��
	 */
	public String getLabel() {
		return label;
	}
	
	
	/**
	 * ���s����B
	 *
	 */
	public void execute(JFD jfd) throws VFSException {
		if(MODE_SHELL.equals(mode)) {
			shellCommand.execute(jfd);
		} else if(MODE_SCRIPT.equals(mode)) {
			scriptCommand.execute(jfd);
		} else if(MODE_SCRIPT_FILE.equals(mode)) {
			fileCommand.execute(jfd);
		}
	}
	
	/**
	 * ����������B
	 * @param node
	 */
	public void init(Element node) {
		title = node.getAttributeValue(ATTR_TITLE);
		mode = node.getAttributeValue(ATTR_MODE);
		
		shellCommand.init(node.getChild(ShellExternalCommand.NODE_NAME));
		scriptCommand.init(node.getChild(ScriptExternalCommand.NODE_NAME));
		fileCommand.init(node.getChild(ScriptFileExternalCommand.NODE_NAME));
	}
	
	/**
	 * XML�m�[�h�ɕϊ�����B
	 * @return
	 */
	public Element toNode() {
		Element rtn = new Element(NODE_NAME);
		rtn.setAttribute(ATTR_TITLE, title);
		rtn.setAttribute(ATTR_MODE, mode);
		
		rtn.addContent(shellCommand.toNode());
		rtn.addContent(scriptCommand.toNode());
		rtn.addContent(fileCommand.toNode());
		
		return rtn;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public ScriptExternalCommand getScriptCommand() {
		return scriptCommand;
	}
	public ScriptFileExternalCommand getScriptFileCommand() {
		return fileCommand;
	}
	public ShellExternalCommand getShellCommand() {
		return shellCommand;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
