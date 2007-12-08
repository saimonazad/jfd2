package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.Permission;


/**
 *    パーミッション取得インターフェイス 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetPermissionManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "get permission";
	/**
	 *    パーミッションを取得する。 
	 *   @return 
	 *  
	 */
	public abstract Permission getPermission();
}
