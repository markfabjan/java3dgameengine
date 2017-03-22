package com.base.engine.physics;


public class Triangle {
	private SimpleVert a;
	private SimpleVert b;
	private SimpleVert c;
	
	public Triangle(SimpleVert a, SimpleVert b, SimpleVert c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public SimpleVert getA() {
		return a;
	}

	public void setA(SimpleVert a) {
		this.a = a;
	}

	public SimpleVert getB() {
		return b;
	}

	public void setB(SimpleVert b) {
		this.b = b;
	}

	public SimpleVert getC() {
		return c;
	}

	public void setC(SimpleVert c) {
		this.c = c;
	}
}
