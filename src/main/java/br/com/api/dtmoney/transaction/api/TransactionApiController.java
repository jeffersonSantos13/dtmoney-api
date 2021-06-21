package br.com.api.dtmoney.transaction.api;

import br.com.api.dtmoney.transaction.service.TransactionService;
import br.com.web.transaction.api.TransactionsApi;
import br.com.web.transaction.api.model.TransactionInput;
import br.com.web.transaction.api.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionApiController implements TransactionsApi {

    @Autowired
    private TransactionService transactionService;

    @Override
    public ResponseEntity<TransactionResponse> createTransaction(TransactionInput input) {
        TransactionResponse response = transactionService.createTransaction(input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> response = transactionService.listAllTransactions();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionResponse> updateTransaction(Integer id, TransactionInput body) {
        return null;
    }
}
