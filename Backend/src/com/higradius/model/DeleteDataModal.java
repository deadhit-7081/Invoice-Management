package com.higradius.model;

import java.util.Arrays;

public class DeleteDataModal 
{
	private String[] invoiceNumber;

	public String[] getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String[] invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "DeleteDataModal [invoiceNumber=" + Arrays.toString(invoiceNumber) + "]";
	}
}
