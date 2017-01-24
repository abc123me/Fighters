package net.net16.jeremiahlowe.fighters;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public DrawCanvas drawCanvas;
	private JPanel contentPane;
	
	public GUI() {
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Game", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		drawCanvas = new DrawCanvas();
		panel_1.add(drawCanvas);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Options", null, panel, null);
	}
}
