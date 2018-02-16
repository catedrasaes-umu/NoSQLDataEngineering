package es.um.nosql.s13e.db.gen.config.pojo;

public class DbGenOptions
{
  private ConfigureOptions configure;

  public void setConfigure(ConfigureOptions configure) {this.configure = configure;}

  public ConfigureOptions getConfigure() {return configure;}

  private InputOptions input;

  public void setInput(InputOptions input) {this.input = input;}

  public InputOptions getInput() {return this.input;}

  private OutputOptions output;

  public void setOutput(OutputOptions output) {this.output = output;}

  public OutputOptions getOutput() {return this.output;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getConfigure() != null)
      result.append("-Configure:\n" + this.getConfigure().toString());

    if (this.getInput() != null)
      result.append("-Input:\n" + this.getInput().toString());

    if (this.getOutput() != null)
      result.append("-Output:\n" + this.getOutput().toString());

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getConfigure() != null)
      this.getConfigure().doCheck();

    if (this.getInput() == null)
      throw new IllegalArgumentException("An \"input\" section is required to provide a valid input XMI file.");

    this.getInput().doCheck();

    if (this.getOutput() != null)
      this.getOutput().doCheck();

    return true;
  }
}