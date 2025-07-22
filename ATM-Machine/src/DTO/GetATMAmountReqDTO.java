package DTO;

public class GetATMAmountReqDTO {
    private final String atmId;

    public GetATMAmountReqDTO(String atmId) {
        this.atmId = atmId;
    }
    public String getAtmId() {
        return atmId;
    }
}
