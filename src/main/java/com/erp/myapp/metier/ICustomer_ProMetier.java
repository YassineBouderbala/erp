package com.erp.myapp.metier;

import com.erp.myapp.entity.Customer_Pro;

public interface ICustomer_ProMetier {
	public Customer_Pro getCustomerByGuid(String guid);
}
