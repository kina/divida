package com.kina.jenkins.divida.test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.model.User;
import hudson.scm.ChangeLogSet;
import hudson.scm.ChangeLogSet.Entry;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kina.jenkins.divida.DividaPublisher;
import com.kina.jenkins.divida.UserDividaProperty;


public class PublisherTest{
	
	@Test
	public void verificaSoma() throws Exception {
        User user = createUser("test");

        List<Entry> changesetList = new ArrayList<ChangeLogSet.Entry>();
        changesetList.add(createEntry(user));

		ChangeLogSet<Entry> changelog = mock(ChangeLogSet.class);
        when(changelog.iterator()).thenReturn(changesetList.iterator(),changesetList.iterator());
      
        AbstractBuild build = createBuild();
        when(build.getChangeSet()).thenReturn(changelog);


        new DividaPublisher().perform(build, null, null);
        
        assertThat( user.getProperty(UserDividaProperty.class).getDivida(), is(1));
	
        new DividaPublisher().perform(build, null, null);
        
        assertThat( user.getProperty(UserDividaProperty.class).getDivida(), is(2));
	}
	
	private AbstractBuild createBuild(){
        AbstractBuild build = mock(AbstractBuild.class);
        when(build.getResult()).thenReturn(Result.FAILURE);
        return build;

	}

	
	private Entry createEntry(User user){
        Entry entry = mock(Entry.class);
        when(entry.getAuthor()).thenReturn(user);
        return entry;
	}

	private User createUser(String id) {
		User user = mock(User.class);
        when(user.getId()).thenReturn( id );
        when(user.getProperty(UserDividaProperty.class)).thenReturn(new UserDividaProperty(0, 0));
		return user;
	}
}
