package it.polimi.ingsw.ps46;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      
	   Result result = JUnitCore.runClasses(PersonalBoardTest.class);
		
      for (Failure failure : result.getFailures()) {
          System.out.println("\n====================ATTENTION==========================");
         System.out.println("\nEND OF THE TEST WITH NEGATIVE RESULT:\n" + failure.toString());
      }
      System.out.println("\n=======================================================");
      System.out.println("RESULT OF THE TEST");
      System.out.println("=======================================================");


      if (result.wasSuccessful())
      System.out.println("\nEND OF THE TEST WITH RESULT: 'SUCCESSFUL'");
      else
          System.out.println("\nTEST ABORTED FOR SOME: 'FAILURE'");

   }
}