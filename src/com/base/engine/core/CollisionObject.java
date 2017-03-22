package com.base.engine.core;

import com.base.engine.physics.CollisionGroup;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

import java.util.ArrayList;

public class CollisionObject{
	private ArrayList<CollisionObject> children;
	private ArrayList<CollisionGroup> groups;
	private CoreEngine engine;

	public CollisionObject(){
		children = new ArrayList<CollisionObject>();
		groups = new ArrayList<CollisionGroup>();
		engine = null;
	}

	public void addChild(CollisionObject child){
		children.add(child);
		child.setEngine(engine);
	}

	public void updateAll(float delta){
		this.update(delta);
		for(CollisionObject child : children)
			child.updateAll(delta);
	}
	
	public void update(float delta){
		for(CollisionGroup coll : groups){
			coll.update(delta);
		}
	}

	public ArrayList<CollisionObject> getAllAttached(){
		ArrayList<CollisionObject> result = new ArrayList<CollisionObject>();

		for(CollisionObject child : children)
			result.addAll(child.getAllAttached());

		result.add(this);
		return result;
	}


	public void setEngine(CoreEngine engine)
	{
		if(this.engine != engine)
		{
			this.engine = engine;
			for(CollisionObject child : children)
				child.setEngine(engine);
		}
	}

	public void addGroup(CollisionGroup coll1) {
		groups.add(coll1);
		coll1.setParent(this);
	}
}
