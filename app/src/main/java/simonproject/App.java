/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package simonproject;

import simonproject.model.*;
import simonproject.controller.*;

public class App
{
    public static void main(String[] args)
    {
        SimonModel model = new SimonModel();
        GameController controller = new GameController(model);
    }
}
