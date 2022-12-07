package Customer

import (
	"time"
)

type CouponIssued struct{
	EventType string	`json:"eventType" type:"string"`
	TimeStamp string 	`json:"timeStamp" type:"string"`
	Id int `json:"id" type:"int"` 
	Item string `json:"item" type:"string"` 
	DeliveryAddress string `json:"deliveryAddress" type:"string"` 
	
}

func NewCouponIssued() *CouponIssued{
	event := &CouponIssued{EventType:"CouponIssued", TimeStamp:time.Now().String()}

	return event
}
