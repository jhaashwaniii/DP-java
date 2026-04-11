package org.example.adapter;
//Convert one interface into another that the client expects.”
//Something doesn’t fit → Adapter makes it fit


//Target Interface
interface PaymentProcessor{
    void pay(int amount);
}


//Adaptee -Existing System
class OldPaymentGateway{
    void payment(double amount)
    {
        System.out.println(amount+" Paid using Old Gateway");
    }

}

//Adapter

class PaymentAdapter implements PaymentProcessor{
    private OldPaymentGateway oldPaymentGateway;

    public PaymentAdapter(OldPaymentGateway oldPaymentGateway)
    {
        this.oldPaymentGateway=oldPaymentGateway;
    }

    public void pay(int amount)
    {
        oldPaymentGateway.payment((double) amount);
    }

}

public class Payments {
    public static void main(String [] args)
    {
        System.out.println("Welcome to adapter Pattern- we will convert here old system to new system");
        PaymentAdapter paymentAdapter=new PaymentAdapter(new OldPaymentGateway());
        paymentAdapter.pay(100);
    }

}
