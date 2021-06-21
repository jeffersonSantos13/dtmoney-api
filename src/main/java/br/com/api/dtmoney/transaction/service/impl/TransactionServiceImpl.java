package br.com.api.dtmoney.transaction.service.impl;

import br.com.api.dtmoney.transaction.model.Transaction;
import br.com.api.dtmoney.transaction.model.Transactiontype;
import br.com.api.dtmoney.transaction.repository.TransactionRepository;
import br.com.api.dtmoney.transaction.service.TransactionService;

import br.com.web.transaction.api.model.TransactionInput;
import br.com.web.transaction.api.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

    public static final String CATEGORY = "default";

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> listAllTransactions() {
        return buildTransactionResponse(transactionRepository.findAll());
    }

    @Override
    public TransactionResponse createTransaction(TransactionInput input) {
        var transaction = transactionRepository.save(buildTransaction(input));

        return new TransactionResponse()
                .id(transaction.getId())
                .category(transaction.getCategory())
                .title(transaction.getTitle())
                .amount(transaction.getAmount())
                .type(transaction.getType().toString().toLowerCase());
    }

    private List<TransactionResponse> buildTransactionResponse(List<Transaction> transactionList) {
        return transactionList
                .stream()
                .map(transaction -> new TransactionResponse()
                        .id(transaction.getId())
                        .amount(transaction.getAmount())
                        .title(transaction.getTitle())
                        .type(transaction.getType().toString().toLowerCase())
                        .category(transaction.getCategory()))
                .collect(Collectors.toList());
    }
    private Transaction buildTransaction(TransactionInput input) {
        var transaction = new Transaction();

        transaction.setAmount(StringUtils.isEmpty(input.getAmount()) ? 0.0 : Double.valueOf(input.getAmount()));
        transaction.setCategory(StringUtils.isEmpty(input.getCategory()) ? CATEGORY : input.getCategory());
        transaction.setTitle(input.getTitle());
        transaction.setType(input.getType().toUpperCase().equals(String.valueOf(Transactiontype.DEPOSIT)) ?
            Transactiontype.DEPOSIT : Transactiontype.WITHDRAW
        );

        return transaction;
    }
}
