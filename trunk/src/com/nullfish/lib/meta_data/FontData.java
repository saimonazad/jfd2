/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.meta_data;

import java.awt.Font;

import org.jdom.Element;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class FontData implements DataType {
	public static final String NAME = "font";
	
	/**
	 * �t�H���g��
	 */
	public static final String ATTR_FONT_NAME = "fontname";
	
	/**
	 * �{�[���h
	 */
	public static final String ATTR_BOLD = "bold";
	
	/**
	 * �C�^���b�N
	 */
	public static final String ATTR_ITALIC = "italic";
	
	/**
	 * �T�C�Y
	 */
	public static final String ATTR_SIZE = "size";
	
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.data_type.DataType#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.data_type.DataType#string2Object(java.lang.String)
	 */
	public Object node2Object(Element node) {
		int style = Font.PLAIN;
		if("true".equals(node.getAttributeValue(ATTR_BOLD))) {
			style = style | Font.BOLD;
		}
		if("true".equals(node.getAttributeValue(ATTR_ITALIC))) {
			style = style | Font.ITALIC;
		}
		
		int size = 12;
		try {
			size = Integer.parseInt( node.getAttributeValue(ATTR_SIZE) );
		} catch (Exception e) {}
		
		return new Font(node.getAttributeValue(ATTR_FONT_NAME), style, size);
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.data_type.DataType#object2String(java.lang.Object)
	 */
	public Element object2Node(Object o) {
		Font font = (Font)o;
		Element rtn = new Element(NAME);
		rtn.setAttribute(ATTR_FONT_NAME, font.getName());
		rtn.setAttribute(ATTR_BOLD, Boolean.toString(font.isBold()));
		rtn.setAttribute(ATTR_ITALIC, Boolean.toString(font.isItalic()));
		rtn.setAttribute(ATTR_SIZE, Integer.toString(font.getSize()));
		
		return rtn;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.data_type.DataType#getDataClass()
	 */
	public Class getDataClass() {
		return Font.class;
	}

	/**
	 * �I�u�W�F�N�g�����̃f�[�^�^�ł��邩���ʂ���B
	 * @param o
	 * @return
	 */
	public boolean isConvertable(Object o) {
		return o instanceof Font;
	}
}
