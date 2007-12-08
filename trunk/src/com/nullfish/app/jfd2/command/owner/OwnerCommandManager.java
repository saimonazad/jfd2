/*
 * Created on 2004/05/26
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.owner;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.KeyStroke;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.nullfish.app.jfd2.Initable;
import com.nullfish.app.jfd2.command.JFDException;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.lib.ui.KeyStrokeUtility;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class OwnerCommandManager implements Initable {
	private JFDOwner owner;
	
	/**
	 * �R�}���h���́A�R�}���h�̃}�b�v
	 */
	private Map nameCommandMap = new HashMap();

	/**
	 * KeyStroke�ƃR�}���h���̂̃}�b�v
	 */
	private Map keyNameMap = new HashMap();

	/**
	 * �R�}���h�̃}�b�s���O�^�O
	 */
	public static final String COMMAND_MAP_TAG = "commandmap";

	/**
	 * �R�}���h������
	 */
	public static final String ATTR_COMMANDNAME = "name";

	/**
	 * �N���X������
	 */
	public static final String ATTR_CLASS_NAME = "class";

	/**
	 * �R�}���h��`�t�@�C��
	 */
	public static final String COMMAND_FILE = "owner_command.xml";
	
	/**
	 * �L�[��`�t�@�C��
	 */
	public static final String KEY_FILE = "owner_keys.xml";
	
	/**
	 * �R���X�g���N�^
	 *  
	 */
	public OwnerCommandManager(JFDOwner owner) {
		this.owner = owner;
	}
	
	/**
	 * �R�}���h��XML�̃X�g���[������ǂݍ��݁A����������B
	 * @param is
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void initCommands(InputStream is) throws JDOMException, IOException {
		nameCommandMap.clear();
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);
		List commandNodes = doc.getRootElement().getChildren(
				OwnerCommand.COMMAND_TAG);
		for (int i = 0; i < commandNodes.size(); i++) {
			OwnerCommand command = node2CommandFactory((Element) commandNodes
					.get(i));
			command.setOwner(owner);
			nameCommandMap.put(command.getName(), command);
		}
	}

	/**
	 * ���̓L�[��XML�̃X�g���[������ǂݍ��݁A����������B
	 * @param is
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void initKeyMap(InputStream is) throws JDOMException, IOException {
		keyNameMap.clear();
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);
		List commandMapList = doc.getRootElement().getChildren(COMMAND_MAP_TAG);
		for (int i = 0; i < commandMapList.size(); i++) {
			Element mapping = (Element)commandMapList.get(i);
			String commandName = mapping.getAttributeValue(ATTR_COMMANDNAME);
			List keys = mapping.getChildren(KeyStrokeUtility.KEY_NODE);
			for(int j=0; j<keys.size(); j++) {
				Element keyNode = (Element)keys.get(j);
				KeyStroke keyStroke = KeyStrokeUtility.node2KeyStroke(keyNode);
				keyNameMap.put(keyStroke, commandName);
			}
		}
	}
	
	/**
	 * �m�[�h����R�}���h�𐶐�����B
	 * 
	 * @param element
	 * @return
	 */
	private OwnerCommand node2CommandFactory(Element element) {
		try {
			String className = element.getAttributeValue(ATTR_CLASS_NAME);
			OwnerCommand rtn = (OwnerCommand)Class.forName(className).newInstance();
			
			rtn.convertFromNode(element);
			
			return rtn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * �L�[�Ɋ֘A�t����ꂽ�R�}���h�����s���� �������֘A�t�����ĂȂ��ꍇ��false��Ԃ��
	 * 
	 * @param keyStroke
	 * @return
	 */
	public boolean execute(KeyStroke keyStroke) {
		String name = (String) keyNameMap.get(keyStroke);
		if (name == null) {
			return false;
		}

		return execute(name);
	}

	/**
	 * �R�}���h���̂Ɋ֘A�t����ꂽ�R�}���h�����s���� �������֘A�t�����ĂȂ��ꍇ��false��Ԃ��
	 * 
	 * @param name
	 * @return
	 */
	public boolean execute(String name) {
		try {
			OwnerCommand command = (OwnerCommand) nameCommandMap.get(name);
			if (command == null) {
				return false;
			}
			
			command.start();
		} catch (VFSException e) {
			e.printStackTrace();
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.Initable#init(com.nullfish.lib.vfs.VFile)
	 */
	public void init(VFile baseDir) throws VFSException {
		try {
			initCommands(baseDir.getChild(COMMAND_FILE).getInputStream());
			initKeyMap(baseDir.getChild(KEY_FILE).getInputStream());
		} catch (VFSException e) {
			throw e;
		} catch (Exception e) {
			throw new JFDException(e, "", null);
		}
	}
}