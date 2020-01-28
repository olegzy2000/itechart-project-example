package com.bsu;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LampFrame extends JFrame {

	private static final String NOT_FOUNDED = "Not founded";
	/**
	 * 
	 */
	private static final long serialVersionUID = -1193497863298524257L;
	private Root root;
	private Map<String, Range> costRanges;
	private Logger logger = Logger.getLogger("LampFrame");
	private List<JPanel> tabs = new ArrayList<JPanel>();
	private JTabbedPane jTabbedPane;

	public LampFrame() {
		super("Lamp Frame");
		initMenu();
		initTabbedPane();
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initTabbedPane() {
		jTabbedPane = new JTabbedPane();
		for (int i = 0; i < 3; i++) {
			JPanel tab = new JPanel();
			tabs.add(tab);
			StringBuilder tabName = new StringBuilder();
			tabName.append("Tab ");
			tabName.append(i + 1);
			jTabbedPane.add(tabName.toString(), tab);
		}
		getContentPane().add(jTabbedPane);
		getContentPane().revalidate();
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openFile = new JMenuItem("Open");
		openFile.addActionListener(addOpenFileActionListener());
		fileMenu.add(openFile);
		menuBar.add(fileMenu);
		JMenu data = new JMenu("Data");
		JMenuItem cheaper = new JMenuItem("Cheaper");
		cheaper.addActionListener(addCheaperActionListener());
		JMenuItem quality = new JMenuItem("Quality");
		quality.addActionListener(addQualityActionListener());
		JMenuItem names = new JMenuItem("Names");
		names.addActionListener(addNamesActionListener());
		JMenuItem search = new JMenuItem("Search");
		search.addActionListener(addSearchActionListener());
		JMenuItem range = new JMenuItem("Range");
		range.addActionListener(addRangeActionListener());
		data.add(cheaper);
		data.add(search);
		data.add(quality);
		data.add(names);
		data.add(range);
		menuBar.add(data);
		setJMenuBar(menuBar);
	}

	private ActionListener addRangeActionListener() {
		return (x) -> {
			if (costRanges != null) {
				String companyName = JOptionPane.showInputDialog(null, "Please input company name");
				Range message = costRanges.get(companyName);
				if (message != null) {
					JOptionPane.showMessageDialog(null, message);
				} else {
					JOptionPane.showMessageDialog(null, NOT_FOUNDED);
				}
			}
		};
	}

	private ActionListener addSearchActionListener() {
		return (x) -> {
			if (root != null) {
				List<LedLamp> ledLamps = root.getLedLamps();
				if (ledLamps != null && !ledLamps.isEmpty()) {
					LedLamp max = ledLamps.stream().max(Comparator.comparingInt(LedLamp::getLedNumber)).get();
					LedLamp min = ledLamps.stream().min(Comparator.comparingInt(LedLamp::getLedNumber)).get();
					int averageLeds = (max.getLedNumber() + min.getLedNumber()) / 2;
					ledLamps.stream().filter(y -> y.getLedNumber() == averageLeds).findFirst()
							.ifPresent(y -> JOptionPane.showMessageDialog(null, y));

				}
			}
		};
	}

	private ActionListener addNamesActionListener() {
		return (x) -> {
			if (root != null) {
				List<GlowLamp> glowLamps = root.getGlowLamps();
				List<LedLamp> ledLamps = root.getLedLamps();
				List<Lamp> lamps = new ArrayList<Lamp>();
				if (glowLamps != null) {
					lamps.addAll(glowLamps);
				}
				if (ledLamps != null) {
					lamps.addAll(ledLamps);
				}
				Set<String> companyNames = lamps.stream().map(Lamp::getName).collect(Collectors.toSet());
				if (!companyNames.isEmpty()) {
					JList list = new JList(companyNames.toArray());
					tabs.get(2).add(list);
					tabs.get(2).revalidate();
				} else {
					JOptionPane.showMessageDialog(null, NOT_FOUNDED);
				}
			}
		};
	}

	private ActionListener addQualityActionListener() {
		return (x) -> {
			if (root != null) {
				List<GlowLamp> glowLamps = root.getGlowLamps();
				List<LedLamp> ledLamps = root.getLedLamps();
				List<Lamp> lamps = new ArrayList<Lamp>();
				if (glowLamps != null) {
					lamps.addAll(glowLamps);
				}
				if (ledLamps != null) {
					lamps.addAll(ledLamps);
				}
				List<Lamp> sortedLamps = lamps.stream().sorted((first, second) -> {
					Float o1 = (first.countCost() / first.getPower());
					Float o2 = (second.countCost() / second.getPower());
					return o1.compareTo(o2);
				}).collect(Collectors.toList());
				if (!sortedLamps.isEmpty()) {
					JList list = new JList(sortedLamps.toArray());
					tabs.get(1).add(list);
					tabs.get(1).revalidate();
				} else {
					JOptionPane.showMessageDialog(null, NOT_FOUNDED);
				}
			}
		};
	}

	private ActionListener addCheaperActionListener() {
		return (x) -> {
			if (root != null) {
				List<GlowLamp> glowLamps = root.getGlowLamps();
				List<LedLamp> ledLamps = root.getLedLamps();
				List<Lamp> lamps = new ArrayList<Lamp>();
				if (glowLamps != null) {
					lamps.addAll(glowLamps);
				}
				if (ledLamps != null) {
					lamps.addAll(ledLamps);
				}
				List<Lamp> sortedLamps = lamps.stream().sorted(Comparator.comparingDouble(Lamp::countCost))
						.collect(Collectors.toList());
				if (!sortedLamps.isEmpty()) {
					JList list = new JList(sortedLamps.toArray());
					tabs.get(0).add(list);
					tabs.get(0).revalidate();
				} else {
					JOptionPane.showMessageDialog(null, NOT_FOUNDED);
				}
			}
		};

	}

	private ActionListener addOpenFileActionListener() {
		return (x) -> {
			JFileChooser jFileChooser = new JFileChooser();
			int result = jFileChooser.showDialog(null, "Open File");
			if (result == JFileChooser.APPROVE_OPTION) {
				clearTabs();
				File file = jFileChooser.getSelectedFile();
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					root = (Root) jaxbUnmarshaller.unmarshal(file);
					StringBuilder messageDialogInfo = new StringBuilder();
					messageDialogInfo.append("Amount of led lamps: ").append(root.getLedLamps().size()).append('\n')
							.append("Amount of glow lamps: ").append(root.getGlowLamps().size());
					JOptionPane.showMessageDialog(null, messageDialogInfo.toString());
					initMap();
				} catch (JAXBException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}

			}
		};
	}

	private void clearTabs() {
		getContentPane().remove(jTabbedPane);
		tabs.clear();
		initTabbedPane();
	}

	private void initMap() {
		if (root != null) {
			costRanges = new HashMap<String, Range>();
			List<GlowLamp> glowLamps = root.getGlowLamps();
			List<LedLamp> ledLamps = root.getLedLamps();
			List<Lamp> lamps = new ArrayList<Lamp>();
			if (glowLamps != null) {
				lamps.addAll(glowLamps);
			}
			if (ledLamps != null) {
				lamps.addAll(ledLamps);
			}
			Set<String> names = lamps.stream().map(Lamp::getName).collect(Collectors.toSet());
			names.forEach((x) -> {
				Float max = lamps.stream().filter(y -> x.equals(y.getName()))
						.max(Comparator.comparingDouble(Lamp::countCost)).map(Lamp::countCost).get();
				Float min = lamps.stream().filter(y -> x.equals(y.getName()))
						.min(Comparator.comparingDouble(Lamp::countCost)).map(Lamp::countCost).get();
				Range range = new Range(min, max);
				costRanges.put(x, range);
			});

		}
	}
}