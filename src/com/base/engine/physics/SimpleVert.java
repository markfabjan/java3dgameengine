package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class SimpleVert {
	
	private Vector3f pos;
	
	public SimpleVert(Vector3f pos){
		this.pos = pos;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
}

