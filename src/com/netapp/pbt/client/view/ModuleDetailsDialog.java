package com.netapp.pbt.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.netapp.gwt.module.models.Module;

public class ModuleDetailsDialog extends DialogBox{
	
public ModuleDetailsDialog(Module module) {
	
	setText(module.getName());
	ScrollPanel mainPanel=new ScrollPanel();
	mainPanel.setSize("600px", "600px");

//	setAnimationEnabled(true);
	setGlassEnabled(true);
	Button ok=new Button("OK");
	ok.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			ModuleDetailsDialog.this.hide();
		}
	});
	
	VerticalPanel moduleDetails=new VerticalPanel();
	moduleDetails.setSpacing(3);
	moduleDetails.add(new HTML(module.getDescription()));
//	problemList.add(new HTML("b) List all the snapmirror relationships from the primary by using snapmirror-get-iter on primary"));
//	problemList.add(new HTML("c) Prepare list of destination volumes and fire snapmirror-get-status on all the destination filers \n"));
//	problemList.add(new HTML("d) volume-list-info-iter-start will be fired on the destination to load the volume content "));
//	problemList.add(new HTML("e) List all the snapshots of the secondary volumes by firing snapshot-list-info on secondary "));
//	problemList.add(new HTML("f) Display the restore wizard"));
	moduleDetails.add(ok);
	
	moduleDetails.setCellHorizontalAlignment(ok, HasHorizontalAlignment.ALIGN_CENTER);
	mainPanel.add(moduleDetails);
	setWidget(mainPanel);
//	setSize("50em", "25em");
}

}
