package ProcessPackage;

public class GaN_Buffer extends GeneralLayer implements Functional {

    public void initFunction() {
        this.setFunction(Functions.Buffer);
    }

     GaN_Buffer () {
        this.initFunction();
    }

}
