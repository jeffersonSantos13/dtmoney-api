package br.com.api.dtmoney.transaction.repository;

import br.com.api.dtmoney.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
