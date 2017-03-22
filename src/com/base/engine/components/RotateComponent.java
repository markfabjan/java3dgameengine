package com.base.engine.components;

import com.base.engine.core.Input;
import com.base.engine.core.Vector3f;

public class RotateComponent extends VisualComponent{
	private float speed;
	private int xpositive;
	private int xnegative;
	private int ypositive;
	private int ynegative;
	private int zpositive;
	private int znegative;

	public RotateComponent(float speed)
	{
		this(speed, Input.KEY_NUMPAD8, Input.KEY_NUMPAD5, Input.KEY_NUMPAD7, Input.KEY_NUMPAD9 ,Input.KEY_NUMPAD4, Input.KEY_NUMPAD6);
	}

	public RotateComponent(float speed, int xpositive, int xnegative, int ypositive, int ynegative, int zpositive, int znegative)
	{
		this.speed = speed;
		this.xpositive = xpositive;
		this.xnegative = xnegative;
		this.ypositive = ypositive;
		this.ynegative = ynegative;
		this.zpositive = zpositive;
		this.znegative = znegative;
	}

	@Override
	public void input(float delta)
	{
		float movAmt = speed * delta;

		if(Input.getKey(xpositive))
			rotate(getTransform().getRot().getRight(), movAmt);
		if(Input.getKey(xnegative))
			rotate(getTransform().getRot().getRight(), -movAmt);
		if(Input.getKey(ypositive))
			rotate(getTransform().getRot().getUp(), movAmt);
		if(Input.getKey(ynegative))
			rotate(getTransform().getRot().getUp(), -movAmt);
		if(Input.getKey(zpositive))
			rotate(getTransform().getRot().getForward(), movAmt);
		if(Input.getKey(znegative))
			rotate(getTransform().getRot().getForward(), -movAmt);
	}

	private void rotate(Vector3f axis, float amt)
	{
		getTransform().rotate(axis, (float) Math.toRadians(amt));
		//getTransform().rotate(getTransform().getRot().getRight(), (float) Math.toRadians(-speed));
	}
}
