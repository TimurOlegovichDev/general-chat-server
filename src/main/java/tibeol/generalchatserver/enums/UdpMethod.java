package tibeol.generalchatserver.enums;

import lombok.Getter;

@Getter
public enum UdpMethod {

    GET_MESSAGES,
    SEND_MESSAGE,
    PATCH_USER,
    DELETE_USER,
    LOGIN,
    REGISTER,
    NOT_SUPPORTED;

    public static UdpMethod of(String data){
        try{
            return UdpMethod.valueOf(
                    data.substring(0, data.indexOf('&'))
                            .toUpperCase()
            );
        } catch (Exception e){
            return NOT_SUPPORTED;
        }
    }
}
