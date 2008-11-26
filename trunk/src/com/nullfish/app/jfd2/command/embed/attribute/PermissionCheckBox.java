package com.nullfish.app.jfd2.command.embed.attribute;

import com.nullfish.lib.ui.TristateCheckBox;
import com.nullfish.lib.ui.TristateState;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.permission.FileAccess;
import com.nullfish.lib.vfs.permission.PermissionType;

/**
 * TODO: èëÇ´Ç©ÇØ
 * @author shunji
 *
 */
public class PermissionCheckBox extends TristateCheckBox {
//	private PermissionType type;
//	private FileAccess access;
	
	public PermissionCheckBox(PermissionType type, FileAccess access, VFile[] files) throws VFSException {
		super("");
//		this.type = type;
//		this.access = access;
		
		boolean isTrue = false;
		boolean isFalse = true;
		for(int i=0; i<files.length; i++) {
			if(!files[i].getPermission().isEditable(type, access)) {
				setEnabled(false);
			}
			
			if(files[i].getPermission().hasPermission(type, access)) {
				isTrue = true;
			} else {
				isFalse = true;
			}
		}
		
		if(isTrue && isFalse) {
			getTristateModel().setState(TristateState.INDETERMINATE);
		} else if(isTrue) {
			getTristateModel().setState(TristateState.SELECTED);
		} else {
			getTristateModel().setState(TristateState.DESELECTED);
		}
	}
	
	public void apply(VFile file) {
		if(!isEnabled() || getTristateModel().isIndeterminate()) {
			return;
		}
	}
}
