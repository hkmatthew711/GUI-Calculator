package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
  /**
  * construct operand from string token.
  */
  private int number; 
  public Operand( String token )
  {
    this.number = Integer.parseInt(token); 
  }
  /**
   * construct operand from integer
   */
  public Operand( int value )
  {
    this.number = value; 
  }
  /**
   * return value of operand
   */
  public int getValue()
  {
      return number;
  }
  /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token )
  {
    try
    {
      Integer.parseInt(token);
    }
    catch(NumberFormatException ex) 
    {
      return false;
    }

    return true;
  }
}
