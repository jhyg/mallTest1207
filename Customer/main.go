package main

import(
	"log"
	"Customer/Customer"
	"Customer/config"
)

func main() {
	config.ConfigMode()
	options := config.Reader(config.GetMode())
	log.Printf("option : %v", options)
	go Customer.InitProducer(config.GetMode())
	// view 와 같이 사용시 InitConsumer 가 중복으로 호출될수 있으니, 하나는 삭제 필요
	go Customer.InitConsumer(config.GetMode())
	e := Customer.RouteInit()

	e.Logger.Fatal(e.Start(":8084"))
}
