/*
 * Created on 2004/06/13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * リソース管理クラス
 * 
 * @author shunji
 */
public class JFDResource {
	/**
	 * ラベル関連リソース
	 */
	public static final ResourceBundle LABELS = ResourceBundle.getBundle(
			"label", Locale.getDefault());
	
	/**
	 * メッセージ関連リソース
	 */
	public static final ResourceBundle MESSAGES = ResourceBundle.getBundle(
			"message", Locale.getDefault());
	
	/**
	 * ファイル名関連リソース
	 */
	public static final ResourceBundle FILENAME = ResourceBundle.getBundle(
			"filename", Locale.getDefault());
}