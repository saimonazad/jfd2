/*
 * Created on 2004/08/30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.container2;

import com.nullfish.app.jfd2.JFDComponent;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JFDContainer {
	/**
	 * コンポーネントを設定する
	 * @param component
	 */
	public void setComponent(JFDComponent component);
	
	/**
	 * コンポーネントを取得する
	 */
	public JFDComponent getComponent();
	
	/**
	 * コンポーネントを削除する
	 */
	public void clearComponent();
	
	/**
	 * コンテナの可視性を切り替える
	 * @param visible
	 */
	public void setContainerVisible(boolean visible);
	
	/**
	 * タイトルを設定する
	 * @param title
	 */
	public void setTitle(String title);
	
	/**
	 * タイトルを取得する
	 * @return タイトル
	 */
	public String getTitle();
	
	/**
	 * 廃棄処理
	 *
	 */
	public void dispose();
	
	/**
	 * このコンテナが保持するコンポーネントにフォーカスをリクエストする。
	 *
	 */
	public void requestContainerFocus();
}
