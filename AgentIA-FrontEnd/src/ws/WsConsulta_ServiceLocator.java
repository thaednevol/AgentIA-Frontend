/**
 * WsConsulta_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

public class WsConsulta_ServiceLocator extends org.apache.axis.client.Service implements ws.WsConsulta_Service {

    public WsConsulta_ServiceLocator() {
    }


    public WsConsulta_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsConsulta_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsConsultaPort
    private java.lang.String wsConsultaPort_address = "http://ec2-35-162-125-10.us-west-2.compute.amazonaws.com:8080/WsConsulta/wsConsulta";

    public java.lang.String getwsConsultaPortAddress() {
        return wsConsultaPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsConsultaPortWSDDServiceName = "wsConsultaPort";

    public java.lang.String getwsConsultaPortWSDDServiceName() {
        return wsConsultaPortWSDDServiceName;
    }

    public void setwsConsultaPortWSDDServiceName(java.lang.String name) {
        wsConsultaPortWSDDServiceName = name;
    }

    public ws.WsConsulta_PortType getwsConsultaPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsConsultaPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsConsultaPort(endpoint);
    }

    public ws.WsConsulta_PortType getwsConsultaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.WsConsultaPortBindingStub _stub = new ws.WsConsultaPortBindingStub(portAddress, this);
            _stub.setPortName(getwsConsultaPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsConsultaPortEndpointAddress(java.lang.String address) {
        wsConsultaPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.WsConsulta_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.WsConsultaPortBindingStub _stub = new ws.WsConsultaPortBindingStub(new java.net.URL(wsConsultaPort_address), this);
                _stub.setPortName(getwsConsultaPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wsConsultaPort".equals(inputPortName)) {
            return getwsConsultaPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws/", "wsConsulta");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws/", "wsConsultaPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsConsultaPort".equals(portName)) {
            setwsConsultaPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
