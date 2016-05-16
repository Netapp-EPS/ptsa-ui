package com.netapp.pbt.client.view;

import java.io.IOException;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.netapp.gwt.module.models.Module;
import com.netapp.gwt.module.models.ProblemDTO;
import com.netapp.gwt.module.models.Product;
import com.netapp.gwt.module.models.SubModule;
import com.netapp.gwt.module.models.Version;
import com.netapp.pbt.client.helper.Util;

public class ProblemView extends Composite{
	
	
	public ProblemView(){
		init();
	}
	
	public Widget init(){
		
	VerticalPanel mainPanel=new VerticalPanel();
	HTML titleHtml=new HTML("<h1>Support Encyclopedia</h1>");
	titleHtml.ensureDebugId("titleHTML");
	HorizontalPanel hpanel=new HorizontalPanel();
	hpanel.setSpacing(20);
	final ValueListBox<Product> productListBox=new ValueListBox<Product>(new Renderer<Product>() {


		@Override
		public String render(Product product) {
			String productName="";
			if(product!=null){
				productName=product.getName();
			}
			return productName;
			
		}

		@Override
		public void render(Product object, Appendable appendable)
				throws IOException {
			String productName=render(object);
			appendable.append(productName);
			System.out.println(productName);
			
		}
	});
	
	populateAllProducts(productListBox);	
	productListBox.ensureDebugId("productListBox");
	productListBox.setVisible(true);
	VerticalPanel productListBoxPanel=new VerticalPanel();
	productListBoxPanel.setSpacing(10);
	productListBoxPanel.add(new HTML("Select Product"));
	productListBoxPanel.add(productListBox);
	
	
	final ValueListBox<Version> productVersionListBox=new ValueListBox<Version>(new Renderer<Version>() {


		@Override
		public String render(Version version) {
			String versionName="";
			if(version!=null){
				versionName=version.getNumber();
			}
			return versionName;
			
		}

		@Override
		public void render(Version object, Appendable appendable)
				throws IOException {
			String versionName=render(object);
			appendable.append(versionName);
			System.out.println(versionName);
			
		}
	});
	
	productVersionListBox.setVisible(true);
	productVersionListBox.ensureDebugId("productVersionListBox");
	productVersionListBox.setWidth("10em");
	productListBoxPanel.add(new HTML("Select Product Version"));
	productListBoxPanel.add(productVersionListBox);

	final ValueListBox<Module> moduleListBox=new ValueListBox<Module>(new Renderer<Module>() {


		@Override
		public String render(Module  module) {
			String moduleName="";
			if( module!=null){
				moduleName=module.getName();
			}
			return moduleName;
			
		}

		@Override
		public void render(Module object, Appendable appendable)
				throws IOException {
			String moduleName=render(object);
			appendable.append(moduleName);
			System.out.println(moduleName);
			
		}
	});
	
	moduleListBox.setVisible(true);
	moduleListBox.ensureDebugId("module");
	moduleListBox.setWidth("10em");
	productListBoxPanel.add(new HTML("Module"));
	productListBoxPanel.add(moduleListBox);
	
	final Button viewWorkflowDetails=new Button("View Module Details");
	viewWorkflowDetails.setEnabled(false);
	viewWorkflowDetails.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			ModuleDetailsDialog moduleDetailsDialog=new ModuleDetailsDialog(moduleListBox.getValue());
			moduleDetailsDialog.show();
			moduleDetailsDialog.center();
			
		}
	});
	productListBoxPanel.add(viewWorkflowDetails);
	hpanel.add(productListBoxPanel);
	
	final ValueListBox<SubModule> subModuleListBox=new ValueListBox<SubModule>(new Renderer<SubModule>() {


		@Override
		public String render(SubModule  subModule) {
			String subModuleName="";
			if( subModule!=null){
				subModuleName=subModule.getName();
			}
			return subModuleName;
			
		}

		@Override
		public void render(SubModule object, Appendable appendable)
				throws IOException {
			String subModuleName=render(object);
			appendable.append(subModuleName);
			System.out.println(subModuleName);
			
		}
	});
	
	subModuleListBox.setVisible(true);
	subModuleListBox.ensureDebugId("productSubModuleListBox");
	subModuleListBox.setWidth("15em");

	VerticalPanel subModuleListBoxPanel=new VerticalPanel();
	subModuleListBoxPanel.setSpacing(10);
	subModuleListBoxPanel.add(new HTML("Sub Modules"));
	subModuleListBoxPanel.add(subModuleListBox);
	subModuleListBoxPanel.add(new HTML("Select ONTAP type"));
	final ListBox ontapVersion=new ListBox(false);
	String [] ontpVersionType={"BOTH","7DOT","CDOT"};
	for(int i=0;i<ontpVersionType.length;i++){
		ontapVersion.addItem(ontpVersionType[i]);

	}
	ontapVersion.setSelectedIndex(0);
	ontapVersion.ensureDebugId("ontapVersion");
	subModuleListBoxPanel.add(ontapVersion);
	hpanel.add(subModuleListBoxPanel);
	subModuleListBox.ensureDebugId("productSubModuleListBox");
	productListBox.addValueChangeHandler(new ValueChangeHandler<Product>() {

		@Override
		public void onValueChange(ValueChangeEvent<Product> event) {
			populateAllVersion(productVersionListBox, event.getValue().getId());
			productVersionListBox.ensureDebugId("productVersionListBox");
		}
	});

	
	productVersionListBox.addValueChangeHandler(new ValueChangeHandler<Version>() {
		
		@Override
		public void onValueChange(ValueChangeEvent<Version> event) {
			populateAllModule(moduleListBox, event.getValue().getId());
			moduleListBox.ensureDebugId("module");
			
		}
	});
	
	moduleListBox.addValueChangeHandler(new ValueChangeHandler<Module>() {
		
		@Override
		public void onValueChange(ValueChangeEvent<Module> event) {
			populateAllSubModule(subModuleListBox, event.getValue().getId());
			subModuleListBox.ensureDebugId("productSubModuleListBox");
			viewWorkflowDetails.setEnabled(true);
		}
	});
	
	HorizontalPanel problemAndBurtPanel=new HorizontalPanel();
	problemAndBurtPanel.setWidth("100em");
	
	VerticalPanel problemViewPanel=new VerticalPanel();
	problemViewPanel.setSpacing(10);
	
	problemViewPanel.add(new HTML("Problem Statement"));
	
	final TextArea problemStatement=new TextArea();
	problemStatement.ensureDebugId("problemStatement");
	problemStatement.setVisibleLines(2);
	problemStatement.setWidth("50em");
	
	problemViewPanel.add(createProblemSubComponents(problemStatement, true));
	
	problemViewPanel.add(new HTML("Problem Symptom or Log message"));
	
	final TextArea problemSymptom=new TextArea();
	problemSymptom.ensureDebugId("problemSymptom");
	problemSymptom.setVisibleLines(2);
	problemSymptom.setWidth("50em");

	
	problemViewPanel.add(createProblemSubComponents(problemSymptom, false));
	
	problemViewPanel.add(new HTML("Troubleshooting Steps"));
	
	final TextArea solution=new TextArea();
	solution.ensureDebugId("Solution");
	solution.setVisibleLines(5);
	solution.setWidth("50em");

	
	problemViewPanel.add(createProblemSubComponents(solution, false));
	
	problemViewPanel.add(new HTML("Required Data to Troubleshoot"));
	
	final TextArea dataCollection=new TextArea();
	dataCollection.ensureDebugId("dataCollection");
	dataCollection.setVisibleLines(4);
	dataCollection.setWidth("50em");
	problemViewPanel.add(createProblemSubComponents(dataCollection, false));

	problemViewPanel.add(new HTML("Additional Note"));
	
	final TextArea additionalNote=new TextArea();
	additionalNote.ensureDebugId("additionalNote");
	additionalNote.setVisibleLines(4);
	additionalNote.setWidth("50em");
	additionalNote.setEnabled(false);
	
	problemViewPanel.add(createProblemSubComponents(additionalNote, false));
	final Button save=new Button("Save");
	problemViewPanel.add(save);
	problemViewPanel.setCellHorizontalAlignment(save, VerticalPanel.ALIGN_CENTER);
	
	problemAndBurtPanel.add(problemViewPanel);

	VerticalPanel burtAndCaseDetailsPanel=new VerticalPanel();
	burtAndCaseDetailsPanel.setSpacing(10);
	burtAndCaseDetailsPanel.setWidth("100em");
	
	burtAndCaseDetailsPanel.add(new HTML("Associated Burts"));
	final TextArea burtList=new TextArea();
	burtList.ensureDebugId("associatedBurts");
	burtList.setWidth("11em");
	burtAndCaseDetailsPanel.add(burtList);
	
	burtAndCaseDetailsPanel.add(new HTML("Associated cases"));
	final TextArea casesList=new TextArea();
	casesList.ensureDebugId("associatedCases");
	casesList.setWidth("11em");
	burtAndCaseDetailsPanel.add(casesList);
	
	final CheckBox performanceProblem=new CheckBox("Performance related");
	performanceProblem.ensureDebugId("performance_related_checkbox");
	burtAndCaseDetailsPanel.add(performanceProblem);

	
	
	final VerticalPanel performanceDataPanel=new VerticalPanel();
	performanceDataPanel.setSpacing(4);
	performanceDataPanel.setVisible(false);
	
	performanceDataPanel.add(new HTML("No of Volumes"));
	final TextBox noOfVolumeTextBox=new TextBox();
	noOfVolumeTextBox.ensureDebugId("noOfVolumeTextBox");
	noOfVolumeTextBox.setWidth("11em");
	performanceDataPanel.add(noOfVolumeTextBox);
	
	performanceDataPanel.add(new HTML("No of Filers"));
	final TextBox noOfFilersTextBox=new TextBox();
	noOfFilersTextBox.ensureDebugId("noOfFilersTextBox");	
	noOfFilersTextBox.setWidth("11em");
	performanceDataPanel.add(noOfFilersTextBox);
	
	performanceDataPanel.add(new HTML("No of Data Protection Relationships"));
	final TextBox noOfDataProtectionRelationshipsTextBox=new TextBox();
	noOfDataProtectionRelationshipsTextBox.ensureDebugId("noOfDataProtectionRelationshipTextBox");	
	noOfDataProtectionRelationshipsTextBox.setWidth("11em");
	performanceDataPanel.add(noOfDataProtectionRelationshipsTextBox);
	
	burtAndCaseDetailsPanel.add(performanceDataPanel);
	

	save.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			ProblemDTO problemDTO=new ProblemDTO();
			problemDTO.setTitle(problemStatement.getText());
			problemDTO.setLog_Message(problemSymptom.getText());
			problemDTO.setTroubleshooting_Steps(solution.getText());
			problemDTO.setProductId(Long.valueOf(productListBox.getValue().getId()).longValue());
			problemDTO.setModule_ID(Long.valueOf(moduleListBox.getValue().getId()).longValue());
			problemDTO.setSub_Module_ID(Long.valueOf(subModuleListBox.getValue().getId()).longValue());
			problemDTO.setAdditional_Data_Collection(dataCollection.getText());
			problemDTO.setBurt_Numbers(burtList.getText());
			problemDTO.setCase_Numbers(casesList.getText());
			problemDTO.setDOT_Details(ontapVersion.getItemText(ontapVersion.getSelectedIndex()));
			problemDTO.setSubmitter("akhilesh");
			Util.getGuiService().addProblem(problemDTO, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Void result) {

					problemStatement.setText("");
					problemSymptom.setText("");
					solution.setText("");
					dataCollection.setText("");
					burtList.setText("");
					casesList.setText("");
					problemStatement.setText("");
					
					
				}
			});
			
			
		}
	});
	
	performanceProblem.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		
		@Override
		public void onValueChange(ValueChangeEvent<Boolean> event) {
			if(event.getValue()){
				performanceDataPanel.setVisible(true);
			}else{
				performanceDataPanel.setVisible(false);
			}
		}
	});
	
	problemAndBurtPanel.add(burtAndCaseDetailsPanel);
	ScrollPanel searchTabPanelScroll=new ScrollPanel();
	
	SplitLayoutPanel searchTabPanel=new SplitLayoutPanel();
	final SimpleLayoutPanel searchViewTabPanelLayout=new SimpleLayoutPanel();
	searchViewTabPanelLayout.setVisible(true);
	final VerticalPanel searchPanel=new VerticalPanel();
	searchPanel.setSpacing(10);
	searchPanel.setWidth("100em");
	final TextArea searchText=new TextArea();
	searchText.setVisibleLines(10);
	searchText.setWidth("50em");

	
	Button search=new Button("Search");
	
	
	searchPanel.add(searchText);
	searchPanel.add(search);


	searchPanel.setCellHorizontalAlignment(search, HasHorizontalAlignment.ALIGN_CENTER);
	searchPanel.setCellHorizontalAlignment(searchText,HasHorizontalAlignment.ALIGN_CENTER);
	
	final TabLayoutPanel problemTabPanel = new TabLayoutPanel(2.5,Unit.EM);	
	problemTabPanel.setAnimationDuration(10);
	problemTabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);
	problemTabPanel.add(problemAndBurtPanel,"Add");
	final SearchView searchView=new SearchView();
	searchTabPanelScroll.add(searchPanel);
	searchTabPanel.addNorth(searchTabPanelScroll,250);
	searchTabPanel.add(searchView.getProblemListTable());
