package com.kina.jenkins.divida;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;

@Extension
public class UserDividaPropertyDescriptor extends UserPropertyDescriptor {

	public UserDividaPropertyDescriptor() {
		super(UserDividaProperty.class);
	}
	
	@Override
	public UserProperty newInstance(User user) {
		return null;
	}
	
	@Override
	public UserProperty newInstance(StaplerRequest req, JSONObject formData) throws hudson.model.Descriptor.FormException {
        if (formData.has("pago")) { 
            return req.bindJSON(UserDividaProperty.class, formData);
        }
        return new UserDividaProperty();

	}
	

	@Override
	public String getDisplayName() {
		return "Divida da coca";
	}
}
