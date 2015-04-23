package edu.csulb.android.blackjack.Utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by FelipeGibran on 4/19/2015.
 */
public class GameObject implements Renderable {

	protected static final float GRAVITY = 9.8f;

	protected Bitmap bitmap;

	protected float x = 0;
	protected float y = 0;

	protected float vx = 0;
	protected float vy = 0;

	protected float sx = 0;
	protected float sy = 0;

	protected float width;
	protected float height;

	protected float dt = 1.f;

	public GameObject()
	{
	}

	public GameObject(Bitmap bitmap) {
		setBitmap(bitmap);
	}

	public GameObject(Bitmap bitmap,float x, float y) {
		setBitmap(bitmap);
		setX(x);
		setY(y);
	}

	public void setRenderListener(Stage stage) {
		stage.getListeners().add(this);
	}

	public void removeRenderListener(Stage stage) {
		stage.getListeners().remove(this);
	}

	@Override
	public void update() {
		vx += sx*dt;
		vy += sy*dt;

		x += vx *dt;
		y += vy *dt;
	}

	@Override
	public void render(Canvas canvas) {
		if(bitmap != null)
			canvas.drawBitmap(bitmap, x, y, null);
	}

	public Bitmap getBitmap()
	{
		return this.bitmap;
	}

	public void setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		this.bitmap = Bitmap.createScaledBitmap(this.bitmap,(int)width,(int)height, true);
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getVx() {
		return vx;
	}
	public void setVx(float vx) {
		this.vx = vx;
	}
	public float getVy() {
		return vy;
	}
	public void setVy(float vy) {
		this.vy = vy;
	}
	public float getSx() { return sx; }
	public void setSx(float sx) { this.sx = sx; }
	public float getSy() { return sy; }
	public void setSy(float sy) { this.sy = sy; }
	public float getDt() {
		return dt;
	}
	public void setDt(float dt) {
		this.dt = dt;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
		this.bitmap = Bitmap.createScaledBitmap(this.bitmap,(int)width,(int)height, true);
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
		this.bitmap = Bitmap.createScaledBitmap(this.bitmap,(int)width,(int)height, true);
	}

}
