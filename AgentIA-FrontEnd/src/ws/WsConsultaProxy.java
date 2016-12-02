package ws;

public class WsConsultaProxy implements ws.WsConsulta_PortType {
  private String _endpoint = null;
  private ws.WsConsulta_PortType wsConsulta_PortType = null;
  
  public WsConsultaProxy() {
    _initWsConsultaProxy();
  }
  
  public WsConsultaProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsConsultaProxy();
  }
  
  private void _initWsConsultaProxy() {
    try {
      wsConsulta_PortType = (new ws.WsConsulta_ServiceLocator()).getwsConsultaPort();
      if (wsConsulta_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsConsulta_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsConsulta_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsConsulta_PortType != null)
      ((javax.xml.rpc.Stub)wsConsulta_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.WsConsulta_PortType getWsConsulta_PortType() {
    if (wsConsulta_PortType == null)
      _initWsConsultaProxy();
    return wsConsulta_PortType;
  }
  
  public java.lang.String patterToSearch(java.lang.String name) throws java.rmi.RemoteException{
    if (wsConsulta_PortType == null)
      _initWsConsultaProxy();
    return wsConsulta_PortType.patterToSearch(name);
  }
  
  
}