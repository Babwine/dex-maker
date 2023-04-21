package dex.constants;

public enum PokemonVersion {

	BLACK_GLASS("src/main/resources/templates/dex_entry_template_black_glass.png"),
	WHITE_SAND("src/main/resources/templates/dex_entry_template_white_sand.png") ;
	
	private String templatePath;
	
	private PokemonVersion(String templatePath) {
		this.setTemplatePath(templatePath);
	}

	public String getTemplatePath() {
		return templatePath;
	}

	private void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	
}
