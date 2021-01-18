package lab14;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HealerFinished extends Zombie {
	private static final BufferedImage image;
	private static final int HEALTH = 20;
	private static final int COOLDOWN = 30;
	private static final int VELOCITY = -1;
	private static final int ATTACK_DAMAGE = 0;
	private static final int HEAL_AMOUNT = 10;
	static {
		BufferedImage localImage = null;
		try {
			localImage = ImageIO.read(new File("src/lab14/Animal-Icons/black-cat-icon.png"));
		} catch (IOException e) {
			System.out.println("Healer image was not found");
			System.exit(0);
		}
		image = localImage;

	}
	public HealerFinished(Point2D.Double position) {
		super(position, new Point2D.Double(image.getWidth(), image.getHeight()), 
				image, HEALTH, COOLDOWN, VELOCITY, ATTACK_DAMAGE);
	}

	@Override
	public void attack(Actor other) {
		if (other instanceof Zombie) {
			if (this != other && this.isCollidingOther(other)) {
				if (this.isReadyForAction()) {
					other.changeHealth(HEAL_AMOUNT);
					this.resetCoolDown();
				}
			}
		}
	}

}
