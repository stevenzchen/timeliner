package timeliner;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.*;


public class Timeline implements Serializable{
	

	private static final long serialVersionUID = 1L;
	String name;
	ArrayList<Event> events = new ArrayList<Event>();
	DateTime begin;
	DateTime end;
	boolean sorted;
}
