package tibeol.generalchatserver.service;

import org.springframework.stereotype.Service;
import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.net.UdpRequest;
import tibeol.generalchatserver.net.UdpResponse;

public interface ActionService<T> {

    UdpResponse serve(T serveObject);
}
