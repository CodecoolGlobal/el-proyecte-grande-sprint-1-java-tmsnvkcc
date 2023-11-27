package com.codecool.service.trackPage;

import com.codecool.dto.ExternalTransactionDTO;
import com.codecool.dto.GetMonthlyTransactionsDTO;
import com.codecool.dto.LocalTransactionDTO;
import com.codecool.model.transaction.ExternalTransaction;
import com.codecool.model.transaction.LocalTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackPageService {
//    public GetMonthlyTransactionsDTO getTransactionForMonth(int year, int month) {
//        List<ExternalTransaction> incomeTransactions = incomeDAO.getAllTransactionForMonth(year,month);
//        List<ExternalTransaction> spendingTransactions = spendingsDAO.getAllTransactionForMonth(year, month);
//        List<LocalTransaction> savingsTransactions = savingsDAO.getAllTransactionForMonth(year, month);
//
//        List<ExternalTransactionDTO> incomeDTOList = mapExternalTransactionsToExternalDTOList(incomeTransactions);
//        List<ExternalTransactionDTO> spendingDTOList = mapExternalTransactionsToExternalDTOList(spendingTransactions);
//        List<LocalTransactionDTO> savingsDTOList = mapLocalTransactionsToLocalDTOList(savingsTransactions);
//
//        return new GetMonthlyTransactionsDTO(incomeDTOList,spendingDTOList,savingsDTOList);
//    }

    private LocalTransactionDTO convertTransactionToLocalDTO( LocalTransaction transaction ) {
        return new LocalTransactionDTO(
                transaction.getDescription(),
                transaction.getDateOfTransaction(),
                transaction.getAmount(),
                transaction.isPlanned(),
                transaction.isRecurring()
        );}
    private ExternalTransactionDTO convertTransactionToExternalDTO( ExternalTransaction transaction ){
        return new ExternalTransactionDTO(
                transaction.getDescription(),
                transaction.getDateOfTransaction(),
                transaction.getAmount(),
                transaction.isPlanned(),
                transaction.isRecurring(),
                transaction.getCategoryName()
        );}
    private List<LocalTransactionDTO> mapLocalTransactionsToLocalDTOList( List<LocalTransaction> localTransactions ){
        return localTransactions
                .stream()
                .map(this::convertTransactionToLocalDTO)
                .toList();
    }
    private List<ExternalTransactionDTO> mapExternalTransactionsToExternalDTOList( List<ExternalTransaction> externalTransactions ) {
        return externalTransactions
                .stream()
                .map(this::convertTransactionToExternalDTO)
                .toList();
    }

}
