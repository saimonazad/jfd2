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
public abstract class AbstractPlugin implements Plugin {
	private VFile baseFile;
	
	private PluginInformation info;
	
	/**
	 * ��`�t�@�C����
	 */
	public static final String DEFINITION = "plugin.xml";
	
	/**
	 * ���[�g�m�[�h��
	 */
	public static final String NODE_NAME = "plugin";
	
	/**
	 * ���̑���
	 */
	public static final String ATTR_NAME = "name";
	
	/**
	 * �N���X����
	 */
	public static final String ATTR_CLASS = "class";
	
	/**
	 * �����m�[�h
	 */
	public static final String NODE_DESCRIPTION = "description";
	
	public VFile getBaseDir() {
		return baseFile;
	}
	
	/**
	 * �V�X�e���J�n���ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemStarted() {
	}
	
	/**
	 * �V�X�e���I�����ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemExited() {
	}
	
	/**
	 * �ݒ�ύX���ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void configulationChanged() {
	}
	
	/**
	 * JFD2�C���X�^���X���������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdCreated(JFD jfd) {
	}
	
	/**
	 * JFD2�C���X�^���X�����������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdInited(JFD jfd, VFile baseDir) {
	}
	
	/**
	 * JFD2�C���X�^���X���p�����ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdDisposed(JFD jfd) {
	}
	
	/**
	 * JFD�I�[�i�[�C���X�^���X���������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdOwnerCreated(JFDOwner owner) {
	}
	
	/**
	 * JFD2�I�[�i�[�C���X�^���X���p�����ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdOwnerDisposed(JFDOwner owner) {
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.plugin.Plugin#getParam(java.lang.String)
	 */
	public Object getParam(String key) {
		return info.getParameter(key);
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.plugin.Plugin#getName()
	 */
	public String getName() {
		return info.getName();
	}

	/**
	 * �v���O�C���o�[�W�������擾����B
	 * @return
	 */
	public double getVersion() {
		return info.getVersion();
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.plugin.Plugin#getDescription()
	 */
	public String getDescription() {
		return info.getDescription();
	}
	
	void init(VFile baseFile, PluginInformation info) {
		this.baseFile = baseFile;
		this.info = info;
	}
	
	public VFile getResource(String path) {
		try {
			return baseFile.getRelativeFile(path);
		} catch (VFSException e) {
			return null;
		}
	}
	
	/**
	 * �v���O�C�����~����B
	 *
	 */
	public void close() {
		
	}
}
