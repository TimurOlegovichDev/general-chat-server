package tibeol.generalchatserver.enums;

import lombok.Getter;

@Getter
public enum UdpMethod {

    GET("GET"),
    POST("POST"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    NOT_SUPPORTED(null);

    private final String type;

    UdpMethod(String type) {
        this.type = type;
    }

    public static UdpMethod of(String data){
        try{
            return UdpMethod.valueOf(data.substring(0, data.indexOf(':')));
        } catch (Exception e){
            return NOT_SUPPORTED;
        }
    }
}
