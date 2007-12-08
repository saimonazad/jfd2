package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import java.io.InputStream;


/**
 *    入力ストリームを取得する。 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetInputStreamManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "get input stream";
	public abstract InputStream getInputStream();
}
