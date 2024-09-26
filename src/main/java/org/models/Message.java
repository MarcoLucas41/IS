package org.models;

import java.io.File;
import java.rmi.Remote;

public interface Message extends Remote {
     Content remote_message() throws java.rmi.RemoteException;
}
