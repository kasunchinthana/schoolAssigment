package com.nk.school.elearning.mapping.response;


import lombok.Data;

@Data
public class RequestWrapper<T> {

	T payload;
}
