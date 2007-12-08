package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.permission.PermissionType;
import org.sexyprogrammer.lib.vfs.permission.FileAccess;


/**
 *    パーミッションセット操作クラス 
 *   @author shunji 
 *  
 */
public interface SetPermissionManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "set permission";
	/**
	 *    ファイル属性をセットする。 
	 *   @param type パーミッション種別 
	 *   @param access ユーザーアクセス種別 
	 *   @param value パーミッション値 
	 *  
	 */
	public abstract void addPermission(PermissionType type, FileAccess access, boolean value);
}
