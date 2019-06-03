package com.tgi.sd.domain;

import java.util.HashMap;
import java.util.Map;

public class RequestVO {
	private Map<String, Object> paramObjectsMap = new HashMap<String, Object>();
	
	public final Map<String, Object> getParamObjectsMap() {
		return paramObjectsMap;
	}

	public final void setParamObjectsMap(
			final Map<String, Object> paramObjectsMap) {
		this.paramObjectsMap = paramObjectsMap;
	}	

	public final void addObject(final Object obj, final String key) {
		paramObjectsMap.put(key, obj);
	}

	public final Object getObject(final String key) {
		return this.paramObjectsMap.get(key);
	}

	@Override
	public String toString() {
		return "RequestVO [paramObjectsMap=" + paramObjectsMap + "]";
	}
	
	
}
