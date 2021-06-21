package br.com.api.dtmoney.transaction.service;

import br.com.web.transaction.api.model.TransactionInput;
import br.com.web.transaction.api.model.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {
    List<TransactionResponse> listAllTransactions();

    TransactionResponse createTransaction(TransactionInput input);
}
