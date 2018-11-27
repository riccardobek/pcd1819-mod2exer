package pcd2018.safe;

/**
 * The technical interface of a printer.
 */
interface Printer {

  /**
   * Print a document
   * 
   * @param document
   */
  public void printJob(Object document);
}