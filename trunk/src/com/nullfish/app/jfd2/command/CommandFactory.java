/*
 * Created on 2004/05/27
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.meta_data.MetaDataManager;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class CommandFactory {
	/**
	 * �R�}���h��
	 */
	private String commandName;

	/**
	 * JFD
	 */
	private JFD jfd;
	
	/**
	 * �R�}���h�̃p�����[�^�̃}�b�v
	 */
	private Map paramMap = new HashMap();

	/**
	 * �L���b�V���g�p�t���O
	 */
	private boolean usesCache = false;
	
	/**
	 * �R�}���h�̃L���b�V��
	 */
	private Command cache;
	
	/**
	 * �񓯊����s�t���O
	 */
	private boolean asynch = false;
	
	/**
	 * �o�ߕ\���̑҂�����
	 */
	private int delay = -1;
	
	/**
	 * ���s���̉�ʃ��b�N���s�����̃t���O
	 */
	private boolean locks = false;
	
	/**
	 * �呀��Ƃ��ĉ�ʂɃZ�b�g����A�o�ߕ\���A���~�����t���s�Ȃ����̃t���O
	 */
	private boolean primary = false;
	
	/**
	 * ���~���Ƀ_�C�A���O�\�����s�Ȃ����̃t���O
	 */
	private boolean showsStopped = true;
	
	public CommandFactory() {
	}
	
	public void convertFromNode(Element node) {
		commandName = node.getAttributeValue(Command.ATTR_NAME);
		
		String usesCacheStr = node.getAttributeValue(Command.ATTR_CACHE);
		if(usesCacheStr != null && usesCacheStr.length() > 0) {
			usesCache = Boolean.valueOf(usesCacheStr).booleanValue();
		}
		
		String asynchStr = node.getAttributeValue(Command.ATTR_ASYNCH);
		if(asynchStr != null && asynchStr.length() > 0) {
			asynch = Boolean.valueOf(asynchStr).booleanValue();
		}
		
		String locksStr = node.getAttributeValue(Command.ATTR_LOCKS);
		if(locksStr != null && locksStr.length() > 0) {
			locks = Boolean.valueOf(locksStr).booleanValue();
		}

		String primaryStr = node.getAttributeValue(Command.ATTR_PRIMARY);
		if(primaryStr != null && primaryStr.length() > 0) {
			primary = Boolean.valueOf(primaryStr).booleanValue();
		}

		String showsStoppedStr = node.getAttributeValue(Command.ATTR_SHOWS_STOP_DIALOG);
		if(showsStoppedStr != null && showsStoppedStr.length() > 0) {
			showsStopped = Boolean.valueOf(showsStoppedStr).booleanValue();
		}

		List params = node.getChildren(Command.PARAM_TAG);
		for(int i=0; i<params.size(); i++) {
			Element param = (Element)params.get(i);
			String name = param.getAttributeValue(MetaDataManager.ATTR_NAME);
			Object value = MetaDataManager.getInstance().paramNode2Object(param);
			paramMap.put(name, value);
		}
	}
	
	public void init(String commandName, boolean usesCache, boolean asynch, int delay, boolean locks, boolean primary, boolean showsStopped) {
		this.commandName = commandName;
		this.usesCache = usesCache;
		this.asynch = asynch;
		this.delay = delay;
		this.locks = locks;
		this.primary = primary;
		this.showsStopped = showsStopped;
	}
	
	/**
	 * �p�����[�^���Z�b�g����B
	 * @param name
	 * @param value
	 */
	public void setParam(String name, Object value) {
		paramMap.put(name, value);
	}
	
	/**
	 * �p�����[�^���擾����B
	 * @param name
	 * @return
	 */
	public Object getParam(String name) {
		return paramMap.get(name);
	}
	
	public final Command getCommand() {
		//	�L���b�V���g�p
		if(usesCache && cache != null) {
			return cache;
		}
		
		Command rtn = doGetCommand();
		if(usesCache) {
			cache = rtn;
		}
		
		rtn.setAsynch(asynch);
		rtn.setLocks(locks);
		rtn.setName(commandName);
		rtn.setJFD(jfd);
		rtn.setParamsMap(paramMap);
		rtn.setPrimary(primary);
		rtn.setShowsStopDialog(showsStopped);
		
		return rtn;
	}
	
	/**
	 * �R�}���h�I�u�W�F�N�g�𐶐����ĕԂ��
	 * @return
	 */
	public abstract Command doGetCommand();

	/**
	 * @return Returns the commandName.
	 */
	public String getCommandName() {
		return commandName;
	}
	
	/**
	 * @param jfdModel The jfdModel to set.
	 */
	public void setJFD(JFD jfd) {
		this.jfd = jfd;
	}
}
