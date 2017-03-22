package com.base.engine.physics;

import com.base.engine.components.VisualComponent;
import com.base.engine.core.Input;

public class ForceControll extends VisualComponent
{
	/*private float amount;
	private int forwardKey;
	private int backKey;
	private int leftKey;
	private int rightKey;
	private int upKey;
	private int downKey;*/

	public ForceControll(float amount)
	{
		this(amount, Input.KEY_NUMPAD8, Input.KEY_NUMPAD2, Input.KEY_NUMPAD4, Input.KEY_NUMPAD6, Input.KEY_NUMPAD1, Input.KEY_NUMPAD3);
	}
		
	public ForceControll(float amount, int forwardKey, int backKey, int leftKey, int rightKey, int upKey, int downKey)
	{
		/*this.amount= amount;
		this.forwardKey = forwardKey;
		this.backKey = backKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
		this.downKey = downKey;*/

	}
	@Override
	public void input(float delta)
	{
		/*
		float amt = amount;
		if(Input.getKey(forwardKey))
			setForce(getForce().getX(),getForce().getY(),getForce().getZ()-amt);
		if(Input.getKey(backKey))
			setForce(getForce().getX(),getForce().getY(),getForce().getZ()+amt);
		if(Input.getKey(leftKey))
			setForce(getForce().getX()+amt,getForce().getY(),getForce().getZ());
		if(Input.getKey(rightKey))
			setForce(getForce().getX()-amt,getForce().getY(),getForce().getZ());
		if(Input.getKey(upKey))
			setForce(getForce().getX(),getForce().getY()+amt,getForce().getZ());
		if(Input.getKey(downKey))
			setForce(getForce().getX(),getForce().getY()-amt,getForce().getZ());
		*/
	}
	
}
