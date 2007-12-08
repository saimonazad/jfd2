package com.nullfish.app.jfd2.config;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextArea;
import javax.swing.text.Document;

public class ListConfigTextField extends JTextArea {
	private String configName;

	public ListConfigTextField(String configName) {
		this.configName = configName;
	}
	
	private static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * ê›íËÇï\é¶Ç…îΩâfÇ∑ÇÈÅB
	 * 
	 * @param configulation
	 */
	public void setConfigulation(Configulation configulation) {
		try {
		List list = (List) configulation.getParam(configName, null);
		setText("");
		
		Document doc = getDocument();
		
		for(int i=0; list != null && i<list.size(); i++) {
			doc.insertString(doc.getLength(), (String)list.get(i), null);
			if(i != list.size() - 1) {
				doc.insertString(doc.getLength(), LINE_SEPARATOR, null);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ê›íËÇ…îΩâfÇ∑ÇÈÅB
	 * 
	 * @param configulation
	 */
	public void apply(Configulation configulation) {
		StringTokenizer tokenizer = new StringTokenizer(getText(), "\n\r");
		List list = new ArrayList();
		while(tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			if(line.length() > 0) {
				list.add(line);
			}
		}
		configulation.setParam(configName, list);
	}
}
