package pcd2018.threads;

public class SingleThread {

  public static void main(String[] args) {
    Thread a = new ThreadSupplier().get();

    System.out.println("Starting Single Thread");
    a.start();
    System.out.println("Done starting.");

  }

}
