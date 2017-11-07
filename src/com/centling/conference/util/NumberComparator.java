package com.centling.conference.util;

import java.util.Comparator;

import com.centling.conference.sysmenu.entity.ConfSysMenu;

public class NumberComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		ConfSysMenu confSysMenu1 = (ConfSysMenu)o1;
		ConfSysMenu confSysMenu2 = (ConfSysMenu)o2;
		
		return confSysMenu1.getShowOrder()-confSysMenu2.getShowOrder();
	}
}