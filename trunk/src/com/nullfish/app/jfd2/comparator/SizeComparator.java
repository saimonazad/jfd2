/*
 * Created on 2004/06/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.comparator;

import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SizeComparator implements FileComparator {
	private int order;
	
	public SizeComparator(boolean ascend) {
		order = ascend ? 1 : -1;
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.comparator.FileComparator#compare(com.nullfish.lib.vfs.VFile, com.nullfish.lib.vfs.VFile)
	 */
	public int compare(VFile file1, VFile file2) {
		try {
			long size1 = file1.getAttribute().getLength();
			long size2 = file2.getAttribute().getLength();
			if(size1 == size2) {
				return 0;
			}

			return (size2 > size1 ? -1 : 1) * order;
		} catch (VFSException e) {
			return 0;
		}
	}
}
