package com.lujiahao.trade.middleware.rocketmq;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.lujiahao.trade.common.constants.MQEnum;
import com.lujiahao.trade.middleware.rocketmq.base.AbstractRocketMqProducer;
import com.lujiahao.trade.common.exception.RocketMqException;
import com.lujiahao.trade.middleware.rocketmq.base.ProducerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lujiahao
 * @date 2018-06-06 下午1:32
 */
@Component
public class TradeProducer extends AbstractRocketMqProducer {

    @Autowired
    private ProducerProperties producerProperties;

    @Override
    public SendResult sendMessage(String topic, String tags, String keys, String messageText) throws RocketMqException {
        Message message = new Message(topic, tags, keys, messageText.getBytes());
        try {
            SendResult sendResult = this.producer.send(message);
            return sendResult;
        } catch (Exception e) {
            throw new RocketMqException(e);
        }
    }

    public SendResult sendMessage(MQEnum.TopicEnum topicEnum, String keys, String messageText) throws RocketMqException {
        return sendMessage(topicEnum.getTopic(), topicEnum.getTag(), keys, messageText);
    }

    @Override
    public ProducerProperties getMqProperty() {
        return producerProperties;
    }

    public void send(String messageText) {
        try {
            SendResult sendResult = this.sendMessage(producerProperties.getTopic(), producerProperties.getTag(), producerProperties.getKeys(), messageText);
        } catch (RocketMqException e) {
            e.printStackTrace();
        }
    }

}
