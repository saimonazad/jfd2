/*
 * Created on 2004/05/18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2;

/**
 * JFDModel�̕ύX�ʒm���󂯎�郊�X�i�C���^�[�t�F�C�X�B
 * �����̃��\�b�h�́A�K������Swing�̃C�x���g�f�B�X�p�b�`�X���b�h����
 * ��������Ƃ͌���Ȃ��Ƃ������Ƃɒ��ӁB
 * �����N���X�̓X���b�h�Z�[�t�ɂ���K�v������B
 * 
 * @author shunji
 */
public interface JFDModelListener {
	/**
	 * �\�����̃t�@�C�����X�g���ύX���ꂽ�ۂɌĂяo�����B
	 * 
	 * @param model		���f��
	 */
	public void dataChanged(JFDModel model);
	
	/**
	 * �f�B���N�g�����ύX���ꂽ�ۂɌĂяo�����B
	 * 
	 * @param model	���f��
	 */
	public void directoryChanged(JFDModel model);
	
	/**
	 * �J�[�\���ړ����ɌĂяo�����B
	 * @param index
	 */
	public void cursorMoved(JFDModel model);
	
	/**
	 * �}�[�N��Ԃ��ω������Ƃ��ɌĂяo�����B
	 * @param model
	 */
	public void markChanged(JFDModel model);
}
