/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.general;

/**
 *
 * @author polarpatbear
 */
public class Result {

    public enum Is {

        badParts, piledParts, robotInTheWay, unstableParts, partsMissing, verified
    };
    public Is is;
    
    public Result(Is is) {
        this.is = is;
    }
}
