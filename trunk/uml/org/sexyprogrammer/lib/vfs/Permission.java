package org.sexyprogrammer.lib.vfs;


/**
 *    �t�@�C���̃p�[�~�b�V�����C���^�[�t�F�C�X�B 
 *   �����N���X�̃p�[�~�b�V������ύX���Ă��A���ۂ̃t�@�C���ɂ͔��f����܂���B 
 *    
 *   @author Shunji Yamaura 
 *  
 */
public interface Permission {
	/**
	 *    �t�@�C���p�[�~�b�V�����̑��������擾����B 
	 *   @return	�������̔z�� 
	 *  
	 */
	public PermissionType[] getTypes();
	/**
	 *    �A�N�Z�X��ʂ��擾����B 
	 *   @return	�A�N�Z�X��ʂ̔z�� 
	 *  
	 */
	public FileAccess[] getAccess();
	/**
	 *    �t�@�C���p�[�~�b�V�����̑����l���擾����B 
	 *   @param name	�p�[�~�b�V������� 
	 *   @param access	���[�U�[��� 
	 *   @return		�p�[�~�b�V�����l 
	 *  
	 */
	public boolean hasPermission(PermissionType name, FileAccess access);
	/**
	 *    �t�@�C���p�[�~�b�V�����̑����l���Z�b�g���� 
	 *   @param name	�p�[�~�b�V������� 
	 *   @param access	���[�U�[��� 
	 *   @param value		�p�[�~�b�V�����l 
	 *  
	 */
	public void setPermission(PermissionType name, FileAccess access, boolean value);
	/**
	 *    �I�[�i�[���擾���� 
	 *   @return	�I�[�i�[ 
	 *  
	 */
	public String getOwner();
	/**
	 *    �I�[�i�[���Z�b�g����B 
	 *   @param owner	�I�[�i�[ 
	 *  
	 */
	public void setOwner(String owner);
	/**
	 *    �O���[�v���擾���� 
	 *   @return	�O���[�v 
	 *  
	 */
	public String getGroup();
	/**
	 *    �O���[�v���Z�b�g����B 
	 *   @param 	group �O���[�v 
	 *  
	 */
	public void setGroup(String group);
	/**
	 *    ���̃p�[�~�b�V�����̒l����荞�ށB 
	 *   @param permission  
	 *  
	 */
	public void importPermission(Permission permission);
	/**
	 *    �p�[�~�b�V�����̕�����\�����擾����B 
	 *   @return	�p�[�~�b�V���������� 
	 *  
	 */
	public String getPermissionString();
}
