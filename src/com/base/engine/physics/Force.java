package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class Force extends PhysicalComponent{	
	
	public Force(){
		//getForce().set(new Vector3f(0,0,0));
	}
	
	@Override
	public void update(float delta){
		move(getForce(),getForce().length()*delta);
	}
	
	private void move(Vector3f dir, float amt)
	{
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}
	
}
