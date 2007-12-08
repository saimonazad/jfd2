/*
 * Created on 2004/08/22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2;

import java.util.HashSet;
import java.util.Set;

/**
 * �����X�V�̃��b�N���Ǘ�����N���X�B
 * 
 * @author shunji
 */
public class AutoUpdateLockManager {
	private Set locks = new HashSet();
	
	/**
	 * ���b�N����B
	 * @param o	���b�N�I�u�W�F�N�g
	 */
	public synchronized void lock(Object o) {
		locks.add(o);
	}
	
	/**
	 * ���b�N����������B
	 * @param o	���b�N�I�u�W�F�N�g
	 */
	public synchronized void unlock(Object o) {
		locks.remove(o);
	}
	
	/**
	 * ���b�N����Ă���Ȃ�true��Ԃ��B
	 * 
	 * @return
	 */
	public synchronized boolean isLocked() {
		return locks.size() != 0;
	}
}
