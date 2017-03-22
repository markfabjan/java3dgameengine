package com.base.engine.core;

import com.base.engine.components.VisualComponent;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

import java.util.ArrayList;

public class VisualObject{
	private ArrayList<VisualObject> children;
	private ArrayList<VisualComponent> components;
	private Transform transform;
	private CoreEngine engine;

	public VisualObject(){
		children = new ArrayList<VisualObject>();
		components = new ArrayList<VisualComponent>();
		transform = new Transform();
		engine = null;
	}

	public void addChild(VisualObject child){
		children.add(child);
		child.setEngine(engine);
		child.getTransform().setParent(transform);
	}

	public VisualObject addComponent(VisualComponent component){
		components.add(component);
		component.setParent(this);
		return this;
	}

	public void inputAll(float delta){
		input(delta);

		for(VisualObject child : children)
			child.inputAll(delta);
	}

	public void updateAll(float delta){
		update(delta);

		for(VisualObject child : children)
			child.updateAll(delta);
	}

	public void renderAll(Shader shader, RenderingEngine renderingEngine){
		render(shader, renderingEngine);

		for(VisualObject child : children)
			child.renderAll(shader, renderingEngine);
	}

	public void input(float delta){
		transform.update();

		for(VisualComponent component : components)
			component.input(delta);
	}

	public void update(float delta){
		for(VisualComponent component : components)
			component.update(delta);
	}

	public void render(Shader shader, RenderingEngine renderingEngine){
		for(VisualComponent component : components)
			component.render(shader, renderingEngine);
	}

	public ArrayList<VisualObject> getAllAttached(){
		ArrayList<VisualObject> result = new ArrayList<VisualObject>();

		for(VisualObject child : children)
			result.addAll(child.getAllAttached());

		result.add(this);
		return result;
	}

	public Transform getTransform(){
		return transform;
	}

	public void setEngine(CoreEngine engine)
	{
		if(this.engine != engine)
		{
			this.engine = engine;

			for(VisualComponent component : components)
				component.addToEngine(engine);

			for(VisualObject child : children)
				child.setEngine(engine);
		}
	}
}
