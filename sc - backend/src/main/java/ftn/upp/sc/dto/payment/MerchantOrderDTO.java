package ftn.upp.sc.dto.payment;

import java.math.BigDecimal;
import java.util.Date;

import ftn.upp.sc.model.TransactionState;
import ftn.upp.sc.model.Type;

public class MerchantOrderDTO {
	private BigDecimal amountOrder;
	private String merchantOrderID;
	private String buyer_id;
	private String successUrl;
	private String errorUrl;
	private String failedUrl;
	private Date merchantTimestamp;
	private TransactionState transactionState;
	private Type type;
	private String purchaseTypeId;
	private String merchant_id;
	
	public MerchantOrderDTO(){
		
	}

	public BigDecimal getAmountOrder() {
		return amountOrder;
	}

	public void setAmountOrder(BigDecimal amountOrder) {
		this.amountOrder = amountOrder;
	}

	public String getMerchantOrderID() {
		return merchantOrderID;
	}

	public void setMerchantOrderID(String merchantOrderID) {
		this.merchantOrderID = merchantOrderID;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public TransactionState getTransactionState() {
		return transactionState;
	}

	public void setTransactionState(TransactionState transactionState) {
		this.transactionState = transactionState;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(String purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	
	
	
	
}
