package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import java.io.OutputStream;


/**
 *    �o�̓X�g���[���擾�N���X 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetOutputStreamManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "get output stream";
	/**
	 *    �o�̓X�g���[�����擾����B 
	 *   @return 
	 *  
	 */
	public abstract OutputStream getOutputStream();
}
