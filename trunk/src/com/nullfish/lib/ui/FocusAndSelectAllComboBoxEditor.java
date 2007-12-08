/*
 * Created on 2004/09/07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.lib.ui;

import javax.swing.plaf.basic.BasicComboBoxEditor;


/**
 * フォーカスされたら選択状態になるコンボボックスエディタ
 */
public class FocusAndSelectAllComboBoxEditor extends BasicComboBoxEditor {
    public FocusAndSelectAllComboBoxEditor() {
        super();
    }

    public void setItem(Object anObject) {
        super.setItem(anObject);
        selectAll();
    }
}