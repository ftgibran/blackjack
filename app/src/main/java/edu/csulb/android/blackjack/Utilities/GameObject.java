package edu.csulb.android.blackjack.Utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

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

	protected float rotation;

	protected Rect rect;

	protected float dt = 1.f;

	protected Stage stage;

	public GameObject()
	{

	}

	public GameObject(Bitmap bitmap) {
		setBitmap(bitmap);
		setRect();
	}

	public GameObject(Bitmap bitmap,float x, float y) {
		setBitmap(bitmap);
		setX(x);
		setY(y);
		setRect();
	}

	public void setRenderListener(Stage stage) {
		this.stage = stage;
		stage.getListeners().add(this);
	}

	public void removeRenderListener() {
		stage.getListeners().remove(this);
		this.stage = null;
	}

	@Override
	public void onUpdate() {
		vx += sx*dt;
		vy += sy*dt;

		x += vx *dt;
		y += vy *dt;
	}

	@Override
	public void onRender(Canvas canvas) {
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

		Matrix matrix = new Matrix();
		matrix.postRotate(rotation);

		this.bitmap = Bitmap.createBitmap(this.bitmap , 0, 0, (int)width,(int)height, matrix, true);
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

	public float getSx() {
		return sx;
	}

	public void setSx(float sx) {
		this.sx = sx;
	}

	public float getSy() {
		return sy;
	}

	public void setSy(float sy) {
		this.sy = sy;
	}

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
		this.width = Math.abs(width);
		this.bitmap = Bitmap.createScaledBitmap(this.bitmap,(int)width,(int)height, true);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = Math.abs(height);
		this.bitmap = Bitmap.createScaledBitmap(this.bitmap,(int)width,(int)height, true);
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;

		if(this.bitmap == null)	return;
		Matrix matrix = new Matrix();
		matrix.postRotate(rotation);

		this.bitmap = Bitmap.createBitmap(this.bitmap , 0, 0,(int)width,(int)height, matrix, true);
	}

	public void setRect()
	{
		rect = new Rect((int)x, (int)y, (int)(x+width), (int)(y+height));
	}

	public Rect getRect()
	{
		return rect;
	}

	public Stage getStageListener() {
		return stage;
	}
}
