package lab12;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class SimpleAppListenerCompleted extends JFrame implements ActionListener {
	private JButton sayHi;
	private JButton quit;
	private JLabel output;

	public SimpleAppListenerCompleted() {
		super("Simple Button Test");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		sayHi = new JButton("Say Hi!");
		quit = new JButton("Quit");
		output = new JLabel("waiting");

		JPanel panel = new JPanel();
		JPanel hiPanel = new JPanel();
		JPanel helloPanel = new JPanel();
		
		
		// Try a non-default layout
		BoxLayout box = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		GridLayout grid = new GridLayout(2,2);

		panel.setLayout(grid);
		panel.add(sayHi);
		panel.add(quit);
		
		
		panel.add(hiPanel);
		panel.add(helloPanel);
		panel.add(quit);
		panel.add(output);
		this.setContentPane(panel);
		sayHi.addActionListener(this);
		quit.addActionListener(this);
		this.pack();
	}

	public static void main(String[] args) {

				SimpleAppListenerCompleted app = new SimpleAppListenerCompleted();
				app.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sayHi)
			System.out.println("Hi!");
			output.setText("Hi!");
		if (e.getSource() == quit)
			this.dispose();

	}
}
