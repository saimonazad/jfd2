/*
 * Created on 2004/05/18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.config.Configulation;
import com.nullfish.app.jfd2.util.ShortCutFile;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.permission.ClassFileAccess;
import com.nullfish.lib.vfs.permission.PermissionType;

/**
 * ファイルを表示するセルレンダラ。
 * 
 * @author shunji
 */
public class JFDCellRenderer extends JPanel implements TableCellRenderer {

	private JFD jfd;
	private JFDModel jfdModel;

	private RendererMode mode;
	
	JLabel selectionLabel = new JLabel();
	FileNameLabel2 nameLabel = new FileNameLabel2();
//	FileNameLabel nameLabel = new FileNameLabel();
	JLabel sizeLabel = new JLabel();
	JLabel dateLabel = new JLabel();

	GridBagLayout layout = new GridBagLayout();

	DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	Color bgColor = Color.BLACK;
	Color defaultLabelColor = Color.WHITE;
//    Color markedColor = Color.white;
    Color currentDirectoryColor = Color.white;
    Color subDirectoryColor = Color.BLUE;
    Color parentDirectoryColor = Color.WHITE;
    Color readOnlyColor = Color.YELLOW;
    Color linkColor = Color.BLUE;

	private DecimalFormat sizeFormat = new DecimalFormat(" ###,###,###,##0 ");
	
    /**
	 * ディレクトリ表示文字列
	 */
	public static final String DIR_STR = "<dir> ";

    /**
	 * ショートカット表示文字列
	 */
	public static final String LINK_STR = "<lnk> ";

	/**
	 * デフォルトの日付フォーマット
	 */
	public static final String DEFAULT_DATE_FORMAT = "yy-MM-dd HH:mm ";

	/**
	 * 値が不明な場合に表示される文字列
	 */
	public static final String NOT_AVAILABLE = "N/A ";

	/**
	 * コンストラクタ
	 * 
	 * @param jfdModel
	 * @param selectionModel
	 */
	public JFDCellRenderer(JFD jfd) {
		this.jfd = jfd;
		this.jfdModel = jfd.getModel();
		
		initUi();
		setOpaque(false);
		this.setBackground(bgColor);
		this.setForeground(defaultLabelColor);
		setMode(RendererMode.TYPE_1);
	}

