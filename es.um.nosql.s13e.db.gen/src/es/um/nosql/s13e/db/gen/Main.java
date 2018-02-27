package es.um.nosql.s13e.db.gen;

import es.um.nosql.s13e.db.gen.utils.Constants;

public class Main
{
  public static void main(String[] args)
  {
    Controller controller = new Controller();
    controller.start(Constants.GET_INPUT_FILE());
  }
}