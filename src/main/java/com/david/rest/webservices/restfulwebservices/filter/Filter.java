package com.david.rest.webservices.restfulwebservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Filter {
	
	@GetMapping("/demo-data")
	public MappingJacksonValue demoData() {
		
		List<DemoData> demoData = Arrays.asList(new DemoData("data1", "data2", "data3"));
		
		MappingJacksonValue filterData =  getFilterDemoData(demoData, "data1", "data2");
		return filterData;
	}
	
	@GetMapping("/demo-data-list")
	public MappingJacksonValue demoDataList() {
		List<DemoData> demoData = Arrays.asList(new DemoData("data1", "data2", "data3"), new DemoData("data12", "data22", "data32"));
		MappingJacksonValue filterData =  getFilterDemoData(demoData, "data2", "data3");
		return filterData;
		
	}
	
	private MappingJacksonValue getFilterDemoData(List<DemoData> data, String ...dataFilter) {

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(dataFilter);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("DataFilter",filter);
		
		MappingJacksonValue mapping =  new MappingJacksonValue(data);
		
		mapping.setFilters(filters);

		return mapping;
	}

}
