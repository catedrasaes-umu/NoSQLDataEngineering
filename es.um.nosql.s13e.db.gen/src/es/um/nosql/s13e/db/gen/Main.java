package es.um.nosql.s13e.db.gen;

import es.um.nosql.s13e.db.gen.controllers.ControllerFactory;
import es.um.nosql.s13e.db.gen.controllers.Controller_Type;
import es.um.nosql.s13e.db.gen.controllers.IController;
import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class Main
{
  public static void main(String[] args)
  {
    IController controller = ControllerFactory.GET_CONTROLLER(Controller_Type.PARALLEL_CONTROLLER);
    controller.start(ConfigConstants.GET_INPUT_FILE());
  }
}