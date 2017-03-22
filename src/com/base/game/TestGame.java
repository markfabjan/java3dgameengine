package com.base.game;

import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.physics.CollisionGroup;
import com.base.engine.physics.Force;
import com.base.engine.physics.ForceControll;
import com.base.engine.physics.ForceFade;
import com.base.engine.physics.Gravity;
import com.base.engine.physics.Mass;
import com.base.engine.physics.PhyModel;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public void init()
	{
		Mesh mesh = new Mesh("plane3.obj");
		Material material = new Material();//new Texture("test.png"), new Vector3f(1,1,1), 1, 8);
		material.addTexture("diffuse", new Texture("bricks.jpg"));
		material.addTexture("normalMap", new Texture("bricks_normal.jpg"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);

		Material material2 = new Material();//new Texture("test.png"), new Vector3f(1,1,1), 1, 8);
		material2.addTexture("diffuse", new Texture("bricks2.jpg"));
		material2.addTexture("normalMap", new Texture("bricks2_normal.jpg"));
		material2.addFloat("specularIntensity", 1);
		material2.addFloat("specularPower", 8);

		Mesh tempMesh = new Mesh("monkey3.obj");

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		VisualObject planeObject = new VisualObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, -1, 5);

		VisualObject directionalLightObject = new VisualObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1,1,1), 0.8f);

		directionalLightObject.addComponent(directionalLight);

		VisualObject pointLightObject = new VisualObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Attenuation(0,0,1)));

		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Attenuation(0,0,0.1f), 0.7f);

		VisualObject spotLightObject = new VisualObject();
		spotLightObject.addComponent(spotLight);

		spotLightObject.getTransform().getPos().set(5, 0, 5);
		spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90.0f)));

		//VisualObject ambientLightObject = new VisualObject();
		
		
		//addVisualObject(planeObject);
		addVisualObject(directionalLightObject);
		addVisualObject(pointLightObject);
		addVisualObject(spotLightObject);

		VisualObject testMesh3 = new VisualObject().addComponent(new LookAtComponent()).addComponent(new MeshRenderer(tempMesh, material));

		
		
		//addObject(testMesh3);

		testMesh3.getTransform().getPos().set(5,5,5);
		testMesh3.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-70.0f)));

		//addObject(new GameObject().addComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2)));

		directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(-45)));
		/*
		Mesh characterMesh = new Mesh("PE2_3.obj");
		Material characterMaterial = new Material();
		characterMaterial.addTexture("diffuse", new Texture("PE2.png"));
		characterMaterial.addFloat("specularIntensity", 1);
		characterMaterial.addFloat("specularPower", 8);
		GameObject character = new GameObject().addComponent(new Motor(0.0f)).addComponent(new RotateComponent(100f)).addComponent(new MeshRenderer(characterMesh, characterMaterial));
		character.getTransform().setScale(new Vector3f(0.05f,0.05f,0.05f));
		addObject(character);
		*/
		/*
		Mesh tankhullmesh = new Mesh("tankhull.obj");
		Material tankhullmat = new Material();
		tankhullmat.addTexture("diffuse", new Texture("camo.jpg"));
		tankhullmat.addFloat("specularIntensity", 1);
		tankhullmat.addFloat("specularPower", 8);
		GameObject tankhull = new GameObject().addComponent(new Mass(1000f)).addComponent(new Force()).addComponent(new ForceControll(0.5f)).addComponent(new MeshRenderer(tankhullmesh, tankhullmat));
		tankhull.getTransform().setScale(new Vector3f(0.5f,0.5f,0.5f));
		tankhull.getTransform().setPos(new Vector3f(0,3f,0));
		addObject(tankhull);
		
		Mesh tankturretmesh = new Mesh("tankturret.obj");
		Material tankturretmat = new Material();
		tankturretmat.addTexture("diffuse", new Texture("camo.jpg"));
		tankturretmat.addFloat("specularIntensity", 1);
		tankturretmat.addFloat("specularPower", 8);
		//GameObject tankturret = new GameObject().addComponent(new GluedTo(tankhull,0.0f,2.37202f,-2.37239f)).addComponent(new MeshRenderer(tankturretmesh, tankturretmat));
		GameObject tankturret = new GameObject().addComponent(new GluedTo(tankhull,0.0f,-2.37239f,2.37202f)).addComponent(new MeshRenderer(tankturretmesh, tankturretmat));
		tankturret.getTransform().setScale(new Vector3f(0.5f,0.5f,0.5f));
		addObject(tankturret);
		*/
		/*
		Mesh ballmesh = new Mesh("TestBall.obj");
		Material ballmat = new Material();
		ballmat.addTexture("diffuse", new Texture("test.png"));
		ballmat.addFloat("specularIntensity", 1);
		ballmat.addFloat("specularPower", 8);
		VisualObject ball = new VisualObject().addComponent(new Mass(1000f)).addComponent(new Force()).addComponent(new ForceFade(0.1f)).addComponent(new ForceControll(0.5f)).addComponent(new MeshRenderer(ballmesh, ballmat));
		ball.getTransform().setScale(new Vector3f(1f,1f,1f));
		addVisualObject(ball);
		*/
		
		//CollisionObject collisions = new CollisionObject();
		CollisionGroup coll1 = new CollisionGroup(true);
		
		//plane
		PhysicalObject planephy1 = new PhysicalObject("planephy.obj",0.0f);
		//planephy1.addComponent(new Force());
		//planephy1.addComponent(new Gravity(0.981f));
		//planephy1.getTransform().setPos(new Vector3f(0,-50,0f));
		planephy1.getTransform().setPos(new Vector3f(0,-50,50f));
		addPhysicalObject(planephy1);
		
		Mesh planeMesh = new Mesh("plane.obj");
		Material planeMat = new Material();
		planeMat.addTexture("diffuse", new Texture("test.png"));
		planeMat.addFloat("specularIntensity", 1);
		planeMat.addFloat("specularPower", 8);
		VisualObject plane1 = new VisualObject();
		plane1.addComponent(new MeshRenderer(planeMesh, planeMat));
		plane1.addComponent(new IsSkinFor(planephy1,0,0,0));
		addVisualObject(plane1);
		
		coll1.addObject(planephy1);
		
		//tetra1
		PhysicalObject tetraphy1 = new PhysicalObject("tetraphy.obj",0.5f,false);
		tetraphy1.addComponent(new Force());
		tetraphy1.addComponent(new Gravity(9.81f));
		//tetraphy1.getTransform().setPos(new Vector3f(0,5,0f));
		tetraphy1.getTransform().setPos(new Vector3f(0,5,50f));
		addPhysicalObject(tetraphy1);
		
		Mesh tetraMesh = new Mesh("tetra.obj");
		Material tetraMat = new Material();
		tetraMat.addTexture("diffuse", new Texture("test.png"));
		tetraMat.addFloat("specularIntensity", 1);
		tetraMat.addFloat("specularPower", 8);
		VisualObject tetra1 = new VisualObject();
		tetra1.addComponent(new MeshRenderer(tetraMesh, tetraMat));
		tetra1.addComponent(new IsSkinFor(tetraphy1,0,0,0));
		addVisualObject(tetra1);
		
		coll1.addObject(tetraphy1);
		
		//tetra2
		PhysicalObject tetraphy2 = new PhysicalObject("tetraphy.obj",0.5f,false);
		tetraphy2.addComponent(new Force());
		tetraphy2.addComponent(new Gravity(9.81f));
		//tetraphy1.getTransform().setPos(new Vector3f(0,5,0f));
		tetraphy2.getTransform().setPos(new Vector3f(2,-45,51f));
		addPhysicalObject(tetraphy2);
		
		Mesh tetraMesh2 = new Mesh("tetra.obj");
		Material tetraMat2 = new Material();
		tetraMat2.addTexture("diffuse", new Texture("test.png"));
		tetraMat2.addFloat("specularIntensity", 1);
		tetraMat2.addFloat("specularPower", 8);
		VisualObject tetra2 = new VisualObject();
		tetra2.addComponent(new MeshRenderer(tetraMesh2, tetraMat2));
		tetra2.addComponent(new IsSkinFor(tetraphy2,0,0,0));
		addVisualObject(tetra2);
		
		coll1.addObject(tetraphy2);
		
		
		CollisionObject collisions = new CollisionObject();
		collisions.addGroup(coll1);
		addCollisionObject(collisions);

		
		Camera camera1 = new Camera((float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f);
		//addObject(new GameObject().addComponent(new CameraFollow(character)).addComponent(new FreeLook(0.5f)).addComponent(camera1));
		addVisualObject(new VisualObject().addComponent(new FreeLook(0.5f)).addComponent(new FreeMove(10.0f)).addComponent(camera1));
	}
}
