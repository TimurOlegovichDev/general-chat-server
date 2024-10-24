package tibeol.generalchatserver.net;

import lombok.Getter;
import tibeol.generalchatserver.enums.UdpResponseCode;

@Getter
public class UdpResponse {

    private final UdpResponseCode code;
    private final String body;

    private UdpResponse(UdpResponseCode code, String body) {
        this.code = code;
        this.body = body;
    }

    public static UdpResponse ok(String body){
        return new UdpResponse(UdpResponseCode.OK, body);
    }

    public static UdpResponse bad(String body){
        return new UdpResponse(UdpResponseCode.BAD, body);
    }

    public static UdpResponse serverError(String body){
        return new UdpResponse(UdpResponseCode.INTERNAL_SERVER_ERROR, body);
    }

    @Override
    public String toString() {
        return "UdpResponse{" +
                "code=" + code.getCode() +
                ", body='" + body + '\'' +
                '}';
    }
}
