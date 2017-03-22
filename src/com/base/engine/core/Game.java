package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;

public abstract class Game
{
	private VisualObject vroot;
	private PhysicalObject proot;
	private CollisionObject croot;

	public void init() {}

	public void input(float delta)
	{
		getVRootObject().inputAll(delta);
		getPRootObject().inputAll(delta);
	}

	public void update(float delta)
	{
		getVRootObject().updateAll(delta);
		getPRootObject().updateAll(delta);
		getCRootObject().updateAll(delta);
	}

	public void render(RenderingEngine renderingEngine)
	{
		renderingEngine.render(getVRootObject());
	}

	public void addVisualObject(VisualObject object)
	{
		getVRootObject().addChild(object);
	}
	
	public void addPhysicalObject(PhysicalObject object)
	{
		getPRootObject().addChild(object);
	}
	
	public void addCollisionObject(CollisionObject object)
	{
		getCRootObject().addChild(object);
	}

	private VisualObject getVRootObject()
	{
		if(vroot == null)
			vroot = new VisualObject();

		return vroot;
	}
	
	private PhysicalObject getPRootObject()
	{
		if(proot == null)
			proot = new PhysicalObject();

		return proot;
	}
	
	private CollisionObject getCRootObject()
	{
		if(croot == null)
			croot = new CollisionObject();

		return croot;
	}

	public void setEngine(CoreEngine engine) { getVRootObject().setEngine(engine); }
}