//	searchViewTabPanelLayout.add(searchView.getProblemListTable());
//	searchScrollPanel.add(searchPanel);
	problemTabPanel.add(searchTabPanel,"Search");
	problemTabPanel.selectTab(0);
	mainPanel.add(hpanel);
	
	
	search.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
			if(productListBox.getValue()!=null && moduleListBox.getValue()!=null && subModuleListBox.getValue()!=null){
				searchView.getAllProductSubModuleProblemsByLogs(searchText.getText(), productListBox.getValue().getId(), moduleListBox.getValue().getId(), subModuleListBox.getValue().getId());
			}
			else if(productListBox.getValue()!=null && moduleListBox.getValue()!=null){
				searchView.getAllProductModuleProblemsByLogs(searchText.getText(), productListBox.getValue().getId(), moduleListBox.getValue().getId())	;			
			}			
			else if(productListBox.getValue()!=null){
				searchView.getAllProductProblemsByLogs(searchText.getText(), productListBox.getValue().getId());
			}else{
				searchView.getAllProblemsByLogs(searchText.getText());
			}
		}
	});
	
//	problemDetails.addClickHandler(new ClickHandler() {
//		
//		@Override
//		public void onClick(ClickEvent event) {
//			if(searchView.getProblemListTable().=null){
//				
//			}
//		}
//	});
	
	SplitLayoutPanel container=new SplitLayoutPanel(0);
	container.addNorth(titleHtml,80);
	container.addWest(mainPanel,500);
	container.add(problemTabPanel);
	return container;
	}
	

	
