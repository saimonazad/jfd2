/*
 * Created on 2004/10/10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.aliase;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.util.FileHistory;
import com.nullfish.lib.vfs.FileSystem;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �G�C���A�X���
 * 
 * @author shunji
 */
public class AliaseInfo {
	/**
	 * �G�C���A�X��t�@�C��
	 */
	private String path;
	
	/**
	 * true�Ȃ瓯��t�@�C���V�X�e���̍Ō�ɊJ���ꂽ�f�B���N�g�����w�������B
	 */
	private boolean lastOpenedDir;
	
	/**
	 * �Ώ�jFD
	 */
	private JFD jfd;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param path	�t�@�C��
	 * @param lastOpenedDir	������true�Ȃ瓯��t�@�C���V�X�e���̍Ō�ɊJ���ꂽ�f�B���N�g�����w�������B
	 * @param jfd	���̃I�u�W�F�N�g��ێ�����jFD2�C���X�^���X
	 */
	public AliaseInfo(String path, boolean lastOpenedDir, JFD jfd) {
		this.path = path;
		this.lastOpenedDir = lastOpenedDir;
		this.jfd = jfd;
	}
	
	/**
	 * �G�C���A�X��t�@�C�����擾����B
	 * 
	 * @return
	 */
	public VFile getFile() {
		try {
			VFile file = VFS.getInstance(jfd).getFile(path);
			if(!lastOpenedDir) {
				return file;
			}
			
			FileSystem fileSystem = file.getFileSystem();
			FileHistory history = jfd.getModel().getHistory();
			for(int i=0; i<history.getSize(); i++) {
				VFile oldFile = history.fileAt(i);
				if(oldFile != null && oldFile.getFileSystem().equals(fileSystem)) {
					return oldFile;
				}
			}
			
			return file;
		} catch (VFSException e) {
			return null;
		}
	}
}
