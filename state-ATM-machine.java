
interface ATMState{
    void insertcard(ATM atm);
    void ejectcard(ATM atm);
    void withdrawcash(ATM atm);
}

class NoCardState implements ATMState{
    public void insertcard(ATM atm)
    {
        System.out.println("Card Inserted");
        atm.setstate(new HasCardState());
    }
    
    public void ejectcard(ATM atm)
    {
        System.out.println("Card Ejected");
    }
    
    public void withdrawcash(ATM atm)
    {
        System.out.println("Please insert the card first");
    }
    
}

class HasCardState implements ATMState{
    
    public void insertcard(ATM atm)
    {
        System.out.println("Card already inserted");
    }
    
    public void ejectcard(ATM atm)
    {
        System.out.println("Card Ejected");
        atm.setstate(new NoCardState());
    }
    public void withdrawcash(ATM atm)
    {
        System.out.println("Cash Withdrwal");
        atm.setstate(new NoCardState());
    }
    
}
class ATM{
    private ATMState state;
    public ATM()
    {
        state=new NoCardState();
    }
    
    public void setstate(ATMState state)
    {
        this.state=state;
    }
    
    public void insertcard()
    {
        state.insertcard(this);
    }
    public void ejectcard()
    {
        state.ejectcard(this);
    }
    public void withdrawcash()
    {
       state.withdrawcash(this);
    }
    
}
class Main {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        ATM atm=new ATM();
        atm.withdrawcash();
    }
}


// No Card → Insert Card
// Has Card → Enter PIN
// Authenticated → Withdraw
