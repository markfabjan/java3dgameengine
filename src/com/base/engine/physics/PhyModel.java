package com.base.engine.physics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.base.engine.core.AABB;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Transform;
import com.base.engine.core.Util;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Vertex;

public class PhyModel{
	
	private ArrayList<Vector3f> startingVertices;
	private ArrayList<Vector3f> currentVertices;
	private ArrayList<Index> indices;
	private ArrayList<Triangle> triangles;
	private Transform transform;
	private Float proximity;
	//private ArrayList<Vector3f> aabb;
	private AABB aabb;
	private float speed;
	private boolean stationary;
	private float bounce;



	public PhyModel(String filename, Transform transform){
		this.proximity = 0.0f;
		this.transform = transform;
		loadModel(filename);
		/*for (int i = 0; i < indices.size(); i++){
			Index ci=indices.get(i);
			triangles.set(i, new Triangle(currentVertices.get(ci.getA()),
											currentVertices.get(ci.getB()),
											currentVertices.get(ci.getC())));
		}*/
		//triangulate();
		recalculate();
		//enhance :DDD();
		calcAABB();
		bounce = 0;
		this.stationary = true;
	}
	
	public PhyModel(String filename, Transform transform, float bounce){
		this.proximity = 0.0f;
		this.transform = transform;
		loadModel(filename);
		/*for (int i = 0; i < indices.size(); i++){
			Index ci=indices.get(i);
			triangles.set(i, new Triangle(currentVertices.get(ci.getA()),
											currentVertices.get(ci.getB()),
											currentVertices.get(ci.getC())));
		}*/
		//triangulate();
		recalculate();
		//enhance :DDD();
		calcAABB();
		this.bounce = bounce;
		this.stationary = true;
		
	}
	
	public PhyModel(String filename, Transform transform, float bounce, boolean stationary){
		this.proximity = 0.0f;
		this.transform = transform;
		loadModel(filename);
		/*for (int i = 0; i < indices.size(); i++){
			Index ci=indices.get(i);
			triangles.set(i, new Triangle(currentVertices.get(ci.getA()),
											currentVertices.get(ci.getB()),
											currentVertices.get(ci.getC())));
		}*/
		//triangulate();
		recalculate();
		//enhance :DDD();
		calcAABB();
		this.bounce = bounce;
		this.stationary = stationary;
	}
	
	public void recalculate(){
		/*Vector3f absolute;
		Vector3f now = new Vector3f(transform.getPos().getX(),transform.getPos().getY(),transform.getPos().getZ());
		Quaternion crot = transform.getRot();
		Vector3f csca = transform.getScale();
		for (int i = 0; i < startingVertices.size(); i++){
			absolute = startingVertices.get(i);
			now.set(now.add(crot.getRight().mul((absolute.getPos().getX()*csca.getX()))));
			now.set(now.add(crot.getUp().mul((absolute.getPos().getY()*csca.getY()))));
			now.set(now.add(crot.getForward().mul((absolute.getPos().getZ()*csca.getZ()))));
			currentVertices.set(i, new Vector3f(now));
		}
		for (int i = 0; i < indices.size(); i++){
			Index ci=indices.get(i);
			triangles.set(i, new Triangle(currentVertices.get(ci.getA()),
											currentVertices.get(ci.getB()),
											currentVertices.get(ci.getC())));
		}*/
		//something in here is wrong and all currentVertices become the same //TODO
		//fix attempt 1:
		currentVertices.clear();
		for (Vector3f starting : startingVertices){
			currentVertices.add(starting.transform(transform));
		}
		
	}
	
	public ArrayList<Triangle> getTriangles(){
		return triangles;
	}
	
