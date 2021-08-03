/**
 * SendSimpleMessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.webservice.wechat.sendSimpleMessage.bean;

import com.ccic.webservice.wechat.bean.ResponseHeadDTO;

public class SendSimpleMessageResponse  implements java.io.Serializable {
    private ResponseHeadDTO responseHead;

    private SendSimpleMessageResponseDTO responseBody;

    public SendSimpleMessageResponse() {
    }

    public SendSimpleMessageResponse(
           ResponseHeadDTO responseHead,
           SendSimpleMessageResponseDTO responseBody) {
           this.responseHead = responseHead;
           this.responseBody = responseBody;
    }


    /**
     * Gets the responseHead value for this SendSimpleMessageResponse.
     * 
     * @return responseHead
     */
    public ResponseHeadDTO getResponseHead() {
        return responseHead;
    }


    /**
     * Sets the responseHead value for this SendSimpleMessageResponse.
     * 
     * @param responseHead
     */
    public void setResponseHead(ResponseHeadDTO responseHead) {
        this.responseHead = responseHead;
    }


    /**
     * Gets the responseBody value for this SendSimpleMessageResponse.
     * 
     * @return responseBody
     */
    public SendSimpleMessageResponseDTO getResponseBody() {
        return responseBody;
    }


    /**
     * Sets the responseBody value for this SendSimpleMessageResponse.
     * 
     * @param responseBody
     */
    public void setResponseBody(SendSimpleMessageResponseDTO responseBody) {
        this.responseBody = responseBody;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SendSimpleMessageResponse)) return false;
        SendSimpleMessageResponse other = (SendSimpleMessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responseHead==null && other.getResponseHead()==null) || 
             (this.responseHead!=null &&
              this.responseHead.equals(other.getResponseHead()))) &&
            ((this.responseBody==null && other.getResponseBody()==null) || 
             (this.responseBody!=null &&
              this.responseBody.equals(other.getResponseBody())));
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
        if (getResponseHead() != null) {
            _hashCode += getResponseHead().hashCode();
        }
        if (getResponseBody() != null) {
            _hashCode += getResponseBody().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSimpleMessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", ">SendSimpleMessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseHead");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "responseHead"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "ResponseHeadDTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "responseBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "SendSimpleMessageResponseDTO"));
        elemField.setMinOccurs(0);
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