private HorizontalPanel createProblemSubComponents(final TextArea subComponent,boolean addSelection){
		
		HorizontalPanel subComponentPanel=new HorizontalPanel();
		subComponentPanel.setSpacing(4);
		subComponentPanel.add(subComponent);
		
		return subComponentPanel;
	}
	
	private void populateAllProducts(final ValueListBox<Product> valueListBox){
		Util.getGuiService().getProducts(new AsyncCallback<List<Product>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Product> result) {
				valueListBox.setAcceptableValues(result);
			}
		});
	}
	
	private void populateAllVersion(final ValueListBox<Version> valueListBox,String productId){
		Util.getGuiService().getProductVersions(productId,new AsyncCallback<List<Version>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Version> result) {
				valueListBox.setValue(null);
				valueListBox.setAcceptableValues(result);
			}
		});
	}
	private void populateAllModule(final ValueListBox<Module> valueListBox,String versionId){
		Util.getGuiService().getProductModules(versionId,new AsyncCallback<List<Module>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Module> result) {
				valueListBox.setValue(null);
				valueListBox.setAcceptableValues(result);
			}
		});
	}
	
	private void populateAllSubModule(final ValueListBox<SubModule> valueListBox,String moduleId){
		Util.getGuiService().getProductSubModules(moduleId,new AsyncCallback<List<SubModule>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<SubModule> result) {
				valueListBox.setValue(null);
				valueListBox.setAcceptableValues(result);
			}
		});
	}
	

	
}
