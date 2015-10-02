package com.erp.myapp.dao;

import com.erp.myapp.entity.Theme;

public interface IThemeDao {
	public Theme getTheme();
	public void addTheme(Theme t);
	public void updateTheme(Theme t);
}
