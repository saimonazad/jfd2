/*
 * Created on 2004/08/17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.lib.ui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/**
 * �X���b�h�Z�[�t�֌W���[�e�B���e�B�N���X�B
 * 
 * @author shunji
 */
public class ThreadSafeUtilities {
	/**
	 * Runnable�����s����B
	 * Swing�̃V���O���X���b�h���[���Ɉ�v����悤�ɂ��A
	 * �������C�x���g�f�B�X�p�b�`�X���b�h�ł͂Ȃ��ꍇ�͓������s������B
	 * 
	 * @param runnable
	 */
	public static void executeRunnable(Runnable runnable) {
		executeRunnable(runnable, true);
	}
	
	/**
	 * Runnable�����s����B
	 * Swing�̃V���O���X���b�h���[���Ɉ�v����悤�ɂ��A
	 * �������C�x���g�f�B�X�p�b�`�X���b�h�ł͂Ȃ��ꍇ�͓������s������B
	 * 
	 * @param runnable
	 * @param waits
	 */
	public static void executeRunnable(Runnable runnable, boolean waits) {
		if(SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			try {
				if(waits) {
					SwingUtilities.invokeAndWait(runnable);
				} else {
					SwingUtilities.invokeLater(runnable);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ReturnableRunnable�����s���AgetReturnValue�̒l��Ԃ��B
	 * Swing�̃V���O���X���b�h���[���Ɉ�v����悤�ɂ��A
	 * �������C�x���g�f�B�X�p�b�`�X���b�h�ł͂Ȃ��ꍇ�͓������s������B
	 * 
	 * @param runnable
	 */
	public static Object executeReturnableRunnable(ReturnableRunnable runnable) {
		if(SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(runnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return runnable.getReturnValue();
	}
}
