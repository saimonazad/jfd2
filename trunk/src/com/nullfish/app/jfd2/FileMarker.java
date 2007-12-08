/*
 * Created on 2004/06/06
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2;

import com.nullfish.lib.vfs.VFile;

/**
 * VFile�ƃ}�[�N��Ԃ��܂Ƃ߂Ĉ������߂̃N���X
 * 
 * @author shunji
 */
public class FileMarker {
	private boolean marked;
	
	private VFile file;
	
	public FileMarker(VFile file) {
		this.file = file;
	}
	
	/**
	 * @return Returns the file.
	 */
	public VFile getFile() {
		return file;
	}
	
	/**
	 * @return Returns the marked.
	 */
	public boolean isMarked() {
		return marked;
	}
	
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
}
