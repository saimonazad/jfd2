package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.Permission;


/**
 *    �p�[�~�b�V�����擾�C���^�[�t�F�C�X 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetPermissionManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "get permission";
	/**
	 *    �p�[�~�b�V�������擾����B 
	 *   @return 
	 *  
	 */
	public abstract Permission getPermission();
}
