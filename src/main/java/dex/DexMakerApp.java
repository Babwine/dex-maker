package dex;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import dex.entities.Pokemon;
import dex.service.impl.BlackGlassDexImageService;
import dex.service.impl.WhiteSandDexImageService;

/**
 * Hello world!
 *
 */
public class DexMakerApp {
	
	
    public static void main( String[] args ) throws IOException, ParseException {
    	WhiteSandDexImageService whiteSandDexImageService = new WhiteSandDexImageService();
    	BlackGlassDexImageService blackGlassDexImageService = new BlackGlassDexImageService();
    	
    	JSONParser parser = new JSONParser();
    	Object obj = parser.parse(new FileReader("src/main/resources/POKEMONS_JSON.json"));
    	JSONArray jsonArray =  (JSONArray) obj;	
    	for (int i =0; i<jsonArray.size(); i++) {
    		Pokemon pkmn = new Gson().fromJson(jsonArray.get(i).toString(), Pokemon.class);
    		
    		whiteSandDexImageService.addDataToImageByPokemon(pkmn);
    		whiteSandDexImageService.generateDexEntryImage(pkmn);

    		blackGlassDexImageService.addDataToImageByPokemon(pkmn);
    		blackGlassDexImageService.generateDexEntryImage(pkmn);

            System.out.println(pkmn.getIdAsString()+" - "+pkmn.getName());
    	}
    }
}
