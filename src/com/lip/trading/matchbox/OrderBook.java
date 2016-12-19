package com.lip.trading.matchbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lip on 2016-12-19 11:44
 */
public class OrderBook {
    private Ask ask;
    private Bid bid;
    private double price;//最新价格
    private List<Order>orderHistory;
    public OrderBook()
    {
        ask=new Ask();
        bid=new Bid();
        orderHistory=new ArrayList<>();
    }

    /**
     * 处理新来的订单
     * @param order
     */
    public synchronized void process(Order order)
    {
        if(order.direction==Direction.BUY)
        {
            List<Order> list=bid.out(order.price,order.num);
            if(list==null||list.size()==0)//买单 价格小于卖1,只能放在ask中
            {
                ask.in(order);
            }else{//成交
                orderHistory.addAll(list);
                if(order.num>getTradeNum(list))//部分成交
                {
                    ask.in(order);
                }
            }
        }else if(order.direction==Direction.SELL)
        {
            List<Order> list=ask.out(order.price,order.num);
            if(list==null||list.size()==0)//卖单 价格大于卖1,只能放在bid中
            {
                bid.in(order);
            }else{//成交
                orderHistory.addAll(list);
                if(order.num>getTradeNum(list))//部分成交
                {
                    bid.in(order);
                }
            }
        }
    }

    /**
     * 成交的数量
     * @param list
     * @return
     */
    private int getTradeNum(List<Order>list)
    {
        int num=0;
        for(Order order:list)
        {
            num+=order.num-order.left;
        }
        return num;
    }
}
