package com.bsk.dican.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BasePojo implements Serializable{

	private static final long serialVersionUID = 5864029644097180288L;

	public Date created;
	
	public Date updated;
	
}
