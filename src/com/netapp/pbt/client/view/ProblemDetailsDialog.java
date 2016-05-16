package com.netapp.pbt.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.netapp.gwt.module.models.ProblemViewModel;

public class ProblemDetailsDialog extends DialogBox{
	
	public ProblemDetailsDialog(ProblemViewModel problem) {
		
		
		ScrollPanel mainPanel=new ScrollPanel();
		
		Label problemTitle=new Label(problem.getTitle());
		Label problemSymptom=new Label(problem.getLog_Message());
		Label problemTroubleshooting=new Label(problem.getTroubleshooting_Steps());
		Label problemDataCollection=new Label(problem.getAdditional_Data_Collection());
		Label problemDOTDetails=new Label(problem.getDOT_Details());
		Label problemProductName=new Label(problem.getProductName());
		Label problemModuleName=new Label(String.valueOf(problem.getModuleName()));
		Label problemSubModuleName=new Label(String.valueOf(problem.getSubModuleName()));
		
		
		mainPanel.setSize("600px", "600px");
		setText("Problem Details");
//		setAnimationEnabled(true);
		setGlassEnabled(true);
		Button ok=new Button("OK");
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ProblemDetailsDialog.this.hide();
			}
		});
		
		VerticalPanel problemDetailsPanel=new VerticalPanel();
		problemDetailsPanel.setSpacing(3);
		problemDetailsPanel.add(new HTML("<h3>Product Name</h3>"));
		problemDetailsPanel.add(problemProductName);
		problemDetailsPanel.add(new HTML("<h3>Module Name</h3>"));
		problemDetailsPanel.add(problemModuleName);
		problemDetailsPanel.add(new HTML("<h3>SubModule Name"));
		problemDetailsPanel.add(problemSubModuleName);
		problemDetailsPanel.add(new HTML("<h3>ONTAP Mode</h3>"));
		problemDetailsPanel.add(problemDOTDetails);
		problemDetailsPanel.add(new HTML("<h3>Title</h3>"));
		problemDetailsPanel.add(problemTitle);
		problemDetailsPanel.add(new HTML("<h3> Error Messages</h3>"));
		problemDetailsPanel.add(problemSymptom);
		problemDetailsPanel.add(new HTML("<h3> Troubleshooting steps</h3>"));
		problemDetailsPanel.add(problemTroubleshooting);
		problemDetailsPanel.add(new HTML("<h3>Data collection steps</h3>"));
		problemDetailsPanel.add(problemDataCollection);
		

		problemDetailsPanel.add(ok);
		
		problemDetailsPanel.setCellHorizontalAlignment(ok, HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(problemDetailsPanel);
		setWidget(mainPanel);
//		setSize("50em", "25em");
}

}
