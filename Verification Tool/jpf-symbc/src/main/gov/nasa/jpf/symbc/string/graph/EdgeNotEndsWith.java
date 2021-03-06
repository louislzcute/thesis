package gov.nasa.jpf.symbc.string.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EdgeNotEndsWith implements Edge{
	Vertex v1, v2;
	final String name;
	
	public EdgeNotEndsWith (String name, Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.name = name;
	}

	@Override
	public Vertex getDest() {
		return v2;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Vertex getSource() {
		return v1;
	}

	@Override
	public List<Vertex> getSources() {
		List<Vertex> result = new ArrayList<Vertex>();
		result.add (v1);
		return result;
	}

	@Override
	public boolean isHyper() {
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int smallest, biggest, v1HashCode, v2HashCode;
		v1HashCode = v1.hashCode();
		v2HashCode = v2.hashCode();
		if (v1HashCode > v2HashCode) {
			smallest = v2HashCode;
			biggest = v1HashCode;
		}
		else {
			smallest = v1HashCode;
			biggest = v2HashCode;
		}
		result = prime * result + ((v1 == null) ? 0 : smallest);
		result = prime * result + ((v2 == null) ? 0 : biggest);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EdgeNotEndsWith)) {
			return false;
		}
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeNotEndsWith other = (EdgeNotEndsWith) obj;
		if (v1 == null) {
			if (other.v1 != null)
				return false;
		} 
		if (v2 == null) {
			if (other.v2 != null)
				return false;
		} 
		
		if (v1.equals(other.v1) && v2.equals(other.v2)) {
			//System.out.println("[NOTE] " + this.toString() + " is equal to " + other.toString());
			return true;
		}
		else if (v1.equals(other.v2) && v2.equals(other.v1)) {
			//System.out.println("[NOTE] " + this.toString() + " is equal to " + other.toString());
			return true;
		}
		
		return false;
	}
	
	public String toString () {
		return v1 + " --> " + v2;
	}

	@Override
	public boolean isDirected() {
		return true;
	}
	
	public void setSource (Vertex v) {
		this.v1 = v;
	}
	public void setDest (Vertex v) {
		this.v2 = v;
	}

	@Override
	public boolean allVertecisAreConstant() {
		return v1.isConstant() && v2.isConstant();
	}

	@Override
	public Edge cloneAndSwapVertices(Map<Vertex, Vertex> oldToNew) {
		return new EdgeNotEndsWith(name, oldToNew.get(v1), oldToNew.get(v2));
	}
}
