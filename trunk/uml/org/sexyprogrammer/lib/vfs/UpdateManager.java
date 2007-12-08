package org.sexyprogrammer.lib.vfs;

import java.util.Map;


/**
 *    �t�@�C���X�V�Ǘ��N���X�B 
 *   �V���O���g���ɂȂ��Ă���B 
 *    
 *   @author shunji 
 *  
 */
public class UpdateManager {
	private static final int FILE_CHANGED = 0;
	private static final int FILE_CREATED = 1;
	private static final int FILE_DELETED = 2;
	 Map fileListenerMap = new HashMap();
	 Map childListenerMap = new HashMap();
	private static  UpdateManager instance = new UpdateManager();
	/**
	 *    �R���X�g���N�^ 
	 *   
	 *  
	 */
	private UpdateManager() {
	}
	/**
	 *    �V���O���g���C���X�^���X���擾����B 
	 *   @return 
	 *  
	 */
	public static UpdateManager getInstance() {
		return null;
	}
	/**
	 *    �t�@�C�����X�i��ǉ�����B 
	 *    
	 *   @param file �Ώۃt�@�C�� 
	 *   @param listener	�ǉ����郊�X�i 
	 *  
	 */
	public void addFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    �t�@�C�����X�i���폜���� 
	 *    
	 *   @param file �Ώۃt�@�C�� 
	 *   @param listener	�폜����t�@�C�����X�i 
	 *  
	 */
	public void removeFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    �q�t�@�C���̃t�@�C�����X�i��ǉ�����B 
	 *    
	 *   @param file �Ώۃt�@�C�� 
	 *   @param listener	�ǉ����郊�X�i 
	 *  
	 */
	public void addChildFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    �q�t�@�C���̃t�@�C�����X�i���O���B 
	 *    
	 *   @param file �Ώۃt�@�C�� 
	 *   @param listener	�폜����t�@�C�����X�i 
	 *  
	 */
	public void removeChildFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    �t�@�C�����X�V���ꂽ�ۂɌĂяo����� 
	 *   @param file �Ώۃt�@�C�� 
	 *  
	 */
	public void fileChanged(VFile file) {
	}
	/**
	 *    �t�@�C�����폜���ꂽ�ۂɌĂяo�����B 
	 *   @param file �Ώۃt�@�C�� 
	 *  
	 */
	public void fileDeleted(VFile file) {
	}
	/**
	 *    �t�@�C�����������ꂽ�ۂɌĂяo�����B 
	 *   @param file �Ώۃt�@�C�� 
	 *  
	 */
	public void fileCreated(VFile file) {
	}
	/**
	 *    ���X�i���Ă� 
	 *   @param file 
	 *   @param updateType 
	 *  
	 */
	private void callListeners(VFile file, int updateType) {
	}
}
