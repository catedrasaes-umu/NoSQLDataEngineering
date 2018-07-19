package es.um.nosql.s13e.db.gen.controllers;

public class ControllerFactory
{
  public static IController GET_CONTROLLER(Controller_Type type)
  {
    switch (type)
    {
      case PARALLEL_CONTROLLER: return new ParallelController();
      case SERIAL_CONTROLLER:   return new SerialController();
      default: throw new IllegalArgumentException(type + " is not a valid controller");
    }
  }
}
