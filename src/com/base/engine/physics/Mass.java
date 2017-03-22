package com.base.engine.physics;

import com.base.engine.components.VisualComponent;

public class Mass extends VisualComponent{
	private float mass;
	
	public Mass(float mass){
		this.mass = mass;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}
}