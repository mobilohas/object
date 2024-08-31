package chapter11.bell.notnecessaryinterface;

import java.util.Hashtable;

public class Properties {

  private Hashtable<String, String> properties = new Hashtable<>();

  public void setProperties(final Hashtable<String, String> properties) {
    this.properties = properties;
  }

  public Hashtable<String, String> getProperties() {
    return properties;
  }
}
