/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.owner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.jdom.Element;

import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.lib.meta_data.MetaDataManager;
import com.nullfish.lib.vfs.Manipulation;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public abstract class OwnerCommand extends AbstractManipulation {
	/**
	 * jFDOwner�̎Q��
	 */
	private JFDOwner owner;

	/**
	 * ����
	 */
	private String name;

	/**
	 * �p�����[�^�̃}�b�v
	 */
	private Map paramsMap;

	/**
	 * �R�}���h�^�O��
	 */
	public static final String COMMAND_TAG = "command";

	/**
	 * ���̑���
	 */
	public static final String ATTR_NAME = "name";

	/**
	 * �L���b�V���g�p����
	 */
	public static final String ATTR_CACHE = "cache";

	/**
	 * ���̑���
	 */
	public static final String ATTR_ASYNCH = "asynch";

	/**
	 * �p�����[�^�^�O
	 */
	public static final String PARAM_TAG = "param";

	/**
	 * �S�q����
	 */
	private Manipulation[] manipulations;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param name
	 */
	public OwnerCommand() {
		super(null);
	}

	/**
	 * Manipulation#start()�̃I�[�o�[���C�h�B
	 * 
	 */
	public void start() throws VFSException {
		try {
			super.start();
		} catch (Exception e) {
			JTextArea text = new JTextArea();

			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			writer.flush();
			try {
				writer.close();
			} catch (IOException e1) {
			}

			text.setText(writer.toString());
			JOptionPane.showMessageDialog(null, text);
		}
	}

	/**
	 * ����̖��̂�Ԃ��
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the jfdModel.
	 */
	public JFDOwner getJFDOwner() {
		return owner;
	}

	public Object getParameter(String name) {
		if (paramsMap == null) {
			return null;
		}

		return paramsMap.get(name);
	}

	public void setParameter(String name, Object value) {
		if (paramsMap == null) {
			paramsMap = new HashMap();
		}
		paramsMap.put(name, value);
	}

	/**
	 * 
	 * @param paramsMap
	 */
	public void setParamsMap(Map paramsMap) {
		this.paramsMap = paramsMap;
	}

	/**
	 * ��ƌo�߃��b�Z�[�W���擾����B
	 * 
	 * @return
	 */
	public String getProgressMessage() {
		Manipulation currentManipulation = getCurrentManipulation();
		
		if(currentManipulation == null) {
			return "";
		}
		
		if (currentManipulation != this) {
			return currentManipulation.getProgressMessage();
		}

		return "";
	}

	/**
	 * �q�����ݒ肷��B
	 * 
	 * @param children
	 */
	protected void setChildManipulations(Manipulation[] children) {
		manipulations = children;
	}

	public long getProgressMin() {
		if (manipulations == null) {
			return PROGRESS_INDETERMINED;
		}

		int sum = 0;
		for (int i = 0; i < manipulations.length; i++) {
			long p = manipulations[i].getProgressMin();
			if (p == PROGRESS_INDETERMINED) {
				return PROGRESS_INDETERMINED;
			}
			sum += p;
		}

		return sum;
	}

	public long getProgressMax() {
		if (manipulations == null) {
			return PROGRESS_INDETERMINED;
		}

		int sum = 0;
		for (int i = 0; i < manipulations.length; i++) {
			long p = manipulations[i].getProgressMax();
			if (p == PROGRESS_INDETERMINED) {
				return PROGRESS_INDETERMINED;
			}
			sum += p;
		}

		return sum;
	}

	public long getProgress() {
		if (manipulations == null) {
			return PROGRESS_INDETERMINED;
		}

		int sum = 0;
		for (int i = 0; i < manipulations.length; i++) {
			long p = manipulations[i].getProgress();
			if (p == PROGRESS_INDETERMINED) {
				return PROGRESS_INDETERMINED;
			}
			sum += p;
		}

		return sum;
	}

	public JFDOwner getOwner() {
		return owner;
	}
	

	public void setOwner(JFDOwner owner) {
		this.owner = owner;
	}
	
	public void convertFromNode(Element node) {
		name = node.getAttributeValue(ATTR_NAME);
		
		List params = node.getChildren(PARAM_TAG);
		for(int i=0; i<params.size(); i++) {
			Element param = (Element)params.get(i);
			String name = param.getAttributeValue(MetaDataManager.ATTR_NAME);
			Object value = MetaDataManager.getInstance().paramNode2Object(param);
			setParameter(name, value);
		}
	}
	
}
