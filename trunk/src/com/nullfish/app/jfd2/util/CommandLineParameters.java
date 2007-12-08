package com.nullfish.app.jfd2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandLineParameters {
	private Map parameterMap = new HashMap();
	
	private List parameters = new ArrayList();
	
	public CommandLineParameters(String[] args) {
		for(int i=0; i<args.length; i++) {
			if(args[i].startsWith("-")) {
				parameters.add(args[i]);
				if(i + 1 < args.length) {
					if(!args[i + 1].startsWith("-")) {
						parameterMap.put(args[i], args[i + 1]);
						i++;
					} else {
						parameterMap.put(args[i], "");
					}
				}
			}
		}
	}
	
	/**
	 * �p�����[�^�ꗗ���擾����B
	 * @return
	 */
	public List getParameters() {
		return parameters;
	}
	
	/**
	 * �p�����[�^�̒l���擾����B
	 * @param name
	 * @return
	 */
	public String getParameter(String name) {
		return (String)parameterMap.get(name);
	}
}
