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

    public static UdpMethod of(String method){
        try{
            return UdpMethod.valueOf(method);
        } catch (Exception e){
            return NOT_SUPPORTED;
        }
    }
}
