/*
 * Created on 2004/09/16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.container2;


import com.nullfish.app.jfd2.JFDComponent;
import com.nullfish.app.jfd2.command.owner.OwnerCommandManager;
import com.nullfish.lib.vfs.VFile;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JFDOwner {
	/**
	 * ���݃A�N�e�B�u��jFD���擾����B
	 * @return
	 */
	public JFDComponent getActiveComponent();
	
	/**
	 * ���݃A�N�e�B�u�ȃR���|�[�l���g��ݒ肷��B
	 */
	public void setActiveComponent(JFDComponent component);

	/**
	 * �A�N�e�B�u�ȃR���|�[�l���g���ύX���ꂽ�ۂɌĂяo�����B
	 */
	public void componentActivated(JFDComponent component);

	/**
	 * �ێ����Ă���R���|�[�l���g�̐���Ԃ��B
	 * @return
	 */
	public int getCount();
	
	/**
	 * �V�����R���|�[�l���g��ǉ�����B
	 * @param constants
	 */
	public void addComponent(JFDComponent component, ContainerPosition constants, TitleUpdater titleUpdater);
	
	/**
	 * �R���|�[�l���g���폜����B
	 * @param component
	 */
	public void removeComponent(JFDComponent component);
	
	/**
	 * �ݒ�t�@�C���̃f�B���N�g�����擾����
	 * @return
	 */
	public VFile getConfigDirectory();
	
	/**
	 * �R���|�[�l���g�̈ʒu�����߂�B
	 * 
	 * @param component
	 * @return
	 */
	public ContainerPosition getComponentPosition(JFDComponent component);
	
	/**
	 * �p������
	 *
	 */
	public void dispose();
	
	/**
	 * �R�}���h�}�l�[�W�����擾����B
	 * 
	 * @return
	 */
	public OwnerCommandManager getCommandManager();
	
	/**
	 * �w��ʒu�̃R���|�[�l���g���擾����B
	 * ��������ꍇ�͍őO�ʂ̃R���|�[�l���g���擾����B
	 * 
	 * @param comp
	 * @return
	 */
	public JFDComponent getComponent(ContainerPosition position);
	
	/**
	 * �őO�ʂɈړ�����B
	 *
	 */
	public void toFront();
}
