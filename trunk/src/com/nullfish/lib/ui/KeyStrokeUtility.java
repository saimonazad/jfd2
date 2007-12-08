/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import org.jdom.Element;

import com.nullfish.app.jfd2.config.DefaultConfig;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class KeyStrokeUtility {
	/**
	 * �L�[�^�O��
	 */
	public static final String KEY_NODE = "key";
	
	/**
	 * �L�[���̑���
	 */
	public static final String ATTR_NAME = "name";
	
	/**
	 * �V�t�g�L�[����
	 */
	public static final String ATTR_SHIFT = "shift";
	
	/**
	 * Ctrl�L�[����
	 */
	public static final String ATTR_CTRL = "ctrl";
	
	/**
	 * ���^�L�[����
	 */
	public static final String ATTR_META = "meta";
	
	/**
	 * Alt�L�[����
	 */
	public static final String ATTR_ALT = "alt";
	
	/**
	 * �L�[�𗣂����쑮��
	 */
	public static final String ATTR_ON_RELEASE = "onrelease";
	
	/**
	 * XML�m�[�h����KeyStroke�𐶐�����B
	 * @param node
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static KeyStroke node2KeyStroke(Element node) throws IllegalArgumentException {
		try {
			String keyName = node.getAttributeValue(ATTR_NAME);
			if(keyName == null || keyName.length() == 0) {
				throw new IllegalArgumentException("key name is : " + keyName);
			}
			
			Class keyEventClass = Class.forName("java.awt.event.KeyEvent");
			int keyCode = keyEventClass.getDeclaredField(keyName).getInt(null);
			
			//	���f�B�t�@�C�A
			int modifier = 0;
			if( Boolean.valueOf(node.getAttributeValue(ATTR_SHIFT)).booleanValue() ) {
				modifier += InputEvent.SHIFT_MASK;
			}
			
			if( Boolean.valueOf(node.getAttributeValue(ATTR_CTRL)).booleanValue() ) {
				if(DefaultConfig.getDefaultConfig().isSwapCtrlMeta()) {
					modifier += KeyEvent.META_MASK;
				} else {
					modifier += KeyEvent.CTRL_MASK;
				}
			}
			
			if( Boolean.valueOf(node.getAttributeValue(ATTR_META)).booleanValue() ) {
				if(DefaultConfig.getDefaultConfig().isSwapCtrlMeta()) {
					modifier += KeyEvent.CTRL_MASK;
				} else {
					modifier += KeyEvent.META_MASK;
				}
			}
			
			if( Boolean.valueOf(node.getAttributeValue(ATTR_ALT)).booleanValue() ) {
				modifier += KeyEvent.ALT_MASK;
			}
			
			//	�L�[�����[�X��������
			String onReleaseStr = node.getAttributeValue(ATTR_ON_RELEASE);
			boolean onRelease = false;
			if(onReleaseStr != null && onReleaseStr.length() > 0) {
				onRelease = Boolean.valueOf(onReleaseStr).booleanValue();
			}

			return KeyStroke.getKeyStroke(keyCode, modifier, onRelease);
		} catch (ClassNotFoundException e) {
			//	���������Ȃ��Bjava.awt.event.KeyEvent�͕K�����݂��邽�߁B
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		} catch (SecurityException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (NoSuchFieldException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
