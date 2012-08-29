package dk.noitso.vaerloesefh.data;

import java.util.ArrayList;
import java.util.List;

public class Obstruction {
	private String name;
	private String safety;
	private String description;
	private int number;
	private List<Integer> images;
	
	public Obstruction(String name, String safety, String description, int number) {
		this.name = name;
		this.safety = safety;
		this.description = description;
		this.number = number;
		this.images = new ArrayList<Integer>();
	}

	public void setImages(List<Integer> images) {
		this.images = images;
	}
	
	public void addImage(int imageUrl) {
		this.images.add(imageUrl);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Integer> getImages() {
		return images;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
