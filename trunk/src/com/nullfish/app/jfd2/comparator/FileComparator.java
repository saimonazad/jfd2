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
public interface FileComparator {
	/**
	 * �t�@�C�����r���Afile1���傫����Ε��̐����A
	 * file2���傫����ΐ��̐����A���������0��Ԃ��B
	 * @param file1
	 * @param file2
	 * @return
	 */
	public int compare(VFile file1, VFile file2);
}
