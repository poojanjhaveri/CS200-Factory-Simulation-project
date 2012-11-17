/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.interfaces;

import factory.general.Kit;
import factory.general.Part;

/**
 *
 * @author James Dalton
 */
public interface PartsInterface extends FactoryBase{

    public void msgHereIsPart(Part part);
    
    public void msgEmptyKitReady(Kit kit);
    public void msgHereIsKit(Kit kit);
    
}
