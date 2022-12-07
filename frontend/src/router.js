
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"

import MenuView from "./components/MenuView"
import MenuViewDetail from "./components/MenuViewDetail"
import ConsolidatedOrderStatusView from "./components/ConsolidatedOrderStatusView"
import ConsolidatedOrderStatusViewDetail from "./components/ConsolidatedOrderStatusViewDetail"
import OrderManagementManager from "./components/listers/OrderManagementCards"
import OrderManagementDetail from "./components/listers/OrderManagementDetail"

import OrderDetailsViewView from "./components/OrderDetailsViewView"
import OrderDetailsViewViewDetail from "./components/OrderDetailsViewViewDetail"
import PaymentHistoryManager from "./components/listers/PaymentHistoryCards"
import PaymentHistoryDetail from "./components/listers/PaymentHistoryDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },


            {
                path: '/menus',
                name: 'MenuView',
                component: MenuView
            },
            {
                path: '/menus/:id',
                name: 'MenuViewDetail',
                component: MenuViewDetail
            },

            {
                path: '/consolidatedOrderStatuses',
                name: 'ConsolidatedOrderStatusView',
                component: ConsolidatedOrderStatusView
            },
            {
                path: '/consolidatedOrderStatuses/:id',
                name: 'ConsolidatedOrderStatusViewDetail',
                component: ConsolidatedOrderStatusViewDetail
            },
            {
                path: '/orderManagements',
                name: 'OrderManagementManager',
                component: OrderManagementManager
            },
            {
                path: '/orderManagements/:id',
                name: 'OrderManagementDetail',
                component: OrderManagementDetail
            },


            {
                path: '/orderDetailsViews',
                name: 'OrderDetailsViewView',
                component: OrderDetailsViewView
            },
            {
                path: '/orderDetailsViews/:id',
                name: 'OrderDetailsViewViewDetail',
                component: OrderDetailsViewViewDetail
            },
            {
                path: '/paymentHistories',
                name: 'PaymentHistoryManager',
                component: PaymentHistoryManager
            },
            {
                path: '/paymentHistories/:id',
                name: 'PaymentHistoryDetail',
                component: PaymentHistoryDetail
            },




    ]
})
