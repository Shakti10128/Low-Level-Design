package DTO;

public class CreateTransactionDTO {
    private final String atmId;

    public CreateTransactionDTO(String atmId) {
        this.atmId = atmId;
    }
    public String getAtmId() {
        return atmId;
    }
}
