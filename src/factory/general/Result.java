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

    public enum NestError {

        badParts, piledParts, robotInTheWay, unstableParts, partsMissing, verified
    };
    public NestError nestError;
    
    public Result(NestError nestError) {
        this.nestError = nestError;
    }
}
