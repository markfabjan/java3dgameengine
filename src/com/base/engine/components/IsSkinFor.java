package com.base.engine.components;

import com.base.engine.core.PhysicalObject;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector3f;
import com.base.engine.core.VisualObject;

public class IsSkinFor extends VisualComponent{
	
	private PhysicalObject target;
	private float offsetx;
	private float offsety;
	private float offsetz;
	
	public IsSkinFor(PhysicalObject target, float offsetx, float offsetz, float offsety){
		this.target = target;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.offsetz = offsetz;
	}
	
	public void update(float delta)
	{
		Vector3f cpos = target.getTransform().getPos();
		Quaternion crot = target.getTransform().getRot();
		Vector3f csca = target.getTransform().getScale();
		//Vector3f npos = new Vector3f(0,0,0);
		//Quaternion nrot = new Quaternion(0,0,0,0);
		
		getTransform().setPos(cpos);
		move(crot.getRight(), (offsetx*csca.getX()));
		move(crot.getUp(), (offsety*csca.getY()));
		move(crot.getForward(), (-offsetz*csca.getZ()));
	}
	
	private void move(Vector3f dir, float amt)
	{
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}
}
