package com.lip.trading.matchbox;


import com.lip.trading.ApplicationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lip on 2016-12-19 11:44
 * 买单
 */
public class Ask {
    private OrderNode orderNode;

    public Ask() {

    }
    /**
     * 得到买1订单
     * @return
     */
    public Order get()
    {
        return  orderNode.order;
    }
    /**
     * 添加一个订单
     * 按照价格优先，时间其次的顺序插入到队列中
     *
     * @param order
     */
    public synchronized void in(Order order) {
        if (order == null)
            throw new NullPointerException("order is null");
        if(order.direction!=Direction.BUY)
            throw new ApplicationException("trading direction  error");
        if (orderNode == null) {
            orderNode = new OrderNode(order);
            return;
        }
        OrderNode temp = orderNode;
        while (order.price <= temp.order.price && order.time.getTime() >= temp.order.time.getTime()
                && temp.next != null) {
            temp = temp.next;
        }
        temp.next = new OrderNode(order);
    }

    /**
     * some one want to sell  and bid a price under ask 1
     *  新的成交订单
     * @param price 成交价格
     * @param num 成交数量
     */
    public synchronized List<Order> out(double price, int num)
    {
        List<Order>list=new ArrayList<>();
        //no ask
        if(orderNode==null||num<=0)
            return list;
        OrderNode temp=orderNode;
        int n=num;
        while (n>0&&temp!=null)
        {
            if(temp.order.price>=price) {
                if (temp.order.left >= n) {
                    temp.order.left -= n;
                    list.add(temp.order);
                    return list;
                } else {
                    n -= temp.order.left;
                    temp.order.left = 0;
                    list.add(temp.order);
                    temp = temp.next;
                    /************************/
                    OrderNode temp2 = orderNode.next;
                    orderNode.next = null;
                    orderNode = temp2;
                    /************************/
                }
            }else {
                break;
            }
        }
        return list;
    }
}
