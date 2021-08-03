package com.ccic.webservice.sms.sendSimpleMessage.intf;

import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageRequest;
import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageResponse;

public class SendSimpleMessageServicePortProxy implements  SendSimpleMessageServicePort {
  private String _endpoint = null;
  private  SendSimpleMessageServicePort sendSimpleMessageServicePort = null;
  
  public SendSimpleMessageServicePortProxy() {
    _initSendSimpleMessageServicePortProxy();
  }
  
  public SendSimpleMessageServicePortProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendSimpleMessageServicePortProxy();
  }
  
  private void _initSendSimpleMessageServicePortProxy() {
    try {
      sendSimpleMessageServicePort = (new SendSimpleMessageServiceServiceLocator()).getSendSimpleMessageServicePort();
      if (sendSimpleMessageServicePort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendSimpleMessageServicePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendSimpleMessageServicePort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendSimpleMessageServicePort != null)
      ((javax.xml.rpc.Stub)sendSimpleMessageServicePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public  SendSimpleMessageServicePort getSendSimpleMessageServicePort() {
    if (sendSimpleMessageServicePort == null)
      _initSendSimpleMessageServicePortProxy();
    return sendSimpleMessageServicePort;
  }
  
  public SendSimpleMessageResponse sendSimpleMessageService(SendSimpleMessageRequest sendSimpleMessageRequest) throws java.rmi.RemoteException{
    if (sendSimpleMessageServicePort == null)
      _initSendSimpleMessageServicePortProxy();
    return sendSimpleMessageServicePort.sendSimpleMessageService(sendSimpleMessageRequest);
  }
  
  
}