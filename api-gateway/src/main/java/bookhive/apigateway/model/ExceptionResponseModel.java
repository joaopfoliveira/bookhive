package bookhive.apigateway.model;

import java.util.Date;

public class ExceptionResponseModel {
    String errCode;
    String err;
    String errDetails;
    Date date;

    public ExceptionResponseModel(String errCode, String err, String errDetails, Date date) {
        this.errCode = errCode;
        this.err = err;
        this.errDetails = errDetails;
        this.date = date;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getErrDetails() {
        return errDetails;
    }

    public void setErrDetails(String errDetails) {
        this.errDetails = errDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
