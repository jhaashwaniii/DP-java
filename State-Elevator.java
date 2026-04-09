package org.example.DP.State;

import java.util.LinkedList;
import java.util.Queue;

//State-Idle, Movingup, Movingdown, dooropen
interface ElevatorState{
    void handleRequests(Elevator e,int floor);
    void openDoor(Elevator e);
    void closeDoor(Elevator e);
}
class IdleState implements ElevatorState{
    public void handleRequests(Elevator e, int floor)
    {
      if(e.getCurrentFloor()>floor)
      {
          e.setState(new MovingDown());
      }
      else if(e.getCurrentFloor()<floor)
      {
          e.setState(new MovingUp());
      }
      e.getState().handleRequests(e, floor);

    }
    public void openDoor(Elevator e)
    {
        System.out.println("Opening the Door");
        e.setState(new DoorOpen());
    }
    public void closeDoor(Elevator e)
    {
        System.out.println("Closing the Door");
    }
}
class MovingUp implements ElevatorState{
    public void handleRequests(Elevator e, int floor)
    {
      while (!e.getRequests().isEmpty())
      {
          int target=e.getRequests().poll();
          while(target>e.getCurrentFloor())
          {
              e.setCurrentFloor(e.getCurrentFloor()+1);
              System.out.println("Moving Up");
          }
          e.openDoor();
          e.closeDoor();
      }
      e.setState(new IdleState());
    }
    public void openDoor(Elevator e)
    {
        System.out.println("Can't open while moving Up");
    }
    public void closeDoor(Elevator e)
    {
        System.out.println("moving Up- Doors already closed");
    }
}
class MovingDown implements ElevatorState{
    public void handleRequests(Elevator e, int floor)
    {
        while (!e.getRequests().isEmpty())
        {
            int target=e.getRequests().poll();
            while(target<e.getCurrentFloor())
            {
                e.setCurrentFloor(e.getCurrentFloor()-1);
                System.out.println("Moving Down");
            }
            e.openDoor();
            e.closeDoor();
        }
        e.setState(new IdleState());
    }
    public void openDoor(Elevator e)
    {
        System.out.println("Can't open while moving Down");
    }
    public void closeDoor(Elevator e)
    {
        System.out.println("moving Down- Doors already closed");
    }
}

class DoorOpen implements ElevatorState{
    public void handleRequests(Elevator e, int floor)
    {
        System.out.println("Queuing your request");
        e.getRequests().offer(floor);

    }
    public void openDoor(Elevator e)
    {
        System.out.println("Doors already opened");
    }
    public void closeDoor(Elevator e)
    {
        System.out.println("Closing Doors");
        e.setState(new IdleState());
    }
}
class Elevator {
    private ElevatorState state;
    private int currentFloor;
    private Queue<Integer> requests=new LinkedList<>();

    public Elevator()
    {
        state=new IdleState();
        currentFloor=0;
    }

    public void setState(ElevatorState es)
    {
        this.state=es;
    }

    public ElevatorState getState()
    {
        return state;
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor)
    {
        this.currentFloor=currentFloor;
    }
    public Queue<Integer> getRequests()
    {
        return requests;
    }
    public void addRequest(int floor)
    {
        requests.offer(floor);
        state.handleRequests(this,floor);
    }
    public void openDoor()
    {
        state.openDoor(this);
    }
    public void closeDoor()
    {
        state.closeDoor(this);
    }
}
public class ElevatorSystem{
    public static void main(String[] args)
    {
        Elevator elevator=new Elevator();
        System.out.println(elevator.getCurrentFloor());
        elevator.addRequest(3);
        System.out.println(elevator.getCurrentFloor());


    }
}

