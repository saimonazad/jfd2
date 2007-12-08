/*
 * Created on 2004/06/28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StringHistory {
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
	public StringHistory(int maxSize, boolean noOverwraps) {
		this.maxSize = maxSize;
		this.noOverwraps = noOverwraps;
	}
	
	/**
	 * 
	 * @param str
	 */
	public void add(String str) {
		if(noOverwraps) {
			history.remove(str);
		}
		
		history.add(0, str);
		if(history.size() > maxSize) {
			history.remove(history.size() - 1);
		}
	}
	
	/**
	 * �w��C���f�b�N�X�Ŗڂ̕�������擾����B
	 * @param index
	 * @return
	 */
	public String getAt(int index) {
		return (String)history.get(index);
	}
	
	/**
	 * �q�X�g���̃T�C�Y���擾����B
	 * @return
	 */
	public int getSize() {
		return history.size();
	}
	/**
	 * @return Returns the maxSize.
	 */
	public int getMaxSize() {
		return maxSize;
	}
	/**
	 * @return Returns the noOverwraps.
	 */
	public boolean isNoOverwraps() {
		return noOverwraps;
	}
	
	/**
	 * ������z��Ƃ��Ď擾����
	 * @return
	 */
	public String[] toArray() {
		String[] rtn = new String[history.size()];
		rtn = (String[])history.toArray(rtn);
		return rtn;
	}
	
	/**
	 * ���������X�g�Ƃ��Ď擾����
	 * @return
	 */
	public List toList() {
		return new ArrayList(history);
	}
}
