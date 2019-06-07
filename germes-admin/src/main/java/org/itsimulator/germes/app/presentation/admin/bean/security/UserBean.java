package org.itsimulator.germes.app.presentation.admin.bean.security;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserBean.class);

	private String username;
	private String password;

	public void login() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());

		try {
			subject.login(token);

			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

		} catch (UnknownAccountException ex) {
			facesError("Unknown account");
			LOGGER.error(ex.getMessage(), ex);
		} catch (IncorrectCredentialsException ex) {
			facesError("Wrong password");
			LOGGER.error(ex.getMessage(), ex);
		} catch (LockedAccountException ex) {
			facesError("Locked account");
			LOGGER.error(ex.getMessage(), ex);
		} catch (AuthenticationException | IOException ex) {
			facesError("Unknown error: " + ex.getMessage());
			LOGGER.error(ex.getMessage(), ex);
		} finally {
			token.clear();
		}
	}

	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
}
