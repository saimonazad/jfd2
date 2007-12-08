/*
 * Created on 2004/06/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.comparator;

import com.nullfish.lib.vfs.VFile;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ParentNameComparator implements FileComparator {
	private int order;
	
	public ParentNameComparator(boolean ascend) {
		order = ascend ? 1 : -1;
	}
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.comparator.FileComparator#compare(com.nullfish.lib.vfs.VFile, com.nullfish.lib.vfs.VFile)
	 */
	public int compare(VFile file1, VFile file2) {
		VFile parent1 = file1.getParent();
		VFile parent2 = file2.getParent();
		if(parent1 == null && parent2 == null) {
			return 0;
		}
		
		if(parent1 == null)
		{
			return 1 * order;
		}
		
		if(parent2 == null) {
			return -1 * order;
		}
		
		if(parent1.equals(parent2)) {
			return 0;
		}
		
		return (parent1.getAbsolutePath().toLowerCase().compareTo(parent2.getAbsolutePath().toLowerCase())) * order;
	}
}
