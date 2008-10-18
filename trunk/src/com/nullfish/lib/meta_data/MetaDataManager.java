/*
 * Created on 2004/05/27
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.meta_data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class MetaDataManager {
	/**
	 * �V���O���g���C���X�^���X
	 */
	private static MetaDataManager instance = new MetaDataManager(); 
	
	/**
	 * �o�^�ς݃f�[�^�^
	 */
	private static DataType[] dataTypes;
	
	Map nameDataTypeMap = new HashMap();

	/**
	 * �^�O����
	 */
	public static final String TAG_NAME = "param";
	
	/**
	 * ���̑���
	 */
	public static final String ATTR_NAME = "name";

	/**
	 * �R���X�g���N�^
	 *
	 */
	private MetaDataManager() {
		DataType[] d = {
				new ListData(),
				new IntegerData(),
				new ColorData(),
				new FontData(),
				new BooleanData(),
				new RendererModeData(),
				new DimensionData(),
				new StringHistoryData(),
				new TextData(),
				new MapData(),
		};

		dataTypes = d;
		
		for(int i=0; i<dataTypes.length; i++) {
			nameDataTypeMap.put(dataTypes[i].getName(), dataTypes[i]);
		}
	}
	
	/**
	 * �V���O���g���C���X�^���X���擾����B
	 * @return
	 */
	public static MetaDataManager getInstance() {
		return instance;
	}
	
	/**
	 * param�^�O�m�[�h����I�u�W�F�N�g�ɕϊ�����B
	 * @param node
	 * @return
	 */
	public Object paramNode2Object(Element node) {
		List children = node.getChildren();
		if(children == null || children.size() == 0) {
			return null;
		}
		
		Element child = (Element)children.get(0);
		
		return node2Object(child);
	}
	
	/**
	 * �m�[�h����I�u�W�F�N�g�ɕϊ�����B
	 * @param node
	 * @return
	 */
	public Object node2Object(Element node) {
		DataType dataType = (DataType)nameDataTypeMap.get(node.getName());
		if(dataType == null) {
			return null;
		}
				
		return dataType.node2Object(node);
	}
	
	/**
	 * �I�u�W�F�N�g��XML�m�[�h�iparam�^�O�j�ɕϊ�����B
	 * @param o
	 * @param name
	 * @return
	 */
	public Element object2ParamNode(Object o, String name) {
		if(o == null || name == null) {
			return null;
		}
		Element e = new Element(TAG_NAME);
		e.setAttribute(ATTR_NAME, name);
		e.addContent(object2Node(o));
		
		return e;
	}
	
	/**
	 * �I�u�W�F�N�g��XML�m�[�h�iparam�^�O�j�ɕϊ�����B
	 * @param o
	 * @param name
	 * @return
	 */
	public Element object2Node(Object o) {
		for(int i=0; i<dataTypes.length; i++) {
			if(dataTypes[i].isConvertable(o)) {
				return dataTypes[i].object2Node(o);
			}
		}
		
		return null;
	}
}