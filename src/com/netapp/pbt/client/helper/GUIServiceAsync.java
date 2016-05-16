package com.netapp.pbt.client.helper;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.netapp.gwt.module.models.Module;
import com.netapp.gwt.module.models.ProblemDTO;
import com.netapp.gwt.module.models.ProblemViewModel;
import com.netapp.gwt.module.models.Product;
import com.netapp.gwt.module.models.SubModule;
import com.netapp.gwt.module.models.Version;

public interface GUIServiceAsync {
	public void getProducts(AsyncCallback<List<Product>> callback);
	public void getProductVersions(String productId,AsyncCallback<List<Version>> callback);
	public void getProductModules(String versionId,AsyncCallback<List<Module>> callback);
	public void getProductSubModules(String moduleId,AsyncCallback<List<SubModule>> callback);
	public void addProblem(ProblemDTO problem,AsyncCallback<Void> callback);
	public void getAllProblemsByLogs(String logs,AsyncCallback<List<ProblemViewModel>> callback);
	public void getAllProductProblemsByLogs(String logs,String productId,AsyncCallback<List<ProblemViewModel>> callback);
	public void getAllProductModuleProblemsByLogs(String logs,String productId,String moduleId,AsyncCallback<List<ProblemViewModel>> callback);
	public void getAllProductSubModuleProblemsByLogs(String logs,String productId,String moduleId,String subModuleId,AsyncCallback<List<ProblemViewModel>> callback);


}
