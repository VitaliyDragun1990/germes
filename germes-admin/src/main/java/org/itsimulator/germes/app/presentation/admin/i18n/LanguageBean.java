package org.itsimulator.germes.app.presentation.admin.i18n;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Locale for the current user
	 */
	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	public void setLanguage(String lang) {
		this.locale = new Locale(lang);
	}
	
	@PostConstruct
	public void init() {
		// after creation bean gets its locale from the user's request
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}
}
