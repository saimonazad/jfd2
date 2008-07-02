/*
 * Created on 2004/05/26
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.KeyStroke;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.nullfish.app.jfd2.Initable;
import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.config.Configulation;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.lib.ui.KeyStrokeUtility;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class CommandManager implements Initable {
	private JFD jfd;

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
	 * �R�}���h��`�t�@�C��
	 */
	public static final String COMMAND_FILE = "command.xml";

	/**
	 * �L�[��`�t�@�C��
	 */
	public static final String KEY_FILE = "keys.xml";

	/**
	 * �R���X�g���N�^
	 * 
	 */
	public CommandManager(JFD jfd) {
		this.jfd = jfd;
	}

	/**
	 * �R�}���h��XML�̃X�g���[������ǂݍ��݁A����������B
	 * 
	 * @param is
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void initCommands(InputStream is, boolean override)
			throws JDOMException, IOException {
		if (!override) {
			nameCommandMap.clear();
		}

		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);
		List commandNodes = doc.getRootElement().getChildren(
				Command.COMMAND_TAG);
		for (int i = 0; i < commandNodes.size(); i++) {
			CommandFactory factory = node2CommandFactory((Element) commandNodes
					.get(i));
			factory.setJFD(jfd);
			nameCommandMap.put(factory.getCommandName(), factory);
		}
	}

	public void addCommandFactory(CommandFactory factory) {
		factory.setJFD(jfd);
		nameCommandMap.put(factory.getCommandName(), factory);
	}

	/**
	 * ���̓L�[��XML�̃X�g���[������ǂݍ��݁A����������B
	 * 
	 * @param is
	 * @param override
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void initKeyMap(InputStream is, boolean override)
			throws JDOMException, IOException {
		if (!override) {
			keyNameMap.clear();
		}

		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);
		List commandMapList = doc.getRootElement().getChildren(COMMAND_MAP_TAG);
		for (int i = 0; i < commandMapList.size(); i++) {
			Element mapping = (Element) commandMapList.get(i);
			String commandName = mapping.getAttributeValue(ATTR_COMMANDNAME);
			List keys = mapping.getChildren(KeyStrokeUtility.KEY_NODE);
			for (int j = 0; j < keys.size(); j++) {
				try {
					Element keyNode = (Element) keys.get(j);
					KeyStroke keyStroke = KeyStrokeUtility
							.node2KeyStroke(keyNode);
					keyNameMap.put(keyStroke, commandName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * �m�[�h����R�}���h�̃t�@�N�g���𐶐�����B
	 * 
	 * @param element
	 * @return
	 */
	private CommandFactory node2CommandFactory(Element element) {
		CommandFactory factory = new DefaultCommandFactory();
		factory.convertFromNode(element);

		return factory;
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
			CommandFactory factory = (CommandFactory) nameCommandMap.get(name);
			if (factory == null) {
				// �Ή�����R�}���h�������ꍇ�̓I�[�i�[�R�}���h���s�������B
				JFDOwner owner = jfd.getJFDOwner();
				if (owner == null) {
					return false;
				}

				return owner.getCommandManager().execute(name);
			}

			Command command = factory.getCommand();
			command
					.addManipulationListener(new JFDManipulationListener(
							command));
			/*
			 * if(command.isShowsProgress()) {
			 * jfd.getProgressViewer().addCommand(command); }
			 */
			if (command.isLocks()) {
				command.getJFD().setLocked(true);
			}

			if (command.isAsynch()) {
				command.startAsync();
			} else {
				command.start();
			}
		} catch (VFSException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.Initable#init(com.nullfish.lib.vfs.VFile)
	 */
	public void init(VFile baseDir) throws VFSException {
		try {
			initCommands(baseDir.getChild(COMMAND_FILE).getInputStream(), false);
			initKeyMap(baseDir.getChild(KEY_FILE).getInputStream(), false);

			Configulation commonConfig = Configulation.getInstance(baseDir.getChild(JFD.COMMON_PARAM_FILE));
			VFile userConfDir = VFS.getInstance(jfd).getFile(
					(String) commonConfig.getParam(
							"user_conf_dir",
							baseDir.getRelativeFile("../.jfd2_user/conf")
									.getAbsolutePath()));
			try {
				VFile userCommandFile = userConfDir.getChild(COMMAND_FILE);
				if (userCommandFile.exists()) {
					initCommands(userCommandFile.getInputStream(), true);
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			}
			try {
				VFile userKeyFile = userConfDir.getChild(KEY_FILE);
				if (userKeyFile.exists()) {
					initKeyMap(userKeyFile.getInputStream(), true);
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			}
		} catch (VFSException e) {
			throw e;
		} catch (Exception e) {
			throw new JFDException(e, "", null);
		}
	}

	/**
	 * �R�}���h������L�[�̃��X�g���擾����B
	 * 
	 * @param command
	 * @return
	 */
	public List getKeys(String command) {
		List rtn = new ArrayList();
		Iterator entriesIte = keyNameMap.entrySet().iterator();
		while (entriesIte.hasNext()) {
			Entry entry = (Entry) entriesIte.next();
			if (command.equals(entry.getValue())) {
				rtn.add(entry.getKey());
			}
		}

		return rtn;
	}
	
	public List getCommmandNames() {
		List rtn = new ArrayList(nameCommandMap.keySet());
		Collections.sort(rtn);
		
		return rtn;
	}
}