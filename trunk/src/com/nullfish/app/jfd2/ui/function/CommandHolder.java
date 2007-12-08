/*
 * Created on 2004/06/15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.function;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommandHolder {
	private String command;
	
	private String labelText;
	
	public CommandHolder(String command, String labelText) {
		this.command = command;
		this.labelText = labelText;
	}
	
	/**
	 * @return Returns the command.
	 */
	public String getCommand() {
		return command;
	}
	/**
	 * @return Returns the labelText.
	 */
	public String getLabelText() {
		return labelText;
	}
}
