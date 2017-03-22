package com.base.engine.core;

import com.base.engine.physics.PhyModel;
import com.base.engine.physics.PhysicalComponent;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

import java.util.ArrayList;

public class PhysicalObject
{
	private ArrayList<PhysicalObject> children;
	private ArrayList<PhysicalComponent> components;
	private Transform transform;
	private PhyModel phyModel;
	private Vector3f force;
	
	public PhysicalObject(){
		children = new ArrayList<PhysicalObject>();
		components = new ArrayList<PhysicalComponent>();
		transform = new Transform();
		force = new Vector3f(0,0,0);
		this.phyModel = new PhyModel("tetraphy.obj", transform);
	}

	public PhysicalObject(String filename){
		children = new ArrayList<PhysicalObject>();
		components = new ArrayList<PhysicalComponent>();
		transform = new Transform();
		force = new Vector3f(0,0,0);
		this.phyModel = new PhyModel(filename, transform);
	}
	
	public PhysicalObject(String filename, float bounce)
	{
		children = new ArrayList<PhysicalObject>();
		components = new ArrayList<PhysicalComponent>();
		transform = new Transform();
		force = new Vector3f(0,0,0);
		this.phyModel = new PhyModel(filename, transform, bounce);
	}
	
	public PhysicalObject(String filename, float bounce, boolean stationary)
	{
		children = new ArrayList<PhysicalObject>();
		components = new ArrayList<PhysicalComponent>();
		transform = new Transform();
		force = new Vector3f(0,0,0);
		this.phyModel = new PhyModel(filename, transform, bounce, stationary);
	}

	public void addChild(PhysicalObject child)
	{
		children.add(child);
		child.getTransform().setParent(transform);
		child.getForce().set(force);
		//child.setForce(force);
	}

	public PhysicalObject addComponent(PhysicalComponent component)
	{
		components.add(component);
		component.setParent(this);
		return this;
	}

	public void inputAll(float delta)
	{
		input(delta);
		for(PhysicalObject child : children)
			child.inputAll(delta);
	}

	public void updateAll(float delta)
	{
		update(delta);
		for(PhysicalObject child : children)
			child.updateAll(delta);
	}

	public void input(float delta)
	{
		transform.update();
		for(PhysicalComponent component : components)
			component.input(delta);
		phyModel.recalculate();
	}

	public void update(float delta)
	{
		phyModel.recalculate();
		for(PhysicalComponent component : components)
			component.update(delta);
	}

	/*public void render(Shader shader, RenderingEngine renderingEngine)
	{
		for(PhysicalComponent component : components)
			component.render(shader, renderingEngine);
	}*/

	public ArrayList<PhysicalObject> getAllAttached()
	{
		ArrayList<PhysicalObject> result = new ArrayList<PhysicalObject>();

		for(PhysicalObject child : children)
			result.addAll(child.getAllAttached());

		result.add(this);
		return result;
	}

	public Transform getTransform()
	{
		return transform;
	}

	public Vector3f getForce() {
		return force;
	}
	
	public void setForce(Vector3f force) {
		this.force = force;
	}
	
	public void setForce(float x, float y, float z) {
		this.force = new Vector3f(x,y,z);
	}

	public PhyModel getPhyModel() {
		return phyModel;
	}

	public void setPhyModel(PhyModel phyModel) {
		this.phyModel = phyModel;
	}
}
