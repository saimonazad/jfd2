/*
 * Created on 2004/08/03
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog;

import com.nullfish.app.jfd2.config.Configulation;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfigulationInfo {
	/**
	 * �ݒ�N���X
	 */
	private Configulation config;
	
	/**
	 * �ݒ薼
	 */
	private String paramName;

	/**
	 * �R���X�g���N�^
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
