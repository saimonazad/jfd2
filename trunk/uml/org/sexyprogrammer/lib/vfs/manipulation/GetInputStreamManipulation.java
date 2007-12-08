package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import java.io.InputStream;


/**
 *    ���̓X�g���[�����擾����B 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetInputStreamManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "get input stream";
	public abstract InputStream getInputStream();
}
