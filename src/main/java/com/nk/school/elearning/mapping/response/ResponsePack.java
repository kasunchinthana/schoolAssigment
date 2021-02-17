package com.nk.school.elearning.mapping.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponsePack<T> {
	
	private Status status;
	//Data<T> data;
	//private List<T> data;
	
	private T data;

}
