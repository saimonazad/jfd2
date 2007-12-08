/*
 * Created on 2004/05/23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.comparator;

import java.util.Comparator;

import com.nullfish.lib.vfs.VFile;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class JFDComparator implements Comparator {
	/**
	 * �f�B���N�g����r�̃t���O
	 */
	private boolean comparesDirectory = false;

	/**
	 * �t�@�C����r�N���X�̔z��B
	 * �D��x�̍������ɓ����Ă���B
	 */
	private FileComparator[] comparators;
	
	public JFDComparator(FileComparator[] comparators) {
		this.comparators = comparators;
	}
	
	public int compare(Object o1, Object o2) {
		if(o1 == null && o2 == null) {
			return 0;
		}
		
		if(o1 == null) {
			return 1;
		}
		
		if(o2 == null) {
			return -1;
		}
		
		VFile file1 = (VFile)o1;
		VFile file2 = (VFile)o2;
		
		int rtn = 0;
		
		for(int i=0; i < comparators.length && rtn == 0; i++) {
			rtn = comparators[i].compare(file1, file2);
		}
		
		return rtn;
	}
	
	/**
	 * �t�@�C���̐e�f�B���N�g���𖼏̂Ŕ�r����B
	 */
	public int compareDirectory(VFile file1, VFile file2) {
		VFile parent1 = file1.getParent();
		VFile parent2 = file2.getParent();

		if (parent1 == null && parent2 == null) {
			return 0;
		}

		if (parent1 == null && parent2 != null) {
			return -1;
		}

		if (parent1 != null && parent2 == null) {
			return 1;
		}

		return parent1.getAbsolutePath().compareTo(parent2.getAbsolutePath());
	}

	/**
	 * @return Returns the comparesDirectory.
	 */
	public boolean isComparesDirectory() {
		return comparesDirectory;
	}

	/**
	 * @param comparesDirectory
	 *            The comparesDirectory to set.
	 */
	public void setComparesDirectory(boolean comparesDirectory) {
		this.comparesDirectory = comparesDirectory;
	}
	
	/**
	 * ��r���s�������肷��B
	 * 
	 * @return
	 */
	public boolean compares() {
		return comparators != null && comparators.length > 0; 
	}
}