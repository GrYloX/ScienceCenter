package ftn.upp.sc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.payment.MerchantOrder;

@Repository
public interface MerchantOrderRepository extends JpaRepository<MerchantOrder, String> {
		List<MerchantOrder> findByBuyer_username(String username);
	}

