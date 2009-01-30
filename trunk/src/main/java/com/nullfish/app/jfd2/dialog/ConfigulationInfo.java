/*
 * Created on 2004/08/03
 *
 */
package com.nullfish.app.jfd2.dialog;

import com.nullfish.app.jfd2.config.Configulation;

/**
 * @author shunji
 *
 */
public class ConfigulationInfo {
	/**
	 * 設定クラス
	 */
	private Configulation config;
	
	/**
	 * 設定名
	 */
	private String paramName;

	/**
	 * コンストラクタ
	 * @param config
	 * @param name
	 */
	public ConfigulationInfo(Configulation config, String paramName) {
		this.config = config;
		this.paramName = paramName;
	}
	
	public Object getParam() {
		return config.getParam(paramName, null);
	}
	
	public void setParam(Object value) {
		config.setParam(paramName, value);
	}
}
