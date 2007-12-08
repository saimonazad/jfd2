package org.sexyprogrammer.lib.vfs;


/**
 *    �t�@�C���V�X�e���̒��ۃN���X�B 
 *    
 *   @author shunji 
 *  
 */
public abstract class FileSystem {
	/**
	 *    �t�@�C���V�X�e�����J�� 
	 *   @throws VFSException 
	 *  
	 */
	public abstract void open();
	/**
	 *    �t�@�C���V�X�e�������B 
	 *   @throws VFSException 
	 *  
	 */
	public abstract void close();
	/**
	 *    �t�@�C���V�X�e�����J����Ă��邩���肷��B 
	 *   @return	�t�@�C���V�X�e�����J����Ă���Ȃ�true��Ԃ��B 
	 *  
	 */
	public abstract boolean isOpened();
	/**
	 *    �t�@�C���V�X�e���𐶐�����B 
	 *   @throws VFSException 
	 *  
	 */
	public abstract void createFileSystem();
	/**
	 *    �w�肳�ꂽ�t�@�C�����̃t�@�C���C���X�^���X���擾����B 
	 *   @param fileName	�t�@�C�� 
	 *   @return 
	 *  
	 */
	public abstract VFile getFile(FileName fileName);
	/**
	 *    �t�@�C���V�X�e���̊�t�@�C�����擾����B 
	 *   @return	��t�@�C�� 
	 *  
	 */
	public abstract VFile getMountPoint();
	/**
	 *    �t�@�C���V�X�e���̃��[�g���擾����B 
	 *   @return	���[�g 
	 *  
	 */
	public abstract FileName getRootName();
	/**
	 *    ���̃t�@�C���V�X�e���𐶐�����VFS���擾����B 
	 *   @return	��������VFS 
	 *  
	 */
	public abstract VFS getVFS();
	/**
	 *    ���̃t�@�C���V�X�e�������[�J���t�@�C���V�X�e���Ȃ�true��Ԃ��B 
	 *   @return 
	 *  
	 */
	public abstract boolean isLocal();
}
