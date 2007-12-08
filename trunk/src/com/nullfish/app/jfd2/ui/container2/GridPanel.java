package com.nullfish.app.jfd2.ui.container2;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.tablelayout.HtmlTablePanel;
import com.nullfish.lib.ui.ClockLabel;
import com.nullfish.lib.ui.grid.CrossLineGrid;
import com.nullfish.lib.ui.grid.HorizontalLineGrid;
import com.nullfish.lib.ui.grid.VerticalLineGrid;

public class GridPanel extends HtmlTablePanel {
	/**
	 * ƒ‰ƒxƒ‹
	 */
	private JLabel labelTitle = new JLabel(JFDResource.LABELS
			.getString("title"));

	private JLabel labelTitleLeftPad = new JLabel(" ");

	private JLabel labelTitleRightPad = new JLabel(" ");

	private ClockLabel clockLabel = new ClockLabel();

	private JLabel clockLabelLeftPad = new JLabel(" ");

	private JLabel clockLabelRightPad = new JLabel(" ");

	private JLabel[] labels = {
			labelTitle, labelTitleLeftPad, labelTitleRightPad,
			clockLabel, clockLabelLeftPad, clockLabelRightPad
	};
	
	private CrossLineGrid crossTopLeft = new CrossLineGrid(this);
	private HorizontalLineGrid horizonTopLeft = new HorizontalLineGrid(this);
	private HorizontalLineGrid horizonTopRight = new HorizontalLineGrid(this);
	private CrossLineGrid crossTopRight = new CrossLineGrid(this);
	private VerticalLineGrid verticalBottomLeft = new VerticalLineGrid(this);
	private VerticalLineGrid verticalBottomRight = new VerticalLineGrid(this);
	private CrossLineGrid crossBottomLeft = new CrossLineGrid(this);
	private HorizontalLineGrid horizonBottomLeft = new HorizontalLineGrid(this);
	private CrossLineGrid crossBottomRight = new CrossLineGrid(this);


	
	public GridPanel() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, FactoryConfigurationError {
		super("/out_layout.xml");
		
		initGui();
	}

	private void initGui() {
		labelTitle.setOpaque(true);
		labelTitleLeftPad.setOpaque(true);
		labelTitleRightPad.setOpaque(true);
		
		clockLabel.setOpaque(true);
		clockLabelLeftPad.setOpaque(true);
		clockLabelRightPad.setOpaque(true);

		addComponent(labelTitle, "title");
		addComponent(labelTitleLeftPad, "title_left_pad");
		addComponent(labelTitleRightPad, "title_right_pad");

		addComponent(clockLabel, "clock");
		addComponent(clockLabelLeftPad, "clock_left_pad");
		addComponent(clockLabelRightPad, "clock_right_pad");

		addComponent(crossTopLeft, "cross_top_left");
		addComponent(horizonTopLeft, "hor_top_left");
		addComponent(horizonTopRight, "hor_top_right");
		addComponent(crossTopRight, "cross_top_right");

		addComponent(verticalBottomLeft, "ver_bottom_left");
		addComponent(verticalBottomRight, "ver_bottom_right");
		addComponent(crossBottomLeft, "cross_bottom_left");
		addComponent(crossBottomRight, "cross_bottom_right");
	}
	
	public void setContent(JComponent comp) {
		addComponent(comp, "main");
	}
}
