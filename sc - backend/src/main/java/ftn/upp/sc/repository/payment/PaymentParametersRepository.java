package ftn.upp.sc.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.payment.PaymentParameters;

@Repository
public interface PaymentParametersRepository extends JpaRepository<PaymentParameters, Long> {
	
}