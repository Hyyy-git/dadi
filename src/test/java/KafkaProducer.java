/*


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;


*/
/**
 * Created by TaoHang on 2019/9/3.
 *//*


public class KafkaProducer {
    private final Producer<String, String> producer;
    public final static String TOPIC = "BUSI-SYS-ALARM";

    private KafkaProducer() {
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "10.1.12.187:9092,10.1.12.188:9092,10.1.12.189:9092");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        producer = new Producer<>(new ProducerConfig(props));
    }

    void produce() {
        int messageNo = 1000;
        final int COUNT = 10000;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message ";
            producer.send(new KeyedMessage<>(TOPIC, data));
            System.out.println(data);
            messageNo++;
        }
    }

//    public static void main(String[] args) {
//        new KafkaProducer().produce();
//    }
}
*/
