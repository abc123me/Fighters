package net.net16.jeremiahlowe.fighters;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public DrawCanvas drawCanvas;
	private JPanel contentPane;
	public ScrollingGraph statusGraph;
	
	public GUI() {
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Simulation", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		drawCanvas = new DrawCanvas();
		panel_1.add(drawCanvas);
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox, BorderLayout.SOUTH);
		
		JButton btnNextGeneration = new JButton("Next generation");
		btnNextGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fighters.onNewGeneration();
			}
		});
		horizontalBox.add(btnNextGeneration);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fighters.onPause();
			}
		});
		horizontalBox.add(btnPause);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fighters.onReset();
			}
		});
		horizontalBox.add(btnNewButton);
		
		JCheckBox chckbxAutopilot = new JCheckBox("Autopilot");
		chckbxAutopilot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fighters.autoPilot = chckbxAutopilot.isSelected();
			}
		});
		horizontalBox.add(chckbxAutopilot);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Progress", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		statusGraph = new ScrollingGraph();
		panel.add(statusGraph);
	}
}
