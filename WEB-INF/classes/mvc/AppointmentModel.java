package mvc;

public class AppointmentModel
{
  int appId;
  String userSender;
  String userReceiver;
  String appdate;
  String apptime;
  boolean accepted;

  public AppointmentModel() {}

  public AppointmentModel(int appId, String userSender, String userReciever, String appdate, String apptime)
  {
    this.appId = appId;
    this.userSender = userSender;
    this.userReceiver = userReceiver;
    this.appdate = appdate;
    this.apptime = apptime;
    accepted = false;
  }

  public int getAppId()
  {
    return this.appId;
  }

  public void setAppId(int appId)
  {
    this.appId = appId;
  }

  public String getUserSender()
  {
    return this.userSender;
  }

  public void setUserSender(String userSender)
  {
    this.userSender = userSender;
  }

  public String getUserReceiver()
  {
    return this.userReceiver;
  }

  public void setUserReceiver(String userReceiver)
  {
    this.userReceiver = userReceiver;
  }

  public String getAppdate()
  {
    return this.appdate;
  }

  public void setAppdate(String appdate)
  {
    this.appdate = appdate;
  }

  public String getApptime()
  {
    return this.apptime;
  }

  public void setApptime(String apptime)
  {
    this.apptime = apptime;
  }

  public boolean isAccepted()
  {
    return this.accepted;
  }

  public void setAccepted(boolean accepted)
  {
    this.accepted = accepted;
  }
}
