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
public class ExtensionComparator implements FileComparator {
	private int order; 
	public ExtensionComparator(boolean ascend) {
		order = ascend ? 1 : -1;
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.comparator.FileComparator#compare(com.nullfish.lib.vfs.VFile, com.nullfish.lib.vfs.VFile)
	 */
	public int compare(VFile file1, VFile file2) {
		String name1 = file1.getName();
		String name2 = file2.getName();
		
		String extension1 = file1.getFileName().getLowerExtension();
		String extension2 = file2.getFileName().getLowerExtension();
		
		return extension1.compareTo(extension2) * order;
	}
}
