package tibeol.generalchatserver.service;

import tibeol.generalchatserver.entity.Client;
import tibeol.generalchatserver.net.UdpRequest;
import tibeol.generalchatserver.net.UdpResponse;

public interface ActionService {

    UdpResponse serve(Client client);
}
