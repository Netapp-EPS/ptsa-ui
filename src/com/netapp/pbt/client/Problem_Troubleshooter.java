package com.netapp.pbt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.netapp.pbt.client.view.ProblemView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Problem_Troubleshooter implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final ProblemView pbView=new ProblemView();
		RootLayoutPanel.get().add(pbView.init());

	}
}
