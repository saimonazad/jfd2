package com.nullfish.app.jfd2.util;

import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

public class KeyboardMap {
	private String name;
	
	private String mapPath;
	
	private boolean inited = false;
	
	private Map keyMap = new HashMap();
	
	private KeyboardMap(String name, String mapPath) {
		this.name = name;
		this.mapPath = mapPath;
	}
	
	public KeyStroke convert(KeyStroke stroke) {
		return null;
	}
	
	private void init() {
		if(inited) {
			return;
		}
		
		
	}
}
