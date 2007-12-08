package org.sexyprogrammer.lib.vfs;

import java.util.Map;

public class UserInfo {
	/**
	 *    ユーザー 
	 *  
	 */
	public static final String USER = "user";
	/**
	 *    パスワード 
	 *  
	 */
	public static final String PASSWORD = "password";
	 Map infoMap = new HashMap();
	public UserInfo() {
	}
	/**
	 *    ユーザー情報をセットする。 
	 *   @param key 
	 *   @param value 
	 *  
	 */
	public void setInfo(String key, Object value) {
	}
	/**
	 *    ユーザー情報を取得する。 
	 *   @param key 
	 *   @return 
	 *  
	 */
	public Object getInfo(String key) {
		return null;
	}
	public int hashCode() {
		return 0;
	}
	public boolean equals(Object o) {
		return false;
	}
}
