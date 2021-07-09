package com.higradius.model;

import java.sql.Date;

public class Invoice 
{
	private String business_code;
	private String cust_name;
	private String name_customer;
	private Date clear_date;
	private double business_year;
	private double doc_id;
	private Date posting_date;
	private Date document_create_date;
	private Date document_create_date_1;
	private Date due_in_date;
	private String invoice_currency;
	private String document_type;
	private double posting_id;
	private double total_open_amount;
	private Date baseline_create_date;
	private String cust_payment_terms;
	private double invoice_id;
	private int isOpen;
	private Date predicted_date;
	private String Aging_Bucket;
	private String notes;
	
	
	public String getBusiness_code() {
		return business_code;
	}
	public void setBusiness_code(String business_code) {
		this.business_code = business_code;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	public Date getClear_date() {
		return clear_date;
	}
	public void setClear_date(Date clear_date) {
		this.clear_date = clear_date;
	}
	public double getBusiness_year() {
		return business_year;
	}
	public void setBusiness_year(double business_year) {
		this.business_year = business_year;
	}
	public double getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(double doc_id) {
		this.doc_id = doc_id;
	}
	public Date getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}
	public Date getDocument_create_date() {
		return document_create_date;
	}
	public void setDocument_create_date(Date document_create_date) {
		this.document_create_date = document_create_date;
	}
	public Date getDocument_create_date_1() {
		return document_create_date_1;
	}
	public void setDocument_create_date_1(Date document_create_date_1) {
		this.document_create_date_1 = document_create_date_1;
	}
	public Date getDue_in_date() {
		return due_in_date;
	}
	public void setDue_in_date(Date due_in_date) {
		this.due_in_date = due_in_date;
	}
	public String getInvoice_currency() {
		return invoice_currency;
	}
	public void setInvoice_currency(String invoice_currency) {
		this.invoice_currency = invoice_currency;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public double getPosting_id() {
		return posting_id;
	}
	public void setPosting_id(double posting_id) {
		this.posting_id = posting_id;
	}
	public double getTotal_open_amount() {
		return total_open_amount;
	}
	public void setTotal_open_amount(double total_open_amount) {
		this.total_open_amount = total_open_amount;
	}
	public Date getBaseline_create_date() {
		return baseline_create_date;
	}
	public void setBaseline_create_date(Date baseline_create_date) {
		this.baseline_create_date = baseline_create_date;
	}
	public String getCust_payment_terms() {
		return cust_payment_terms;
	}
	public void setCust_payment_terms(String cust_payment_terms) {
		this.cust_payment_terms = cust_payment_terms;
	}
	public double getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(double invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public Date getPredicted_date() {
		return predicted_date;
	}
	public void setPredicted_date(Date predicted_date) {
		this.predicted_date = predicted_date;
	}
	public String getAging_Bucket() {
		return Aging_Bucket;
	}
	public void setAging_Bucket(String aging_Bucket) {
		Aging_Bucket = aging_Bucket;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "Invoice [business_code=" + business_code + ", cust_name=" + cust_name + ", name_customer="
				+ name_customer + ", clear_date=" + clear_date + ", business_year=" + business_year + ", doc_id="
				+ doc_id + ", posting_date=" + posting_date + ", document_create_date=" + document_create_date
				+ ", document_create_date_1=" + document_create_date_1 + ", due_in_date=" + due_in_date
				+ ", invoice_currency=" + invoice_currency + ", document_type=" + document_type + ", posting_id="
				+ posting_id + ", total_open_amount=" + total_open_amount + ", baseline_create_date="
				+ baseline_create_date + ", cust_payment_terms=" + cust_payment_terms + ", invoice_id=" + invoice_id
				+ ", isOpen=" + isOpen + ", predicted_date=" + predicted_date + ", Aging_Bucket=" + Aging_Bucket
				+ ", notes=" + notes + "]";
	}
	
}