	public void initUi() {
		setLayout(layout);
		add(selectionLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		add(nameLabel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		add(sizeLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		add(dateLabel, new GridBagConstraints(3, 0, 1, 1, 0.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		selectionLabel.setOpaque(false);
		nameLabel.setOpaque(false);
		sizeLabel.setOpaque(false);
		dateLabel.setOpaque(false);

		sizeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
	 *      java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value == null) {
			selectionLabel.setText("");
			nameLabel.setFile(null, null, false);
			sizeLabel.setText("");
			dateLabel.setText("");
			initLabelColor(null, null, isSelected);
			return this;
		}

		VFile file = (VFile) value;

		VFile current = jfdModel.getCurrentDirectory();

		//	マーク
		int rowCount = jfd.getRowCount();
		int columnCount = jfd.getColumnCount();
		int page = jfd.getPage();
		int index = (rowCount * columnCount * page) + (rowCount * column) + row;
		setMarked(jfdModel.isMarked(index));
		
		initSizeLabel(file);
		initDateLabel(file);
		initLabelColor(file, current, isSelected);

		nameLabel.setFile(file, current, mode.isRelative());

		return this;
	}

	private void setMarked(boolean selected) {
		selectionLabel.setText(selected ? "*" : " ");
	}

	/**
	 * サイズ表示初期化
	 */
	private void initSizeLabel(VFile file) {
		if (!sizeLabel.isVisible()) {
			return;
		}

		try {
			if (file.isDirectory()) {
				sizeLabel.setText(DIR_STR);
			} else if(ShortCutFile.EXTENSION.equals(file.getFileName().getExtension())) {
				sizeLabel.setText(LINK_STR);
			} else {
				sizeLabel.setText(sizeFormat.format(file.getLength()));
			}
		} catch (VFSException e) {
			sizeLabel.setText(NOT_AVAILABLE);
		}
	}

	/**
	 * 日付表示初期化
	 * 
	 * @param file
	 */
	private void initDateLabel(VFile file) {
		if (!dateLabel.isVisible()) {
			return;
		}

		try {
			Date date = file.getTimestamp();
			if(date != null) {
				dateLabel.setText(dateFormat.format(date));
			} else {
				dateLabel.setText(NOT_AVAILABLE);
			}
		} catch (VFSException e) {
			dateLabel.setText(NOT_AVAILABLE);
		}
	}

	private void initLabelColor(VFile file, VFile current, boolean selected) {
		if (file == null || current == null) {
			setOpaque(false);
			this.setBackground(bgColor);
			return;
		}
		
		Color foreColor;
		Color backColor;
		boolean writable = true;
		boolean isDirectory = false;		
		try {
			isDirectory = file.isDirectory();
			writable = file.getPermission().hasPermission(PermissionType.WRITABLE, ClassFileAccess.ALL);
		} catch (Exception e) {
			writable = false;
		}
		
		if(file.equals(current)) {
			foreColor = currentDirectoryColor;
		} else if(file.equals(current.getParent())) {
			foreColor = parentDirectoryColor;
		} else if(ShortCutFile.EXTENSION.equals(file.getFileName().getExtension())) {
			foreColor = linkColor;
		} else if(!writable) {
			foreColor = readOnlyColor;
		} else if(isDirectory) {
			foreColor = subDirectoryColor;
		} else {
			foreColor = defaultLabelColor;
		}

		if (selected) {
			setOpaque(true);
			backColor = foreColor;
			foreColor = bgColor;
		} else {
			setOpaque(false);
			backColor = bgColor;
		}
		
		this.setBackground(backColor);
		selectionLabel.setForeground(foreColor);
		nameLabel.setForeground(foreColor);
		sizeLabel.setForeground(foreColor);
		dateLabel.setForeground(foreColor);
	}
	
	public void setLabelFont(Font font) {
		selectionLabel.setFont(font);
		nameLabel.setFont(font);
		sizeLabel.setFont(font);
		dateLabel.setFont(font);
	}
	
	/**
	 * 表示モードをセットする。
	 * @param mode
	 */
	public void setMode(RendererMode mode) {
		this.mode = mode;
		sizeLabel.setVisible(mode.isSizeVisible());
		dateLabel.setVisible(mode.isDateVisible());
		dateFormat = mode.getDateFormat();
	}
	
	/**
	 * 表示モードを取得する。
	 * @return
	 */
	public RendererMode getMode() {
		return mode;
	}
	
	/**
	 * これでパフォーマンスが上がる、みたいなことがどこかに書いてあったんだが、
	 * 嘘か本当か怪しい。
	 */
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}
    public void firePropertyChange(String propertyName, byte oldValue, byte newValue) {}
    public void firePropertyChange(String propertyName, char oldValue, char newValue) {}
    public void firePropertyChange(String propertyName, double oldValue, double newValue) {}
    public void firePropertyChange(String propertyName, float oldValue, float newValue) {}
    public void firePropertyChange(String propertyName, int oldValue, int newValue) {}
    public void firePropertyChange(String propertyName, long oldValue, long newValue) {}
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {}
    public void firePropertyChange(String propertyName, short oldValue, short newValue) {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}

	/**
	 * 色を初期化する。
	 * @see com.nullfish.app.jfd2.Initable#init(com.nullfish.lib.vfs.VFile)
	 */
	public void initColor() throws VFSException {
		Configulation config = jfd.getCommonConfigulation();

		bgColor = (Color)config.getParam("background_color", Color.BLACK);
		
		defaultLabelColor = (Color)config.getParam("default_label_color", Color.WHITE);

		currentDirectoryColor = (Color)config.getParam("current_directory_color", Color.WHITE);
		
		subDirectoryColor = (Color)config.getParam("sub_directory_color", Color.CYAN);
		
		parentDirectoryColor = (Color)config.getParam("parent_directory_color", Color.WHITE);
		
		readOnlyColor = (Color)config.getParam("read_only_color", Color.YELLOW);
		
		linkColor = (Color)config.getParam("link_color", Color.GREEN);
	}
}