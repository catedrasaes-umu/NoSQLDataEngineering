package es.um.nosql.s13e.db.gen;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class Main
{
  public static void main(String[] args)
  {
    Controller controller = new Controller();
    controller.start(ConfigConstants.GET_INPUT_FILE());
  }
}