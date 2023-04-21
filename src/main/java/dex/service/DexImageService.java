package dex.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import dex.constants.AppConstants;
import dex.constants.PokemonType;
import dex.constants.PokemonVersion;
import dex.entities.Pokemon;

public abstract class DexImageService {
	
	protected String path;
	protected BufferedImage image;
	protected Graphics graphics;
	protected PokemonVersion version;
	
	protected static String MAIN_FONT_NAME = "Pokemon X and Y";
	
	protected static int ENTRY_MAX_LENGTH = 55;
	
	protected static int SPRITE_FRONT_X = 10;
	protected static int SPRITE_FRONT_Y = 80;

	protected static int ICON_X = 267;
	protected static int ICON_Y = 174;
	
	protected static int TYPE1_X = 358;
	protected static int TYPE1_Y = 161;
	protected static int TYPE2_X = 462;
	protected static int TYPE2_Y = 161;
	
	protected static int NAME_X = 308;
	protected static int NAME_Y = 92;
	
	protected static int CATEG_X = 270;
	protected static int CATEG_Y = 134;

	protected static int HEIGHT_X = 435;
	protected static int HEIGHT_Y = 251;

	protected static int WEIGHT_X = 435;
	protected static int WEIGHT_Y = 292;
	
	protected static int ENTRY_X = 50;
	protected static int ENTRY_Y = 380;
	protected static int ENTRY_INTER = 30;

	protected static int ID_X = 580;
	protected static int ID_Y = 32;
	
	protected static int SIZE_BIG = 25;
	protected static int SIZE_NORMAL_PLUS = 24;
	protected static int SIZE_NORMAL = 22;
	
	
	public DexImageService (PokemonVersion version) throws IOException {
		this.version = version;
		this.path = this.getTemplatePath();
		this.image = ImageIO.read(new File(path));
		this.graphics = this.image.getGraphics();
	}
	
	public void addDataToImageByPokemon(Pokemon pokemon, Color colorName, Color colorCategory, Color colorHeightWeight, Color colorEntry, Color colorId) 
			throws IOException {
		this.initImage();
		this.writePokemonName(pokemon.getName(), colorName);
		this.writePokemonCategory(pokemon.getCategory(), colorCategory);
		this.writePokemonHeightAndWeight(pokemon.getHeight(), pokemon.getWeight(), colorHeightWeight);
		if (pokemon.getDexEntries() != null && !pokemon.getDexEntries().isEmpty()) this.writePokemonDexEntry(pokemon.getDexEntries().get(this.version), colorEntry);
		this.drawPokemonType1(pokemon.getType1());
		if (pokemon.getType2() != null) this.drawPokemonType2(pokemon.getType2());
    	this.drawPokemonSpriteFront(pokemon);
    	this.drawPokemonIcon(pokemon);
    	this.writePokemonId(pokemon, colorId);
	}
	
	public void initImage() throws IOException {
		this.image = ImageIO.read(new File(path));
		this.graphics = this.image.getGraphics();
	}
	
