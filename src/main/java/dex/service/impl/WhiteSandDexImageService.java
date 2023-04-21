package dex.service.impl;

import java.awt.Color;
import java.io.IOException;

import dex.constants.PokemonVersion;
import dex.entities.Pokemon;
import dex.service.DexImageService;

public class WhiteSandDexImageService extends DexImageService  {

	public WhiteSandDexImageService() throws IOException {
		super(PokemonVersion.WHITE_SAND);
	}
	
	public void addDataToImageByPokemon(Pokemon pokemon) throws IOException {
		super.addDataToImageByPokemon(pokemon, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
	}
	
	public void writePokemonName(String name) {
		super.writePokemonName(name, Color.BLACK);
	}
	
	public void writePokemonHeightAndWeight(double height, double weight) {
		super.writePokemonHeightAndWeight(height, weight, Color.BLACK);
	}
	
	public void writePokemonCategory(String category) {
		super.writePokemonCategory(category, Color.BLACK);
	}
	
	public void generateDexEntryImage(Pokemon pokemon) throws IOException {
        super.generateDexEntryImage(pokemon, "src/main/resources/dex/", "_WS");
	}


}
