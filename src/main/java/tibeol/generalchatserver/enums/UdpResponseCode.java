package tibeol.generalchatserver.enums;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UdpResponseCode {

    OK(200),
    BAD(400),
    INTERNAL_SERVER_ERROR(500);


    private final int code;

    @JsonValue
    public int getCode() {
        return code;
    }
}
