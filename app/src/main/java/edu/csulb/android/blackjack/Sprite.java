package edu.csulb.android.blackjack;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by FelipeGibran on 4/19/2015.
 */
public class Sprite {

	protected static final float GRAVITY = 9.8f;
	protected static final boolean HAS_GRAVITY = false;

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

	protected boolean hasGravity = HAS_GRAVITY;

	public Sprite()
	{

	}

	public Sprite(Bitmap bitmap) {
		setBitmap(bitmap);
	}

	public Sprite(Bitmap bitmap,float x, float y) {
		setBitmap(bitmap);
		setX(x);
		setY(y);
	}

	public void update()
	{
		vx += sx*dt;
		vy += sy*dt;

		x += vx *dt;
		y += vy *dt;
	}

	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(bitmap,x,y,null);
		update();
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

	public void setGravityOn()
	{
		if(hasGravity) return;
		hasGravity = true;
		sy += GRAVITY;
	}

	public void setGravityOff()
	{
		if(!hasGravity) return;
		hasGravity = false;
		sy -= GRAVITY;
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

	public boolean isHasGravity() {
		return hasGravity;
	}

	public void setHasGravity(boolean hasGravity) {
		this.hasGravity = hasGravity;
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
