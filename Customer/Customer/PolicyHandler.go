package Customer

import (
	"github.com/mitchellh/mapstructure"
)

func wheneverOrderPlaced_Notification(data map[string]interface{}){
	
	event := NewOrderPlaced()
	mapstructure.Decode(data,&event)

	Notification(event);
}

func wheneverOrderCancelled_Notification(data map[string]interface{}){
	
	event := NewOrderCancelled()
	mapstructure.Decode(data,&event)

	Notification(event);
}

func wheneverCouponIssued_Notification(data map[string]interface{}){
	
	event := NewCouponIssued()
	mapstructure.Decode(data,&event)

	Notification(event);
}

func wheneverDeliveryStarted_Notification(data map[string]interface{}){
	
	event := NewDeliveryStarted()
	mapstructure.Decode(data,&event)

	Notification(event);
}

