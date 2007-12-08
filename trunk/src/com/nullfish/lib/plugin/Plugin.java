/*
 * Created on 2005/01/25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.lib.plugin;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Plugin {
	/**
	 * �V�X�e���J�n���ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemStarted();
	
	/**
	 * �V�X�e���I�����ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemExited();
	
	/**
	 * �ݒ�ύX���ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void configulationChanged();
	
	/**
	 * JFD2�C���X�^���X���������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdCreated(JFD jfd);
	
	/**
	 * JFD2�C���X�^���X�����������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdInited(JFD jfd, VFile baseDir);
	
	/**
	 * JFD2�C���X�^���X���p�����ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdDisposed(JFD jfd);
	
	/**
	 * JFD�I�[�i�[�C���X�^���X���������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdOwnerCreated(JFDOwner owner);
	
	/**
	 * JFD2�I�[�i�[�C���X�^���X���p�����ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdOwnerDisposed(JFDOwner owner);
	
	/**
	 * �v���O�C�����̂��擾����B
	 * @return
	 */
	public String getName();
	
	/**
	 * �v���O�C���o�[�W�������擾����B
	 * @return
	 */
	public double getVersion();
	
	/**
	 * ���������擾����B
	 * @return
	 */
	public String getDescription();
	
	/**
	 * �p�����[�^���擾����B
	 * @param key
	 * @return
	 */
	public Object getParam(String key);
	
	/**
	 * �v���O�C�����̃��\�[�X���擾����B
	 * @param path
	 * @return
	 */
	public VFile getResource(String path);
	
	/**
	 * �v���O�C���̃f�B���N�g�����擾����B
	 * @return
	 */
	public VFile getBaseDir();
	
	/**
	 * �v���O�C�����~����B
	 *
	 */
	public void close();
}
