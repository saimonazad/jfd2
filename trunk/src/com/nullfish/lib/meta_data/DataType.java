/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.meta_data;

import org.jdom.Element;

/**
 * ������Ƒ��ݕϊ��\�ȃf�[�^�^�̃��^����\���C���^�[�t�F�C�X�B
 * 
 * @author shunji
 */
public interface DataType {
	/**
	 * �^�̖��̂����߂�B
	 * @return
	 */
	public String getName();
	
	/**
	 * �m�[�h���I�u�W�F�N�g�̕ϊ����s���B
	 * @param str
	 * @return
	 */
	public Object node2Object(Element node);
	
	/**
	 * �I�u�W�F�N�g���m�[�h�̕ϊ����s���B
	 * @param o
	 * @return
	 */
	public Element object2Node(Object o);
	
	/**
	 * Java�I�u�W�F�N�g�̌^�����߂�B
	 * @return
	 */
	public Class getDataClass();
	
	/**
	 * �I�u�W�F�N�g�����̃f�[�^�^�ł��邩���ʂ���B
	 * @param o
	 * @return
	 */
	public boolean isConvertable(Object o);
}
