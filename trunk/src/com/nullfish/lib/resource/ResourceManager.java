/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.resource;

/**
 * 文字列リソースを抽象化するインターフェイス
 * 
 * @author shunji
 */
public interface ResourceManager {
	/**
	 * 文字列リソースを取得する。
	 * @param name
	 * @return
	 */
	public String getResource(String name);
}
