package apis;

import DTO.CreateTransactionDTO;
import DTO.GetATMAmountReqDTO;
import DTO.UpdateATMStateDTO;

public class NodeBackendApi implements BackendAPI{
    // should be only responsible to connect the backend and return the response

    @Override
    public int createTransaction(CreateTransactionDTO createTransactionDTO) {
        // 1: validate
//        if(createTransactionDTO.getAtmId() == null || createTransactionDTO.getAtmId().isEmpty()) {
//            throw new IllegalArgumentException("ATM ID cannot be null or empty");
//        }

        // 2: connect to the backend
        // to mimic the backend call let's create a new random transaction Id
        // 3: return the response
        int txnId = (int)(Math.random() * 10000);
        return txnId;
    }

    @Override
    public boolean updateState(UpdateATMStateDTO updateATMStateDTO) {
        // assume that there is implementation that call the backend to update the state of the ATM
        return true; // mimic the response
    }

    @Override
    public int getATMAmount(GetATMAmountReqDTO getATMAmountReqDTO) {
        return 100000; // ATM has 1lac amount currently
    }
}
