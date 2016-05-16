package com.netapp.pbt.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.repackaged.org.codehaus.jackson.JsonParseException;
import com.google.appengine.repackaged.org.codehaus.jackson.map.JsonMappingException;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.netapp.gwt.module.models.Module;
import com.netapp.gwt.module.models.ProblemDTO;
import com.netapp.gwt.module.models.ProblemViewModel;
import com.netapp.gwt.module.models.Product;
import com.netapp.gwt.module.models.SubModule;
import com.netapp.gwt.module.models.Version;
import com.netapp.pbt.client.RESTfulServiceException;
import com.netapp.pbt.client.helper.GUIService;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class GUIServiceImpl extends RemoteServiceServlet implements GUIService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2605624408144636798L;

	@Override
	public List<Version> getProductVersions(String productId) {
		List<Version> versionList=new ArrayList<Version>();
		if(productId!=null){
		Map<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("productId", productId);

		try {
			String response=invokeGetRESTfulWebService(GUIServiceUtil.ALL_PRODUCT_VERSIONS_URL, null,parameter);
	
			ObjectMapper mapper=new ObjectMapper();
			try {
				versionList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, Version.class));
				System.out.println(versionList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return versionList;
	
	}

	@Override
	public List<Module> getProductModules(String versionId) {
		Map<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("versionId", versionId);
		List<Module> moduleList=new ArrayList<Module>();
		try {
			String response=invokeGetRESTfulWebService(GUIServiceUtil.ALL_PRODUCT_MODULES_URL, null,parameter);	
			ObjectMapper mapper=new ObjectMapper();
			try {
				moduleList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, Module.class));
				System.out.println(moduleList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moduleList;
	
	}

	@Override
	public List<SubModule> getProductSubModules(String moduleId) {
		Map<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("moduleId", moduleId);
		List<SubModule> subModuleList=new ArrayList<SubModule>();
		try {
			String response=invokeGetRESTfulWebService(GUIServiceUtil.ALL_PRODUCT_SUBMODULES_URL,null,parameter);	
			ObjectMapper mapper=new ObjectMapper();
			try {
				subModuleList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, SubModule.class));
				System.out.println(subModuleList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subModuleList;
	
	}

	public String invokeGetRESTfulWebService(String uri, String contentType,Map<String,Object> parameters)
			throws RESTfulServiceException {
		try{
			URL u=new URL(uri);
			HttpURLConnection uc=(HttpURLConnection) u.openConnection();
			if(contentType!=null){
			uc.setRequestProperty("Accept","application/json;charset=UTF-8");
			uc.setRequestProperty("Content-Type", contentType);
			}
//			uc.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//			uc.setRequestProperty("User-Agent", "Mozilla/5.0");
			uc.setDoOutput(true);
			uc.setRequestMethod("POST");

//			uc.setDoInput(true);

			if(parameters!=null && !parameters.isEmpty()){
				StringBuffer requestParams=new StringBuffer();
				Set<String> entrySet=parameters.keySet();
				for(String key:entrySet){

					if(parameters.get(key) instanceof String){
						requestParams.append(URLEncoder.encode(key,"UTF-8"));
						requestParams.append("=");
						requestParams.append(URLEncoder.encode(parameters.get(key).toString(),"UTF-8"));
						requestParams.append("&");
					}else{
						ObjectMapper objectmapper=new ObjectMapper();
					requestParams.append(objectmapper.writeValueAsString(parameters.get(key)));
					}
				}
				OutputStream writer=uc.getOutputStream();
				writer.write(requestParams.toString().getBytes("UTF-8"));
				writer.flush();
				writer.close();
			}

			int status=uc.getResponseCode();
			if(status!=200){
				throw new RESTfulServiceException("Invalid HTTP response status code"+status+
				" from web server");
			}
				InputStream in =uc.getInputStream();
				BufferedReader d=new BufferedReader(new InputStreamReader(in));
				String buffer =d.readLine();
				return buffer;
			}
			catch(MalformedURIException e){
				throw new RESTfulServiceException(e.getMessage(),e);
			}
			catch(IOException e){
				throw new RESTfulServiceException(e.getMessage(),e);
			}
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> productList=new ArrayList<Product>();
		try {
			String response=invokeGetRESTfulWebService(GUIServiceUtil.ALL_PRODUCTS_URL,null,null);
	
			ObjectMapper mapper=new ObjectMapper();
			try {
				productList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
				System.out.println(productList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public void addProblem(ProblemDTO problem) {
		// TODO Auto-generated method stub
		try {
			Map<String,Object> parameter=new HashMap<String,Object>();
			parameter.put("problem",problem);
			invokeGetRESTfulWebService(GUIServiceUtil.ADD_PROBLEM_URL, "application/json",parameter);
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ProblemViewModel> getAllProblemsByLogs(String logs) {
		List<ProblemViewModel> problemList=new ArrayList<ProblemViewModel>();
		try {
			Map<String,Object> parameter=new HashMap<String,Object>();
			parameter.put("logMessage",logs);
			String response=invokeGetRESTfulWebService(GUIServiceUtil.ALL_PROBLEM_BY_LOGS,null,parameter);
	
			ObjectMapper mapper=new ObjectMapper();
			try {
				problemList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, ProblemViewModel.class));
				System.out.println(problemList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemList;
	}

	@Override
	public List<ProblemViewModel> getAllProductProblemsByLogs(String logs,
			String productId) {
		List<ProblemViewModel> problemList=new ArrayList<ProblemViewModel>();
		try {
			Map<String,Object> parameter=new HashMap<String,Object>();
			parameter.put("logMessage",logs);
			parameter.put("productId", productId);
			String response=invokeGetRESTfulWebService(GUIServiceUtil.All_PRODUCT_PROBLEM_BY_LOGS,null,parameter);
	
			ObjectMapper mapper=new ObjectMapper();
			try {
				problemList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, ProblemViewModel.class));
				System.out.println(problemList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemList;
	}

	@Override
	public List<ProblemViewModel> getAllProductModuleProblemsByLogs(String logs,
			String productId, String moduleId) {
		List<ProblemViewModel> problemList=new ArrayList<ProblemViewModel>();
		try {
			Map<String,Object> parameter=new HashMap<String,Object>();
			parameter.put("logMessage",logs);
			parameter.put("productId", productId);
			parameter.put("moduleId", moduleId);
			String response=invokeGetRESTfulWebService(GUIServiceUtil.All_PRODUCT_MODULE_PROBLEM_BY_LOGS,null,parameter);
	
			ObjectMapper mapper=new ObjectMapper();
			try {
				problemList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, ProblemViewModel.class));
				System.out.println(problemList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemList;
	}

	@Override
	public List<ProblemViewModel> getAllProductSubModuleProblemsByLogs(String logs,
			String productId, String moduleId, String subModuleId) {
		List<ProblemViewModel> problemList=new ArrayList<ProblemViewModel>();
		try {
			Map<String,Object> parameter=new HashMap<String,Object>();
			parameter.put("logMessage",logs);
			parameter.put("productId", productId);
			parameter.put("moduleId", moduleId);
			parameter.put("subModuleId", subModuleId);
			String response=invokeGetRESTfulWebService(GUIServiceUtil.All_PRODUCT_SUB_MODULE_PROBLEM_BY_LOGS,null,parameter);
	
			ObjectMapper mapper=new ObjectMapper();
			try {
				problemList=mapper.readValue(response,mapper.getTypeFactory().constructCollectionType(List.class, ProblemViewModel.class));
				System.out.println(problemList);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (RESTfulServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return problemList;
	}
	

}
