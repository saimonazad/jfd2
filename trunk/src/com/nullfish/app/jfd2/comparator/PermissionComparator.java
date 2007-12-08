/*
 * Created on 2004/06/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.comparator;

import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.permission.ClassFileAccess;
import com.nullfish.lib.vfs.permission.PermissionType;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PermissionComparator implements FileComparator {

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.comparator.FileComparator#compare(com.nullfish.lib.vfs.VFile, com.nullfish.lib.vfs.VFile)
	 */
	public int compare(VFile file1, VFile file2) {
		try {
			boolean writable1 = file1.getPermission().hasPermission(PermissionType.WRITABLE, ClassFileAccess.ALL);
			boolean writable2 = file2.getPermission().hasPermission(PermissionType.WRITABLE, ClassFileAccess.ALL);
			if(writable1 == writable2) {
				return 0;
			}
			
			if(!writable1) {
				return -1;
			} else {
				return 1;
			}
		} catch (VFSException e) {
			return 0;
		}
	}
}
