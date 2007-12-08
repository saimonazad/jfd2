/*
 * Created on 2004/06/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.Container;

/**
 * GUI関連ユーティリティクラス
 * 
 * @author shunji
 */
public class UIUtilities {
	/**
	 * コンポーネントを保持するフレーム、ダイアログを取得する。
	 * 
	 * @param container
	 * @return
	 */
	public static Container getTopLevelOwner(Container container) {
		Container c = container;
		while(c.getParent() != null) {
			c = c.getParent();
		}
		
		return c;
	}
}
