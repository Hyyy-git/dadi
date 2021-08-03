/**
 * ResponseHeadDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.webservice.sms.bean;

public class ResponseHeadDTO  implements java.io.Serializable {
    private String seqNo;

    private String consumerSeqNo;

    private String providerSeqNo;

    private int status;

    private String esbCode;

    private String esbMessage;

    private String appCode;

    private String appMessage;

    public ResponseHeadDTO() {
    }

    public ResponseHeadDTO(
           String seqNo,
           String consumerSeqNo,
           String providerSeqNo,
           int status,
           String esbCode,
           String esbMessage,
           String appCode,
           String appMessage) {
           this.seqNo = seqNo;
           this.consumerSeqNo = consumerSeqNo;
           this.providerSeqNo = providerSeqNo;
           this.status = status;
           this.esbCode = esbCode;
           this.esbMessage = esbMessage;
           this.appCode = appCode;
           this.appMessage = appMessage;
    }


    /**
     * Gets the seqNo value for this ResponseHeadDTO.
     * 
     * @return seqNo
     */
    public String getSeqNo() {
        return seqNo;
    }


    /**
     * Sets the seqNo value for this ResponseHeadDTO.
     * 
     * @param seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }


    /**
     * Gets the consumerSeqNo value for this ResponseHeadDTO.
     * 
     * @return consumerSeqNo
     */
    public String getConsumerSeqNo() {
        return consumerSeqNo;
    }


    /**
     * Sets the consumerSeqNo value for this ResponseHeadDTO.
     * 
     * @param consumerSeqNo
     */
    public void setConsumerSeqNo(String consumerSeqNo) {
        this.consumerSeqNo = consumerSeqNo;
    }


    /**
     * Gets the providerSeqNo value for this ResponseHeadDTO.
     * 
     * @return providerSeqNo
     */
    public String getProviderSeqNo() {
        return providerSeqNo;
    }


    /**
     * Sets the providerSeqNo value for this ResponseHeadDTO.
     * 
     * @param providerSeqNo
     */
    public void setProviderSeqNo(String providerSeqNo) {
        this.providerSeqNo = providerSeqNo;
    }


    /**
     * Gets the status value for this ResponseHeadDTO.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ResponseHeadDTO.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the esbCode value for this ResponseHeadDTO.
     * 
     * @return esbCode
     */
    public String getEsbCode() {
        return esbCode;
    }


    /**
     * Sets the esbCode value for this ResponseHeadDTO.
     * 
     * @param esbCode
     */
    public void setEsbCode(String esbCode) {
        this.esbCode = esbCode;
    }


    /**
     * Gets the esbMessage value for this ResponseHeadDTO.
     * 
     * @return esbMessage
     */
    public String getEsbMessage() {
        return esbMessage;
    }


    /**
     * Sets the esbMessage value for this ResponseHeadDTO.
     * 
     * @param esbMessage
     */
    public void setEsbMessage(String esbMessage) {
        this.esbMessage = esbMessage;
    }


    /**
     * Gets the appCode value for this ResponseHeadDTO.
     * 
     * @return appCode
     */
    public String getAppCode() {
        return appCode;
    }


    /**
     * Sets the appCode value for this ResponseHeadDTO.
     * 
     * @param appCode
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }


    /**
     * Gets the appMessage value for this ResponseHeadDTO.
     * 
     * @return appMessage
     */
    public String getAppMessage() {
        return appMessage;
    }


    /**
     * Sets the appMessage value for this ResponseHeadDTO.
     * 
     * @param appMessage
     */
    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ResponseHeadDTO)) return false;
        ResponseHeadDTO other = (ResponseHeadDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.seqNo==null && other.getSeqNo()==null) || 
             (this.seqNo!=null &&
              this.seqNo.equals(other.getSeqNo()))) &&
            ((this.consumerSeqNo==null && other.getConsumerSeqNo()==null) || 
             (this.consumerSeqNo!=null &&
              this.consumerSeqNo.equals(other.getConsumerSeqNo()))) &&
            ((this.providerSeqNo==null && other.getProviderSeqNo()==null) || 
             (this.providerSeqNo!=null &&
              this.providerSeqNo.equals(other.getProviderSeqNo()))) &&
            this.status == other.getStatus() &&
            ((this.esbCode==null && other.getEsbCode()==null) || 
             (this.esbCode!=null &&
              this.esbCode.equals(other.getEsbCode()))) &&
            ((this.esbMessage==null && other.getEsbMessage()==null) || 
             (this.esbMessage!=null &&
              this.esbMessage.equals(other.getEsbMessage()))) &&
            ((this.appCode==null && other.getAppCode()==null) || 
             (this.appCode!=null &&
              this.appCode.equals(other.getAppCode()))) &&
            ((this.appMessage==null && other.getAppMessage()==null) || 
             (this.appMessage!=null &&
              this.appMessage.equals(other.getAppMessage())));
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
        if (getSeqNo() != null) {
            _hashCode += getSeqNo().hashCode();
        }
        if (getConsumerSeqNo() != null) {
            _hashCode += getConsumerSeqNo().hashCode();
        }
        if (getProviderSeqNo() != null) {
            _hashCode += getProviderSeqNo().hashCode();
        }
        _hashCode += getStatus();
        if (getEsbCode() != null) {
            _hashCode += getEsbCode().hashCode();
        }
        if (getEsbMessage() != null) {
            _hashCode += getEsbMessage().hashCode();
        }
        if (getAppCode() != null) {
            _hashCode += getAppCode().hashCode();
        }
        if (getAppMessage() != null) {
            _hashCode += getAppMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseHeadDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "ResponseHeadDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seqNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "seqNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consumerSeqNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "consumerSeqNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("providerSeqNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "providerSeqNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esbCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "esbCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esbMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "esbMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "appCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "appMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
