package dmit2015.oe.web;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("nlsLanguageController")
@ApplicationScoped
public class LanguageController implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Map<String, String> languageMap = createLanguages();
	
	private Map<String, String> createLanguages() {
		Map<String, String> nlsMap = new TreeMap<>();
		nlsMap.put("AR","Arabic");
		nlsMap.put("CA","Catalan");
		nlsMap.put("CS","Czech");
		nlsMap.put("D","German");
		nlsMap.put("DK","Danish");
		nlsMap.put("E","Spanish");
		nlsMap.put("EL","Greek");
		nlsMap.put("ESA","Latin American Spanish");
		nlsMap.put("F","French");
		nlsMap.put("FRC","Canadian French");
		nlsMap.put("HU","Hungarin");
		nlsMap.put("I","Italian");
		nlsMap.put("IW","Hebrew");
		nlsMap.put("JA","Japan");
		nlsMap.put("KO","Korean");
		nlsMap.put("N","Norwegian");
		nlsMap.put("NL","Dutch");
		nlsMap.put("PL","Polish");
		nlsMap.put("PT","Portuguese");
		nlsMap.put("PTB","Brazilian Portuguese");
		nlsMap.put("RO","Romanian");
		nlsMap.put("RU","Russian");
		nlsMap.put("S","Swedish");
		nlsMap.put("SF","Finish");
		nlsMap.put("SK","Slovak");
		nlsMap.put("TH","Thai");
		nlsMap.put("TR","Turkish");
		nlsMap.put("US","American");
		nlsMap.put("ZHS","Simplified Chinese");
		nlsMap.put("ZHT","Traditional Chinese");

		return nlsMap;
	}

	public Map<String, String> getLanguageMap() {
		return languageMap;
	}
	
}
