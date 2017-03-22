package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.core.VisualObject;

public class CameraFollow extends VisualComponent{
	
	private VisualObject target;
	
	public CameraFollow(VisualObject target){
		this.target = target;
	}
	
	public void input(float delta)
	{
		Vector3f currentPosition = target.getTransform().getPos();
		//Vector3f forward = target.getTransform().getRot().getForward();
		//Vector3f up = target.getTransform().getRot().getUp();
		Vector3f newPosition = getTransform().getPos();
		newPosition.set(currentPosition.add(target.getTransform().getRot().getUp()));
		newPosition.set(newPosition.add(target.getTransform().getRot().getBack().add(target.getTransform().getRot().getBack())));	
		//camera.getTransform().setPos(newPosition);
		getTransform().setPos(newPosition);
		getTransform().setRot(target.getTransform().getRot());

	}
	/*private void move(Vector3f dir, float amt)
	{
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}*/
	
}
