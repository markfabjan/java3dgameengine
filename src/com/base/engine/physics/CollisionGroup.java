package com.base.engine.physics;

import java.util.ArrayList;

import com.base.engine.core.AABB;
import com.base.engine.core.CollisionObject;
import com.base.engine.core.PhysicalObject;
import com.base.engine.core.Vector3f;

public class CollisionGroup{
	
	public boolean active;
	public CollisionObject parent;
	private ArrayList<PhysicalObject> objects;
	
	public CollisionGroup(boolean active){
		this.objects = new ArrayList<PhysicalObject>();
		this.active = active;
	}
	
	public void update(float delta){
		//check for collisions, but only if collision group contains more that one object.
		if(objects.size()>1){
			PhysicalObject objecta;
			PhysicalObject objectb;
			for (int i = 0; i < objects.size()-1; i++){
				objecta = objects.get(i);
				for (int j = i+1; j < objects.size(); j++){
					objectb = objects.get(j);
					//get center positions and proximities.
					Vector3f centera = objecta.getTransform().getPos();
					float proxa = objecta.getPhyModel().getProximity();
					Vector3f centerb = objectb.getTransform().getPos();
					float proxb = objectb.getPhyModel().getProximity();
					//calculate eucledian distance
					float distance = (float)Math.sqrt(Math.pow((centerb.getX()-centera.getX()),2) + Math.pow((centerb.getY()-centera.getY()),2) + Math.pow((centerb.getZ()-centera.getZ()),2));
					//check for collisions only if euclidean distance is lower than the sum of proximities.
					if(distance < (proxa + proxb)){
						//and for now set force of both objects to 0 on collision
						//objecta.setForce(new Vector3f(0,0,0));
						//objectb.setForce(new Vector3f(0,0,0));
						//insert collision here
						
						//for now just stop falling at 0
						/*if(centera.getY()<0){
							objecta.setForce(new Vector3f(0,0,0));
						}
						if(centerb.getY()<0){
							objectb.setForce(new Vector3f(0,0,0));
						}*/
						
						//recalculate vertexes and update aabb boxes on both phyModels
						objecta.getPhyModel().recalculate();
						objectb.getPhyModel().recalculate();
						objecta.getPhyModel().update();
						objectb.getPhyModel().update();
						//get both AABBs with forceExtend (to predict collisions)
						AABB aaabb = objecta.getPhyModel().getAabb().forceExtend(objecta.getForce(),delta);
						AABB baabb = objectb.getPhyModel().getAabb().forceExtend(objectb.getForce(),delta);
						//for now just set forces to 0 if AABBs+forces intersect
						//System.out.println("a.miny:"+aaabb.getMiny()+", a.maxy:"+aaabb.getMaxy()+", b.miny:"+baabb.getMiny()+", b.maxy:"+baabb.getMaxy());
						if (aaabb.intersectsWith(baabb)){
							//objecta.setForce(new Vector3f(0,0,0));
							//objectb.setForce(new Vector3f(0,0,0));
							float collDelta = aaabb.getCollisionDelta(objecta.getForce(), baabb, objectb.getForce(), delta, 10);
							float afterCollDelta = delta - collDelta;
							Vector3f collisionNormala = aaabb.getCollisionNormal(baabb);
							Vector3f collisionNormalb = baabb.getCollisionNormal(aaabb);
							//ignore if boxes are heading away from each-other or if force is low enough
							if(objecta.getForce().dot(collisionNormala)>0 || objectb.getForce().dot(collisionNormalb)>0){
								//move phyModel in direction after collision for collDelta+force afterCollDelta*newforce
								//(newforce = force*collisionNormal)
								//ignore collision if force is too low.
								//System.out.println("a:"+objecta.getForce()+", b:"+objectb.getForce());
								if(objecta.getForce().add(objectb.getForce()).length()>0/*0.67f*/ && 
										objecta.getForce().getX()==0 && objectb.getForce().getX()==0 && 
										objecta.getForce().getZ()==0 && objectb.getForce().getZ()==0){
									//move phyModel in direction force before collision
									if(!objecta.getPhyModel().isStationary())objecta.getTransform().setPos(objecta.getTransform().getPos().add(objecta.getForce().mul(collDelta)));
									if(!objectb.getPhyModel().isStationary())objectb.getTransform().setPos(objectb.getTransform().getPos().add(objectb.getForce().mul(collDelta)));
									//calcualte new force
									if(!objecta.getPhyModel().isStationary())objecta.setForce(objecta.getForce().mul(objecta.getPhyModel().getBounce()).mul(collisionNormala));
									if(!objectb.getPhyModel().isStationary())objectb.setForce(objectb.getForce().mul(objectb.getPhyModel().getBounce()).mul(collisionNormalb));
									//move phyModel in direction force after collision
									if(!objecta.getPhyModel().isStationary())objecta.getTransform().setPos(objecta.getTransform().getPos().add(objecta.getForce().mul(afterCollDelta)));
									if(!objectb.getPhyModel().isStationary())objectb.getTransform().setPos(objectb.getTransform().getPos().add(objectb.getForce().mul(afterCollDelta)));
									//begin bisection to check at what point boxes collide TODO
									//sampling 5 times for now.
								}else{
									objecta.setForce(new Vector3f(0,0,0));
									objectb.setForce(new Vector3f(0,0,0));
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void addObject(PhysicalObject object){
		objects.add(object);
	}
	
	public void setParent(CollisionObject coll){
		this.parent = coll;
	}
}
