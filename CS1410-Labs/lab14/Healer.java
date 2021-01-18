package lab14;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Healer extends Zombie {
	
	private static final int HEALTH = 20;
	private static final int COOLDOWN = 30;
	private static final int SPEED = -1;
	private static final int DAMAGE = 0;
	private static final int HEALAMOUNT = 10;
	private static final BufferedImage IMAGE;
	
	static {
		BufferedImage tempImage = null;
		try {
			tempImage = ImageIO.read(new File("src/lab14/Animal-Icons/black-cat-icon.png"));
		} catch (IOException e) {
			System.out.println("A file was not found");
			System.exit(0);
		}
		IMAGE = tempImage;
	}
	
	public Healer(Point2D.Double position) {
		super(position, new Point2D.Double(IMAGE.getWidth(), IMAGE.getHeight()), 
				IMAGE, HEALTH, COOLDOWN, SPEED, DAMAGE);
	}
	@Override
	public void attack(Actor other) {
		if(other instanceof Zombie) {
			if(this != other && this.isCollidingOther(other)) {
				if(this.isReadyForAction()) {
					other.changeHealth(HEALAMOUNT);
					this.resetCoolDown();
				}
			}
			
		}

	}

}
