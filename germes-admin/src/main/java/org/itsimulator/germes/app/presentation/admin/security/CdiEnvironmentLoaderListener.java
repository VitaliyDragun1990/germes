package org.itsimulator.germes.app.presentation.admin.security;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;
import org.itsimulator.germes.app.infra.util.SecurityUtil;
import org.itsimulator.germes.app.model.entity.person.User;
import org.itsimulator.germes.app.service.UserService;

/**
 * Initializes authentication realm that delegates processing to CDIRealm
 * 
 * @author Vitaly Dragun
 *
 */
@WebListener
public class CdiEnvironmentLoaderListener extends EnvironmentLoaderListener {

	@Inject
	private UserService userService;

	@Override
	protected WebEnvironment createEnvironment(ServletContext context) {
		WebEnvironment environment = super.createEnvironment(context);
		RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();
		rsm.setRealm(new CDIRealm(userService));
		
		saveTestUser();
		
		return environment;
	}
	
	private void saveTestUser() {
		// test user
//		User user = new User();
//		user.setUserName("guest");
//		user.setPassword(SecurityUtil.encryptSHA("guest"));
//		userService.save(user);
	}

}
