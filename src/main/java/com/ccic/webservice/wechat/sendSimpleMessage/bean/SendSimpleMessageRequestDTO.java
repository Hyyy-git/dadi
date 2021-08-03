/**
 * SendSimpleMessageRequestDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.webservice.wechat.sendSimpleMessage.bean;

public class SendSimpleMessageRequestDTO  implements java.io.Serializable {
    private String channel;

    private String subChannel;

    private String messageType;

    private String comcode;

    private String targetType;

    private String[] target;

    private String content;

    private Integer priority;

    private java.util.Calendar sendTime;

    public SendSimpleMessageRequestDTO() {
    }

    public SendSimpleMessageRequestDTO(
           String channel,
           String subChannel,
           String messageType,
           String comcode,
           String targetType,
           String[] target,
           String content,
           Integer priority,
           java.util.Calendar sendTime) {
           this.channel = channel;
           this.subChannel = subChannel;
           this.messageType = messageType;
           this.comcode = comcode;
           this.targetType = targetType;
           this.target = target;
           this.content = content;
           this.priority = priority;
           this.sendTime = sendTime;
    }


    /**
     * Gets the channel value for this SendSimpleMessageRequestDTO.
     * 
     * @return channel
     */
    public String getChannel() {
        return channel;
    }


    /**
     * Sets the channel value for this SendSimpleMessageRequestDTO.
     * 
     * @param channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }


    /**
     * Gets the subChannel value for this SendSimpleMessageRequestDTO.
     * 
     * @return subChannel
     */
    public String getSubChannel() {
        return subChannel;
    }


    /**
     * Sets the subChannel value for this SendSimpleMessageRequestDTO.
     * 
     * @param subChannel
     */
    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }


    /**
     * Gets the messageType value for this SendSimpleMessageRequestDTO.
     * 
     * @return messageType
     */
    public String getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this SendSimpleMessageRequestDTO.
     * 
     * @param messageType
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the comcode value for this SendSimpleMessageRequestDTO.
     * 
     * @return comcode
     */
    public String getComcode() {
        return comcode;
    }


    /**
     * Sets the comcode value for this SendSimpleMessageRequestDTO.
     * 
     * @param comcode
     */
    public void setComcode(String comcode) {
        this.comcode = comcode;
    }


    /**
     * Gets the targetType value for this SendSimpleMessageRequestDTO.
     * 
     * @return targetType
     */
    public String getTargetType() {
        return targetType;
    }


    /**
     * Sets the targetType value for this SendSimpleMessageRequestDTO.
     * 
     * @param targetType
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }


    /**
     * Gets the target value for this SendSimpleMessageRequestDTO.
     * 
     * @return target
     */
    public String[] getTarget() {
        return target;
    }


    /**
     * Sets the target value for this SendSimpleMessageRequestDTO.
     * 
     * @param target
     */
    public void setTarget(String[] target) {
        this.target = target;
    }

    public String getTarget(int i) {
        return this.target[i];
    }

    public void setTarget(int i, String _value) {
        this.target[i] = _value;
    }


    /**
     * Gets the content value for this SendSimpleMessageRequestDTO.
     * 
     * @return content
     */
    public String getContent() {
        return content;
    }


    /**
     * Sets the content value for this SendSimpleMessageRequestDTO.
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * Gets the priority value for this SendSimpleMessageRequestDTO.
     * 
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this SendSimpleMessageRequestDTO.
     * 
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    /**
     * Gets the sendTime value for this SendSimpleMessageRequestDTO.
     * 
     * @return sendTime
     */
    public java.util.Calendar getSendTime() {
        return sendTime;
    }


    /**
     * Sets the sendTime value for this SendSimpleMessageRequestDTO.
     * 
     * @param sendTime
     */
    public void setSendTime(java.util.Calendar sendTime) {
        this.sendTime = sendTime;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SendSimpleMessageRequestDTO)) return false;
        SendSimpleMessageRequestDTO other = (SendSimpleMessageRequestDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.channel==null && other.getChannel()==null) || 
             (this.channel!=null &&
              this.channel.equals(other.getChannel()))) &&
            ((this.subChannel==null && other.getSubChannel()==null) || 
             (this.subChannel!=null &&
              this.subChannel.equals(other.getSubChannel()))) &&
            ((this.messageType==null && other.getMessageType()==null) || 
             (this.messageType!=null &&
              this.messageType.equals(other.getMessageType()))) &&
            ((this.comcode==null && other.getComcode()==null) || 
             (this.comcode!=null &&
              this.comcode.equals(other.getComcode()))) &&
            ((this.targetType==null && other.getTargetType()==null) || 
             (this.targetType!=null &&
              this.targetType.equals(other.getTargetType()))) &&
            ((this.target==null && other.getTarget()==null) || 
             (this.target!=null &&
              java.util.Arrays.equals(this.target, other.getTarget()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.sendTime==null && other.getSendTime()==null) || 
             (this.sendTime!=null &&
              this.sendTime.equals(other.getSendTime())));
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
        if (getChannel() != null) {
            _hashCode += getChannel().hashCode();
        }
        if (getSubChannel() != null) {
            _hashCode += getSubChannel().hashCode();
        }
        if (getMessageType() != null) {
            _hashCode += getMessageType().hashCode();
        }
        if (getComcode() != null) {
            _hashCode += getComcode().hashCode();
        }
        if (getTargetType() != null) {
            _hashCode += getTargetType().hashCode();
        }
        if (getTarget() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTarget());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTarget(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getSendTime() != null) {
            _hashCode += getSendTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSimpleMessageRequestDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "SendSimpleMessageRequestDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("channel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "channel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subChannel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "subChannel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "messageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "comcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "targetType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("target");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "target"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://service.ccic.com/communicate/sendSimpleMessage/bean", "sendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
