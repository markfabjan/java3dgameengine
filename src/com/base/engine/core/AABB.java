package com.base.engine.core;

public class AABB {
	private float maxx;
	private float minx;
	private float maxy;
	private float miny;
	private float maxz;
	private float minz;
	
	public AABB(float maxx, float minx, float maxy, float miny, float maxz, float minz){
		this.maxx = maxx;
		this.minx = minx;
		this.maxy = maxy;
		this.miny = miny;
		this.maxz = maxz;
		this.minz = minz;
	}
	
	public boolean intersectsWith(AABB b){
		return ((minx <= b.maxx && maxx >= b.minx) &&
				(miny <= b.maxy && maxy >= b.miny) &&
				(minz <= b.maxz && maxz >= b.minz));
	}
	
	public boolean containsPoint(Vector3f point){
		return (point.getX()<=maxx && point.getX()>=minx &&
				point.getY()<=maxy && point.getY()>=miny &&
				point.getZ()<=maxz && point.getZ()>=minz);	
	}
	
	public AABB forceExtend(Vector3f force,float delta){
		AABB box = new AABB(maxx, minx, maxy, miny, maxz, minz);
		if(force.getX()>0){
			box.maxx+=(force.getX()*delta);
		}else{
			box.minx+=(force.getX()*delta);
		}
		if(force.getY()>0){
			box.maxy+=(force.getY()*delta);
		}else{
			box.miny+=(force.getY()*delta);
		}
		if(force.getZ()>0){
			box.maxz+=(force.getZ()*delta);
		}else{
			box.minz+=(force.getZ()*delta);
		}
		return box;
	}
	
	public Vector3f getCollisionNormal(AABB b){
		Vector3f normal = new Vector3f(0,0,0);
		if(b.containsPoint(new Vector3f(minx,miny,minz))){normal=normal.add(new Vector3f(minx,miny,minz));}
		if(b.containsPoint(new Vector3f(minx,miny,maxz))){normal=normal.add(new Vector3f(minx,miny,maxz));}
		if(b.containsPoint(new Vector3f(minx,maxy,minz))){normal=normal.add(new Vector3f(minx,maxy,minz));}
		if(b.containsPoint(new Vector3f(minx,maxy,maxz))){normal=normal.add(new Vector3f(minx,maxy,maxz));}
		if(b.containsPoint(new Vector3f(maxx,miny,minz))){normal=normal.add(new Vector3f(maxx,miny,minz));}
		if(b.containsPoint(new Vector3f(maxx,miny,maxz))){normal=normal.add(new Vector3f(maxx,miny,maxz));}
		if(b.containsPoint(new Vector3f(maxx,maxy,minz))){normal=normal.add(new Vector3f(maxx,maxy,minz));}
		if(b.containsPoint(new Vector3f(maxx,maxy,maxz))){normal=normal.add(new Vector3f(maxx,maxy,maxz));}
		return normal.normalized();
	}
	
	public float getCollisionDelta(Vector3f forcea,AABB b,Vector3f forceb,float delta,int sampling){
		//TODO: do this with bisection later as this may be too slow.
		float collDelta = 0.0f;
		for(int i=0;i<sampling;i++){
			collDelta = (delta/sampling)*(i+1); 
			if(this.forceExtend(forcea, collDelta).intersectsWith(b.forceExtend(forceb, collDelta))){
				return collDelta;
			}
		}
		return collDelta;
	}

	public float getMaxx() {
		return maxx;
	}

	public void setMaxx(float maxx) {
		this.maxx = maxx;
	}

	public float getMinx() {
		return minx;
	}

	public void setMinx(float minx) {
		this.minx = minx;
	}

	public float getMaxy() {
		return maxy;
	}

	public void setMaxy(float maxy) {
		this.maxy = maxy;
	}

	public float getMiny() {
		return miny;
	}

	public void setMiny(float miny) {
		this.miny = miny;
	}

	public float getMaxz() {
		return maxz;
	}

	public void setMaxz(float maxz) {
		this.maxz = maxz;
	}

	public float getMinz() {
		return minz;
	}

	public void setMinz(float minz) {
		this.minz = minz;
	}
	
	
}
