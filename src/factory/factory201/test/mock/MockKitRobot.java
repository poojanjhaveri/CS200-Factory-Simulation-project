package factory.factory201.test.mock;

import factory.factory201.interfaces.KitRobot;
import factory.factory201.partsManagement.PartsAgent;
import factory.factory201.test.mock.EventLog;
import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.MockAgent;
import factory.general.Kit;
import factory.general.Part;

/**
 * @author Alex Young
 */
public class MockKitRobot extends MockAgent implements KitRobot {
    PartsAgent partsagent;
    public EventLog log = new EventLog();
    Kit kit;
    
    public MockKitRobot(String name) {
        super(name);
    }

    @Override
    public void msgHereIsEmptyKit(Kit kit) {
        log.add(new LoggedEvent("Received msgHereIsEmptyKit from the "
                + "conveyor to get empty kit: " + kit.name + "."));
        
    }

    @Override
    public void msgKitInspected(boolean result) {
        log.add(new LoggedEvent("Received msgKitInspected from the "
                + "camera that the kit is verified."));
    }

   @Override
    public void msgKitIsFull(Kit k) {
        log.add(new LoggedEvent("Received msgKitIsFull from parts agent "
                + "that the kit is full."));
        /*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {System.out.print("stopped sleeping for 10 seconds");}
        //partsagent.msgEmptyKitReady(kit);*/
    }

    @Override
    public void msgNeedEmptyKit() {
        log.add(new LoggedEvent("Received msgNeedEmptyKit from parts agent "
                + "that it needs an empty kit to put parts in."));
       /* kit = new Kit("Kit 1");
        kit.addPart(new Part(Part.Type.p1, false, 1));
        kit.addPart(new Part(Part.Type.p2, false, 1));
        
        kit.addPart(new Part(Part.Type.p3, false, 1));
        kit.addPart(new Part(Part.Type.p4, false, 1));
        kit.addPart(new Part(Part.Type.p5, false, 1));
        kit.addPart(new Part(Part.Type.p6, false, 1));
        kit.addPart(new Part(Part.Type.p7, false, 1));
        kit.addPart(new Part(Part.Type.p8, false, 1));
        partsagent.msgEmptyKitReady(kit);*/
    }
    public void setPartsAgent(PartsAgent p){
        this.partsagent=p;
    }

}
