package client;

import java.util.Arrays;

public enum HttpMethod {
    GET(1),
    PUT(2),
    DELETE(3);

    private final int actionNumber;

    HttpMethod(int actionNumber) {
        this.actionNumber = actionNumber;
    }

    public int getActionNumber() {
        return actionNumber;
    }

    public static HttpMethod fromActionNumber(int actionNumber) {
        return Arrays.stream(HttpMethod.values())
                .filter(method -> method.actionNumber == actionNumber)
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(HttpMethod.class, "Http method for '" + actionNumber + "' number not found!"));
    }
}
