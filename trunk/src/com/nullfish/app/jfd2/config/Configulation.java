/*
 * Created on 2004/07/07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.nullfish.lib.meta_data.MetaDataManager;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Configulation {
	/**
	 * �ݒ�
	 */
	private Map params = new LinkedHashMap();

	/**
	 * �ݒ�̃o�[�W����
	 */
	private double configVersion = 0;
	
	private MetaDataManager dataManager = MetaDataManager.getInstance();

	private boolean updatedAfterSave = false;
	
	/**
	 * �ݒ�t�@�C���ƃC���X�^���X�̃}�b�v
	 */
	private static Map fileInstanceMap = new HashMap();
	
	/**
	 * �ݒ�t�@�C���̃��[�g�m�[�h
	 */
	public static final String NODE_ROOT = "config";

	/**
	 * �ݒ�t�@�C���̃o�[�W��������
	 */
	public static final String ATTR_VERSION = "version";

	/**
	 * �R���X�g���N�^
	 *  
	 */
	public Configulation() {
	}

	/**
	 * �ݒ��ǂݍ��ށB
	 * @param is
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void load(InputStream is) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);

		Element root = doc.getRootElement();
		
		String versionStr = root.getAttributeValue(ATTR_VERSION);
		if (versionStr != null && versionStr.length() > 0) {
			try {
				configVersion = Double.parseDouble(versionStr);
			} catch (NumberFormatException e) {}
		} else {
			configVersion = 0;
		}
		
		List nodes = root.getChildren(MetaDataManager.TAG_NAME);
		for (int i = 0; i < nodes.size(); i++) {
			Element element = (Element) nodes.get(i);
			String paramName = element
					.getAttributeValue(MetaDataManager.ATTR_NAME);
			Object paramValue = dataManager.paramNode2Object(element);
			params.put(paramName, paramValue);
		}
	}

	/**
	 * �ݒ��ۑ�����B
	 * �X�g���[���͕ۑ��������B
	 * @param os
	 * @throws IOException
	 */
	public void save(OutputStream os) throws IOException {
		try {
			Element root = new Element(NODE_ROOT);
			root.setAttribute(ATTR_VERSION, Double.toString(configVersion));

			Iterator ite = params.keySet().iterator();
			while (ite.hasNext()) {
				String key = (String) ite.next();
				Element param = dataManager.object2ParamNode(params.get(key), key);
				if(param != null)
				root.addContent(param);
			}
			
			Document doc = new Document();
			doc.setRootElement(root);

			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(doc, os);

			updatedAfterSave = false;
		} finally {
			try {
				os.flush();
			} catch (Exception e) {}
			try {
				os.close();
			} catch (Exception e) {}
		}
	}

	/**
	 * ���ʐݒ���擾����B
	 * @param key
	 * @return
	 */
	public Object getParam(String key, Object defaultValue) {
		Object rtn = params.get(key);
		if(rtn == null) {
			params.put(key, defaultValue);
			return defaultValue;
		}
		
		return rtn;
	}
	
	/**
	 * ���ʐݒ��ݒ肷��B
	 * @param key
	 * @param value
	 */
	public void setParam(String key, Object value) {
		params.put(key, value);
		updatedAfterSave = true;
	}
	
	/**
	 * �ݒ�t�@�C���ɑΉ������C���X�^���X���擾����B
	 * @param file
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 * @throws VFSException
	 */
	public static Configulation getInstance(VFile file) throws JDOMException, IOException, VFSException {
		Configulation rtn = (Configulation) fileInstanceMap.get(file);
		if (rtn == null) {
			rtn = new Configulation();
			rtn.load(file.getInputStream());
			fileInstanceMap.put(file, rtn);
		}
		
		return rtn;
	}

	public double getConfigVersion() {
		return configVersion;
	}

	public void setConfigVersion(double configVersion) {
		this.configVersion = configVersion;
	}
	
	public boolean isUpdatedAfterSave() {
		return updatedAfterSave;
	}
}