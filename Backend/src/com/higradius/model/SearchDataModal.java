package com.higradius.model;

public class SearchDataModal 
{
	private String invoiceNumber;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "SearchDataModal [invoiceNumber=" + invoiceNumber + "]";
	}
}
