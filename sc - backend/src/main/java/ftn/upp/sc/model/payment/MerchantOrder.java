package ftn.upp.sc.model.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ftn.upp.sc.model.TransactionState;
import ftn.upp.sc.model.Type;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.users.User;


@Entity
public class MerchantOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=30)
	private String merchantOrderID;  //id ove klase ne poklapa se ni sa jednim drugim

	private BigDecimal amountOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="buyer_id")
	@JsonIgnore
	private User buyer; // poklapa se sa id kupca u NC
	
	private String successUrl;
	
	private String errorUrl;

	private String failedUrl;

	@Temporal(TemporalType.DATE)
	private Date merchantTimestamp;  ///@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    
	@Enumerated(EnumType.STRING)
	private TransactionState transactionState;

	@Enumerated(EnumType.STRING)
	private Type type;
	
	private String purchaseTypeId; // ukoliko nije paper samo pretvoriti u long/int sta god treba

	//bi-directional many-to-one association to Merchant
	@ManyToOne
	@JoinColumn(name="merchant_id")
	@JsonIgnore
	private Magazine merchant;

	public MerchantOrder() {
		
	}

	public String getMerchantOrderID() {
		return this.merchantOrderID;
	}

	public void setMerchantOrderID(String merchantOrderID) {
		this.merchantOrderID = merchantOrderID;
	}

	public BigDecimal getAmountOrder() {
		return this.amountOrder;
	}

	public void setAmountOrder(BigDecimal amountOrder) {
		this.amountOrder = amountOrder;
	}

	public String getErrorUrl() {
		return this.errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getFailedUrl() {
		return this.failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public Date getMerchantTimestamp() {
		return this.merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getSuccessUrl() {
		return this.successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
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

	public Magazine getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Magazine merchant) {
		this.merchant = merchant;
	}


	public String getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(String purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public User getBuyer() {
		return buyer;
	}


	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

}