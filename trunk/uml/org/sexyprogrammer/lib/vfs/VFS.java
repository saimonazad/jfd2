package org.sexyprogrammer.lib.vfs;

import java.util.Map;


/**
 *    �t�@�C���V�X�e�����Ǘ�����V���O���g���N���X�B 
 *   ���̃��C�u�����̏����̋N�_�ƂȂ�B 
 *  
 */
public class VFS {
	/**
	 *    �W���C���X�^���X 
	 *  
	 */
	public static final String DEFAULT_INSTANCE = "default_file_system_manager";
	/**
	 *    �t�@�C���V�X�e����`�t�@�C����URL 
	 *  
	 */
	private static final String FILE_SYSTEMS = "/file_systems";
	private  FileFactory[] interpreters;
	private  Map fileSystemsMap = new HashMap();
	private static  Map instanceMap = new HashMap();
	/**
	 *    �R���X�g���N�^�B 
	 *   �v���C�x�[�g�B 
	 *   
	 *  
	 */
	private VFS() {
	}
	/**
	 *    �p�X����t�@�C�����擾����B 
	 *   @param path 
	 *   @return 
	 *   @throws VFSException 
	 *  
	 */
	public VFile getFile(String path) {
		return null;
	}
	/**
	 *    �t�@�C��������t�@�C�����擾����B 
	 *   @param fileName 
	 *   @return 
	 *   @throws VFSException 
	 *  
	 */
	public VFile getFile(FileName fileName) {
		return null;
	}
	/**
	 *    ���̃N���X�̃C���X�^���X���擾����B 
	 *   @return 
	 *  
	 */
	public static VFS getInstance() {
		return null;
	}
	/**
	 *    ���̃N���X�̃C���X�^���X���擾����B 
	 *   @param key 
	 *   @return 
	 *  
	 */
	public static VFS getInstance(Object key) {
		return null;
	}
	/**
	 *    �A�N�e�B�u�ȃt�@�C���V�X�e����ǉ�����B 
	 *   @param fileSystem 
	 *  
	 */
	public void addFileSystem(FileName root, FileSystem fileSystem) {
	}
	/**
	 *    �A�N�e�B�u�ȃt�@�C���V�X�e�����擾����B 
	 *   @param root 
	 *   @return 
	 *  
	 */
	public FileSystem getFileSystem(FileName root) {
		return null;
	}
	/**
	 *    �A�N�e�B�u�ȃt�@�C���V�X�e�����폜����B 
	 *   @param root 
	 *  
	 */
	public void removeFileSystem(FileName root) {
	}
}
