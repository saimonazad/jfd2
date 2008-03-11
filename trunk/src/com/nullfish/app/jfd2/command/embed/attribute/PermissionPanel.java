package com.nullfish.app.jfd2.command.embed.attribute;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nullfish.lib.vfs.Permission;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.permission.FileAccess;
import com.nullfish.lib.vfs.permission.PermissionType;

public class PermissionPanel extends JPanel {
	private VFile[] files;
	
	private List typesList = new ArrayList();
	private List accessesList = new ArrayList();
	
	private List typeLabels = new ArrayList();
	private List accessLabels = new ArrayList();

	private List checkBoxs = new ArrayList();
	
	public PermissionPanel(VFile[] files) throws VFSException {
		super(new GridBagLayout());
		this.files = files;
		
		for(int i=0; i<files.length; i++) {
			Permission permission = files[i].getPermission();
			FileAccess[] accesses = permission.getAccess();
			PermissionType[] types = permission.getTypes();
			
			for(int j=0; j<accesses.length; j++) {
				if(!accessesList.contains(accesses[j])) {
					accessesList.add(accesses[j]);
				}
			}
			
			for(int j=0; j<types.length; j++) {
				if(!typesList.contains(types[j])) {
					typesList.add(types[j]);
				}
			}
		}
		
		for(int i=0; i<accessesList.size(); i++) {
			add(new JLabel(((FileAccess)accessesList.get(i)).getName()), new GridBagConstraints(i+1, 0,  1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		
		for(int i=0; i<typesList.size(); i++) {
			add(new JLabel(((PermissionType)typesList.get(i)).getName()), new GridBagConstraints(0, i + 1,  1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			for(int j=0; j<accessesList.size(); j++) {
				PermissionCheckBox checkBox = new PermissionCheckBox((PermissionType)typesList.get(i), (FileAccess)accessesList.get(j), files);
				add(checkBox, new GridBagConstraints(j+1, i+1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				checkBoxs.add(checkBox);
			}
		}
	}
	
	private class TypeAccess {
		private PermissionType type;
		private FileAccess access;
		
		TypeAccess(PermissionType type, FileAccess access) {
			this.type = type;
			this.access = access;
		}
		
		public boolean equals(Object o) {
			if(o == null) {
				return false;
			}
			
			TypeAccess other = (TypeAccess)o;
			return type.equals(other.type) && access.equals(other.access);
		}
		
		public int hashCode() {
			return type.hashCode() + access.hashCode();
		}
	}
	
}
