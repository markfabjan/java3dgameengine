package com.base.engine.physics;

import com.base.engine.components.VisualComponent;
//import com.base.engine.core.Vector3f;

public class ForceFade extends VisualComponent{	
	
	//private float fade;
	//private float fadefa;
	
	public ForceFade(float fade)
	{
		//this.fade = fade;
		//this.fadefa = fade+(fade/2);
	}
	
	@Override
	public void update(float delta){
		/*
		Vector3f force = getForce();
		
		
		if(force.getX()<fadefa && force.getX()>-fadefa)
			force.setX(0);
		if(force.getX()>fadefa)
			force.setX(force.getX()-fade);
		if(force.getX()<-fadefa)
			force.setX(force.getX()+fade);
		
		if(force.getY()<fadefa && force.getY()>-fadefa)
			force.setY(0);
		if(force.getY()>fadefa)
			force.setY(force.getY()-fade);
		if(force.getY()<-fadefa)
			force.setY(force.getY()+fade);
		
		if(force.getZ()<fadefa && force.getZ()>-fadefa)
			force.setZ(0);
		if(force.getZ()>fadefa)
			force.setZ(force.getZ()-fade);
		if(force.getZ()<-fadefa)
			force.setZ(force.getZ()+fade);
		*/
	}
}
