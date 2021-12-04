package dev.codewizz.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0, 5, 0);
	private float pitch = 10;
	private float yaw;
	private float roll;

	private float speed = 0.2f;
	private float rspeed = 1;

	public Camera() {
	}

	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= speed;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			pitch-=rspeed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			pitch+=rspeed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			yaw+=rspeed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			yaw-=rspeed;
		}

	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

}
