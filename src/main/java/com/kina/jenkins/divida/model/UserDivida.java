package com.kina.jenkins.divida.model;

import hudson.model.User;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import com.kina.jenkins.divida.UserDividaProperty;

@ExportedBean(defaultVisibility = 999)
public class UserDivida {
	private User user;
	private UserDividaProperty property;

	public UserDivida(User user, UserDividaProperty property) {
		this.user = user;
		this.property = property;
	}

	@Exported
	public User getUser() {
		return user;
	}
	
    @Exported
	public int getTotalDivida() {
		return property.getQuantidadeTotalDivida();
	}
    
    @Exported
    public int getDivida(){
    	return property.getDivida();
    }

    @Exported
    public int getPago(){
    	return property.getPago();
    }
	
	

}
