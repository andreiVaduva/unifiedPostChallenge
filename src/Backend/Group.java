package Backend;

public class Group {
	public int id;
	public String name;
	public int points;
	public Group(int id,String name,int points) {
		this.id = id;
		this.name = name;
		this.points = points;
	}
	
	public String toString() {
		return id + " " + name;
	}
}
