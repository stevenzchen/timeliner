package timeliner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.*;


public class Timeline implements Serializable{
	

	private static final long serialVersionUID = 1L;
	public String name;
	public ArrayList<Event> events = new ArrayList<Event>();
	public DateTime start = null;
	public DateTime end = null;
	public boolean sorted;
	
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
		sorted = false;
	}
	
	public void sort()
	{
		Collections.sort(events);
		sorted = true;
	}
	
	public int size()
	{
		return events.size();
	}
	
	public ArrayList<Event> getEventsInYear(int year)
	{
		if(events.size() == 0)
		{
			return new ArrayList<Event>();
		}
		else
		{
			ArrayList<Event> result = new ArrayList<Event>();
			for(Event e: events)
			{
				if(e.getDate().getYear() == year)
				{
					result.add(e);
				}
			}
			return result;
		}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DateTime getStart() {
		return start;
	}
	public void setStart(DateTime start) {
		this.start = start;
	}
	public DateTime getEnd() {
		return end;
	}
	public void setEnd(DateTime end) {
		this.end = end;
	}
	public boolean isSorted() {
		return sorted;
	}
	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}
	
	
}
