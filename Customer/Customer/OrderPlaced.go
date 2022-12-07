package Customer

import (
	"time"
)

type OrderPlaced struct{
	EventType string	`json:"eventType" type:"string"`
	TimeStamp string 	`json:"timeStamp" type:"string"`
	Id int `json:"id" type:"int"` 
	OrderId int `json:"orderId" type:"int"` 
	MenuId int `json:"menuId" type:"int"` 
	Quantity int `json:"quantity" type:"int"` 
	StoreId int `json:"storeId" type:"int"` 
	
}

func NewOrderPlaced() *OrderPlaced{
	event := &OrderPlaced{EventType:"OrderPlaced", TimeStamp:time.Now().String()}

	return event
}
