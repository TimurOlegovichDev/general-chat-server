package tibeol.generalchatserver.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UdpResponseCode {

    OK(200),
    BAD(400),
    INTERNAL_SERVER_ERROR(500);

    private final int code;
}
