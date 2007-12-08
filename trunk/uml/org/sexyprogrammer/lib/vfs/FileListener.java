package org.sexyprogrammer.lib.vfs;


/**
 *    �t�@�C���V�X�e���ł̃t�@�C�������A�����ύX�Ȃǂ̃C�x���g�̃��X�i 
 *    
 *   @author shunji 
 *  
 */
public interface FileListener {
	/**
	 *    �t�@�C�����X�V���ꂽ�ۂɌĂяo����� 
	 *   @param file �t�@�C�� 
	 *  
	 */
	public void fileChanged(VFile file);
	/**
	 *    �t�@�C�����폜���ꂽ�ۂɌĂяo�����B 
	 *   @param file �t�@�C�� 
	 *  
	 */
	public void fileDeleted(VFile file);
	/**
	 *    �t�@�C�����������ꂽ�ۂɌĂяo�����B 
	 *   @param file �t�@�C�� 
	 *  
	 */
	public void fileCreated(VFile file);
}
