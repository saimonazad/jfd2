package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.permission.PermissionType;
import org.sexyprogrammer.lib.vfs.permission.FileAccess;


/**
 *    �p�[�~�b�V�����Z�b�g����N���X 
 *   @author shunji 
 *  
 */
public interface SetPermissionManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "set permission";
	/**
	 *    �t�@�C���������Z�b�g����B 
	 *   @param type �p�[�~�b�V������� 
	 *   @param access ���[�U�[�A�N�Z�X��� 
	 *   @param value �p�[�~�b�V�����l 
	 *  
	 */
	public abstract void addPermission(PermissionType type, FileAccess access, boolean value);
}
