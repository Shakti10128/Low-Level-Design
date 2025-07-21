package apis;

import DTO.CreateTransactionDTO;

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
}
