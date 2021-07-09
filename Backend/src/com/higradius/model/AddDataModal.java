package com.higradius.model;

import java.sql.Date;

public class AddDataModal 
{
	private String customerName;
	private String customerNumber;
	private String invoiceNumber;
	private String invoiceAmount;
	private String dueinDate;
	private String note;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getDueinDate() {
		return dueinDate;
	}
	public void setDueinDate(String dueinDate) {
		this.dueinDate = dueinDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "AddDataModal [customerName=" + customerName + ", customerNumber=" + customerNumber + ", invoiceNumber="
				+ invoiceNumber + ", invoiceAmount=" + invoiceAmount + ", dueinDate=" + dueinDate + ", note=" + note
				+ "]";
	}
	
	
	
	
	
}
