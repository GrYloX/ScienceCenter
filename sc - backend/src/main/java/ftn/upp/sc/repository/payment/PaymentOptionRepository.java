package ftn.upp.sc.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.payment.PaymentOption;

@Repository
public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {
	
}