package dex.service.impl;

import java.awt.Color;
import java.io.IOException;

import dex.constants.PokemonVersion;
import dex.entities.Pokemon;
import dex.service.DexImageService;

public class BlackGlassDexImageService extends DexImageService {

	public BlackGlassDexImageService() throws IOException {
		super(PokemonVersion.BLACK_GLASS);
	}
	
	public void addDataToImageByPokemon(Pokemon pokemon) throws IOException {
		super.addDataToImageByPokemon(pokemon, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE);
	}
	
	public void writePokemonName(String name) {
		super.writePokemonName(name, Color.WHITE);
	}
	
	public void writePokemonHeightAndWeight(double height, double weight) {
		super.writePokemonHeightAndWeight(height, weight, Color.BLACK);
	}
	
	public void writePokemonCategory(String category) {
		super.writePokemonCategory(category, Color.BLACK);
	}
	
	public void generateDexEntryImage(Pokemon pokemon) throws IOException {
        super.generateDexEntryImage(pokemon, "src/main/resources/dex/", "_BG");
	}

}
