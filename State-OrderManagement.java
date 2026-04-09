package org.example.DP.State;
//Order-states
//Placed
//Confirmed
//Shipped
//Delivered
//Cancelled
interface OrderState{
    void next(Order o);
    void cancel(Order o);
    String orderStatus(Order o);

}
class Placed implements OrderState{
    public void next(Order o)
    {
        System.out.println("Order Placed");
        o.setState(new Confirmed());
    }
    public void cancel(Order o)
    {
        System.out.println("Order Cancelled after being Placed");
        o.setState(new Cancelled());
    }
    public String orderStatus(Order o)
    {
       return "PLACED";
    }
}
class Confirmed implements OrderState{
    public void next(Order o)
    {
        System.out.println("Order Placed");
        o.setState(new Shipped());
    }
    public void cancel(Order o)
    {
        System.out.println("Order Cancelled after being Confirmed");
        o.setState(new Cancelled());
    }
    public String orderStatus(Order o)
    {
        return "Confirmed";
    }
}
class Shipped implements OrderState{
    public void next(Order o)
    {
        System.out.println("Order Placed");
        o.setState(new Delivered());
    }
    public void cancel(Order o)
    {
        System.out.println("Order Cancelled after being Shipped");
        o.setState(new Cancelled());
    }
    public String orderStatus(Order o)
    {
        return "Shipped";
    }
}
class Delivered implements OrderState{
    public void next(Order o)
    {
        System.out.println("Order Placed");
        o.setState(new Confirmed());
    }
    public void cancel(Order o)
    {
        System.out.println("Order can not be cancelled after Delivered");
        o.setState(new Cancelled());
    }
    public String orderStatus(Order o)
    {
      return "Delivered";
    }
}

class Cancelled implements OrderState{
    public void next(Order o)
    {
        System.out.println("Order Placed");
        o.setState(new Confirmed());
    }
    public void cancel(Order o)
    {
        System.out.println("Order already cancelled");
        o.setState(new Cancelled());
    }
    public String orderStatus(Order o)
    {
       return "Cancelled";
    }
}


class Order{
    private OrderState state;
    public Order()
    {
        state=new Placed();
    }

    public void setState(OrderState state)
    {
        this.state=state;
    }
    public void next()
    {
        state.next(this);
    }
    public void cancel()
    {
        state.cancel(this);
    }
    public String orderStatus()
    {
        return state.orderStatus(this);
    }

    public void printStatus()
    {
        System.out.println("current status is"+state.orderStatus(this));
    }


}
public class OrderManagement {
    public static void main(String[] args)
    {
        System.out.println("Welcome to Order Management System");
        Order order=new Order();
        System.out.println("Order is in state- "+ order.orderStatus());
        order.next();
        System.out.println("Order is in state- "+ order.orderStatus());
        order.cancel();
        System.out.println("Order is in state- "+ order.orderStatus());
        order.cancel();
    }

}
