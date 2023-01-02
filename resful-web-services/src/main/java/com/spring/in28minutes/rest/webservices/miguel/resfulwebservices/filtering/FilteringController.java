package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        SomeBean someBean = new SomeBean("value1","value2","value3");
        // mapping jackson value will allow us to then filter the specific bean that we pass in
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        // the class below will let us specify what are the fields that we want to make  "a filter property"
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        // then we add it to the filterProvider with an Id in this case "SomeBeanFilter"
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        // here we set the filter for mappingJacksonValue
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){
        List<SomeBean> list = Arrays.asList(
                new SomeBean("value1","value2","value3"),
                new SomeBean("value4","value5","value6"),
                new SomeBean("value7","value8","value9")
                );
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
