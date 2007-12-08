/*
 * Created on 2004/05/24
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * ���v�\�����x��
 * 
 * @author shunji
 */
public class ClockLabel extends JLabel {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Timer timer;
	
	Date date = new Date();
	
	/**
	 * �R���X�g���N�^
	 *
	 */
	public ClockLabel() {
		timer = new Timer(1000, new Updater());
		timer.start();
	}

	/**
	 * �\���t�H�[�}�b�g���Z�b�g����B
	 * @param formatStr
	 */
	public void setFormat(String formatStr) {
		format = new SimpleDateFormat(formatStr);
	}
	
	/**
	 * �\���X�V�X���b�h
	 * 
	 * @author shunji
	 */
	private class Updater implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			date.setTime(System.currentTimeMillis());
			setText(format.format(date));
		}
	}
	
	/**
	 * ���v���~����B
	 * ���̃I�u�W�F�N�g�͎����ł͉������Ȃ��̂ŁA
	 * �K�v�Ȃ��Ȃ�����ĂԂ��ƁB
	 *
	 */
	public void stop() {
		timer.stop();
	}
}
