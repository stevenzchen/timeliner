package timeliner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.*;


public class Timeline implements Serializable{
	

	private static final long serialVersionUID = 1L;
	String name;
	ArrayList<Event> events = new ArrayList<Event>();
	DateTime begin;
	DateTime end;
	boolean sorted;
	
	public Timeline(String n)
	{
		name = n;
	}
	public Timeline(String n, ArrayList<Event> e)
	{
		name = n;
		events = e;
	}
	
	public void addEvent(Event e)
	{
		events.add(e);
	}
	
	public void sort()
	{
		Collections.sort(events);
	}
	
	public String toString()
	{
		String s = "[";
		for(Event e: events)
		{
			s = s + e.getName() + ", ";
		}
		s = s.substring(0, s.length()-2);
		s = s + "]";
		return s;
	}
	
	
}
