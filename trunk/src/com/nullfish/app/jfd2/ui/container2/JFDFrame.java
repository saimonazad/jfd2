/*
 * Created on 2005/01/19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.container2;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.dnd.DropTarget;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDComponent;
import com.nullfish.app.jfd2.command.owner.OwnerCommandManager;
import com.nullfish.app.jfd2.config.Configulation;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.ui.container2.components.TabContainer;
import com.nullfish.app.jfd2.ui.container2.components.VisibilityChangeTabbedPane;
import com.nullfish.app.jfd2.util.thumbnail.ThumbnailDataBase;
import com.nullfish.app.jfd2.viewer.FileViewerManager;
import com.nullfish.lib.command.CommandCallable;
import com.nullfish.lib.keymap.KeyStrokeMap;
import com.nullfish.lib.plugin.PluginManager;
import com.nullfish.lib.resource.ResourceManager;
import com.nullfish.lib.ui.ThreadSafeUtilities;
import com.nullfish.lib.ui.layout.EqualSplitLayout;
import com.nullfish.lib.ui.xml_menu.XMLMenuBar;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JFDFrame extends JFrame implements JFDOwner {
	/**
	 * 設定のディレクトリ
	 */
	private VFile configDir;

	private JPanel contentPanel = new JPanel() {
		/**
		 * JComponent#processKeyEventの拡張。 キー入力を元に、登録されたコマンドを実行する。
		 */
	    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
				int condition, boolean pressed) {
			if (commandManager.execute(ks)) {
				e.consume();
				return true;
			}

			return super.processKeyBinding(ks, e, condition, pressed);
		}
	};

	private VisibilityChangeTabbedPane mainTabbedPane = new VisibilityChangeTabbedPane();

	private VisibilityChangeTabbedPane subTabbedPane = new VisibilityChangeTabbedPane();

	private XMLMenuBar menuBar;

	private List components = new ArrayList();

	private EqualSplitLayout layout = new EqualSplitLayout();
	
	private JFDComponent activeComponent;
	
	/**
	 * コンポーネントとコンテナのマップ
	 */
	private static Map componentContainerMap = new HashMap();

	/**
	 * コンポーネントと位置のマップ
	 */
	private static Map componentPositionMap = new HashMap();

	/**
	 * サーバーモード
	 */
	private static boolean serverMode = false;
	
	private static List instances = new ArrayList();

	private OwnerCommandManager commandManager = new OwnerCommandManager(this);
	
	/**
	 * JFD2のインスタンスのリスト
	 */
	public static final String MENUBAR_CONFIG = "menubar.xml";
	public static int MAX_TITLE_LENGTH = 40;

	public static final String CONFIG_X = "frame_x";
	public static final String CONFIG_Y = "frame_y";
	public static final String CONFIG_WIDTH = "frame_width";
	public static final String CONFIG_HEIGHT = "frame_height";
	
	public JFDFrame(VFile configDir) {
		instances.add(this);

		this.configDir = configDir;
		initGUI();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				while(components.size() > 0) {
					JFDComponent c = (JFDComponent)components.get(0);
					removeComponent(c);
					c.dispose();
				}
				
				dispose();
			}
			
			public void windowActivated(WindowEvent e) {
				JFDComponent c = getActiveComponent();
				if(c != null) {
					c.getComponent().requestFocusInWindow();
				}
			}
		});
	
		try {
			commandManager.init(configDir);
		} catch (VFSException e1) {
			e1.printStackTrace();
		}
	}

	public void pack() {
		super.pack();
		try {
			Configulation config = Configulation.getInstance(configDir.getChild(JFD.COMMON_PARAM_FILE));
			Rectangle rect = new Rectangle();
			rect.x = ((Integer)config.getParam(CONFIG_X, new Integer(0))).intValue();
			rect.y = ((Integer)config.getParam(CONFIG_Y, new Integer(0))).intValue();
			rect.width = ((Integer)config.getParam(CONFIG_WIDTH, new Integer(640))).intValue();
			rect.height = ((Integer)config.getParam(CONFIG_HEIGHT, new Integer(480))).intValue();
			setBounds(rect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dispose() {
		try {
			VFile commonConfigFile = configDir.getChild(JFD.COMMON_PARAM_FILE);
			Configulation config = Configulation.getInstance(commonConfigFile);
			
			Rectangle rect = getBounds();
			config.setParam(CONFIG_X, new Integer(rect.x));
			config.setParam(CONFIG_Y, new Integer(rect.y));
			config.setParam(CONFIG_WIDTH, new Integer(rect.width));
			config.setParam(CONFIG_HEIGHT, new Integer(rect.height));
			config.save(commonConfigFile.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FileViewerManager.getInstance().dispose(this);
		PluginManager.getInstance().jfdOwnerDisposed(this);
		
		super.dispose();
		
		instances.remove(this);
		
		if(!serverMode && instances.size() == 0) {
			ThumbnailDataBase.getInstance().close();
			System.exit(0);
		}
	}
	
	private void initGUI() {
		setContentPane(contentPanel);
		
		setTitle("jFD2");
		
		contentPanel.setLayout(layout);
		contentPanel.add(mainTabbedPane, EqualSplitLayout.FIRST);
		contentPanel.add(subTabbedPane, EqualSplitLayout.SECOND);

		initTabbedPane(mainTabbedPane);
		initTabbedPane(subTabbedPane);

		// mainTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		TabbedPaneDropTargetListener mainDropListener = new TabbedPaneDropTargetListener(
				mainTabbedPane);
		new DropTarget(mainTabbedPane, mainDropListener);

		TabbedPaneDropTargetListener subDropListener = new TabbedPaneDropTargetListener(
				subTabbedPane);
		new DropTarget(subTabbedPane, subDropListener);

		initMenu();
	}

	private void initTabbedPane(final JTabbedPane tabbedPane) {
		tabbedPane.setFocusable(false);

		TabbedPaneDropTargetListener dropListener = new TabbedPaneDropTargetListener(
				tabbedPane);
		new DropTarget(tabbedPane, dropListener);
	}

	private void initMenu() {
		ResourceManager resource = new ResourceManager() {
			public String getResource(String name) {
				return JFDResource.LABELS.getString(name);
			}
		};

		CommandCallable callable = new CommandCallable() {
			public void callCommand(String command) {
				NumberedJFD2 jfd = NumberedJFD2.getActiveJFD();
				if (jfd == null) {
					return;
				}

				jfd.getCommandManager().execute(command);
			}
		};

		menuBar = new XMLMenuBar(resource, callable);
		try {
			Document doc = new SAXBuilder().build(configDir.getChild(
					MENUBAR_CONFIG).getInputStream());
			menuBar.convertFromNode(doc.getRootElement());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setJMenuBar(menuBar);
		addWindowFocusListener(new WindowFocusListener() {

			public void windowGainedFocus(WindowEvent e) {
				closeAllMenu();
			}

			public void windowLostFocus(WindowEvent e) {
				closeAllMenu();
			}

			private void closeAllMenu() {
				int c = menuBar.getMenuCount();
				JMenu menu;

				for (int i = 0; i < c; i++) {
					menu = menuBar.getMenu(i);
					if (menu.isSelected()) {
						menu.setSelected(false);
					}

					if (menu.isPopupMenuVisible()) {
						menu.setPopupMenuVisible(false);
					}
				}
			}
		});
	}

	/**
	 * 現在アクティブなjFDを取得する。
	 * @return
	 */
	public JFDComponent getActiveComponent() {
/*
		JTabbedPane tabbedPane = getActiveTabbedPane();
		if(tabbedPane == null) {
			return null;
		}
		
		return ((JFDContainer)tabbedPane.getSelectedComponent()).getComponent();
*/
		return activeComponent;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.ui.container2.JFDOwner#setActiveComponent(java.awt.Component)
	 */
	public void setActiveComponent(final JFDComponent component) {
		Runnable runnable = new Runnable() {
			public void run() {
				JFDContainer container = (JFDContainer) componentContainerMap
						.get(component);
				if (container != null) {
					container.requestContainerFocus();
				}
			}
		};
		
		ThreadSafeUtilities.executeRunnable(runnable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.ui.container2.JFDOwner#getCount()
	 */
	public int getCount() {
		return mainTabbedPane.getTabCount() + subTabbedPane.getTabCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.ui.container2.JFDOwner#addComponent(com.nullfish.app.jfd2.JFDComponent,
	 *      com.nullfish.app.jfd2.ui.container2.ContainerConstraints,
	 *      com.nullfish.app.jfd2.ui.container2.TitleUpdater)
	 */
	public void addComponent(final JFDComponent component,
			final com.nullfish.app.jfd2.ui.container2.ContainerPosition position,
			final TitleUpdater titleUpdater) {
		Runnable runnable = new Runnable() {
			public void run() {
				if (position == ContainerPosition.NEW_WINDOW) {
					JFDFrame newFrame = new JFDFrame(configDir);
					newFrame.addComponent(component, ContainerPosition.MAIN_PANEL,
							titleUpdater);
					newFrame.pack();
					newFrame.setVisible(true);
					return;
				}

				component.setOwner(JFDFrame.this);

				JFDContainer container;
				if (ContainerPosition.MAIN_PANEL == position) {
					container = new TabContainer(mainTabbedPane);
				} else {
					container = new TabContainer(subTabbedPane);
				}
				
				components.add(component);
				componentContainerMap.put(component, container);
				componentPositionMap.put(component, position);

				container.setComponent(component);
				container.setContainerVisible(true);
				titleUpdater.setContainer(container);

				setActiveComponent(component);
			}
		};
		
		ThreadSafeUtilities.executeRunnable(runnable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.ui.container2.JFDOwner#removeComponent(java.awt.Component)
	 */
	public void removeComponent(JFDComponent component) {
		JFDContainer container = (JFDContainer) componentContainerMap
				.get(component);
		if (container == null) {
			return;
		}

		container.setContainerVisible(false);
		container.clearComponent();
		container.dispose();

		components.remove(component);
		componentContainerMap.remove(component);
		componentPositionMap.remove(component);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.ui.container2.JFDOwner#getConfigDirectory()
	 */
	public VFile getConfigDirectory() {
		return configDir;
	}

	/**
	 * コンポーネントの位置を求める。
	 * 
	 * @see com.nullfish.app.jfd2.ui.container2.JFDOwner#getComponentPosition(com.nullfish.app.jfd2.JFDComponent)
	 */
	public ContainerPosition getComponentPosition(JFDComponent component) {
		return (ContainerPosition) componentPositionMap.get(component);
	}

	public void changeSplit() {
		layout.setHorizontal(!layout.isHorizontal());
		this.doLayout();
	}

	public OwnerCommandManager getCommandManager() {
		return commandManager;
	}
	
	public void increasePosition(int delta) throws VFSException {
		Component c = getFocusOwner();

		Container container = c.getParent();
		int index = 0;
		while(true) {
			if(container == null) {
				return;
			}
			
			if(container == mainTabbedPane) {
				index = mainTabbedPane.getSelectedIndex();
				break;
			}
			
			if(container == subTabbedPane) {
				index = subTabbedPane.getSelectedIndex() + mainTabbedPane.getTabCount();
				break;
			}
			
			container = container.getParent();
		}
		
		index += delta;
		
		int allTabCount = mainTabbedPane.getTabCount() + subTabbedPane.getTabCount();
		
		while(index < 0) {
			index += allTabCount;
		}
		
		while(index >= allTabCount) {
			index -= allTabCount;
		}
		
		TabContainer tabContainer;
		if(index < mainTabbedPane.getTabCount()) {
			tabContainer = (TabContainer)mainTabbedPane.getComponentAt(index);
		} else {
			tabContainer = (TabContainer)subTabbedPane.getComponentAt(index - mainTabbedPane.getTabCount());
		}
		
		tabContainer.requestContainerFocus();
    }
	
	public void increaseTabPosition(int delta) throws VFSException {
		JTabbedPane tabbedPane = getActiveTabbedPane();
		if(tabbedPane == null) {
			return;
		}
		
		int index = tabbedPane.getSelectedIndex();
		
		index += delta;
		while(index < 0) {
			index += tabbedPane.getTabCount();
		}
		
		while(index >= tabbedPane.getTabCount()) {
			index -= tabbedPane.getTabCount();
		}
		
		((TabContainer)tabbedPane.getComponentAt(index)).requestContainerFocus();
    }
	
	private JTabbedPane getActiveTabbedPane() {
		Component c = getFocusOwner();
		if(c == null) {
			return null;
		}
		
		Container container = c.getParent();
		
		while(true) {
			if(container == null) {
				return null;
			}
			
			if(container == mainTabbedPane) {
				return mainTabbedPane;
			}
			
			if(container == subTabbedPane) {
				return subTabbedPane;
			}
			
			container = container.getParent();
		}
	}
	
	/**
	 * 指定位置のコンポーネントを取得する。
	 * 複数ある場合は最前面のコンポーネントを取得する。
	 * 
	 * @param comp
	 * @return
	 */
	public JFDComponent getComponent(ContainerPosition position) {
		if(position == ContainerPosition.MAIN_PANEL) {
			if(mainTabbedPane.getComponentCount() == 0) {
				return null;
			}
			
			JFDContainer container = (JFDContainer)mainTabbedPane.getSelectedComponent();
			return container.getComponent();
		} else if(position == ContainerPosition.SUB_PANEL) {
			if(subTabbedPane.getComponentCount() == 0) {
				return null;
			}
			
			JFDContainer container = (JFDContainer)subTabbedPane.getSelectedComponent();
			return container.getComponent();
		}
		
		return null;
	}
	
	public static int getIndexOf(JFDFrame frame) {
		return instances.indexOf(frame);
	}
	
	public static JFDFrame getInstance(int index) {
		while(index < 0) {
			index += instances.size();
		}
		
		while(index >= instances.size()) {
			index -= instances.size();
		}
		
		return (JFDFrame)instances.get(index);
	}
	
	/**
	 * インスタンス数を返す。
	 * @return
	 */
	public static int getInstanceCount() {
		return instances.size();
	}
	
	public void finalize() throws Throwable {
		System.out.println("finalize JFDFrame");
		super.finalize();
	}
	
	/**
	 * 最前面に移動する。
	 *
	 */
	public void toFront() {
		Runnable runnable = new Runnable() {
			public void run() {
				JFDFrame.super.toFront();
			}
		};
		
		ThreadSafeUtilities.executeRunnable(runnable);
	}

	/**
	 * サーバーモードならtrueを返す。
	 * @return
	 */
	public static boolean isServerMode() {
		return serverMode;
	}

	/**
	 * サーバーモードを設定する。
	 * @param serverMode
	 */
	public static void setServerMode(boolean serverMode) {
		JFDFrame.serverMode = serverMode;
	}

	public void componentActivated(JFDComponent component) {
		this.activeComponent = component;
	}
}
