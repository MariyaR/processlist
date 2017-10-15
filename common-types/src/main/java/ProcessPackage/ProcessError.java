package ProcessPackage;

public class ProcessError {

    private ProcessErrors Error;

    public void setError(ProcessErrors error) {
        Error = error;
    }

    public ProcessErrors getError() {
        return Error;
    }

}

enum ProcessErrors {Err1, Err2, Err3}