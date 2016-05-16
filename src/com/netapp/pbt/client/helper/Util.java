package com.netapp.pbt.client.helper;

import com.google.gwt.core.shared.GWT;

public class Util {
	
	private static GUIServiceAsync guiService=GWT.create(GUIService.class);


	public static void setGuiService(GUIServiceAsync guiService) {
		Util.guiService = guiService;
	}

	public static GUIServiceAsync getGuiService() {
		// TODO Auto-generated method stub
		return guiService;
	}

}
