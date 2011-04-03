package com.kina.jenkins.divida;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.tasks.Publisher;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

@Extension
public class DividaDescriptor  extends BuildStepDescriptor<Publisher> {

    public DividaDescriptor() {
    	super(DividaPublisher.class);
    	load();
    }
	
	private String divida = "coca";

	public String getDivida() {
		return divida;
	}

	public boolean isApplicable(Class<? extends AbstractProject> aClass) {
        return true;
    }



    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        req.bindJSON(this, json);
        save();
        return true;
    }
    
    @Override
    public String getDisplayName() {
        return "Divida da " + divida;
    }
    
    @Override
    public DividaPublisher newInstance(StaplerRequest req, JSONObject formData)
    		throws hudson.model.Descriptor.FormException {
    	return new DividaPublisher();
    }



}