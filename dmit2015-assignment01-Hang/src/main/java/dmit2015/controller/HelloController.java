package dmit2015.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named("currentHelloController")
@RequestScoped
public class HelloController {

	private String username;	// +getter +setter

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void sayHello(ActionEvent event) {
		Messages.addGlobalInfo("Hello {0} :) Welcome to JSF world!", username);
		username = "";
	}
}
