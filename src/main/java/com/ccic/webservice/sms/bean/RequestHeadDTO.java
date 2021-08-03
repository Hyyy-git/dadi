/**
 * RequestHeadDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.webservice.sms.bean;

public class RequestHeadDTO  implements java.io.Serializable {
    private String seqNo;

    private String consumerSeqNo;

    private String consumerID;

    private String providerID;

    private String classCode;

    private String riskCode;

    private String regionCode;

    private String version;

    public RequestHeadDTO() {
    }

    public RequestHeadDTO(
           String seqNo,
           String consumerSeqNo,
           String consumerID,
           String providerID,
           String classCode,
           String riskCode,
           String regionCode,
           String version) {
           this.seqNo = seqNo;
           this.consumerSeqNo = consumerSeqNo;
           this.consumerID = consumerID;
           this.providerID = providerID;
           this.classCode = classCode;
           this.riskCode = riskCode;
           this.regionCode = regionCode;
           this.version = version;
    }


    /**
     * Gets the seqNo value for this RequestHeadDTO.
     * 
     * @return seqNo
     */
    public String getSeqNo() {
        return seqNo;
    }


    /**
     * Sets the seqNo value for this RequestHeadDTO.
     * 
     * @param seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }


    /**
     * Gets the consumerSeqNo value for this RequestHeadDTO.
     * 
     * @return consumerSeqNo
     */
    public String getConsumerSeqNo() {
        return consumerSeqNo;
    }


    /**
     * Sets the consumerSeqNo value for this RequestHeadDTO.
     * 
     * @param consumerSeqNo
     */
    public void setConsumerSeqNo(String consumerSeqNo) {
        this.consumerSeqNo = consumerSeqNo;
    }


    /**
     * Gets the consumerID value for this RequestHeadDTO.
     * 
     * @return consumerID
     */
    public String getConsumerID() {
        return consumerID;
    }


    /**
     * Sets the consumerID value for this RequestHeadDTO.
     * 
     * @param consumerID
     */
    public void setConsumerID(String consumerID) {
        this.consumerID = consumerID;
    }


    /**
     * Gets the providerID value for this RequestHeadDTO.
     * 
     * @return providerID
     */
    public String getProviderID() {
        return providerID;
    }


    /**
     * Sets the providerID value for this RequestHeadDTO.
     * 
     * @param providerID
     */
    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }


    /**
     * Gets the classCode value for this RequestHeadDTO.
     * 
     * @return classCode
     */
    public String getClassCode() {
        return classCode;
    }


    /**
     * Sets the classCode value for this RequestHeadDTO.
     * 
     * @param classCode
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }


    /**
     * Gets the riskCode value for this RequestHeadDTO.
     * 
     * @return riskCode
     */
    public String getRiskCode() {
        return riskCode;
    }


    /**
     * Sets the riskCode value for this RequestHeadDTO.
     * 
     * @param riskCode
     */
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }


    /**
     * Gets the regionCode value for this RequestHeadDTO.
     * 
     * @return regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }


    /**
     * Sets the regionCode value for this RequestHeadDTO.
     * 
     * @param regionCode
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    /**
     * Gets the version value for this RequestHeadDTO.
     * 
     * @return version
     */
    public String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this RequestHeadDTO.
     * 
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestHeadDTO)) return false;
        RequestHeadDTO other = (RequestHeadDTO) obj;
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
            ((this.consumerID==null && other.getConsumerID()==null) || 
             (this.consumerID!=null &&
              this.consumerID.equals(other.getConsumerID()))) &&
            ((this.providerID==null && other.getProviderID()==null) || 
             (this.providerID!=null &&
              this.providerID.equals(other.getProviderID()))) &&
            ((this.classCode==null && other.getClassCode()==null) || 
             (this.classCode!=null &&
              this.classCode.equals(other.getClassCode()))) &&
            ((this.riskCode==null && other.getRiskCode()==null) || 
             (this.riskCode!=null &&
              this.riskCode.equals(other.getRiskCode()))) &&
            ((this.regionCode==null && other.getRegionCode()==null) || 
             (this.regionCode!=null &&
              this.regionCode.equals(other.getRegionCode()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
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
        if (getConsumerID() != null) {
            _hashCode += getConsumerID().hashCode();
        }
        if (getProviderID() != null) {
            _hashCode += getProviderID().hashCode();
        }
        if (getClassCode() != null) {
            _hashCode += getClassCode().hashCode();
        }
        if (getRiskCode() != null) {
            _hashCode += getRiskCode().hashCode();
        }
        if (getRegionCode() != null) {
            _hashCode += getRegionCode().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestHeadDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "RequestHeadDTO"));
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
        elemField.setFieldName("consumerID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "consumerID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("providerID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "providerID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "classCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("riskCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "riskCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "regionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/common/bean", "version"));
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
