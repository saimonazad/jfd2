/*
 * Created on 2004/06/28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.util;

import java.util.ArrayList;
import java.util.List;

import com.nullfish.lib.vfs.VFile;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileHistory {
	private int position;
	
	/**
	 * �ő�L�^��
	 */
	private int maxSize = 50;
	
	/**
	 * �q�X�g��
	 */
	private List history = new ArrayList();
	
	/**
	 * ����I�u�W�F�N�g�̕ێ����֎~���邩�̃t���O
	 */
	private boolean noOverwraps = false;
	
	/**
	 * �R���X�g���N�^
	 * @param maxSize
	 */
	public FileHistory(int maxSize, boolean noOverwraps) {
		this.maxSize = maxSize;
		this.noOverwraps = noOverwraps;
	}
	
	/**
	 * 
	 * @param file
	 */
	public void add(VFile file) {
		if(history.size() > 0 && file.equals(history.get(position))) {
			return;
		}
		
		for(int i=0; i<position; i++) {
			history.remove(0);
		}
		
		position = 0;
		
		if(noOverwraps) {
			history.remove(file);
		}
		
		history.add(0, file);
		if(history.size() > maxSize) {
			history.remove(history.size() - 1);
		}
	}
	
	/**
	 * �w��C���f�b�N�X�Ŗڂ̃t�@�C�����擾����B
	 * @param index
	 * @return
	 */
	public VFile fileAt(int index) {
		return (VFile)history.get(index);
	}
	
	/**
	 * �q�X�g���̃T�C�Y���擾����B
	 * @return
	 */
	public int getSize() {
		return history.size();
	}

	/**
	 * �z��ɂ��ĕԂ�
	 * @return
	 */
	public VFile[] toArray() {
		VFile[] rtn = new VFile[history.size()];
		rtn = (VFile[])history.toArray(rtn);
		return rtn;
	}
	
	/**
	 * ���X�g�ɂ��ĕԂ�
	 * @return
	 */
	public List toList() {
		return new ArrayList(history);
	}
	
	/**
	 * ��O�̃q�X�g���ɃJ�[�\�������킹�A���̃t�@�C����Ԃ��B
	 * 
	 * @return
	 */
	public VFile prev() {
		position++;
		while(position >= history.size()) {
			position--;
		}
		
		return (VFile)history.get(position);
	}
	
	/**
	 * ����̃q�X�g���ɃJ�[�\�������킹�A���̃t�@�C����Ԃ��B
	 * 
	 * @return
	 */
	public VFile next() {
		position--;
		while(position < 0) {
			position++;
		}
		
		return history.size() == 0 ? null : (VFile)history.get(position);
	}
}
