/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto.login.exception;

/**
 *
 * @author Administrator
 */
public class ParseCRSFTokenError extends Exception {

    public ParseCRSFTokenError(String content) {
        super(content);
    }
}
