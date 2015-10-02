package com.erp.myapp.metier;

import com.erp.myapp.entity.Theme;

public interface IThemeMetier {
	public Theme getTheme();
	public void addTheme(Theme t);
	public void updateTheme(Theme t);
}
