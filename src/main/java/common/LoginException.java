package common;

import javax.xml.ws.WebFault;

/**
 * Created by echo on 17/6/10.
 */
@WebFault(faultBean = "common.FaultBean")
public class LoginException extends Exception{
    private static final long serialVersionUID = 1L;

    private FaultBean faultBean;

    public LoginException() {
        super();
    }

    public LoginException(String message, FaultBean faultBean,
                                 Throwable cause) {
        super(message, cause);
        this.faultBean = faultBean;
    }

    public LoginException(String message, FaultBean faultBean) {
        super(message);
        this.faultBean = faultBean;
    }

    public FaultBean getFaultInfo() {
        return faultBean;
    }
}
