package Customer

import (
	"time"
)

type OrderCancelled struct{
	EventType string	`json:"eventType" type:"string"`
	TimeStamp string 	`json:"timeStamp" type:"string"`
	Id int `json:"id" type:"int"` 
	OrderId int `json:"orderId" type:"int"` 
	
}

func NewOrderCancelled() *OrderCancelled{
	event := &OrderCancelled{EventType:"OrderCancelled", TimeStamp:time.Now().String()}

	return event
}
