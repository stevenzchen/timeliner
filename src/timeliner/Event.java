package timeliner;

import java.io.Serializable;
import org.joda.time.DateTime;


public class Event implements Serializable, Comparable<Event>{
	

	private static final long serialVersionUID = 1L;


	String name;
	String description;
	DateTime date;
	
	/**
	 * Creates an event with given parameters.
	 * @param n 	The name of the event.
	 * @param desc 	The description for the event.
	 * @param d 	The date on which the event occurred.
	 */
	public Event(String n, String desc, DateTime d)
	{
		name = n;
		description = desc;
		date = d;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public String toString()
	{
		return name;
	}
	
	
	@Override
	public int compareTo(Event o) {
		return this.getDate().compareTo(o.getDate());
	}
}
