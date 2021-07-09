package com.higradius.model;

public class EditDataModal 
{
	private String invoiceNumber;
	private String invoiceAmount;
	private String note;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "EditDataModal [invoiceNumber=" + invoiceNumber + ", invoiceAmount=" + invoiceAmount + ", note=" + note
				+ "]";
	}
	
	
	
}
