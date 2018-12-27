public class HyperPlaneNotFoundException extends Exception{

    public HyperPlaneNotFoundException(){
        System.out.println("No Hyper Plane is found under this gamma! \n");
    }

    public HyperPlaneNotFoundException(String message){
        System.out.println(message);
        System.out.println("No Hyper Plane is found under this gamma! \n");
    }

}
