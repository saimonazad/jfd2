/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.meta_data;

import org.jdom.Element;


/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TextData implements DataType {
	public static final String NAME = "text";
	
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
		return node.getText();
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.data_type.DataType#object2String(java.lang.Object)
	 */
	public Element object2Node(Object o) {
		Element rtn = new Element(NAME);
		rtn.setText(o.toString());
		return rtn;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.data_type.DataType#getDataClass()
	 */
	public Class getDataClass() {
		return String.class;
	}
	
	/**
	 * オブジェクトがこのデータ型であるか判別する。
	 * @param o
	 * @return
	 */
	public boolean isConvertable(Object o) {
		return o instanceof String;
	}
}
