package lab13;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ActorTest extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private ArrayList<Actor> zombies;
	private Random randGenerator;
	BufferedImage image;
	int score;
	JLabel scoreLabel;
	
	public ActorTest() throws IOException {
		setPreferredSize(new Dimension(500, 300));
		randGenerator = new Random();
		score = 0;
		addMouseListener(this);
		scoreLabel = new JLabel("Score: 0");
		this.add(scoreLabel);
		
		zombies = new ArrayList<>();
		image = ImageIO.read(new File("src/lab13/smile.png"));			
		
		Actor zombie = new Actor(new Point2D.Double(100, 100), new Point2D.Double(image.getWidth(), image.getHeight()),
							image, 100, 200, 0, 10);
		zombies.add(zombie);

		timer = new Timer(30, this);
		timer.start();
	}

	/***
	 * Implement the paint method to draw the zombies
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Actor zombie : zombies) {
			zombie.draw(g, 0);
			zombie.drawHealthBar(g);
		}
	}

	/**
	 * 
	 * This is triggered by the timer. It is the game loop of this test.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		// Update decrements an internal counter for some kind of action.
		for (Actor zombie : zombies) {
			zombie.update();
		}
		
		 
		if(randGenerator.nextInt(100) > 97) {
			
		int row = randGenerator.nextInt(6);
		int col = randGenerator.nextInt(10);
		
		int x = col * 50;
		int y = row * 50;
		
		
		
		Actor zombie = new Actor(new Point2D.Double(x, y), new Point2D.Double(image.getWidth(), image.getHeight()),
				image, 100, 200, 0, 10);
		zombies.add(zombie);
		}
		ArrayList<Actor> newZombies = new ArrayList<>();
		for(Actor zombie : zombies) {
			if(!zombie.readyForAction()) {
				newZombies.add(zombie);
			}
			else {
				score =- 5;
			}
		}
		zombies = newZombies;
		
		newZombies = new ArrayList<Actor>();
		for(Actor zombie : zombies) {
			if(zombie.isAlive()) {
				newZombies.add(zombie);
			}
			else {
				score ++;
			}
		}
		zombies = newZombies;
		scoreLabel.setText("Score " + score);

		// Redraw the new scene
		repaint();
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame app = new JFrame("Whack-a-Zombie");
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ActorTest panel = null;
				try {
					panel = new ActorTest();
				} catch (IOException e) {
					System.out.println("A file was not found");
					System.exit(0);
				}

				app.setContentPane(panel);
				app.pack();
				app.setVisible(true);
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		for(Actor zombie : zombies) {
			if(zombie.isCollidingPoint(new Point2D.Double(x,y))) {
				zombie.changeHealth(-50);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}