/**
 * SendSimpleMessageRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.webservice.wechat.sendSimpleMessage.bean;

import com.ccic.webservice.wechat.bean.RequestHeadDTO;

public class SendSimpleMessageRequest  implements java.io.Serializable {
    private RequestHeadDTO requestHead;

    private SendSimpleMessageRequestDTO requestBody;

    public SendSimpleMessageRequest() {
    }

    public SendSimpleMessageRequest(RequestHeadDTO requestHead,
           SendSimpleMessageRequestDTO requestBody) {
           this.requestHead = requestHead;
           this.requestBody = requestBody;
    }


    /**
     * Gets the requestHead value for this SendSimpleMessageRequest.
     * 
     * @return requestHead
     */
    public RequestHeadDTO getRequestHead() {
        return requestHead;
    }


    /**
     * Sets the requestHead value for this SendSimpleMessageRequest.
     * 
     * @param requestHead
     */
    public void setRequestHead(RequestHeadDTO requestHead) {
        this.requestHead = requestHead;
    }


    /**
     * Gets the requestBody value for this SendSimpleMessageRequest.
     * 
     * @return requestBody
     */
    public SendSimpleMessageRequestDTO getRequestBody() {
        return requestBody;
    }


    /**
     * Sets the requestBody value for this SendSimpleMessageRequest.
     * 
     * @param requestBody
     */
    public void setRequestBody(SendSimpleMessageRequestDTO requestBody) {
        this.requestBody = requestBody;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SendSimpleMessageRequest)) return false;
        SendSimpleMessageRequest other = (SendSimpleMessageRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestHead==null && other.getRequestHead()==null) || 
             (this.requestHead!=null &&
              this.requestHead.equals(other.getRequestHead()))) &&
            ((this.requestBody==null && other.getRequestBody()==null) || 
             (this.requestBody!=null &&
              this.requestBody.equals(other.getRequestBody())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRequestHead() != null) {
            _hashCode += getRequestHead().hashCode();
        }
        if (getRequestBody() != null) {
            _hashCode += getRequestBody().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSimpleMessageRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", ">SendSimpleMessageRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestHead");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "requestHead"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "RequestHeadDTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "requestBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "SendSimpleMessageRequestDTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
