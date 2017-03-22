package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class Gravity extends PhysicalComponent{
	
	private Vector3f direction;
	private float acceleration;
	
	public Gravity(){
		this(new Vector3f(0,-1,0).normalized(), 0);
	}
	
	public Gravity(float acceleration){
		this(new Vector3f(0,-1,0).normalized(),acceleration);
	}
	
	public Gravity(Vector3f direction){
		this(direction, 0);
	}
	
	public Gravity(Vector3f direction, float acceleration){
		this.direction=direction;
		this.acceleration=acceleration;
	}
	
	public void update(float delta){
		setForce(getForce().add(direction.mul(acceleration*delta)));
		//setForce(getForce().add(direction.mul(acceleration)));
	}
	
}
