package it.polimi.ingsw.ps46;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(DiceTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println("Il risultato del TestRunner: " + failure.toString());
      }
		
      System.out.println("Il risultato del TestRunner: " + result.wasSuccessful());
   }
}