	public String getTemplatePath() {
		return this.version.getTemplatePath();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void writePokemonName(String name, Color color) {
		if (name != null) {
	        graphics.setColor(color);
	        graphics.setFont(new Font("Pokemon X and Y", Font.BOLD, SIZE_BIG));
	        graphics.drawString(name, NAME_X, NAME_Y);
		}
	}
	
	public void writePokemonHeightAndWeight(double height, double weight, Color color) {
		graphics.setColor(color);
        graphics.setFont(new Font(MAIN_FONT_NAME, Font.BOLD, SIZE_NORMAL));
        graphics.drawString(height+"m", HEIGHT_X, HEIGHT_Y);
        graphics.drawString(weight+"kg", WEIGHT_X, WEIGHT_Y);
	}
	
	public void writePokemonCategory(String category, Color color) {
		graphics.setColor(color);
        graphics.setFont(new Font(MAIN_FONT_NAME, Font.BOLD, SIZE_NORMAL_PLUS));
        graphics.drawString("Pokémon "+category, CATEG_X, CATEG_Y);
	}
	
	public void writePokemonDexEntry(String entry, Color color) {
		graphics.setColor(color);
        graphics.setFont(new Font(MAIN_FONT_NAME, Font.BOLD, 24));
        
        if (entry != null && !entry.isBlank()) {
        	String[] tmp = this.splitEntry(entry);
            for (int i=0; i<tmp.length; i++) {
                graphics.drawString(tmp[i], ENTRY_X, ENTRY_Y+(ENTRY_INTER*i));
            }
        }
	}
	
	private String[] splitEntry(String entry) {
		String[] res = new String[3];
		int idx = 0;
		int cpt = 0;
		int lastBegin = 0;
		int oldSpace = 0;
		int newSpace = 0;
		for (int i=0; i<entry.length(); i++) {
			cpt++;
			if (" ".equals(entry.charAt(i)+"")) {
				oldSpace = newSpace;
				newSpace = i;
				if (cpt>=ENTRY_MAX_LENGTH) {
					res[idx] = entry.substring(lastBegin, oldSpace);
					lastBegin = oldSpace+1;
					i = lastBegin;
					cpt = 0;
					idx++;
				}
			} else if (i == entry.length()-1) {
				res[idx] = entry.substring(lastBegin, i+1);
			}
		} return res;
	}
	
	private BufferedImage getTypeImageFromType(PokemonType type) throws IOException {
		if (type == null) {
			return null;
		} else {
			return ImageIO.read(new File(AppConstants.GLOBAL_RESOURCES_IMG_PATH+"/types/"+type+".png"));
		}
	}
	
	public void drawPokemonType1(PokemonType type) throws IOException {
		BufferedImage typeImage = this.getTypeImageFromType(type);
		graphics.drawImage(typeImage, TYPE1_X, TYPE1_Y, null);
	}
	
	public void drawPokemonType2(PokemonType type) throws IOException {
		BufferedImage typeImage = this.getTypeImageFromType(type);
		graphics.drawImage(typeImage, TYPE2_X, TYPE2_Y	, null);
	}
	
	private BufferedImage getPokemonSpriteFront(String path) throws IOException {
		return ImageIO.read(new File(path));
	}
	
	private BufferedImage getPokemonSpriteFront(Pokemon pokemon) throws IOException {
		try {
			return ImageIO.read(new File(AppConstants.GLOBAL_RESOURCES_IMG_PATH+"/sprites/front/"+pokemon.getValidName()+".png"));
		} catch (IOException e) {
			return ImageIO.read(new File(AppConstants.GLOBAL_RESOURCES_IMG_PATH+"/templates/sprite_front_placeholder.png"));
		}
	}
	
	public void drawPokemonSpriteFront(String path) throws IOException {
		BufferedImage spriteFrontImage = this.getPokemonSpriteFront(path);
		graphics.drawImage(spriteFrontImage, SPRITE_FRONT_X, SPRITE_FRONT_Y, null);
	}
	
	public void drawPokemonSpriteFront(Pokemon pokemon) throws IOException {
		BufferedImage spriteFrontImage = this.getPokemonSpriteFront(pokemon);
		graphics.drawImage(spriteFrontImage, SPRITE_FRONT_X, SPRITE_FRONT_Y, null);
	}
	
	private BufferedImage getPokemonIcon(String path) throws IOException {
		return ImageIO.read(new File(path));
	}	
	
	private BufferedImage getPokemonIcon(Pokemon pokemon) throws IOException {
		try {
			return ImageIO.read(new File(AppConstants.GLOBAL_RESOURCES_IMG_PATH+"/sprites/icon/"+pokemon.getValidName()+".png"));
		} catch (IOException e) {
			return ImageIO.read(new File(AppConstants.GLOBAL_RESOURCES_IMG_PATH+"/templates/icon_placeholder.png"));
		}
	}
	
	public void drawPokemonIcon(String path) throws IOException {
		BufferedImage spriteIconImage = this.getPokemonIcon(path);
		graphics.drawImage(spriteIconImage, ICON_X, ICON_Y, null);
	}
	
	public void drawPokemonIcon(Pokemon pokemon) throws IOException {
		BufferedImage spriteIconImage = this.getPokemonIcon(pokemon);
		graphics.drawImage(spriteIconImage, ICON_X, ICON_Y, null);
	}
	
	public void generateDexEntryImage(Pokemon pokemon, String path, String extra) throws IOException {
		String fullPath = path+pokemon.getIdAsString()+"_"+(pokemon.getValidName());
		if (!Files.exists(Paths.get(fullPath))) {
			Files.createDirectory(Paths.get(fullPath));
		}
        ImageIO.write(this.image, "jpg", new File(fullPath+"/"+(pokemon.getValidName()+extra+".jpg")));
        this.image = null;
	}
	
	public void writePokemonId(Pokemon pokemon, Color color) {
		graphics.setColor(color);
        graphics.setFont(new Font(MAIN_FONT_NAME, Font.BOLD, SIZE_BIG));
        graphics.drawString(pokemon.getIdAsString(), ID_X, ID_Y);
	}
}
