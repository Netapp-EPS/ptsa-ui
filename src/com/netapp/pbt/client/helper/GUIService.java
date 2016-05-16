package com.netapp.pbt.client.helper;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.netapp.gwt.module.models.Module;
import com.netapp.gwt.module.models.ProblemDTO;
import com.netapp.gwt.module.models.ProblemViewModel;
import com.netapp.gwt.module.models.Product;
import com.netapp.gwt.module.models.SubModule;
import com.netapp.gwt.module.models.Version;
@RemoteServiceRelativePath("GUIService")
public interface GUIService extends RemoteService {
	public List<Product> getProducts();
	public List<Version> getProductVersions(String productId);
	public List<Module> getProductModules(String versionId);
	public List<SubModule> getProductSubModules(String moduleId);
	public void addProblem(ProblemDTO problem);
	public List<ProblemViewModel> getAllProblemsByLogs(String logs);
	public List<ProblemViewModel> getAllProductProblemsByLogs(String logs,String productId);
	public List<ProblemViewModel> getAllProductModuleProblemsByLogs(String logs,String productId,String moduleId);
	public List<ProblemViewModel> getAllProductSubModuleProblemsByLogs(String logs,String productId,String moduleId,String subModuleId);

	
}
 