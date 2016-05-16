package com.netapp.pbt.client.view;

import java.util.List;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SingleSelectionModel;
import com.netapp.gwt.module.models.ProblemViewModel;
import com.netapp.pbt.client.helper.Util;


public class SearchView {
	
	private DataGrid<ProblemViewModel> problemListTable=new DataGrid<ProblemViewModel>();
	
	public SearchView() {
		
		init();
	}

	private void init(){
		problemListTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		ClickableTextCell customTextCell=new ClickableTextCell();
		Column<ProblemViewModel, String> problemTitelColumn=new Column<ProblemViewModel, String>(customTextCell) {

			@Override
			public String getValue(ProblemViewModel problem) {
				// TODO Auto-generated method stub
				return problem.getTitle();
			}
		};
		problemTitelColumn.setFieldUpdater(new FieldUpdater<ProblemViewModel, String>() {
			
			@Override
			public void update(int index, ProblemViewModel object, String value) {
			ProblemDetailsDialog problemDetails=new ProblemDetailsDialog(object);
			problemDetails.show();
			problemDetails.center();
			problemListTable.redraw();
				
			}
		});
//		TextColumn<Problem> titleColumn=new TextColumn<Problem>() {
//
//			@Override
//			public String getValue(Problem problem) {
//				
//				return problem.getTitle();
//			};
//			
////@Override
////public void onBrowserEvent(Context context, Element elem,
////		Problem object, NativeEvent event) {
////	// TODO Auto-generated method stub
////	super.onBrowserEvent(context, elem, object, event);
////	if("click".equals(event.getType())){
////		ProblemDetailsDialog problemDetails=new ProblemDetailsDialog(object);
////		problemDetails.setVisible(true);
////	}
////}
//
//		};
		problemListTable.addColumn(problemTitelColumn,"Title");
		
		TextColumn<ProblemViewModel> symptomColumn=new TextColumn<ProblemViewModel>() {
			
			@Override
			public String getValue(ProblemViewModel problem) {
				// TODO Auto-generated method stub
				return problem.getLog_Message();
			}
		};
		TextColumn<ProblemViewModel> productNameColumn=new TextColumn<ProblemViewModel>() {
			
			@Override
			public String getValue(ProblemViewModel problem) {
				// TODO Auto-generated method stub
				return problem.getProductName();
			}
		};
		problemListTable.addColumn(productNameColumn,"Product");
		problemListTable.addColumn(symptomColumn,"Error logs");
		
		
		final SingleSelectionModel<ProblemViewModel> selectionModel=new SingleSelectionModel<ProblemViewModel>();
		problemListTable.setSelectionModel(selectionModel);

		
		problemListTable.setRowCount(5,false);
		problemListTable.setVisible(true);
		problemListTable.setWidth("100%");
		
//		titleColumn.getCell().getConsumedEvents().add("click");
		
		
	}

	public DataGrid<ProblemViewModel> getProblemListTable() {
		return problemListTable;
	}

	public void setProblemListTable(DataGrid<ProblemViewModel> problemListTable) {
		
		this.problemListTable = problemListTable;
	}
	
	public void getAllProblemsByLogs(String logs){

		Util.getGuiService().getAllProblemsByLogs(logs, new AsyncCallback<List<ProblemViewModel>>() {
			

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<ProblemViewModel> result) {
				problemListTable.setRowData(result);	
//				problemListTable.redraw();
			}
		});
		
	}
	
	public void getAllProductProblemsByLogs(String logs,String productId){
		
		Util.getGuiService().getAllProductProblemsByLogs(logs, productId, new AsyncCallback<List<ProblemViewModel>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<ProblemViewModel> result) {
				// TODO Auto-generated method stub
				problemListTable.setRowData(result);
				
			}
		});
		
	}
	
	public void getAllProductModuleProblemsByLogs(String logs,String productId,String moduleId){
		Util.getGuiService().getAllProductModuleProblemsByLogs(logs, productId, moduleId, new AsyncCallback<List<ProblemViewModel>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<ProblemViewModel> result) {
				problemListTable.setRowData(result);
				
			}
		});
	}
	
	public void getAllProductSubModuleProblemsByLogs(String logs,String productId,String moduleId,String subModuleId){
		Util.getGuiService().getAllProductSubModuleProblemsByLogs(logs, productId, moduleId, subModuleId, new AsyncCallback<List<ProblemViewModel>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<ProblemViewModel> result) {
				problemListTable.setRowData(result);
				
			}
		});

	}
	
	
}
