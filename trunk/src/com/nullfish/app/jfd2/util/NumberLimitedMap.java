package com.nullfish.app.jfd2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * �ő�v�f�������߂���Map�B
 * �Ō�ɃA�N�Z�X���ꂽ�v�f����폜�����B
 * 
 * @author shunji
 *
 */
public class NumberLimitedMap extends WeakHashMap {
	/**
	 * �ő�v�f��
	 */
	private int maxItemNumber = 100;

	/**
	 * �L�[�l�̏��ԃ��X�g
	 */
	private List keyOrderList = new ArrayList();
	
	/**
	 * Map#put(Object, Object)�̃I�[�o�[���C�h
	 */
	public Object put(Object key, Object value) {
		keyOrderList.remove(key);
		keyOrderList.add(key);
		
		checkItemNumber();
		
		return super.put(key, value);
	}
	
	/**
	 * �A�C�e�������ő�l�Ɏ��܂�悤��������B
	 *
	 */
	private void checkItemNumber() {
		while(keyOrderList.size() > maxItemNumber) {
			super.remove(keyOrderList.get(0));
			keyOrderList.remove(0);
		}
	}
	
	public Object get(Object key) {
		Object rtn = super.get(key);
		if(rtn != null) {
			keyOrderList.remove(key);
			keyOrderList.add(key);
		}
		
		return rtn;
	}
	
	public Object remove(Object key) {
		keyOrderList.remove(key);
		return super.remove(key);
	}
	
	public void clear() {
		super.clear();
		keyOrderList.clear();
	}
	
	public int getMaxItemNumber() {
		return maxItemNumber;
	}

	public void setMaxItemNumber(int maxItemNumber) {
		this.maxItemNumber = maxItemNumber;
	}
	
	
}
