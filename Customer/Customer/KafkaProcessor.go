package Customer

import (
	"gopkg.in/confluentinc/confluent-kafka-go.v1/kafka"
	"fmt"
    "encoding/json"
    "Customer/config"
)

var producer *kafka.Producer
var consumer *kafka.Consumer
var topic string

func InitProducer(platform string){
	var err error
    var options = config.Reader(platform)
	producer, err = kafka.NewProducer(&kafka.ConfigMap{"bootstrap.servers": options["bootstrap_servers"]})
	if err != nil {
		panic(err)
	}
	topic = options["destination"]
}

func InitConsumer(platform string){
	var err error
    var options = config.Reader(platform)
	consumer, err = kafka.NewConsumer(&kafka.ConfigMap{
		"bootstrap.servers": options["bootstrap_servers"],
		"group.id":          options["group_id"],
		"broker.address.family": "v4",
		"session.timeout.ms":    6000,
		"auto.offset.reset": "earliest",
	})
	topic = options["destination"]

	if err != nil {
		panic(err)
	}
	defer consumer.Close()
	KafkaConsumer()
	
}

func KafkaProducer() (*kafka.Producer, string){
	
	return producer, topic
}

func KafkaConsumer(){
    
	
    consumer.SubscribeTopics([]string{topic}, nil)

	var dat map[string]interface{}
    for {
        msg, err := consumer.ReadMessage(-1)
        if err == nil {
			if err := json.Unmarshal(msg.Value, &dat); err != nil {
				panic(err)
			}
			for _, header := range msg.Headers {
				if header.Key == "type" && string(header.Value) == "OrderPlaced" {
					wheneverOrderPlaced_Notification(dat)
				}
			}
            //if dat["eventType"] == "OrderPlaced"{
            //    wheneverOrderPlaced_Notification(dat)
            //}
			for _, header := range msg.Headers {
				if header.Key == "type" && string(header.Value) == "OrderCancelled" {
					wheneverOrderCancelled_Notification(dat)
				}
			}
            //if dat["eventType"] == "OrderCancelled"{
            //    wheneverOrderCancelled_Notification(dat)
            //}
			for _, header := range msg.Headers {
				if header.Key == "type" && string(header.Value) == "CouponIssued" {
					wheneverCouponIssued_Notification(dat)
				}
			}
            //if dat["eventType"] == "CouponIssued"{
            //    wheneverCouponIssued_Notification(dat)
            //}
			for _, header := range msg.Headers {
				if header.Key == "type" && string(header.Value) == "DeliveryStarted" {
					wheneverDeliveryStarted_Notification(dat)
				}
			}
            //if dat["eventType"] == "DeliveryStarted"{
            //    wheneverDeliveryStarted_Notification(dat)
            //}
			
        } else {
            // The client will automatically try to recover from all errors.
            fmt.Printf("Consumer error: %v (%v)\n", err, msg)
        }
    }
}

func SendStreamMsg(message, eventType string) {
	producer, topic := KafkaProducer()
	eventHeaders := []kafka.Header{
		{"type", []byte(eventType)},
	}
	producer.Produce(&kafka.Message{
		TopicPartition: kafka.TopicPartition{Topic: &topic, Partition: kafka.PartitionAny},
		Value:          []byte(message),
		Headers:        eventHeaders,
	}, nil)
}