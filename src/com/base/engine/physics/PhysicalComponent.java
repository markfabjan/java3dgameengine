package com.base.engine.physics;

import com.base.engine.core.CoreEngine;
import com.base.engine.core.PhysicalObject;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.core.Transform;
import com.base.engine.rendering.Shader;

public abstract class PhysicalComponent
{
	private PhysicalObject parent;
	public void input(float delta) {}
	public void update(float delta) {}

	public void setParent(PhysicalObject parent)
	{
		this.parent = parent;
	}

	public Transform getTransform()
	{
		return parent.getTransform();
	}
	
	public Vector3f getForce() {
		return parent.getForce();
	}
	
	public void setForce(Vector3f force){
		parent.setForce(force);
	}
}

