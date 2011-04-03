package com.kina.jenkins.divida;

import hudson.Extension;
import hudson.model.RootAction;
import hudson.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import com.kina.jenkins.divida.model.UserDivida;


@ExportedBean(defaultVisibility = 999)
@Extension
public class BoardDividaAction implements RootAction{
    private static final long serialVersionUID = 1L;

	public String getIconFileName() {
		return "/plugin/divida/coca-gelada.jpg";
	}

	public String getDisplayName() {
		return "Divida da Coca";
	}

    public String getUrlName() {
        return "/divida"; 
    }
	
	@Exported
	public Collection<UserDivida> getUserDividas(){
		List<UserDivida> userDivida = new ArrayList<UserDivida>();
		Collection<User> allUsers = User.getAll();
		for (User user : allUsers) {
			UserDividaProperty property = user.getProperty(UserDividaProperty.class);
			if(property == null){
				continue;
			}
			userDivida.add( new UserDivida(user, property) );
		}
		
        Collections.sort(userDivida, new Comparator<UserDivida>() {
            public int compare(UserDivida o1, UserDivida o2) {
                if (o1.getTotalDivida() < o2.getTotalDivida())
                    return 1;
                if (o1.getTotalDivida() > o2.getTotalDivida())
                    return -1;
                return 0;
            }
        });

		
		return userDivida;
	}

}
