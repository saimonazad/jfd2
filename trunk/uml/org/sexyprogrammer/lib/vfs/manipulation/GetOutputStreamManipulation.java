package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import java.io.OutputStream;


/**
 *    出力ストリーム取得クラス 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetOutputStreamManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "get output stream";
	/**
	 *    出力ストリームを取得する。 
	 *   @return 
	 *  
	 */
	public abstract OutputStream getOutputStream();
}
