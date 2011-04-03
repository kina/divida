package com.kina.jenkins.divida;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.User;
import hudson.model.Action;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.scm.ChangeLogSet;
import hudson.scm.ChangeLogSet.Entry;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;

public class DividaPublisher extends Notifier{

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.BUILD;
	}
	
	
	@Override
	public boolean needsToRunAfterFinalized() {
		return true;
	}
	
	@Override
	public Action getProjectAction(AbstractProject<?, ?> project) {
		return null;
	}
	
	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,BuildListener listener) throws InterruptedException, IOException {
		boolean retorno = false;
		
		
		if( isResultCanNotSum(build.getResult()) ){
			return retorno;
		}
		
		ChangeLogSet<? extends Entry> changeSet = build.getChangeSet();
		if(changeSet == null){
			return retorno;
		}
		for (Entry entry : changeSet) {
			User author = entry.getAuthor();
			UserDividaProperty property = author.getProperty(UserDividaProperty.class);
			if( property == null ){
				property = new UserDividaProperty();
				author.addProperty(property);
			}
			property.somaMaisUmaDivida();
			author.save();
			retorno = true;
		}
		
		return retorno;
	}

	private boolean isResultCanNotSum(Result result) {
		return Result.SUCCESS == result || Result.ABORTED == result || Result.NOT_BUILT == result;
	}
	

}
