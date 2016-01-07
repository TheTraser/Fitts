import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class FittsVenster extends JFrame implements ActionListener, ComponentListener {
	private static final long serialVersionUID = 0;
	
	// Configuratie
	final static int WIDTH = 600;
	final static int HEIGHT = 600;
	final static int BUTTONWIDTH = 200;
	final static int BUTTONHEIGHT = 40;
	
	// De elementen in de GUI
	private JButton btnStart, btnTarget;
	
	/**
	 * Constructor, wordt aangeroepen bij het maken van Main object
	 */
	
	/* Bonusopdracht: het interface een wat 'vriendelijker' uiterlijk gegeven door,
	 * De achtergrond roze te kleuren
	 * De knoppen lichtblauw te kleuren
	 */
	public FittsVenster() {
		// Wat standaard eigenschappen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Wet van Fitts");
		setSize(WIDTH, HEIGHT);
		addComponentListener(this);
		
		// Maak een interface om de knoppen te kunnen plaatsen
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		
		// Startknop, standaard zichtbaar
		btnStart = new JButton( "Start"); 
		btnStart.addActionListener(this);
		btnStart.setBounds(0, 20, BUTTONWIDTH, BUTTONHEIGHT);
		btnStart.setBackground(Color.CYAN); 
		panel.add(btnStart);
		
		// Doelknop, standaard onzichtbaar
		btnTarget = new JButton("Klik hier!");
		btnTarget.addActionListener(this);
		btnTarget.setVisible(false);
		btnTarget.setBackground(Color.CYAN);
		panel.add(btnTarget);
	}

	/**
	 * Methode die aangeroepen wordt bij een druk op een knop
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			btnTarget.setBounds(Fitts.getLocatie(getContentPane().getWidth(), getContentPane().getHeight()));
			Fitts.start();
			btnStart.setVisible(false);
			btnTarget.setVisible(true);
		} else if(e.getSource() == btnTarget) {
			Fitts.stop();
			btnStart.setVisible(true);
			btnTarget.setVisible(false);
		}
	}
	
	
	/**
	 * De main methode om het venster te laten zien, details
	 * over de implementatie zijn niet belangrijk.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FittsVenster ex = new FittsVenster();
				ex.setVisible(true);
			}
		});
	}

	/**
	 * Wordt aangeroepen wanneer het venster van grootte verandert,
	 * zorgt ervoor dat knoppen gecentreerd worden.
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		btnStart.setLocation(getContentPane().getWidth() / 2 - btnStart.getWidth() / 2,
				getContentPane().getHeight() / 2 - btnStart.getHeight() / 2);
	}

	/**
	 * Wordt aangeroepen wanneer het venster getoond wordt,
	 * zorgt ervoor dat we initieel de knoppen centreren.
	 */
	@Override
	public void componentShown(ComponentEvent e) {
		this.componentResized(e);
	}
	
	// Ongebruikte events
	
	@Override
	public void componentHidden(ComponentEvent e) { }

	@Override
	public void componentMoved(ComponentEvent e) { }

}