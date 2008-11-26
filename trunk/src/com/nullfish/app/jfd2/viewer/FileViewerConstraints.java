/*
 * Created on 2004/12/28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.viewer;

import java.util.HashMap;
import java.util.Map;

import com.nullfish.app.jfd2.JFDComponent;
import com.nullfish.app.jfd2.ui.container2.ContainerPosition;
import com.nullfish.app.jfd2.viewer.constraints.MainConstraints;
import com.nullfish.app.jfd2.viewer.constraints.NoneConstraints;
import com.nullfish.app.jfd2.viewer.constraints.OponentConstraints;
import com.nullfish.app.jfd2.viewer.constraints.SameConstraints;
import com.nullfish.app.jfd2.viewer.constraints.SubConstraints;

/**
 * 
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class FileViewerConstraints {
	/**
	 * ���̂ƃC���X�^���X�̃}�b�v
	 */
	private static Map nameInstanceMap = new HashMap();
	
	public static FileViewerConstraints MAIN = new MainConstraints();
	public static FileViewerConstraints SUB = new SubConstraints();
	public static FileViewerConstraints OPONENT = new OponentConstraints();
	public static FileViewerConstraints SAME = new SameConstraints();
	public static FileViewerConstraints NONE = new NoneConstraints();

	/**
	 * �R���X�g���N�^
	 * @param name
	 */
	public FileViewerConstraints(String name) {
		nameInstanceMap.put(name, this);
	}
	
	/**
	 * ���̂���C���X�^���X���擾����B
	 * @param name
	 * @return
	 */
	public static FileViewerConstraints getInstance(String name) {
		return (FileViewerConstraints)nameInstanceMap.get(name);
	}
	
	/**
	 * 
	 * @param jfd
	 * @return
	 */
	public abstract ContainerPosition getPosition(JFDComponent component);
}
