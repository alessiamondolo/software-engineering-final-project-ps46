package it.polimi.ingsw.ps46.server.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ResourceSet is a container of objects of the type Resource.
 * To store the resource objects it uses a Map, that has the name of the type of resource as key
 * and the resource itself as value.
 * 
 * @author Alessia Mondolo
 */
public class ResourceSet implements Serializable {
	
	private static final long serialVersionUID = 786707586186550634L;
	
	private LinkedHashMap<String, Resource> resourcesMap;

	
	/**
	 * Creates a new ResourceSet object by reading a list of Resource objects and filling the hashmap
	 * with the ID of the resource as Key and the resource itself as value.
	 * 
	 * @param List<Resource>
	 */
	public ResourceSet(List<Resource> resourcesList) {
		
		resourcesMap = new LinkedHashMap<String, Resource>();
		
		for(Resource resource : resourcesList) {
			resourcesMap.put(resource.getId(), resource);
		}
	}
	
	/**
	 * This constructor is used to create a deep copy of a resourceSet passed parameter called resourceSetToBeCloned.
	 * 
	 * @param resourceSetToBeCloned
	 */
	public ResourceSet (ResourceSet resourceSetToBeCloned){
		
		resourcesMap = new LinkedHashMap<String, Resource>();
		ResourcesFactory factory = new ResourcesFactory();
		
		for (String keyString : resourceSetToBeCloned.getResourcesMap().keySet()) {
			
			String tempId = resourceSetToBeCloned.getResourcesMap().get(keyString).getId();
			int tempValue = resourceSetToBeCloned.getResourcesMap().get(keyString).getQuantity();
			Resource tempResource = factory.getResource(tempId, tempValue);
			
			resourcesMap.put(tempId, tempResource);
		}
	}
	
	/**
	 * 
	 * 
	 * @param beforeResourceSet
	 * @param afterResourceSet
	 */
	public ResourceSet (ResourceSet beforeResourceSet, ResourceSet afterResourceSet){
		
		resourcesMap = new LinkedHashMap<String, Resource>();
		ResourcesFactory factory = new ResourcesFactory();
		
		for (String keyStringBefore : beforeResourceSet.getResourcesMap().keySet()) {
			for (String keyStringAfter : afterResourceSet.getResourcesMap().keySet()) {
				if(keyStringAfter == keyStringBefore) {
					Resource differecedResource = factory.getResourceByDifference(beforeResourceSet.getResourcesMap().get(keyStringBefore), afterResourceSet.getResourcesMap().get(keyStringAfter));					
					resourcesMap.put(keyStringBefore, differecedResource);
				}
			}
		}
	}
		
	
	
	public LinkedHashMap<String, Resource> getResourcesMap() {
		
		return resourcesMap;
	}

	/**
	 * Increases the value of the resource of the ResourceSet that has the same ID as the resource received as parameter.
	 * 
	 * @param Resource
	 */
	public void add(Resource moreResources) {//da aggiungere check per risorsa non esistente nella mappa
		resourcesMap.get(moreResources.getId()).add(moreResources);
	}
	
	/**
	 * Decreases the value of the resource of the ResourceSet that has the same ID as the resource received as parameter.
	 * 
	 * @param Resource
	 */
	public boolean sub (Resource lessResources) {//da aggiungere check per risorsa non esistente nella mappa o lessResource > myResource
		return resourcesMap.get(lessResources.getId()).sub(lessResources);
	}
	
	/**
	 * Returns true if the the value of the resource of the ResourceSet that has the same ID as the resource received as parameter 
	 * is greater or equal than the resource received as parameter.
	 * 
	 * @param Resource
	 */
	public boolean greaterOrEqual(Resource resource) {
		return resourcesMap.get(resource.getId()).greaterOrEqual(resource);
	}
	
	
	/**
	 * Increases the value of the resources of the ResourceSet that has the same ID as the resources in the resourceSet 
	 * received as parameter.
	 * 
	 * @param Resource
	 */
	public void add(ResourceSet moreResources) {//da aggiungere check per risorsa non esistente nella mappa
		for(String key : moreResources.getResourcesMap().keySet())
			//gets the resource of this resourceSet with the same key of the resource received by parameter
			//and increases it by the resource received by parameter
			resourcesMap.get(key).add(moreResources.getResourcesMap().get(key));
	}
	
	/**
	 * Decreases the value of the resources of the ResourceSet that has the same ID as the resources in the ResourceSet 
	 * received as parameter.
	 * This method checks if the sub operation is done with a positive (or negative) result, and return this result as a boolean value. 
	 *
	 * @param lessResources
	 * @result subResult;
	 */
	public boolean sub(ResourceSet lessResources) {
		if (greaterOrEqual( lessResources)){
			for(String key : lessResources.getResourcesMap().keySet()){
					//gets the resource of this resourceSet with the same key of the resource received by parameter
					//and decreases it by the resource received by parameter			
					resourcesMap.get(key).sub(lessResources.getResourcesMap().get(key));
				}
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * Returns true if the the value of all the resources of the ResourceSet that has the same ID as the resources in the ResourceSet 
	 * received as parameter is greater or equal than all the resources of the resourceSet received as parameter.
	 * 
	 * @param Resource
	 * @return boolean
	 */
	public boolean greaterOrEqual(ResourceSet resource) {
		for(String key : resource.getResourcesMap().keySet()) {
			//gets the resource of this resourceSet with the same key of the resource received by parameter
			//and increases it by the resource received by parameter
			if(resourcesMap.get(key).greaterOrEqual(resource.getResourcesMap().get(key)) == false)
				return false;
		}
		return true;
	}
	
	
	
	public ArrayList<Resource> toArray() {
		ArrayList<Resource> array = new ArrayList<Resource>();
		for(String key : getResourcesMap().keySet())
			array.add(resourcesMap.get(key));
		return array;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(String key : getResourcesMap().keySet()) {
			if(stringBuilder.length()>0)
				stringBuilder.append(", ");
			stringBuilder.append(resourcesMap.get(key).toString());
		}
		return stringBuilder.toString();
	}
	
}