	private void loadModel(String file){
		startingVertices = new ArrayList<Vector3f>();
		currentVertices = new ArrayList<Vector3f>();
		indices = new ArrayList<Index>();

		BufferedReader meshReader = null;

		try
		{
			meshReader = new BufferedReader(new FileReader("res/phy/"+file));
			String line;

			while((line = meshReader.readLine()) != null)
			{
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyStrings(tokens);

				if(tokens.length == 0 || tokens[0].equals("#"))
					continue;
				else if(tokens[0].equals("v"))
				{
					//Vector3f absolute = new Vector3f(new Vector3f(Float.valueOf(tokens[1]),Float.valueOf(tokens[1]),Float.valueOf(tokens[1])));
					startingVertices.add((new Vector3f(Float.valueOf(tokens[1]),
																	Float.valueOf(tokens[2]),
																	Float.valueOf(tokens[3]))));
					//Vector3f now = transform.getPos();
					/*Vector3f now = new Vector3f(0,0,0);
					Vertex absolute = new Vertex(new Vector3f(Float.valueOf(tokens[1]),Float.valueOf(tokens[1]),Float.valueOf(tokens[1])));
					now.set(now.add(transform.getRot().getRight().mul(absolute.getPos().getX())));
					now.set(now.add(transform.getRot().getUp().mul(absolute.getPos().getY())));
					now.set(now.add(transform.getRot().getForward().mul(absolute.getPos().getZ())));
					currentVertices.add(now);*/
					currentVertices.clear();
					for (Vector3f starting : startingVertices){
						Vector3f newOne = starting.transform(transform);
						currentVertices.add(newOne);
						if(newOne.length() > proximity){
							proximity = newOne.length();
						}
					}
					//currentVertices.add(new Vector3f(new Vector3f(0,0,0)));
				}
				else if(tokens[0].equals("vt")){}
				else if(tokens[0].equals("vn")){}
				else if(tokens[0].equals("f"))
				{
					indices.add(new Index(Integer.parseInt(tokens[1])-1,
											Integer.parseInt(tokens[2])-1,
											Integer.parseInt(tokens[3])-1));
					/*triangles.add(new Triangle(startingVertices.get(Integer.parseInt(tokens[1])-1),
												startingVertices.get(Integer.parseInt(tokens[2])-1),
												startingVertices.get(Integer.parseInt(tokens[3])-1)));*/
				}
			}

			meshReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/*private void triangulate(){
		triangles = new ArrayList<Triangle>();
		for (int i = 0; i < indices.size(); i++){
			Index ci=indices.get(i);
			triangles.add(new Triangle(currentVertices.get(ci.getA()),
											currentVertices.get(ci.getB()),
											currentVertices.get(ci.getC())));
		}
	}*/

	public Float getProximity() {
		return proximity;
	}
	
	public void calcAABB(){
		float maxx =0.0f;
		float minx =0.0f;
		float maxy =0.0f;
		float miny =0.0f;
		float maxz =0.0f;
		float minz =0.0f;
		boolean first = true;
		for(Vector3f vert : currentVertices){
			if(first){
				maxx = vert.getX();
				minx = vert.getX();
				maxy = vert.getY();
				miny = vert.getY();
				maxz = vert.getZ();
				minz = vert.getZ();
				first = false;
			}else{
				maxx = Math.max(vert.getX(),maxx);
				minx = Math.min(vert.getX(),minx);
				maxy = Math.max(vert.getY(),maxy);
				miny = Math.min(vert.getY(),miny);
				maxz = Math.max(vert.getZ(),maxz);
				minz = Math.min(vert.getZ(),minz);
			}
		}
		//make documentation as you seem confused by this point
		//aabb = new ArrayList();
		//aabb.add(new Vector3f(new Vector3f(maxx,maxy,maxz)));
		//aabb.add(new Vector3f(new Vector3f(minx,miny,minz)));
		/*aabb.add(new Vector3f(new Vector3f(minx,miny,minz)));//1
		aabb.add(new Vector3f(new Vector3f(minx,miny,maxz)));//2
		aabb.add(new Vector3f(new Vector3f(minx,maxy,minz)));//3
		aabb.add(new Vector3f(new Vector3f(minx,maxy,maxz)));//4
		aabb.add(new Vector3f(new Vector3f(maxx,miny,minz)));
		aabb.add(new Vector3f(new Vector3f(maxx,miny,maxz)));
		aabb.add(new Vector3f(new Vector3f(maxx,maxy,minz)));
		aabb.add(new Vector3f(new Vector3f(maxx,maxystartingVertices,maxz)));*/
		aabb = new AABB(maxx, minx, maxy, miny, maxz, minz);
	}
	
	public AABB getAabb() {
		return aabb;
	}

	public void setAabb(AABB aabb) {
		this.aabb = aabb;
	}

	public void update(){
		recalculate();
		calcAABB();
	}
	
	public float getBounce() {
		return bounce;
	}

	public void setBounce(float bounce) {
		this.bounce = bounce;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isStationary() {
		return stationary;
	}

	public void setStationary(boolean stationary) {
		this.stationary = stationary;
	}
	
	
}
