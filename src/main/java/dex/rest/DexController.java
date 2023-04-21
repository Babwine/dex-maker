package dex.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dex.constants.AppConstants;

@RestController
@RequestMapping("dex-maker")
public class DexController {
	  
	  @RequestMapping("/")
	  public ModelAndView home() {
	      ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("index.html");
	      return modelAndView;
	  }

	  //gets html from a default 'resources/public' or 'resources/static' folder
	  @RequestMapping("/dex-WS")
	  public ModelAndView displayDexWS() {
		  List<String> paths = new ArrayList<>();
		  
		  File folder = new File(AppConstants.GLOBAL_RESOURCES_STATIC_PATH+"/dex");
		  System.out.println(AppConstants.GLOBAL_RESOURCES_STATIC_PATH+"/dex");
		  for (File subFolder : folder.listFiles()) {
			  String path = subFolder.getName();
			  for (File f : subFolder.listFiles()) {
				  if (f.getName().endsWith("_WS.jpg")) {
					  path+="/"+f.getName();
					  paths.add(path);
					  path = subFolder.getName();
				  }
			  }
		  }
		  
		  
	      ModelAndView modelAndView = new ModelAndView();
	      modelAndView.addObject("imageNames", paths);
	      modelAndView.setViewName("dex-WS.html");
	      return modelAndView;
	  }
	  
	  @RequestMapping("/dex-BG")
	  public ModelAndView displayDexBG() {
		  List<String> paths = new ArrayList<>();
		  
		  File folder = new File(AppConstants.GLOBAL_RESOURCES_STATIC_PATH+"/dex");
		  System.out.println(AppConstants.GLOBAL_RESOURCES_STATIC_PATH+"/dex");
		  for (File subFolder : folder.listFiles()) {
			  String path = subFolder.getName();
			  for (File f : subFolder.listFiles()) {
				  if (f.getName().endsWith("_BG.jpg")) {
					  path+="/"+f.getName();
					  paths.add(path);
					  path = subFolder.getName();
				  }
			  }
		  }
		  
		  
	      ModelAndView modelAndView = new ModelAndView();
	      modelAndView.addObject("imageNames", paths);
	      modelAndView.setViewName("dex-BG.html");
	      return modelAndView;
	  }
}
