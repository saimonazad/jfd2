/*
 * Created on 2005/01/24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.viewer;

import org.jdom.Element;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.VFile;

/**
 * �t�@�C���r���[�A�̐������Ǘ�����N���X�B
 * 
 * @author shunji
 */
public interface FileViewerFactory {
	/**
	 * JFD2�C���X�^���X�ɑΉ������t�@�C���r���[�A���擾����B
	 * @param view
	 * @return
	 */
	public FileViewer getFileViewer(JFD view);

	/**
	 * �p�����[�^���Z�b�g����B
	 * 
	 * @param name
	 * @param value
	 */
	public void setParam(String name, Object value);

	/**
	 * �I�[�v���A�N���[�Y���̐U�镑���N���X��Ԃ��B
	 * @return
	 */
	public ViewerController getController();
	
	/**
	 * XML�m�[�h���珉��������B
	 * @param node
	 */
	public void init(VFile baseDir, Element node);
	
	/**
	 * �T�|�[�g����Ă���g���q���擾����B
	 * @return
	 */
	public String[] getSupportedExtensions();
	
	/**
	 * �N���X���[�_�[��ݒ肷��
	 * @param loader
	 */
	public void setLoader(ClassLoader loader);
}
