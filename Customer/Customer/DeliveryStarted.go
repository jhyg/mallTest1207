package Customer

import (
	"time"
)

type DeliveryStarted struct{
	EventType string	`json:"eventType" type:"string"`
	TimeStamp string 	`json:"timeStamp" type:"string"`
	Id int `json:"id" type:"int"` 
	Item string `json:"item" type:"string"` 
	DeliveryAddress string `json:"deliveryAddress" type:"string"` 
	
}

func NewDeliveryStarted() *DeliveryStarted{
	event := &DeliveryStarted{EventType:"DeliveryStarted", TimeStamp:time.Now().String()}

	return event
}
