package org.sexyprogrammer.lib.vfs;


/**
 *    �p�X�����񂩂�t�@�C���𐶐�����N���X�B 
 *   �t�@�C���N���X��ɂ�����݂���B 
 *    
 *   @author shunji 
 *  
 */
public abstract class FileFactory {
	/**
	 *    �t�@�C���̃p�X��ڑ�����p�C�v���� 
	 *  
	 */
	public static final char PIPE = '|';
	protected  VFS fileSystemManager;
	/**
	 *    �p�X�����̃N���X�ŉ��߉\�����肷��B 
	 *    
	 *   @param path	�p�X	 
	 *   @return	���߉\�Ȃ�true��Ԃ��B 
	 *  
	 */
	public abstract boolean isInterpretable(String path);
	/**
	 *    �t�@�C���������̃N���X�ŉ��߂ł��邩���肷��B 
	 *   @param fileName	�t�@�C���� 
	 *   @return	���߂ł���Ȃ�true��Ԃ��B 
	 *  
	 */
	public abstract boolean isBelongingFileName(FileName fileName);
	/**
	 *    �p�X����t�@�C�����𐶐�����B 
	 *   @param path	�p�X 
	 *   @return	�t�@�C���� 
	 *  
	 */
	public FileName interpretFileName(String path) {
		return null;
	}
	/**
	 *    �t�@�C�����N���X�����΃p�X���擾����B 
	 *   @param fileName	�t�@�C���� 
	 *   @return	��΃p�X������ 
	 *  
	 */
	public static String interpretPath(FileName fileName) {
		return null;
	}
	/**
	 *    �t�@�C�����N���X���烆�[�U�[��񔲂��̐�΃p�X���擾����B 
	 *   @param fileName 
	 *   @return	���[�U�[��񔲂��̐�΃p�X 
	 *  
	 */
	public static String interpretSecurePath(FileName fileName) {
		return null;
	}
	/**
	 *    �p�X����t�@�C�����𐶐�����B 
	 *   @param path	�p�X 
	 *   @param baseFileName ��t�@�C���� 
	 *   @return	�t�@�C���� 
	 *  
	 */
	public abstract FileName interpretFileName(String path, FileName baseFileName);
	/**
	 *    �t�@�C��������t�@�C���V�X�e�����擾����B 
	 *   @param fileName	�t�@�C���� 
	 *   @return	�t�@�C���V�X�e�� 
	 *  
	 */
	public abstract FileSystem interpretFileSystem(FileName fileName);
	/**
	 *    �p�X����t�@�C���𐶐�����B 
	 *    
	 *   @param path	�p�X 
	 *   @return	�t�@�C�� 
	 *  
	 */
	public VFile interpretFile(String path) {
		return null;
	}
	/**
	 *    �t�@�C��������t�@�C���𐶐�����B 
	 *   @param fileName	�t�@�C���� 
	 *   @return	�t�@�C�� 
	 *  
	 */
	public VFile interpretFile(FileName fileName) {
		return null;
	}
	/**
	 *    �t�@�C���V�X�e���Ǘ����s��VFS�N���X���擾����B 
	 *   @return	VFS�N���X 
	 *  
	 */
	public VFS getFileSystemManager() {
		return null;
	}
	/**
	 *    �t�@�C���V�X�e���Ǘ����s��VFS�N���X���Z�b�g����B 
	 *   @param manager	VFS�N���X 
	 *  
	 */
	public void setFileSystemManager(VFS manager) {
	}
}
