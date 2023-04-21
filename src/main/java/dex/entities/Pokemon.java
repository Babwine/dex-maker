package dex.entities;

import java.util.Map;

import dex.constants.PokemonType;
import dex.constants.PokemonVersion;

public class Pokemon {

	protected int id;
	protected String name;
	protected String category;
	protected double height;
	protected double weight;
	protected PokemonType type1;
	protected PokemonType type2;
	protected Map<PokemonVersion, String> dexEntries;
	
	public Pokemon(
			int id,
			String name, 
			String category, 
			double height, 
			double weight,
			PokemonType type1,
			PokemonType type2,
			Map<PokemonVersion, String> dexEntries) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.height = height;
		this.weight = weight;
		this.type1 = type1;
		this.type2 = type2;
		this.dexEntries = dexEntries;
	}

	public int getId() {
		return id;
	}
	
	public String getIdAsString() {
		String toWrite = "";
		while (toWrite.length() < 3 - (id+"").length()) {
			toWrite+="0";
	    }
		toWrite += id;
		return toWrite;
	}
	
	public String getValidName() {
		return name == null ? "" :name.replace(" " , "_").replace("'", "");
	}
	
	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public PokemonType getType1() {
		return type1;
	}

	public PokemonType getType2() {
		return type2;
	}

	public Map<PokemonVersion, String> getDexEntries() {
		return dexEntries;
	}

	
	
}
