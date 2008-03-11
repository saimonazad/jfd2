package com.nullfish.app.jfd2.command.embed.attribute;

import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;

import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

public class AttributeDialog extends JDialog {
	private PermissionPanel permissionPanel;
	
	public AttributeDialog(Frame owner) {
		super(owner, true);
	}
	
	public AttributeDialog(Dialog owner) {
		super(owner, true);
	}
	
	public void init(VFile[] files) throws VFSException {
		permissionPanel = new PermissionPanel(files);
		this.add(permissionPanel);
	}
}
