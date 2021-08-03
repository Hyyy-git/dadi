/**
 * SendSimpleMessageServicePort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.webservice.sms.sendSimpleMessage.intf;

import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageRequest;
import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageResponse;

public interface SendSimpleMessageServicePort extends java.rmi.Remote {
    public SendSimpleMessageResponse sendSimpleMessageService(SendSimpleMessageRequest sendSimpleMessageRequest) throws java.rmi.RemoteException;
}
