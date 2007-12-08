package org.sexyprogrammer.lib.vfs;

import org.sexyprogrammer.lib.vfs.manipulation.GetAttributesManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.SetAttributeManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetPermissionManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.SetPermissionManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetInputStreamManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetOutputStreamManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetChildrenManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.CreateFileManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.CreateLinkManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.DeleteManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.MoveManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.CopyFileManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.SetTimestampManipulation;


/**
 *    �t�@�C������N���X�̃t�@�N�g���[�N���X�B 
 *    
 *   @author shunji 
 *   
 *   To change the template for this generated type comment go to 
 *   Window - Preferences - Java - Code Generation - Code and Comments 
 *  
 */
public interface ManipulationFactory {
	/**
	 *    �t�@�C�������������擾�N���X���擾����B 
	 *   @param file 
	 *   @return 
	 *   @throws ManipulationNotAvailableException 
	 *  
	 */
	public GetAttributesManipulation getGetAttributeManipulation(VFile file);
	/**
	 *    �t�@�C�������Z�b�g�N���X���擾����B 
	 *   @param file 
	 *   @return 
	 *   @throws ManipulationNotAvailableException 
	 *  
	 */
	public SetAttributeManipulation getSetAttributeManipulation(VFile file);
	/**
	 *    �t�@�C���p�[�~�b�V�����N���X�擾�N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public GetPermissionManipulation getGetPermissionManipulation(VFile file);
	/**
	 *    �t�@�C���p�[�~�b�V�����Z�b�g����N���X��Ԃ��B 
	 *   @param file 
	 *   @return 
	 *   @throws ManipulationNotAvailableException 
	 *  
	 */
	public SetPermissionManipulation getSetPermissionManipulation(VFile file);
	/**
	 *    ���̓X�g���[���擾�N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public GetInputStreamManipulation getGetInputStreamManipulation(VFile file);
	/**
	 *    �o�̓X�g���[���擾�N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public GetOutputStreamManipulation getGetOutputStreamManipulation(VFile file);
	/**
	 *    �q�t�@�C���擾�N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public GetChildrenManipulation getGetChildrenManipulation(VFile file);
	/**
	 *    �t�@�C�������N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public CreateFileManipulation getCreateFileManipulation(VFile file);
	/**
	 *    �����N�����N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public CreateLinkManipulation getCreateLinkManipulation(VFile file);
	/**
	 *    �t�@�C���폜�N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public DeleteManipulation getDeleteManipulation(VFile file);
	/**
	 *    �t�@�C�����̕ύX�N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public MoveManipulation getMoveManipulation(VFile file);
	/**
	 *    �t�@�C���R�s�[����N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public CopyFileManipulation getCopyFileManipulation(VFile file);
	/**
	 *    �ŏI�X�V���ݒ葀��N���X��Ԃ��B 
	 *   @return 
	 *  
	 */
	public SetTimestampManipulation getSetTimestampManipulation(VFile file);
}
