/*
 * Created on 2004/06/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.Container;

/**
 * GUI�֘A���[�e�B���e�B�N���X
 * 
 * @author shunji
 */
public class UIUtilities {
	/**
	 * �R���|�[�l���g��ێ�����t���[���A�_�C�A���O���擾����B
	 * 
	 * @param container
	 * @return
	 */
	public static Container getTopLevelOwner(Container container) {
		Container c = container;
		while(c.getParent() != null) {
			c = c.getParent();
		}
		
		return c;
	}
}
