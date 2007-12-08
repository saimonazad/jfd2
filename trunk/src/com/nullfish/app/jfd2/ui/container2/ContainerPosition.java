/*
 * Created on 2004/11/13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.container2;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContainerPosition {
	private static Map nameInstanceMap = new HashMap();
	
	private String name;
	
	/**
	 * �V�������C���p�l��
	 */
	public static final ContainerPosition MAIN_PANEL = new ContainerPosition("new_main_panel", "new_sub_panel");
	
	/**
	 * �V�����T�u�p�l��
	 */
	public static final ContainerPosition SUB_PANEL = new ContainerPosition("new_sub_panel", "new_main_panel");
	
	/**
	 * �Ɨ������E�C���h�E
	 */
	public static final ContainerPosition NEW_WINDOW = new ContainerPosition("new_window", "new_window");

	/**
	 * ����ȊO�ijFD�Œ񋟂��Ȃ��j
	 */
	public static final ContainerPosition ETC = new ContainerPosition("etc", null);

	/**
	 * �Ώۖ�
	 */
	private String oponent;
	
	/**
	 * �R���X�g���N�^
	 * @param name
	 */
	private ContainerPosition(String name, String oponent) {
		this.name = name;
		this.oponent = oponent;
		nameInstanceMap.put(name, this);
	}
	
	public static ContainerPosition getInstance(String name) {
		return (ContainerPosition)nameInstanceMap.get(name);
	}
	
	public String toString() {
		return "ContainerConstraints : " + name;
	}
	
	public ContainerPosition getOpenent() {
		return (ContainerPosition)nameInstanceMap.get(oponent);
	}
}
