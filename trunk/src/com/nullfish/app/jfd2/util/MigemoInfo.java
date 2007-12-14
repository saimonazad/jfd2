package com.nullfish.app.jfd2.util;

import java.io.File;

import org.monazilla.migemo.Migemo;

public class MigemoInfo {
	
	private static boolean usesMigemo = false;

	private static boolean init = false;
	
	private static void init() {
		if(init) {
			return;
		}
		init = true;
		Migemo.loadDictionary(new File("migemo-dict"),"EUC_JP");
//		Migemo.loadDictionary(new File("cmigemo-dict"),"MS932");
		usesMigemo = true;
	}

	public static boolean usesMigemo() {
		init();
		return usesMigemo;
	}
}
