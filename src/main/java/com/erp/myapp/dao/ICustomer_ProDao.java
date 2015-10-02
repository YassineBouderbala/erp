package com.erp.myapp.dao;

import com.erp.myapp.entity.Customer_Pro;

public interface ICustomer_ProDao {
	public Customer_Pro getCustomerByGuid(String guid);
}
